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



public class AddTagGUI implements ActionListener{
	private JPanel tagPanel = new JPanel();
	private JPanel inputPanel = new JPanel(new BorderLayout());
	private JPanel mainPanel = new JPanel(new BorderLayout());
	private JPanel bottomPanel = new JPanel();
	private JPanel middlePanel = new JPanel();
	private JLabel tagName = new JLabel("Tag Name: ");
	private JTextField tagText = new JTextField();
	private JButton addTag = new JButton("Add Tag!");
	private JButton returnButton = new JButton("Previous");
	private JButton homeButton = new JButton("Home");
	JFrame frame = new JFrame("Add Tags");
	private GUIController GUIc;
	private Category category;
	private Font font = new Font("Cooper Black", Font.PLAIN, 12);

	public AddTagGUI(GUIController GUIc,Category category) {
			this.GUIc = GUIc;
			this.category = category;
//			createCategory.setSize(new Dimension(20,20));
			addTag.setPreferredSize(new Dimension(200,50));
			tagText.setPreferredSize(new Dimension(150, 20));
			tagPanel.add(tagName);
			tagPanel.add(tagText);
			inputPanel.add(tagPanel, BorderLayout.NORTH);
//			inputPanel.add(addTag, BorderLayout.SOUTH);
			middlePanel.add(addTag);
			bottomPanel.add(returnButton);
			bottomPanel.add(homeButton);
			
			
			
			mainPanel.add(inputPanel, BorderLayout.NORTH);
			mainPanel.add(middlePanel, BorderLayout.CENTER);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			addTag.setFont(font);
			returnButton.setFont(font);
			homeButton.setFont(font);
			addTag.addActionListener(this);
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
		if(e.getSource() == addTag){
			String tagName = tagText.getText();
			GUIc.addTag(tagName,category);
			JOptionPane.showMessageDialog(null,"TAG: [" + tagName + "] Has been added to CATEGORY: " + category.getCategoryName() );
			try {
				GUIc.createTagViewer();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame.dispose();
		}
		
		if(e.getSource() == returnButton){
			try {
				GUIc.createTagViewer();;
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













