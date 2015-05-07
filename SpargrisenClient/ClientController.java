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
	private AvailableUser user;

	
	public ClientController(Client client) throws IOException{
		this.client = client;
		client.setClientController(this);
		startLogin();
	}
	
	public void connect(AvailableUser user) throws IOException{
		this.user = user;
		GUIc = new GUIController(this,user);

	}
	
	public void checkUser(String userName, char[] password){
		user = new AvailableUser(userName);
		user.setPassword(password);
		client.connect(user);
		
	}
	
	
	public void setUserInfo(AvailableUser user) throws IOException {
		GUIc.setUser(user);
		
		
	}
	
	public void registerUser(String userName, char[] password){
		RegisterUser rg = new RegisterUser(userName);
		rg.setPassword(password);
		
		for(int i = 0 ; i<rg.getPassword().length; i++){
			System.out.print(rg.getPassword()[i] + "1");
		}
		
		
		client.registerUser(rg);
	}
	
	
	public void showMessage(String message) throws IOException{
		JOptionPane.showMessageDialog(null, message);
			startLogin();
	
	}
	
	public void startLogin() throws IOException{
		new LoginGUI(this);
	}
	
	public void addCategory(Category category){
		client.addCategory(category);
	}
	
	public void changeCategoryLimit(Category category){
		category.setToDo(1);
		client.changeCategoryLimit(category);
	}
	
	public void addTag(Tag tag){
		client.addTag(tag);
	}
	
	public void editUser(AvailableUser user){
		user.setToDo(1);
		client.editUser(user);
	}
	
	
	
	public static void main(String [] args) throws IOException{
		Client a = new Client("127.0.0.1",3001);
		new ClientController(a);
		
	}
	
	
	

}
