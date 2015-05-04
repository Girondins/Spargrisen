package SpargrisenObjekt;

public interface User {
	public void setName(String name);
	public String getName();
	public void setPassword(char[] password);
	public char[] getPassword();
	public void setCategoryList(CategoryList categoryList);
	public CategoryList getCategoryList();
	public void setID(long id);
	public long getID();
	public void setFirstName(String firstName);
	public String getFirstName();
	public void setLastName(String lastName);
	public String getLastName();
	public void setToDo(int task);
	public int getToDo();
	
}
