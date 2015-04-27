package SpargrisenObjekt;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5728719704629685694L;
	private String categoryName;
	private int budgetLimit;
	private float currentSum;
	private String userID;
	private ArrayList<String> purchaseList = new ArrayList<String>();
	private LinkedList<String> tagList = new LinkedList<String>();
	private ArrayList<Float> sumList = new ArrayList <Float>();
	
	public Category(String categoryName, int budgetLimit, User user, LinkedList<String> tagList){
		this.userID = user.getName();
		this.categoryName = categoryName;
		this.budgetLimit = budgetLimit;
		this.tagList = tagList;
	}
	
	public String getUser(){
		return this.userID;
	}
	
	public void setCategoryName(String newCategoryName){
		this.categoryName = newCategoryName;
	}
	
	public String getCategoryName(){
		return this.categoryName;
	}
	
	public void setBudgetLimit(int newBudgetLimit){
		this.budgetLimit = newBudgetLimit;
	}
	
	public int getBudgetLimit(){
		return this.budgetLimit;
	}
	
	public void setCurrentSum(){
		String[] parts = null;
		for(int i=0; i<purchaseList.size(); i++){
			parts = purchaseList.get(i).split(";");
		}
		for(int j=0; j<parts.length; j++){
			if(parts[j].contains("Cost: ")){
				String cost = parts[j].substring(6,parts[j].length());
			sumList.add(Float.parseFloat(cost));
		}}
	}
	
	public float getCurrentSum(){
		currentSum=0;
		for(int i=0 ; i<sumList.size(); i++){
			currentSum += sumList.get(i);
		}
		return currentSum;
	}
	
	public void addPurchase(String purchase){
		purchaseList.add(purchase);
		setCurrentSum();
	}
	
	public ArrayList<String> getPurchaseList(){
		return this.purchaseList;
	}
	
	public void addTag(String tag){
		tagList.add(tag);
	}
	
	public boolean checkTags(String tag){
		if(tagList.contains(tag)){
			return true;
		}
		else return false;
	}
	
	public LinkedList<String> getTagList(){
		return this.tagList;
	}
		

}
