package main.dto;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import main.facts.Category;
import main.facts.ExtraFeatures;
import main.facts.Tag;
import main.facts.Vehicle;

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
	int features;
	String featureOperation;
	String categoryName;
	Boolean categorizeExistingVehicles;
	
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

	public int getFeatures() {
		return features;
	}

	public void setFeatures(int features) {
		this.features = features;
	}

	public String getFeatureOperation() {
		return featureOperation;
	}

	public void setFeatureOperation(String featureOperation) {
		this.featureOperation = featureOperation;
	}

	public Boolean getCategorizeExistingVehicles() {
		return categorizeExistingVehicles;
	}

	public void setCategorizeExistingVehicles(Boolean categorizeExistingVehicles) {
		this.categorizeExistingVehicles = categorizeExistingVehicles;
	}
}
