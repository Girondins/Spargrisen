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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import SpargrisenClient.Category;



public class CreateCategory implements ActionListener{
	private JPanel limitPanel = new JPanel();
	private JPanel categoryPanel = new JPanel();
	private JPanel inputPanel = new JPanel(new BorderLayout());
	private JPanel mainPanel = new JPanel(new BorderLayout());
	private JPanel bottomPanel = new JPanel();
	private JPanel middlePanel = new JPanel();
	private JLabel categoryName = new JLabel("Category Name: ");
	private JLabel setBudgetLimit = new JLabel("Set a budgetlimit: ");
	private JTextField categoryText = new JTextField();
	private JTextField limitText = new JTextField();
	private JButton createCategory = new JButton("Create Category");
	private JButton returnButton = new JButton("Previous");
	private JButton homeButton = new JButton("Home");
	private JFrame frame = new JFrame("Create a new category");
	private Font font = new Font("Cooper Black", Font.PLAIN, 12);
	private GUIController GUIc;

	public CreateCategory(GUIController GUIc) {
			this.GUIc = GUIc;
//			createCategory.setSize(new Dimension(20,20));
			createCategory.setPreferredSize(new Dimension(200,50));
			categoryText.setPreferredSize(new Dimension(150, 20));
			limitText.setPreferredSize(new Dimension(150, 20));
			categoryPanel.add(categoryName);
			categoryPanel.add(categoryText);
			limitPanel.add(setBudgetLimit);
			limitPanel.add(limitText);
			inputPanel.add(categoryPanel, BorderLayout.NORTH);
			inputPanel.add(limitPanel);
			inputPanel.add(createCategory, BorderLayout.SOUTH);
//			middlePanel.add(createCategory);
			bottomPanel.add(returnButton);
			bottomPanel.add(homeButton);
			
			
			mainPanel.add(inputPanel, BorderLayout.NORTH);
//			mainPanel.add(middlePanel, BorderLayout.CENTER);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			createCategory.setFont(font);
			returnButton.setFont(font);
			homeButton.setFont(font);
			createCategory.addActionListener(this);
			returnButton.addActionListener(this);
			homeButton.addActionListener(this);

		
		

		frame.setPreferredSize(new Dimension(380,560)); // galaxy S4 screen size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.add(mainPanel);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == createCategory){
			String categoryName = categoryText.getText();
			int budgetLimit = Integer.parseInt(limitText.getText());
			GUIc.addCategory(new Category(categoryName, budgetLimit, new LinkedList()));
			JOptionPane.showMessageDialog(null,"CATEGORY: " + categoryName + " has now been created");
			try {
				GUIc.createCategoryList();;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame.dispose();
		}
		
		if(e.getSource() == returnButton){
			try {
				GUIc.createCategoryList();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame.dispose();
		}
		if(e.getSource() == homeButton){
			frame.dispose();
			GUIc.homePage();
		}
		
	}


//	public static void main(String[] args) {
//		new CreateCategory();
//
//	}


}






























// frame.setExtendedState(JFrame.MAXIMIZED_BOTH); / olika mobiler olika sk‰rmar
// gˆr framen lika stor som displayen
// frame.setResizable(false);













