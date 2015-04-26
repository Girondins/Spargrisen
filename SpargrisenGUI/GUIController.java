package SpargrisenGUI;

import java.io.IOException;

import SpargrisenClient.Category;
import SpargrisenClient.ClientController;
import SpargrisenClient.User;

public class GUIController {
	private User user;
	private HomePageGUI hpGUI;
	private ClientController cc;
	
	public GUIController(ClientController cc,User user){
		this.cc = cc;
		this.user = user;
		hpGUI = new HomePageGUI(this,user);
	}
	
	public void setUser(User user){
		this.user=user;
	}
	
	public void setupCategoryList(){
		hpGUI.setUser(user);
	}
	
	public void createCategoryList() throws IOException{
		setupCategoryList();
		hpGUI.createCategoryList(user.getCategoryList());
	}
	
	public void createTagViewer() throws IOException{
		setupCategoryList();
		hpGUI.createTagViewer(user.getCategoryList());
	}
	
	public void addCategory(Category category){
		user.getCategoryList().addCategory(category);
		cc.updateUser(user);		//Går att använda för att uppdatera server, KASTA INTE
	}
	
	public void addTag(String tag,Category category){
		for(int i = 0; i<user.getCategoryList().size(); i++){
			if(user.getCategoryList().getCategoryIndex(i).getCategoryName().equals(category.getCategoryName())){
				user.getCategoryList().getCategoryIndex(i).addTag(tag);
			}
		}
		cc.updateUser(user); 		//Går att använda för att uppdatera server, KASTA INTE
	}
	
	public void homePage(){
		hpGUI = new HomePageGUI(this,user);
	}
	
	
}
