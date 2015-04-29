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



public class TagViewer implements ActionListener{
	private JTextArea textArea = new JTextArea();
	private JPanel panel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JButton returnButton = new JButton("Previous");
	private JButton homeButton = new JButton("Home");
	private JButton addTag = new JButton("Add a new Tag");
	private Font bFont = new Font("Cooper Black", Font.PLAIN, 12);
	private GUIController GUIc;
	private JFrame frame;
	private Category category;

	public TagViewer(Category category, GUIController GUIc) {
		this.GUIc = GUIc;
		this.category = category;
		LinkedList<String> tags = category.getTagList();
		String showText =  "Available Tags for: " + category.getCategoryName() + "\n" + "\n";
		for(int i = 0; i<tags.size(); i++){
			showText += "TAG: " + tags.get(i) + "\n";
		}
		
		
		
		Font stringFont = new Font("Cooper Black", Font.PLAIN, 16);
		Font font = new Font("Cooper Black", Font.PLAIN, 24);
		textArea.setPreferredSize(new Dimension(350,400));
		textArea.setText(showText);
		textArea.setFont(stringFont);
		textArea.setEditable(false);
		JScrollPane txtScroll = new JScrollPane(textArea);
//		txtScroll.setBackground(new Color(49,69,130));
//		txtScroll.setForeground(new Color(49,69,159));
		txtScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		txtScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		panel.setBackground(new Color(49,69,159));
		panel.add(txtScroll);
		panel.add(addTag);
		panel.add(bottomPanel,BorderLayout.SOUTH);
		bottomPanel.add(returnButton);
		bottomPanel.add(homeButton);
		addTag.setFont(bFont);
		returnButton.setFont(bFont);
		homeButton.setFont(bFont);
		addTag.addActionListener(this);
		returnButton.addActionListener(this);
		homeButton.addActionListener(this);

		
		
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
		if(e.getSource() == addTag){
			frame.dispose();
			new AddTagGUI(GUIc,category);
		}
		if(e.getSource() == returnButton){
			frame.dispose();
			try {
				GUIc.createTagViewer();
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













