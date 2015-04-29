package SpargrisenGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;

import SpargrisenObjekt.Category;



public class CategoryViewer implements ActionListener{
	private JTextArea textArea = new JTextArea();
	private JLabel totSum = new JLabel();
	private JPanel panel = new JPanel();
	private JLabel overDraft = new JLabel();
	private JPanel amountPanel = new JPanel(new GridLayout(2,1));
	private JPanel bottomPanel = new JPanel();
	private JButton changeBudgetLimit = new JButton("Change BudgetLimit");
	private JButton returnButton = new JButton("Previous");
	private JButton homeButton = new JButton("Home");
	private Font bFont = new Font("Cooper Black", Font.PLAIN, 12);
	private int change = 0;
	private Timer timer;
	private Category category;
	private JFrame frame;
	private GUIController GUIc;

	public CategoryViewer(Category category, GUIController GUIc) {
		this.GUIc = GUIc;
		this.category = category;
		ArrayList<String> purchases = category.getPurchaseList();
		String showText = "Purchases for: " + category.getCategoryName() + "\n";
		for(int i = 0; i<purchases.size(); i++){
			showText += purchases.get(i).substring(6, purchases.get(i).length()) + "\n";
		}
		totSum.setText("Total Sum: " + category.getCurrentSum());
		
		
		if(category.getBudgetLimit()<category.getCurrentSum()){
		overDraft.setText("Exceeded amount: " + (category.getBudgetLimit() - category.getCurrentSum()));
		}else {
			overDraft.setText("Left to spend: " + (category.getBudgetLimit() - category.getCurrentSum()));
		}
		
		Font stringFont = new Font("Cooper Black", Font.PLAIN, 16);
		Font font = new Font("Cooper Black", Font.PLAIN, 24);
		textArea.setPreferredSize(new Dimension(350,400));
		textArea.setText(showText);
		textArea.setFont(stringFont);
		textArea.setEditable(false);
		JScrollPane txtScroll = new JScrollPane(textArea);
		txtScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		txtScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		totSum.setFont(font);
		overDraft.setFont(font);
//		amountPanel.setBackground(new Color(49,69,159));
		amountPanel.add(totSum);
		amountPanel.add(overDraft);
		bottomPanel.add(changeBudgetLimit);
		bottomPanel.add(returnButton);
		bottomPanel.add(homeButton);
//		panel.setBackground(new Color(49,69,159));
		panel.add(txtScroll);
		panel.add(amountPanel);
		panel.add(bottomPanel,BorderLayout.SOUTH);
		returnButton.addActionListener(this);
		homeButton.addActionListener(this);
		returnButton.setFont(bFont);
		homeButton.setFont(bFont);

		
		
		frame = new JFrame(category.getCategoryName());
		frame.setPreferredSize(new Dimension(380,560)); // galaxy S4 screen size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.add(panel);
		
		
		
	}
	
	public void changeBudgetLimit(){
		
		
		if(change == 0){
		int limit = Integer.parseInt(JOptionPane.showInputDialog("Set new limit:"));
		change = 1;
		GUIc.changeCategorylimit(category.getCategoryName(), limit);
		timer.schedule(new monthCount(), 2592000);
		}else
			JOptionPane.showMessageDialog(null, "30 days has not passed yet since last change");
	}
	
	
	private class monthCount extends TimerTask{

		@Override
		public void run() {
			change = 0;
			
		}
		
	}
	



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == returnButton){
			frame.dispose();
			try {
				GUIc.createCategoryList();;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == homeButton){
			GUIc.homePage();
			frame.dispose();
		}
		
		if(e.getSource() == changeBudgetLimit){
			
			changeBudgetLimit();
			
		}
		
	}

}






























// frame.setExtendedState(JFrame.MAXIMIZED_BOTH); / olika mobiler olika sk‰rmar
// gˆr framen lika stor som displayen
// frame.setResizable(false);













