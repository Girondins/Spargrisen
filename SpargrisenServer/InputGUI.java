package SpargrisenServer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputGUI implements ActionListener {

		private Driver d = new Driver();
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
		private JPanel mainPanel = new JPanel();

		public InputGUI(Driver d2) throws UnknownHostException, IOException {
			this.d=d2;
			mainPanel.setPreferredSize(new Dimension(600, 200));
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

			mainPanel.add(titel, BorderLayout.CENTER);
			mainPanel.add(userL, BorderLayout.WEST);
			mainPanel.add(userTF, BorderLayout.EAST);
			mainPanel.add(purchaseL, BorderLayout.WEST);
			mainPanel.add(purchaseTF, BorderLayout.EAST);
			mainPanel.add(costL, BorderLayout.WEST);
			mainPanel.add(costTF, BorderLayout.EAST);
			mainPanel.add(placeL, BorderLayout.WEST);
			mainPanel.add(placeTF, BorderLayout.EAST);
			mainPanel.add(makePurchase, BorderLayout.CENTER);
			makePurchase.addActionListener(this);
			startFrame(mainPanel);
		}

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == makePurchase) {
				
				int i = Integer.parseInt(costTF.getText());
				d.connectionToMysql();
				d.newPurchase(userTF.getText(), purchaseTF.getText(), i, placeTF.getText(), getDate(),getTime());
				
			}

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
		
		public void startFrame(JPanel mainPanel){
			JFrame frame = new JFrame("KassaGUI");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(mainPanel);
			frame.pack();
			frame.setVisible(true);
		}

}
