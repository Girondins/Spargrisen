package SpargrisenGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
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

public class LoginGUI extends JFrame{
	private JPanel panel = new JPanel();
	private JTextField userTxt = new JTextField();
	private JPasswordField passTxt = new JPasswordField();
	private JTextField invis = new JTextField();
	private JLabel imageLbl = new JLabel();
	private Image backgroundImage;
	private JFrame frame = new JFrame();
	private JButton Loginbtb = new JButton("Logga in");
	
	Font font = new Font("serif", Font.PLAIN, 18);


	
	
	public LoginGUI() throws IOException {
//		backgroundImage = ImageIO.read(new File("/Users/alexander/Downloads/BilderSpargrisen-2/spargrisMain.png"));
//		ImageIcon icon = new ImageIcon(backgroundImage);
//		imageLbl.setIcon(icon);
//		imageLbl.setLayout(null);
	
		
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(425,709));
		panel.setBackground(new Color(49,69,159));
		
		
		userTxt.setText("Användarnamn");
		passTxt.setText("Lösenord");
		userTxt.setPreferredSize(new Dimension(300,30));
		passTxt.setPreferredSize(new Dimension(300,30));
		userTxt.setBounds(135,80,200,27);
		passTxt.setBounds(135,140,200,27);
//		passTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
//		userTxt.setBorder(javax.swing.BorderFactory.createDashedBorder(Color.WHITE, 5, 300, 23, true));
//		userTxt.setBackground(new Color(49,69,159));
		userTxt.setFont(font);
		passTxt.setFont(font);
		

		
		
		
		
		passTxt.addMouseListener(new MouseAdapter(){
            
	 public void mouseClicked(MouseEvent e){
            	
            		passTxt.setText("");
            	
          }
          
         });
			
		
		
		userTxt.addMouseListener(new MouseAdapter(){
	            
		 public void mouseClicked(MouseEvent e){
	            	if(userTxt.getText().equals("Användarnamn")){
	            	userTxt.setText("");
	            	}else{ 
	            		userTxt.setText(userTxt.getText());
	            	}
	            	
	            }
	            public void mouseExited(MouseEvent e) {
	            	
	            	userTxt.setText(userTxt.getText());
	            	
	            	
//	            	if(userTxt.getText()==""){
//	            	userTxt.setText("Anv‰ndarnamn");
//	            	}else {
//	            		userTxt.setText(userTxt.getText());
//	            	}
	            }
	        });
		
		
//		userTxt.addFocusListener(new FocusListener(){
//	      
//		
//			
//			
//			@Override
//	        public void focusGained(FocusEvent e){
//	        	
//	        	if(userTxt.getText()!= "  Anv‰ndarnamn "){
//	        		userTxt.setText("");
//		        	}else {
//		        		userTxt.setText(userTxt.getText());
//		        	}
//	        	
//	        }
//
//			@Override
//			public void focusLost(FocusEvent arg0) {
//				if(userTxt.getText() == ""){
//					userTxt.setText("Lˆsenord");
//					}else {
//						userTxt.setText(userTxt.getText());
//					}
//				}
//		    });
//		}
//		passTxt.addFocusListener(new FocusListener(){
//	        @Override
//	        public void focusGained(FocusEvent e){
//	        	if(passTxt.getText()!= "Anv‰ndarnamn"){
//	        	passTxt.setText("");
//	        	}else {
//	        		passTxt.setText(passTxt.getText());
//	        	}
//	        }
//
//			@Override
//			public void focusLost(FocusEvent arg0) {
//				if(passTxt.getText() == ""){
//				passTxt.setText("Lˆsenord");
//				}else {
//					passTxt.setText(passTxt.getText());
//				}
//			}
//	    });
//		
		
		JPanel wholePanel = new JPanel(new BorderLayout());
	
		
		
		
		
//		panel.add(userTxt);
		panel.add(passTxt, javax.swing.BorderFactory.createLineBorder(Color.BLACK, 30, true));
		panel.add(userTxt, javax.swing.BorderFactory.createLineBorder(Color.BLACK, 30, true));		
		wholePanel.add(imageLbl, BorderLayout.PAGE_START);
		wholePanel.add(panel, BorderLayout.CENTER);
		
		



		
		frame.add(wholePanel);
		frame.setPreferredSize(new Dimension(425,709));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		
	
	}
	public static void main( String [] args) throws IOException {
		LoginGUI gui = new LoginGUI();
		
		
	}
	




	
	
}
