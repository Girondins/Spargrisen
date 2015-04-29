package SpargrisenClient;


import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import SpargrisenGUI.CategoryGUI;
import SpargrisenGUI.GUIController;
import SpargrisenGUI.HomePageGUI;



public class ClientController{
	
	
	private Client client;	
	private GUIController GUIc;
	private String name;

	
	public ClientController(Client client) throws IOException{
		this.client = client;
		name = JOptionPane.showInputDialog("Ange ett användarnamn: ");
		client.setClientController(this);
		connect();
	}
	
	public void connect() throws IOException{
		User user = new User(name); 
		GUIc = new GUIController(this,user);
		client.connect(user);
	}
	
	
	public void setUserInfo(User user) throws IOException {
		GUIc.setUser(user);
		
		
	}
	public void updateUser(User user){
		client.updateUser(user);
		//Går att använda för att uppdatera server, KASTA INTE
	}
	
	
	public void showMessage(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	
	
	
	public static void main(String [] args) throws IOException{
		Client a = new Client("127.0.0.1",3001,new RegisterGUI());
		new ClientController(a);
		
	}
	
	
	

}
