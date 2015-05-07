package SpargrisenGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import SpargrisenObjekt.AvailableUser;
import SpargrisenObjekt.User;

public class OptionGUI implements ActionListener {
	private JLabel titel = new JLabel("Options");
	private JLabel userName = new JLabel("Change Usernamn:");
	private JLabel name = new JLabel("Change Name");
	private JLabel password = new JLabel("Change Password");
	private JLabel vPassword = new JLabel("Verify Password");
	private JLabel oldPassword = new JLabel("Enter old password: ");
	private JTextField userNameTF = new JTextField("");
	private JTextField nameTF = new JTextField("");
	private JPasswordField passwordTF = new JPasswordField("");
	private JPasswordField vPasswordTF = new JPasswordField("");
	private JPasswordField oldPasswordTF = new JPasswordField("");
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
	private AvailableUser user;

	public OptionGUI(GUIController GUIc, AvailableUser user) {
		this.GUIc = GUIc;
		this.user = user;

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
		
		accept.addActionListener(this);
		cancel.addActionListener(this);
		
		
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
			String userName, name;
			char[] pass, vPass, checkPass;
			int count = 0, checkCount = 0;
			userName = userNameTF.getText();
			pass = passwordTF.getPassword();
			vPass = vPasswordTF.getPassword();
			name = nameTF.getText();
			checkPass = oldPasswordTF.getPassword();
			boolean check = false;

			if (user.getPassword().length == checkPass.length) {
				for (int i = 0; i < checkPass.length; i++) {
					if (user.getPassword()[i] == checkPass[i]) {
						checkCount = checkCount + 2;
					}
				}
				if (checkCount == user.getPassword().length * 2) {
					check = true;
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"You have entered wrong password");
			}

			if (check == true) {
				if (userName != null) {
					user.setChangeName(userName);
				}
				if (pass != null) {
					if (pass.length == vPass.length) {
						for (int i = 0; i < pass.length; i++) {
							if (pass[i] == vPass[i]) {
								count = count + 2;
							}
						}

						if (count == pass.length * 2) {
							user.setPassword(pass);
						} else
							JOptionPane
									.showMessageDialog(null,
											"The verified password does not match the desired password!");
					} else
						JOptionPane
								.showMessageDialog(null,
										"The verified password does not match the desired password!");
				} else if (name != null) {
					user.setFirstName(name);
				}
				GUIc.editUser(user);
				JOptionPane.showMessageDialog(null, "Changed Settings Saved");
				GUIc.homePage();
				frame.dispose();
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