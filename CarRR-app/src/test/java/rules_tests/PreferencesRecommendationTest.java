package rules_tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import main.facts.Brand;
import main.facts.CarModel;
import main.facts.Category;
import main.facts.Customer;
import main.facts.CustomerPreferences;
import main.facts.Fuel;
import main.facts.PreferencesRecommendations;
import main.facts.Tag;
import main.facts.Transmission;
import main.facts.Vehicle;

public class PreferencesRecommendationTest {

	protected final String ksessionName = "recommendation_rules_session";
    private KieServices ks;
    private KieContainer kieContainer;
    private KieSession kieSession;
    
    
    @Before
    public void init() {
    	ks = KieServices.Factory.get();
    	kieContainer = ks.getKieClasspathContainer();
    	kieSession = kieContainer.newKieSession(ksessionName);
    }
    
    @Test
    public void testPreferencesRecommendation() {
    	
    	
    	Vehicle vehicle = new Vehicle();
		vehicle.setId(1L);
			Brand brand = new Brand();
			brand.setId(1L);
			brand.setName("Audi");
		vehicle.setBrand(brand);
			Fuel fuel = new Fuel();
			fuel.setId(1L);
			fuel.setName("Gasoline");
		vehicle.setFuel(fuel);
			CarModel model = new CarModel();
			model.setBrand(brand);
			model.setId(1L);
			model.setName("R8");
		vehicle.setModel(model);
			Transmission transmission = new Transmission();
			transmission.setId(1L);
			transmission.setName("Automatic");
		vehicle.setTransmission(transmission);
			List<Category> categories = new ArrayList<Category>();
				Category sportCategory = new Category();
				sportCategory.setId(1L);
				sportCategory.setName("Sport");
			categories.add(sportCategory);
				Category luxaryCategory = new Category();
				luxaryCategory.setId(2L);
				luxaryCategory.setName("Luxury");
			categories.add(luxaryCategory);
		vehicle.setCategories(categories);
			List<Tag> tags = new ArrayList<Tag>();
			Tag tag = new Tag();
			tag.setId(1L);
			tag.setName("Super fast");
		vehicle.setTags(tags);
		vehicle.setAc(true);
		vehicle.setDoorNo(2);
		vehicle.setFuelConsumption(18);
		vehicle.setSeatsNo(2);
		vehicle.setMileageLimit(300);

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setId(2L);
			Brand brand2 = new Brand();
			brand.setId(2L);
			brand.setName("Audi");
		vehicle2.setBrand(brand2);
			Fuel fuel2 = new Fuel();
			fuel2.setId(2L);
			fuel2.setName("Diesel");
		vehicle2.setFuel(fuel2);
			CarModel model2 = new CarModel();
			model2.setBrand(brand);
			model2.setId(2L);
			model2.setName("A3");
		vehicle2.setModel(model2);
			Transmission transmission2 = new Transmission();
			transmission2.setId(1L);
			transmission2.setName("Manual");
		vehicle2.setTransmission(transmission2);
			List<Category> categories2 = new ArrayList<Category>();
				Category compact = new Category();
				compact.setId(1L);
				compact.setName("Compact");
				Category sport = new Category();
				sport.setId(1L);
				sport.setName("Sport");
			categories2.add(compact);
			categories2.add(sport);
		vehicle2.setCategories(categories2);
			List<Tag> tags2 = new ArrayList<Tag>();
			Tag tag2 = new Tag();
	    	tag2.setName("Medium fuel consumption");
	    	tags2.add(tag2);
		vehicle2.setTags(tags2);
		vehicle2.setAc(true);
		vehicle2.setDoorNo(4);
		vehicle2.setFuelConsumption(12.1);
		vehicle2.setSeatsNo(4);
		vehicle2.setMileageLimit(-1);

    	CustomerPreferences preferences = new CustomerPreferences();
    	preferences.getBrandList().add(brand);
		preferences.setDoorNo(4);
		preferences.setSeatsNo(4);
    	preferences.getCategoryList().add(sportCategory);
    	preferences.getCategoryList().add(compact);

    	preferences.getFuelList().add(fuel2);
    	preferences.getTransmissionList().add(transmission);
    	preferences.getTagList().add(tag);
    	preferences.setFuelConsumption(12.2);
    	preferences.setFuelConsumption(12);
    	preferences.setMileageLimit(-1);

    	Customer customer = new Customer();
    	customer.setPreferences(preferences);
    	
    	PreferencesRecommendations recommendations = new PreferencesRecommendations();
    	
    	kieSession.insert(customer);
    	kieSession.insert(recommendations);
    	kieSession.insert(vehicle);
    	kieSession.insert(vehicle2);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(recommendations);

		LinkedHashMap<Vehicle, Integer> sortedMap = 
				recommendations.getMap().entrySet().stream()
			    .sorted(Entry.<Vehicle, Integer>comparingByValue().reversed())
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println(sortedMap);
		assertEquals(new Long(2), ( (Vehicle)sortedMap.keySet().toArray()[0]).getId());
    }
}
