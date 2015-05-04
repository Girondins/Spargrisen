package SpargrisenClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class RegisterGUI implements ActionListener {
	private JLabel titel = new JLabel("Register");
	private JLabel userName = new JLabel("Usernamn:");
	private JLabel name = new JLabel("Name");
	private JLabel password = new JLabel("Lösenord");
	private JLabel vPassword = new JLabel("Verifiera lösenord");
	private JTextField userNameTF = new JTextField("");
	private JTextField nameTF = new JTextField("");
	private JPasswordField passwordTF = new JPasswordField("");
	private JPasswordField vPasswordTF = new JPasswordField("");
	private JButton accept = new JButton("OK");
	private JButton cancel = new JButton("Cancel");
	private JPanel mainPanel = new JPanel(new GridLayout(7, 1));
	private JPanel firstPanel = new JPanel();
	private JPanel secondPanel = new JPanel(new BorderLayout());
	private JPanel thirdPanel = new JPanel(new GridLayout(1, 2));
	private JPanel fourthPanel = new JPanel(new GridLayout(1, 2));
	private JPanel fifthPanel = new JPanel(new GridLayout(1, 2));
	private JPanel lastPanel = new JPanel();
	private Font font = new Font("Cooper Black", Font.PLAIN, 16);
	private Font headFont = new Font("Cooper Black", Font.PLAIN, 20);
	private JFrame frame = new JFrame("Create new Account");
	private ClientController cc;

	public RegisterGUI(ClientController cc) {
		this.cc = cc;

		titel.setFont(headFont);
		userName.setFont(font);
		name.setFont(font);
		password.setFont(font);
		vPassword.setFont(font);

		userNameTF.setPreferredSize(new Dimension(150, 20));
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
		lastPanel.add(accept);
		lastPanel.add(cancel);

		mainPanel.add(firstPanel);
		mainPanel.add(secondPanel);
		mainPanel.add(thirdPanel);
		mainPanel.add(fourthPanel);
		mainPanel.add(fifthPanel);
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
			String userName;
			char[] pass, vPass;
			userName = userNameTF.getText();
			pass = passwordTF.getPassword();
			vPass = vPasswordTF.getPassword();
			int count = 0;

			if (pass.length == vPass.length) {
				for (int i = 0; i < pass.length; i++) {
					if (pass[i] == vPass[i]) {
						count = count + 2;
					}
				}

				if (count == pass.length*2) {
					cc.registerUser(userName, pass);
					frame.dispose();
				} else
					JOptionPane.showMessageDialog(null,
									"The verified password does not match the desired password!");
			} else

				JOptionPane.showMessageDialog(null,
								"The verified password does not match the desired password!");

		}
		if (e.getSource() == cancel) {
			try {
				cc.startLogin();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame.dispose();
		}

	}

	// public static void main(String[] args) {
	// new RegisterGUI(GUIc);
	//
	// }
}