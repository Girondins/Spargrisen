package Spargrisen;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	private String categoryName;
	private int budgetLimit;
	private float currentSum;
	private ArrayList<String> purchaseList;
	private LinkedList<String> tagList;
	private ArrayList<Float> sumList;
	
	public Category(String categoryName, int budgetLimit, LinkedList<String> tagList){
		this.categoryName = categoryName;
		this.budgetLimit = budgetLimit;
		this.tagList = tagList;
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
			parts = purchaseList.get(i).split(",");
		}
		for(int j=0; j<parts.length; j++){
			if(parts[j].contains("Cost: ")){
				parts[j].substring(6,parts[j].length());
			sumList.add(Float.parseFloat(parts[j]));
		}}
	}
	
	public float getCurrentSum(){
		for(int i=0 ; i<sumList.size(); i++){
			currentSum =+ sumList.get(i);
		}
		return currentSum;
	}
	
	public void addPurchase(String purchase){
		purchaseList.add(purchase);
	}
	
	public ArrayList<String> getPurchaseList(){
		return this.purchaseList;
	}
	
	public void addTag(String tag){
		tagList.add(tag);
	}
		

}
