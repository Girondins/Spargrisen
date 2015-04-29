package SpargrisenObjekt;

public class Tag {
	private String tagName;
	private String categoryName;
	private String userName;

	public Tag(String tagName, String categoryName){
		this.tagName = tagName;
		this.categoryName = categoryName;
	}
	
	public String getTagName(){
		return this.tagName;
	}
	
	public String getCategoryName(){
		return this.categoryName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return this.userName;
	}
	
}
