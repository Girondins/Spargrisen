package Spargrisen;

import java.util.Observable;

import javax.swing.JOptionPane;



public class ClientController extends Observable {
	
//	private ClientGUI clientGUI;
	private Client client;
	
	
	public ClientController(Client client){
		String name = JOptionPane.showInputDialog("UserName");
		client.setUserName(name);
		this.client = client;
		
		client.setClientController(this);
		
	}
	
	public void newObject(Object object){
		if(object instanceof User) {
			User user = (User) object;
			extractUserInfo(user);
		}
		
	}
	public void extractUserInfo(User user) {
		extractUserName(user);
		extractCategoryList(user);
		extractUserCategoryInfo(user);
	}
	
	public void extractUserName(User user) {
		//gui.setName(user.getName())
	}
	public void extractCategoryList(User user){
		
	}
	public void extractUserCategoryInfo(User user) {
		
	}
	public static void main(String [] args) {
		
	}
	
	
	

}
