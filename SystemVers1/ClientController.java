package SystemVers1;


import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;



public class ClientController{
	
	
	private Client client;	
	private CategoryGUI cg;

	
	public ClientController(Client client) throws IOException{
		this.client = client;
		client.setClientController(this);
		connect();
	}
	
	public void connect(){
		this.cg = new CategoryGUI();
		client.connect();
	}
	
	
	public void newObject(Object object){
		if(object instanceof User) {
		User user = (User) object;
		extractUserInfo(user);
	}else if(object instanceof String){
		String message = (String)object;
		showMessage(message);
	}
		
	}
	public void extractUserInfo(User user) {

		String name;
		CategoryList catList;
		name = extractUserName(user);
		catList = extractCategoryList(user);
		cg.createCategory(catList);
		
	}
	
	public String extractUserName(User user) {
		return user.getName();

		}
		
	public CategoryList extractCategoryList(User user){
		return user.getCategoryList();
	}
	public void extractUserCategoryInfo(CategoryList catList) {
		
		
	}
	public void showMessage(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	
	
	public static void main(String [] args) throws IOException{
		String name = JOptionPane.showInputDialog("Ange ett användarnamn: ");
		Client a = new Client("127.0.0.1",3001,name, new RegisterGUI());
		new ClientController(a);
		
	}
	
	
	

}
