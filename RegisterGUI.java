package Spargrisen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

import javax.swing.*;

public class RegisterGUI extends JPanel implements ActionListener {
	private JLabel titel = new JLabel("Register");
	private JLabel userL = new JLabel("User: ");
	private JLabel purchaseL = new JLabel("Purchase: ");
	private JLabel costL = new JLabel("Cost: ");
	private JLabel placeL = new JLabel("Place: ");
	private JTextField userTF = new JTextField("");
	private JTextField purchaseTF = new JTextField("");
	private JTextField costTF = new JTextField("");
	private JTextField placeTF = new JTextField("");
	private JButton makePurchase = new JButton("Make Purchase: ");
	private Font font = new Font("SansSerif", Font.BOLD, 14);
	private Socket socket;
	private ObjectOutputStream oos;

	public RegisterGUI(String ip, int port) throws UnknownHostException, IOException {
		socket = new Socket(ip,port);
		oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		setPreferredSize(new Dimension(600, 200));
		titel.setFont(font);
		userL.setFont(font);
		purchaseL.setFont(font);
		costL.setFont(font);
		placeL.setFont(font);
		makePurchase.setFont(font);

		titel.setPreferredSize(new Dimension(600, 20));
		userL.setPreferredSize(new Dimension(290, 20));
		purchaseL.setPreferredSize(new Dimension(290, 20));
		costL.setPreferredSize(new Dimension(290, 20));
		placeL.setPreferredSize(new Dimension(290, 20));
		userTF.setPreferredSize(new Dimension(290, 20));
		purchaseTF.setPreferredSize(new Dimension(290, 20));
		costTF.setPreferredSize(new Dimension(290, 20));
		placeTF.setPreferredSize(new Dimension(290, 20));
		makePurchase.setPreferredSize(new Dimension(290, 50));

		add(titel, BorderLayout.CENTER);
		add(userL, BorderLayout.WEST);
		add(userTF, BorderLayout.EAST);
		add(purchaseL, BorderLayout.WEST);
		add(purchaseTF, BorderLayout.EAST);
		add(costL, BorderLayout.WEST);
		add(costTF, BorderLayout.EAST);
		add(placeL, BorderLayout.WEST);
		add(placeTF, BorderLayout.EAST);
		add(makePurchase, BorderLayout.CENTER);
		makePurchase.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == makePurchase) {
			sendResponse();
		}

	}

	public String sendResponse() {
		String res = getUser() + ";" + getPurchase() + ";"
				+ getCost() + ";" + getPurchase() + ";" + "[" +  getDate() + " (" + getTime() + ")" + "]";
		System.out.println(res);
		return res;
	}
	public String getUser(){
		return "User: " + userTF.getText();
	}
	
	public String getPurchase(){
		return "Purchase: " + purchaseTF.getText();
	}
	
	public String getCost(){
		return "Cost: " + costTF.getText();
	}
	
	public String getPlace(){
		return "Place: " + placeTF.getText();
	}

	public String getTime() {
		Calendar cal = Calendar.getInstance();
		String time = "";

		if (cal.get(Calendar.SECOND) < 10) {
			time =  cal.get(Calendar.HOUR_OF_DAY) + ":"
					+ cal.get(Calendar.MINUTE) + ":0"
					+ cal.get(Calendar.SECOND);
		} else {
			time = cal.get(Calendar.HOUR_OF_DAY) + ":"
					+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
		}
		return time;
	}

	public String getDate() {
		Calendar cal = Calendar.getInstance();
		String date = "";
		int year;
		int month;
		int day;
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		date = day + "/" + month + "/" + year;
		return date;
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		JFrame frame = new JFrame("KassaGUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new RegisterGUI("127.0.0.1",3001));
		frame.pack();
		frame.setVisible(true);

	}
}
