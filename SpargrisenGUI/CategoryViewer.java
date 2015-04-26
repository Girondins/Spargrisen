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
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;

import SpargrisenClient.Category;



public class CategoryViewer implements ActionListener{
	private JTextArea textArea = new JTextArea();
	private JLabel totSum = new JLabel();
	private JPanel panel = new JPanel();
	private JLabel overDraft = new JLabel();
	private JPanel amountPanel = new JPanel(new GridLayout(2,1));
	private JPanel bottomPanel = new JPanel();
	private JButton returnButton = new JButton("Previous");
	private JButton homeButton = new JButton("Home");
	private Font bFont = new Font("Cooper Black", Font.PLAIN, 12);
	private JFrame frame;
	private GUIController GUIc;

	public CategoryViewer(Category category, GUIController GUIc) {
		this.GUIc = GUIc;
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
		
	}

}






























// frame.setExtendedState(JFrame.MAXIMIZED_BOTH); / olika mobiler olika sk‰rmar
// gˆr framen lika stor som displayen
// frame.setResizable(false);













