package Spargrisen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class CategoryList implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Category> categoryList = new ArrayList<Category>();
	private int count = -1;
	private final Category other = new Category("Other", 0, new LinkedList<String>());;

	public CategoryList() {
		Category food = new Category("Food", 2000, new LinkedList<String>());
		Category entertainment = new Category("Entertainment", 1000, new LinkedList<String>());
		categoryList.add(food);
		categoryList.add(entertainment);
		categoryList.add(other);

	}
	public Category getCategoryIndex(int i){
		return categoryList.get(i);
	}

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
