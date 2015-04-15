package Spargrisen;


import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;



public class ClientController{
	
	
	private Client client;	
	
	public ClientController(Client client) throws IOException{
		this.client = client;
		client.setClientController(this);
		connect();
	}
	
	public void connect(){
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
//		new CategoryGUI(name,catList);
		
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
		String purchase = "Endi,Häst,Cost: 1000,Hemköp,120301230";
		String purch = "Endi,Häst,Cost: 500,Hemköp, 12031230";
		Category cat = new Category("Mat", 2000, new LinkedList());
		cat.addPurchase(purchase);
		cat.addPurchase(purch);
		CategoryList catList = new CategoryList();
		
		catList.addCategory(cat);
		Client a = new Client("127.0.0.1",3001,"Stefan");
		new ClientController(a);
		CategoryGUI catGUI = new CategoryGUI();
		catGUI.createCategory(catList);
		
	}
	
	
	

}
