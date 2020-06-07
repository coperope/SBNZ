package main.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryRuleDTO {

	/* fuelConsumption < 8, length < 400, height < 160
	 *   {
	 *   	[ "fuelConsumption", "<", "8" ],
	 *   	[ "length", "<", "400" ],
	 *   	[ "height", "<", "160" ]
	 *   }
	 * 
	 */
	List<List<String>> conditions = new ArrayList<List<String>>();
	String categoryName;
	
	
	public CategoryRuleDTO() {
		
	}

	public List<List<String>> getConditions() {
		return conditions;
	}

	public void setConditions(List<List<String>> conditions) {
		this.conditions = conditions;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
