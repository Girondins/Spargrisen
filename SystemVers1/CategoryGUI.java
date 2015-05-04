package SystemVers1;

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
	private JPanel mainPanel = new JPanel();
	private LinkedList<JLabel>lblList = new LinkedList<JLabel>();
	private LinkedList<JSlider>sliderList = new LinkedList<JSlider>();
	private JFrame frame = new JFrame("BUDGET APPLIKATION");
	private static CategoryGUI cg = new CategoryGUI();

	public CategoryGUI() {
		
		
//		createCategory(catList);
		
		
	}
	public void createCategory(CategoryList cat) {
		int sum;
		totalSum = 0;
		for(int i = 0; i <cat.size(); i++){
				Category cate;
				cate = cat.getCategoryIndex(i);
				
				sum = Math.round(cate.getCurrentSum());
				this.totalSum += cate.getCurrentSum();
//				JPanel CategorySlider = new JPanel(new GridLayout(1,2));
				
				JLabel lblName = new JLabel(cate.getCategoryName());
				JSlider slider = new JSlider(JSlider.HORIZONTAL,0,cate.getBudgetLimit(),sum);
				slider.setMajorTickSpacing(cate.getBudgetLimit());
				slider.setPaintLabels(true);
				slider.setPaintTicks(true);
				slider.setBackground(Color.RED);
				slider.setPreferredSize(new Dimension(200,20));
				slider.setValue(sum);
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
		frame.dispose();
		frame = new JFrame("BUDGET APPLIKATION");
		String totSumString = Float.toString(totalSum);
		
		mainPanel = new JPanel(new GridLayout(sliderList.size()+lblList.size()+1,1));
		JLabel totalPanel = new JLabel("Current Sum:                              " + totSumString);
//		totalPanel.setName("Current Sum: " + totSumString);
		mainPanel.add(totalPanel);
		
		
		while(!lblList.isEmpty()) {
			
			
			mainPanel.add(lblList.pop());
			mainPanel.add(sliderList.pop());
		}
		frame.add(mainPanel);
		startFrame();
		
	}

	public void startFrame() {

		frame.setPreferredSize(new Dimension(300,416)); // galaxy S4 screen size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}

	public static void main(String[] args) {
		CategoryGUI cg = new CategoryGUI();
		CategoryList catList = new CategoryList();
		catList.getCategoryIndex(1).addPurchase("Korv;Cost: 100;Lidl;söndag");
		cg.createCategory(catList);
		catList.getCategoryIndex(2).addPurchase("Korv;Cost: 1000;Lidl;söndag");
		cg.createCategory(catList);
	}

}






























// frame.setExtendedState(JFrame.MAXIMIZED_BOTH); / olika mobiler olika sk‰rmar
// gˆr framen lika stor som displayen
// frame.setResizable(false);













