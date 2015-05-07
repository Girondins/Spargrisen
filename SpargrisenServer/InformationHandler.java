package SpargrisenServer;

import java.io.IOException;

import SpargrisenServer.Driver;
import SpargrisenObjekt.Category;
import SpargrisenObjekt.CategoryList;
import SpargrisenObjekt.RegisterUser;
import SpargrisenObjekt.AvailableUser;
import SpargrisenObjekt.Tag;

public class InformationHandler {
	private AvailableUser user;
	
	
	//DONE
	public AvailableUser retriveUser(AvailableUser user) throws IOException {
		String userName;
		CategoryList catList = null;
		userName = user.getName();
		user.setCategoryList(Driver.retriveUser(userName, catList));
		return this.user;
	}
	
	//DONE
	public boolean doesUserExist(AvailableUser user) {
		String userName;
		char[] password;
		userName = user.getName();
		password = user.getPassword();
		if(Driver.isUserFree(userName)==true){
			System.out.println("Username is available...");
		}else{
			System.out.println("username is taken...");
		}
		return Driver.isUserFree(userName);

	}
	//DONE
	public AvailableUser registerUser(RegisterUser RegisterUser) throws IOException {
		String userName;
		char[] password;
		userName = RegisterUser.getName();
		password = RegisterUser.getPassword();
		user = new AvailableUser(userName);
		Driver.createNewUser(userName, password);
		return user;

	}
	//DONE
	public AvailableUser addCategory(Category category){
		String categoryName,userName, categoryID;
		int limit;
		categoryName = category.getCategoryName();
		userName = category.getUser();
		limit = category.getBudgetLimit(); //Budgetgräns
		Driver.addCategory(categoryName, userName, limit);
		
		return user;
	}
	//DONE
	public AvailableUser addTag(Tag tag){
		String tagName,categoryName,userName;
		userName = tag.getUserName();
		tagName = tag.getTagName();
		if(tag.getCategoryName()==null){
			categoryName = "Diverse";
		}else{
		categoryName = tag.getCategoryName();
		}
		Driver.addTag(userName, tagName, categoryName);
		
		return user;
	}
	//DONE
	public AvailableUser changeBudgetLimit(Category category){
		String categoryName,userName;
		int newLimit;
		categoryName = category.getCategoryName();
		userName = category.getUser();
		newLimit = category.getBudgetLimit();
		
		Driver.changeBudgetLimit(categoryName, userName, newLimit);
		
		return user;
	}
	//DONE
	public AvailableUser editUser(AvailableUser user){
		String userName, changeTo;
		char[] newPassword;
		userName = user.getName();
		changeTo = user.getChangeName();
		newPassword = user.getPassword();
		Driver.editUser(userName, changeTo, newPassword);
		return user;
	}

}
