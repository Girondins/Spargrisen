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
	
	
	//skapa en lista med category
	public AvailableUser retriveUser(AvailableUser user) throws IOException {
		String userName;
		CategoryList catlist;
		Category category;
		userName = user.getName();
		
		Driver.retriveUser(userName);
		
		category = new category(driver)
		
		user.setCategoryList(catlist);
	
		
		
		
		// JODY KOD med DATABAS HÄMTA VÄSENTLIG INFO FRÅN DATABAS OCH LAGRA I ETT USER OBJEKTET
		// SOM SKICKAS TILLBAKA, ANVÄND SETMETODER
		
		return this.user;
		
		
	}
	
	
	
	//DONE
	public boolean doesUserExist(AvailableUser user) {
		String userName;
		char[] password;
		userName = user.getName();
		password = user.getPassword();
		
		if(Driver.isUserFree(userName)==true){
			System.out.println("User name available...");
		}else{
			System.out.println("user name taken...");
		}
		
		return Driver.isUserFree(userName);

	}
	//Not DONE, bryt ner char array till String viceversa
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
	
	public AvailableUser changeBudgetLimit(Category category){
		String categoryName,userName;
		int newLimit;
		categoryName = category.getCategoryName();
		userName = category.getUser();
		newLimit = category.getBudgetLimit();
		
		// JODY KOD MED DATABAS DÄR MAN ÄNDRAR BUDGETLIMIT
		
		return user;
	}

}
