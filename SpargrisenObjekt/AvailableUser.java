package SpargrisenObjekt;

import java.io.Serializable;

public class AvailableUser implements Serializable, User{

	private static final long serialVersionUID = 1L;
	private String userName;
	private String passWord;
	private CategoryList categoryList;
	private long ID;
	private String firstName;
	private String lastName;
	private int toDo;

//	public User(String userName, String passWord) {
//		this.userName = userName;
//		this.passWord = passWord;
//		this.categoryList = new CategoryList();
//
//	}
	
	public AvailableUser(String userName){
		this.userName = userName;
		this.categoryList = new CategoryList(this);
	}

	public void setName(String UserName) {
		this.userName = UserName;
	}

	public String getName() {
		return userName;
	}

	public void setPassword(String passWord) {
		this.passWord = passWord;
	}

	public String getPassword() {
		return passWord;
	}

	public void setCategoryList(CategoryList categoryList) {
		this.categoryList = categoryList;
	}

	public CategoryList getCategoryList() {
		return categoryList;
	}

	public void setID(long id) {
		this.ID = id;
	}

	public long getID() {
		return ID;
	}
	
	public void setToDo(int task){
		this.toDo = task;
	}
	
	public int getToDo(){
		return this.toDo;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

}
