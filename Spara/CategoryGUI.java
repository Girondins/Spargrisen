package Spara;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;



public class CategoryGUI extends JFrame implements ActionListener {
	
	private float totalSum;
	private JPanel mainPanel = new JPanel();
	private LinkedList<JButton> buttons = new LinkedList<JButton>();
	private LinkedList<Category>categories = new LinkedList<Category>();
	private LinkedList<JButton>sliderList = new LinkedList<JButton>();
	private JFrame frame = new JFrame("BUDGET APPLIKATION");
	private JButton createCategory =  new JButton("Create a new Category");
	private static CategoryList catList = new CategoryList();

	public CategoryGUI() {
		
		
//		createCategory(catList);
		
		
	}
	public void createCategory(CategoryList cat) {
		int sum;
		totalSum = 0;
		for(int i = 0; i <cat.size(); i++){
				Category cate;
				cate = cat.getCategoryIndex(i);
				categories.add(cate);
				
				sum = Math.round(cate.getCurrentSum());
				this.totalSum += cate.getCurrentSum();
//				JPanel CategorySlider = new JPanel(new GridLayout(1,2));
				
				JLabel lblName = new JLabel(cate.getCategoryName());
				JSlider slider = new JSlider(JSlider.HORIZONTAL,0,cate.getBudgetLimit(),sum);
				JButton button = new JButton(cat.getCategoryIndex(i).getCategoryName());
				slider.setMajorTickSpacing(cate.getBudgetLimit());
				slider.setPaintLabels(true);
				slider.setPaintTicks(true);
				slider.setBackground(Color.RED);
				slider.setPreferredSize(new Dimension(200,20));
				slider.setValue(sum);
				button.add(slider);
//				CategorySlider.add(lblName);
//				CategorySlider.add(slider);
				
//				this.panelList.add(CategorySlider);
				sliderList.add(button);
//				lblList.add(lblName);
				
		}
//		createGUI(this.panelList, totalSum);
		createGUI(sliderList,totalSum);
		
	}
	
	private void createGUI(LinkedList<JButton> sliderList,float totalSum) {
		frame.dispose();
		frame = new JFrame("BUDGET APPLIKATION");
		String totSumString = Float.toString(totalSum);
		
		mainPanel = new JPanel(new GridLayout(sliderList.size()+1,1));
		JLabel totalPanel = new JLabel("Current Sum:                              " + totSumString);
//		totalPanel.setName("Current Sum: " + totSumString);
		mainPanel.add(totalPanel);
		
		
		while(!sliderList.isEmpty()) {
			JButton but = new JButton();
			but = sliderList.pop();
			but.addActionListener(this);
			mainPanel.add(but);
			buttons.add(but);
		}
		createCategory.addActionListener(this);
		mainPanel.add(createCategory);
		frame.add(mainPanel);
		startFrame();
		
	}

	public void startFrame() {

		frame.setPreferredSize(new Dimension(300,416)); // galaxy S4 screen size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i<buttons.size(); i++){
			if(e.getSource() == buttons.get(i)){
				new CategoryViewer(categories.get(i));
			}
		}
		
		if(e.getSource() == createCategory){
			new CreateCategory(this);
			frame.dispose();
		}
		


	}
	
	public void addCategory(Category category){
		catList.addCategory(category);
	}
	
	public void startCg(){
		CategoryGUI cg = new CategoryGUI();
		cg.createCategory(catList);
	}


	public static void main(String[] args) {
		CategoryGUI cg = new CategoryGUI();
		catList.getCategoryIndex(1).addPurchase("User: Anna ;Korv;Cost: 100;Lidl;söndag");
		cg.createCategory(catList);
		catList.getCategoryIndex(2).addPurchase("User: Anna ;Korv;Cost: 1000;Lidl;söndag");
		cg.createCategory(catList);
	}

}






























// frame.setExtendedState(JFrame.MAXIMIZED_BOTH); / olika mobiler olika sk‰rmar
// gˆr framen lika stor som displayen
// frame.setResizable(false);













