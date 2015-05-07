package SpargrisenObjekt;

import java.io.Serializable;

public class RegisterUser implements Serializable,User{

	private static final long serialVersionUID = 1L;
	private String userName;
	private char[] passWord;
	private CategoryList categoryList;
	private long ID;
	private String firstName;
	private String changeName;

//	public User(String userName, String passWord) {
//		this.userName = userName;
//		this.passWord = passWord;
//		this.categoryList = new CategoryList();
//
//	}
	
	public RegisterUser(String userName){
		this.userName = userName;
		this.categoryList = new CategoryList(this);
	}

	public void setName(String UserName) {
		this.userName = UserName;
	}

	public String getName() {
		return userName;
	}

	public void setPassword(char[] passWord) {
		this.passWord = passWord;
	}

	public char[] getPassword() {
		return passWord;
	}

	public void setCategoryList(CategoryList categoryList) {
		this.categoryList = categoryList;
	}

	public CategoryList getCategoryList() {
		return categoryList;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public long getID() {
		return ID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getChangeName() {
		return changeName;
	}

	@Override
	public void setToDo(int task) {
		
	}

	@Override
	public int getToDo() {
		return 0;
	}

}