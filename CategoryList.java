package Spargrisen;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryList implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Category> categoryList;
	private int count = -1;

	public synchronized Category getCategory() {
		if (count == categoryList.size()) {
			count = -1;
			return null;
		} else
			count++;
		return categoryList.get(count);
	}

	public synchronized void addCategory(Category category) {
		categoryList.add(category);
	}

	public synchronized void removeCategory(String categoryName){
		for(int i=0; i<categoryList.size(); i++){
			if(categoryName==categoryList.get(i).getCategoryName())){
				categoryList.remove(i);
			}
		}
	}

	public boolean isEmpty() {
		return categoryList.isEmpty();
	}

	public int size() {
		return categoryList.size();
	}
}
