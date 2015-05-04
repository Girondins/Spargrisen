package SpargrisenClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI extends JFrame  implements ActionListener{
	private JPanel panelLogin = new JPanel();
	private JPanel panelButton = new JPanel();
	private JTextField userTxt = new JTextField();
	private JPasswordField passTxt = new JPasswordField();
	private JTextField invis = new JTextField();
	private JLabel imageLbl = new JLabel();
	private Image backgroundImage;
	private JFrame frame = new JFrame();
	private JButton Loginbtn = new JButton("Logga in");
	private JButton RegBtn  = new JButton("Registrera");
	private ClientController cc;
	
	
	Font font = new Font("serif", Font.PLAIN, 20);


	
	
	public LoginGUI(ClientController cc) throws IOException {
		this.cc = cc;
//		backgroundImage = ImageIO.read(new File("C:/Users/Alex/Desktop/BilderSpargrisen/spargrisMainCROP.jpg"));
//		ImageIcon icon = new ImageIcon(backgroundImage);
//		imageLbl.setIcon(icon);
//		imageLbl.setLayout(null);
	
		
//		panelLogin.setLayout(null);
		panelLogin.setPreferredSize(new Dimension(425,709));
		panelLogin.setBackground(new Color(49,69,159));
		
		
		userTxt.setText("Anv�ndarnamn");
		passTxt.setText("L�senord");
		userTxt.setPreferredSize(new Dimension(300,30));
		passTxt.setPreferredSize(new Dimension(300,30));
		userTxt.setBounds(135,80,120,32);
		passTxt.setBounds(135,140,120,32);
		passTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		userTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		userTxt.setFont(font);
		passTxt.setFont(font);
		
		Loginbtn.addActionListener(this);
		RegBtn.addActionListener(this);
		
		
		
		
		passTxt.addMouseListener(new MouseAdapter(){
            
	 public void mouseClicked(MouseEvent e){
		 
            	
            		passTxt.setText("");
            	
          }
          
         });
			
		
		
		userTxt.addMouseListener(new MouseAdapter(){
	            
		 public void mouseClicked(MouseEvent e){
	            	if(userTxt.getText().equals("Anv�ndarnamn")){
	            	userTxt.setText("");
	            	}else{ 
	            		userTxt.setText(userTxt.getText());
	            	}
	            	
	            }
	            public void mouseExited(MouseEvent e) {
	            	
	            	userTxt.setText(userTxt.getText());
	            	       
	            }
	        });
		
		
		
		
		JPanel wholePanel = new JPanel(new BorderLayout());
	
		
		
		
		
		panelLogin.add(userTxt);
		panelLogin.add(passTxt);
		
		panelButton.add(Loginbtn);	
		panelButton.add(RegBtn);
		
		wholePanel.add(imageLbl, BorderLayout.PAGE_START);
		wholePanel.add(panelLogin, BorderLayout.CENTER);
		wholePanel.add(panelButton, BorderLayout.SOUTH);

		



		
		frame.add(wholePanel);
		frame.setPreferredSize(new Dimension(425,709));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
		
	
//	public static void main( String [] args) throws IOException {
//		LoginGUI gui = new LoginGUI();
//		
//		
//		
//	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

			
			if (e.getSource() == Loginbtn) {
				char[] pass;
				String userName;
				userName = userTxt.getText();
				pass = passTxt.getPassword();
				cc.checkUser(userName,pass);
				frame.dispose();
			}
			if(e.getSource()== RegBtn){
				frame.dispose();
				new RegisterGUI(cc);
			}

		}
		
	}
	




	
	

