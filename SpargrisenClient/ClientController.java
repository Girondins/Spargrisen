package SpargrisenClient;


import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import SpargrisenGUI.CategoryGUI;
import SpargrisenGUI.GUIController;
import SpargrisenGUI.HomePageGUI;
import SpargrisenObjekt.AvailableUser;
import SpargrisenObjekt.Category;
import SpargrisenObjekt.RegisterUser;
import SpargrisenObjekt.Tag;
import SpargrisenObjekt.User;
import SpargrisenServer.InputGUI;



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
		AvailableUser user = new AvailableUser(name); 
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
	
	public void registerUser(String userName, String password){
		RegisterUser rg = new RegisterUser(userName);
		rg.setPassword(password);
		
		
		client.registerUser(rg);
	}
	
	
	public void showMessage(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void addCategory(Category category){
		client.addCategory(category);
	}
	
	public void addTag(Tag tag){
		client.addTag(tag);
	}
	
	
	
	public static void main(String [] args) throws IOException{
		Client a = new Client("127.0.0.1",3001);
		new ClientController(a);
		
	}
	
	
	

}
