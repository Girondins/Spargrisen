package SpargrisenGUI;

import java.io.IOException;
import java.util.LinkedList;

import SpargrisenClient.ClientController;
import SpargrisenObjekt.Category;
import SpargrisenObjekt.AvailableUser;
import SpargrisenObjekt.RegisterUser;
import SpargrisenObjekt.Tag;
import SpargrisenObjekt.User;

public class GUIController {
	private AvailableUser user;
	private HomePageGUI hpGUI;
	private ClientController cc;
	
	public GUIController(ClientController cc,AvailableUser user){
		this.cc = cc;
		this.user = user;
		hpGUI = new HomePageGUI(this,user);
	}
	
	public void setUser(AvailableUser user){
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
	
	public void addCategory(String categoryName, int budgetLimit){
		cc.addCategory(new Category(categoryName,budgetLimit,user,new LinkedList()));

	}
	
	
	public void changeCategorylimit(String categoryName,int budgetlimit){
		cc.changeCategoryLimit(new Category(categoryName,budgetlimit,user,new LinkedList()));
	}
	
	public void editUser(AvailableUser user){
		cc.editUser(user);
	}
	
	public void addTag(Tag tag){
		tag.setUserName(user.getName());
		cc.addTag(tag); 		
	}
	
	public void option(){
		new OptionGUI(this,user);
	}
	
	public void homePage(){
		hpGUI = new HomePageGUI(this,user);
	}
	
	
}
