package Spargrisen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;



public class CategoryGUI extends JFrame {
	
	private float totalSum;
	private LinkedList<JPanel> panelList = new LinkedList<JPanel>();
	private JPanel mainPanel;
	private LinkedList<JLabel>lblList = new LinkedList<JLabel>();
	private LinkedList<JSlider>sliderList = new LinkedList<JSlider>();


	public CategoryGUI(CategoryList catList) {

		createCategory(catList);
		
		
	}
	public void createCategory(CategoryList cat) {
		int sum=0;
		for(int i = 0; i <cat.size(); i++){
				Category cate;
				cate = cat.getCategory();
				
				sum = Math.round(cate.getCurrentSum());
				this.totalSum += cate.getCurrentSum();
				JPanel CategorySlider = new JPanel(new GridLayout(1,2));
				
				JLabel lblName = new JLabel(cate.getCategoryName());
				JSlider slider = new JSlider(JSlider.HORIZONTAL,0,cate.getBudgetLimit(),sum);
				slider.setMajorTickSpacing(cate.getBudgetLimit());
				slider.setPaintLabels(true);
				slider.setPaintTicks(true);
				slider.setBackground(Color.RED);
				slider.setPreferredSize(new Dimension(200,20));
				
//				CategorySlider.add(lblName);
//				CategorySlider.add(slider);
				
//				this.panelList.add(CategorySlider);
				sliderList.add(slider);
				lblList.add(lblName);
				
		}
//		createGUI(this.panelList, totalSum);
		createGUI(sliderList,lblList,totalSum);
		
	}
	
	private void createGUI(LinkedList<JSlider> sliderList, LinkedList<JLabel> lblList, float totalSum) {
		String totSumString = Float.toString(totalSum);
		
		JPanel mainPanel = new JPanel(new GridLayout(sliderList.size()+lblList.size()+1,1));
		JLabel totalPanel = new JLabel("Current Sum:                              " + totSumString);
//		totalPanel.setName("Current Sum: " + totSumString);
		mainPanel.add(totalPanel);
		
		
		while(!lblList.isEmpty()) {
			
			
			mainPanel.add(lblList.pop());
			mainPanel.add(sliderList.pop());
		}
		
		startFrame(mainPanel);
		
	}

	public void startFrame(JPanel mainPanel) {
		JFrame frame = new JFrame("BUDGET APPLIKATION");
		frame.add(mainPanel);
		frame.setLocationRelativeTo(null);
		frame.setPreferredSize(new Dimension(300,416)); // galaxy S4 screen size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}

	public static void main(String[] args) {
		Category cat = new Category("Mat", 2000, new LinkedList());
		cat.addPurchase("Korv,Cost: 100,Lidl,söndag");
		new CategoryGUI(new CategoryList());
	}

}






























// frame.setExtendedState(JFrame.MAXIMIZED_BOTH); / olika mobiler olika sk‰rmar
// gˆr framen lika stor som displayen
// frame.setResizable(false);













