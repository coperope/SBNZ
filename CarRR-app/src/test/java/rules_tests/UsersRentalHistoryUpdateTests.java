package rules_tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import main.dto.SearchDTO;
import main.facts.Brand;
import main.facts.CarModel;
import main.facts.Category;
import main.facts.Customer;
import main.facts.Fuel;
import main.facts.Rental;
import main.facts.RentalHistory;
import main.facts.SearchHistory;
import main.facts.Tag;
import main.facts.Transmission;
import main.facts.Vehicle;

public class UsersRentalHistoryUpdateTests {

	protected final String ksessionName = "categorisation_tagging_session";
	private KieServices ks;
	private KieContainer kieContainer;
	private KieSession kieSession;
	
	private Customer customer;
	private Vehicle vehicle;

	@Before
	public void init() {
		ks = KieServices.Factory.get();
		kieContainer = ks.getKieClasspathContainer();
		kieSession = kieContainer.newKieSession("rental_history_update_session");
		
		this.customer = new Customer();
		this.customer.setId(1L);
		this.customer.setEmail("email@email.com");
		this.customer.setPassword("123");
		this.customer.setName("Petar");
		this.customer.setSurname("Petrovic");
		this.customer.setPhoneNumber("+123123123");
			RentalHistory rentalHistory = new RentalHistory();
			rentalHistory.setId(1L);
		this.customer.setRentalHistory(rentalHistory);
			SearchHistory searchHistory = new SearchHistory();
			searchHistory.setId(1L);
		this.customer.setSearchHistory(searchHistory);
		
		this.vehicle = new Vehicle();
		this.vehicle.setId(1L);
			Brand brand = new Brand();
			brand.setId(1L);
			brand.setName("Audi");
		this.vehicle.setBrand(brand);
			Fuel fuel = new Fuel();
			fuel.setId(1L);
			fuel.setName("Gasoline");
		this.vehicle.setFuel(fuel);
			CarModel model = new CarModel();
			model.setBrand(brand);
			model.setId(1L);
			model.setName("R8");
		this.vehicle.setModel(model);
			Transmission transmission = new Transmission();
			transmission.setId(1L);
			transmission.setName("Automatic");
		this.vehicle.setTransmission(transmission);
			List<Category> categories = new ArrayList<Category>();
				Category sportCategory = new Category();
				sportCategory.setId(1L);
				sportCategory.setName("Sport");
			categories.add(sportCategory);
				Category luxaryCategory = new Category();
				luxaryCategory.setId(2L);
				luxaryCategory.setName("Luxary");
			categories.add(luxaryCategory);
		this.vehicle.setCategories(categories);
			List<Tag> tags = new ArrayList<Tag>();
			Tag tag = new Tag();
			tag.setId(1L);
			tag.setName("Super fast");
		this.vehicle.setTags(tags);
		this.vehicle.setAc(true);
		this.vehicle.setDoorNo(2);
		this.vehicle.setFuelConsumption(18L);
		this.vehicle.setSeatsNo(2);
	}

	@Test
	public void testEmptyRentalHistory() {		
		
		Rental rental = new Rental();
		rental.setId(1L);
		rental.setCustomer(this.customer);
		rental.setVehicle(this.vehicle);
		

		kieSession.insert(rental);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		for (Brand b : customer.getRentalHistory().getBrands().keySet()) {
			System.out.println(b.getName());
		}

		assertEquals(1, rental.getCustomer().getRentalHistory().getBrands().size());
	}
	
	@Test
	public void testSearchHistory() {		
		
		SearchDTO searchParam = new SearchDTO();
			List<Brand> brands = new ArrayList<Brand>();
			Brand brand = new Brand();
			brand.setId(1L);
			brand.setName("Audi");
			brands.add(brand);
		searchParam.setBrands(brands);
		//searchParam.setCustomer(this.customer);

		kieSession.insert(this.customer);
		kieSession.insert(searchParam);
		kieSession.fireAllRules();
		kieSession.dispose();

		assertEquals(1, this.customer.getSearchHistory().getBrands().size());
	}
}
