package SpargrisenGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SpargrisenClient.ClientController;
import SpargrisenObjekt.CategoryList;
import SpargrisenObjekt.AvailableUser;
import SpargrisenObjekt.User;


public class HomePageGUI implements ActionListener{
	private JPanel firstPanel = new JPanel();
	private JPanel secondPanel = new JPanel(new GridLayout(1,2));
	private JPanel thirdPanel = new JPanel(new GridLayout(1,1));
	private JPanel fourthPanel = new JPanel(new GridLayout(1,1));
	private JLabel userName;
	private JLabel welcome = new JLabel("Welcome!");
	private JButton option = new JButton("Options");
	private JButton categoryList = new JButton("View CategoryList");
	private JButton addTags = new JButton("View Tags for Category");
	private JButton logout = new JButton("Logout");
	private JFrame frame = new JFrame("SparGrisen");
	private JPanel mainPanel = new JPanel(new GridLayout(4,1));
	private GUIController GUIc;
	private User user;
	
	
	public HomePageGUI(GUIController GUIc,User user){
		this.GUIc = GUIc;
		this.user = user;
		
		
		Font head = new Font("Cooper Black", Font.PLAIN, 24);
		Font font = new Font("Cooper Black", Font.PLAIN, 12);
		userName = new JLabel(user.getName());
		userName.setFont(head);
		welcome.setFont(head);
		firstPanel.add(Box.createVerticalStrut(50));
		firstPanel.add(welcome);
		firstPanel.add(userName);
		
		categoryList.setPreferredSize(new Dimension(160,100));
		addTags.setPreferredSize(new Dimension(160,100));
		categoryList.setFont(font);
		addTags.setFont(font);
		secondPanel.add(categoryList);
		secondPanel.add(addTags);
		
		option.setFont(font);
		logout.setFont(font);
		thirdPanel.add(option);
		fourthPanel.add(logout);
		
		
		
		mainPanel.add(firstPanel);
		mainPanel.add(secondPanel);
		mainPanel.add(thirdPanel);
		mainPanel.add(fourthPanel);
		
		
		
		categoryList.addActionListener(this);
		addTags.addActionListener(this);
		option.addActionListener(this);
		logout.addActionListener(this);


		frame.setPreferredSize(new Dimension(380,560)); // galaxy S4 screen size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.add(mainPanel);
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	public void openHome(){
		HomePageGUI hpGUI = new HomePageGUI(GUIc,user);
	}
	
	public void createCategoryList(CategoryList catList) throws IOException{
		new CategoryGUI(catList,GUIc);
	}
	
	public void createTagViewer(CategoryList catList) throws IOException{
		new CheckTagsGUI(catList,GUIc);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logout){
			System.exit(0);
		}
		if(e.getSource() == categoryList){
			try {
				frame.dispose();
				GUIc.createCategoryList();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == addTags){
			frame.dispose();
			try {
				GUIc.createTagViewer();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
//	public static void main(String[]args){
//		new HomePageGUI();
//	}


	

}
