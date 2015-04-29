package SpargrisenGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class OptionGUI implements ActionListener {
	private JLabel titel = new JLabel("Options");
	private JLabel userName = new JLabel("Change Usernamn:");
	private JLabel name = new JLabel("Change Name");
	private JLabel password = new JLabel("Change Password");
	private JLabel vPassword = new JLabel("Verify Password");
	private JLabel oldPassword = new JLabel("Enter old password: ");
	private JTextField userNameTF = new JTextField("");
	private JTextField nameTF = new JTextField("");
	private JTextField passwordTF = new JTextField("");
	private JTextField vPasswordTF = new JTextField("");
	private JTextField oldPasswordTF = new JTextField("");
	private JButton accept = new JButton("Confirm");
	private JButton cancel = new JButton("Cancel");
	private JPanel mainPanel = new JPanel(new GridLayout(8, 1));
	private JPanel firstPanel = new JPanel();
	private JPanel secondPanel = new JPanel(new GridLayout());
	private JPanel thirdPanel = new JPanel(new GridLayout(1, 2));
	private JPanel fourthPanel = new JPanel(new GridLayout(1, 2));
	private JPanel fifthPanel = new JPanel(new GridLayout(1, 2));
	private JPanel sixthPanel = new JPanel(new GridLayout(1, 2));
	private JPanel lastPanel = new JPanel();
	private Font font = new Font("Cooper Black", Font.PLAIN, 16);
	private Font headFont = new Font("Cooper Black", Font.PLAIN, 20);
	private JFrame frame = new JFrame("Create new Account");
	private GUIController GUIc;

	public OptionGUI(GUIController GUIc) {
		this.GUIc = GUIc;

		titel.setFont(headFont);
		userName.setFont(font);
		name.setFont(font);
		password.setFont(font);
		vPassword.setFont(font);
		oldPassword.setFont(font);

		// userNameTF.setPreferredSize(new Dimension(150, 20));
		// passwordTF.setPreferredSize(new Dimension(150, 20));
		// vPasswordTF.setPreferredSize(new Dimension(150, 20));
		// nameTF.setPreferredSize(new Dimension(150, 20));

		// firstPanel.add(Box.createHorizontalStrut(0));
		firstPanel.add(titel);
		secondPanel.add(userName, BorderLayout.WEST);
		secondPanel.add(userNameTF, BorderLayout.CENTER);
		thirdPanel.add(password);
		thirdPanel.add(passwordTF);
		fourthPanel.add(vPassword);
		fourthPanel.add(vPasswordTF);
		fifthPanel.add(name);
		fifthPanel.add(nameTF);
		sixthPanel.add(oldPassword);
		sixthPanel.add(oldPasswordTF);
		lastPanel.add(accept);
		lastPanel.add(cancel);

		mainPanel.add(firstPanel);
		mainPanel.add(secondPanel);
		mainPanel.add(thirdPanel);
		mainPanel.add(fourthPanel);
		mainPanel.add(fifthPanel);
		mainPanel.add(sixthPanel);
		mainPanel.add(lastPanel);

		frame.setPreferredSize(new Dimension(380, 560)); // galaxy S4 screen
															// size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.add(mainPanel);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == accept) {
			String pass, vPass, userName, name, checkPass;
			userName = userNameTF.getText();
			pass = passwordTF.getText();
			vPass = vPasswordTF.getText();
			name = nameTF.getText();
			checkPass = oldPasswordTF.getText();
			
			
			//CHECKPASS KONTROLLE MED DATABAS IFALL LÖSEN STÄMMER: FIXA SEN

			if (userName != null) {

			} else if (pass != null) {
				if (pass.equals(vPass)) {
					// GUIc.registerUser(userName,pass);

				} else {
					JOptionPane
							.showMessageDialog(null,
									"The verified password does not match the desired password!");
				}

			} else if (name != null) {

			}

		}
		if (e.getSource() == cancel) {
			GUIc.homePage();
			frame.dispose();
		}

	}

	// public static void main(String[] args) {
	// new OptionGUI(GUIc);
	//
	// }
}