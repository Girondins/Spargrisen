package Spargrisen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;



public class CategoryGUI extends JFrame {
	
	private float totalSum;
	private JPanel mainPanel = new JPanel();
	private LinkedList<JLabel>lblList = new LinkedList<JLabel>();
	private JFrame frame = new JFrame("BUDGET APPLIKATION");
	private JLabel contePane = new JLabel();
	private LinkedList<JProgressBar> barList = new LinkedList<JProgressBar>();
	private JProgressBar bar = new JProgressBar();
	private Image backgroundImage;

	
	
	
	public CategoryGUI() throws IOException {
		backgroundImage = ImageIO.read(new File("C:/Users/Alex/Desktop/BilderSpargrisen/header.jpg"));
		ImageIcon icon = new ImageIcon(backgroundImage);
		contePane.setIcon(icon);
//		frame.setContentPane(contePane);
		contePane.setLayout(null);
		
	}
	
	public void createCategory(CategoryList cat) throws IOException {
		int sum;
		totalSum = 0;
		for(int i = 0; i <cat.size(); i++){
				Category cate;
				cate = cat.getCategoryIndex(i);
				
				sum = Math.round(cate.getCurrentSum());
				this.totalSum += cate.getCurrentSum();	
				int budget = cate.getBudgetLimit();
				String current;
				current = "" + sum;
				
				JLabel lbl = new JLabel(current);
				JLabel lblName = new JLabel(cate.getCategoryName());

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
//				bar.setForeground(Color.green);
//				bar.setBackground(Color.BLACK);
				bar.setString(current);
				bar.add(lbl);
				bar.setPreferredSize(new Dimension(200,20));
				bar.setValue(sum);
				barList.add(this.bar);

				lblList.add(lblName);
				
		}

		createGUI(barList,lblList,totalSum);
		
	}
	

	private void createGUI(LinkedList<JProgressBar> barList, LinkedList<JLabel> lblList, float totalSum) throws IOException {
		frame.dispose();
		frame = new JFrame("BUDGET APPLIKATION");
		
		
		String totSumString = Float.toString(totalSum);
		JPanel wholePanel = new JPanel(new BorderLayout());
		mainPanel = new JPanel(new GridLayout(barList.size()+lblList.size()+3,1));
		Font font = new Font("serif", Font.PLAIN, 20);
		
		JLabel totalPanel = new JLabel("Current Sum:                              " + totSumString);
		totalPanel.setFont(font);
		totalPanel.setForeground(Color.WHITE);
		
		mainPanel.setBackground(new Color(49,69,159));
		mainPanel.add(totalPanel);
		
		
		while(!lblList.isEmpty()) {
			Random rand = new Random();
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			Color randomColor = new Color(r, g, b);
			
			
			JLabel lbl;
			lbl = lblList.pop();
			lbl.setFont(font);
			lbl.setForeground(Color.WHITE);
			mainPanel.add(lbl);
			
			JProgressBar proBar = null;
			proBar = barList.pop();
			
			mainPanel.add(proBar);
			

			
			
			
			
		}
		
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

	public static void main(String[] args) throws IOException {
		CategoryGUI cg = new CategoryGUI();
		CategoryList catList = new CategoryList();
		catList.getCategoryIndex(1).addPurchase("Korv;Cost: 100;Lidl;söndag");
		cg.createCategory(catList);
		catList.getCategoryIndex(2).addPurchase("Korv;Cost: 1000;Lidl;söndag");
		cg.createCategory(catList);
	}

}











