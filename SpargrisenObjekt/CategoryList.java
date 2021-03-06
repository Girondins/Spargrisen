package SpargrisenObjekt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class CategoryList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2227755002117608428L;
	private ArrayList<Category> categoryList = new ArrayList<Category>();
	private int count = 0;
	private final Category other;

	public CategoryList(User user) {

		Category food = new Category("Food", 2000, user, new LinkedList<String>());
		Category entertainment = new Category("Entertainment", 1000,user, new LinkedList<String>());
		other = new Category("Other", 10000 ,user, new LinkedList<String>());
		categoryList.add(food);
		categoryList.add(entertainment);
		categoryList.add(other);

	}
	public Category getCategoryIndex(int i){
		return categoryList.get(i);
	}

	public synchronized Category getCategory() {
		if (count == categoryList.size()) {
			count = 0;
			return null;
		} else
			count++;
		return categoryList.get(count-1);
	}

	public synchronized void addCategory(Category category) {
		categoryList.add(category);
		Category swapOther = null;

		for (int i = 0; i < categoryList.size(); i++) {
			if (categoryList.get(i).getCategoryName()
					.equals(other.getCategoryName())) {
				swapOther = categoryList.get(i);
				categoryList.remove(categoryList.remove(i));
			}
		}
		categoryList.add(swapOther);

	}

	public synchronized void removeCategory(String categoryName) {
		for (int i = 0; i < categoryList.size(); i++) {
			if (categoryName == categoryList.get(i).getCategoryName()) {
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
