package Spara;

import java.io.IOException;

import SpargrisenObjekt.Category;
import SpargrisenObjekt.RegisterUser;
import SpargrisenObjekt.AvailableUser;
import SpargrisenObjekt.Tag;
import SpargrisenServer.Driver;

public class InformationHandler {
	private AvailableUser user;
	private Driver driver = new Driver();
	
	public AvailableUser retriveUser(AvailableUser user) throws IOException {
		String userName;
		userName = user.getName();
		
		
		user.setToDo(1);
		return this.user;
		
	}

	public boolean doesUserExist(AvailableUser user) {
		String userName;
		char[] password;
		userName = user.getName();
		password = user.getPassword();
		
	
		
		// JODY KOD med DATABAS KOLLA OCH JÄMNFÖR I DATABAS IFALL MATCH
		// return True ifall stämmer annars return false
		
		
		return true;

	}

	public AvailableUser registerUser(RegisterUser RegisterUser) throws IOException {
		String userName;
		char[] password;
		userName = RegisterUser.getName();
		password = RegisterUser.getPassword();
		user = new AvailableUser(userName);
		user.setToDo(2);
		//JODY KOD med DATABAS AV SKAPANDE AV NY ANVÄNDAREN
		
		return user;

	}
	
	public AvailableUser addCategory(Category category){
		String categoryName,userName;
		int limit;
		categoryName = category.getCategoryName();
		userName = category.getUser();
		limit = category.getBudgetLimit(); //Budgetgräns
		
		//JODY KOD MED DATABAS, SKAPA NY CATEGORY I TABELL
		
//		user.getCategoryList().addCategory(category);
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
	
	public AvailableUser addTag(Tag tag){
		String tagName,categoryName,userName;
		userName = tag.getUserName();
		tagName = tag.getTagName();
		categoryName = tag.getCategoryName();
		
		//JODY KOD MED DATABAS DÄR MAN LÄGGER TILL TAG I CATEGORY
		
		return user;
	}

}
