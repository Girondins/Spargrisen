package SpargrisenGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import SpargrisenObjekt.Category;
import SpargrisenObjekt.CategoryList;




public class CategoryGUI extends JFrame implements ActionListener {
	
	private float totalSum;
	private JPanel mainPanel = new JPanel();
	private LinkedList<Category> category = new LinkedList<Category>();
//	private LinkedList<JLabel>lblList = new LinkedList<JLabel>();
	private LinkedList<JButton> buttonsList = new LinkedList<JButton>();
	private LinkedList<JButton> actionPreformedButton = new LinkedList<JButton>();
	private JButton createCategory = new JButton("Create a new Category");
//	private LinkedList<JProgressBar> barList = new LinkedList<JProgressBar>();
	private JFrame frame = new JFrame("BUDGET APPLIKATION");
	private JLabel contePane = new JLabel();
	private JProgressBar bar = new JProgressBar();
	private Image backgroundImage;
	private JButton homeButton = new JButton("Home");
	private JPanel bottomPanel = new JPanel();
	private GUIController GUIc;
	private Font headFont = new Font("Cooper Black", Font.PLAIN, 20);
	private Font font = new Font("Cooper Black", Font.PLAIN, 12);
	
	
	
	public CategoryGUI(CategoryList cat, GUIController GUIc) throws IOException {
//		backgroundImage = ImageIO.read(new File("/Users/alexander/Downloads/BilderSpargrisen/header.jpg"));
//		ImageIcon icon = new ImageIcon(backgroundImage);
//		contePane.setIcon(icon);
//		frame.setContentPane(contePane);
//		contePane.setLayout(null);
		this.GUIc = GUIc;
		int sum;
		totalSum = 0;
		for(int i = 0; i <cat.size(); i++){
				Category cate;
				cate = cat.getCategoryIndex(i);
				
				category.add(cate);
				
				
				sum = Math.round(cate.getCurrentSum());
				this.totalSum += cate.getCurrentSum();	
				int budget = cate.getBudgetLimit();
				String current;
				current = "" + sum;
				
				JLabel lbl = new JLabel(current);
				JLabel lblName = new JLabel(cate.getCategoryName());
				JButton button = new JButton();
				
				this.bar = new JProgressBar(JProgressBar.HORIZONTAL,budget);

				
				bar.setMinimum(0);
				bar.setMaximum(budget);
				bar.setStringPainted(true);
				
				if (budget<sum) {
					bar.setForeground(Color.RED);
				}else if (sum>( budget*0.75) ) {
					bar.setForeground(Color.ORANGE);
					}else {
				
				bar.setForeground(Color.green);
				}
				
				bar.setString(current);
				bar.add(lbl);
				bar.setPreferredSize(new Dimension(200,20));
				bar.setValue(sum);
				lblName.setFont(font);
				button.add(bar);
				button.add(lblName);
//				button.setBorder(BorderFactory.createLineBorder(new Color(49,60,140), 1, true));
				button.setPreferredSize(new Dimension(300,35));
//				button.setBackground(new Color(49,69,146));
				button.setOpaque(true);
				buttonsList.add(button);

			
				
		}

		createGUI(buttonsList,totalSum);
		
	}
	
	

	private void createGUI(LinkedList<JButton> buttons, float totalSum) throws IOException {
		frame.dispose();
		frame = new JFrame("BUDGET APPLIKATION");
		
		
		String totSumString = Float.toString(totalSum);
		JPanel wholePanel = new JPanel(new BorderLayout());
		mainPanel = new JPanel(new GridLayout(buttons.size()+3,1));
//		Font font = new Font("serif", Font.PLAIN, 20);
		
		JLabel totalPanel = new JLabel("Current Sum:                              " + totSumString);
		totalPanel.setFont(headFont);
//		totalPanel.setForeground(Color.BLACK);
		
//		mainPanel.setBackground(new Color(49,69,159));
		mainPanel.add(totalPanel);
		
		
		while(!buttons.isEmpty()) {
			Random rand = new Random();
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			Color randomColor = new Color(r, g, b);
			
			JButton button = new JButton();
			button = buttonsList.pop();
			button.addActionListener(this);
			mainPanel.add(button);
			actionPreformedButton.add(button);

		}
		createCategory.addActionListener(this);
		homeButton.addActionListener(this);
		createCategory.setFont(font);
		homeButton.setFont(font);
		bottomPanel.add(homeButton);
		mainPanel.add(createCategory);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		wholePanel.add(contePane, BorderLayout.PAGE_START);
		wholePanel.add(mainPanel, BorderLayout.CENTER);
		frame.add(wholePanel);
		startFrame();
		
	}

	public void startFrame() {

		frame.setPreferredSize(new Dimension(380,560));
//		frame.setPreferredSize(new Dimension(300,416)); // galaxy S4 screen size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == createCategory){
			new CreateCategory(GUIc);
			frame.dispose();
		}
		
		if(e.getSource() == homeButton){
			frame.dispose();
			GUIc.homePage();
		}
		
		
			for(int i = 0; i<actionPreformedButton.size(); i++) {
				if(e.getSource() == actionPreformedButton.get(i)) {
				new CategoryViewer(category.get(i),GUIc);
				frame.dispose();
			}
				
		}
		
	}
	

	
//	public static void main(String[] args) throws IOException {
//		CategoryGUI cg = new CategoryGUI();
//		catList.getCategoryIndex(1).addPurchase("User: Anna ;Korv;Cost: 100;Lidl;söndag");
//		cg.createCategory(catList);
//		catList.getCategoryIndex(2).addPurchase("User: Anna ;Korv;Cost: 1000;Lidl;söndag");
//		cg.createCategory(catList);
//	}

}











