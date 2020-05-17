package rules_tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import main.facts.Brand;
import main.facts.CarModel;
import main.facts.Category;
import main.facts.Customer;
import main.facts.Fuel;
import main.facts.Rental;
import main.facts.RentalHistory;
import main.facts.Tag;
import main.facts.Transmission;
import main.facts.Vehicle;

public class UsersRentalHistoryUpdateTests {

	protected final String ksessionName = "categorisation_tagging_session";
	private KieServices ks;
	private KieContainer kieContainer;
	private KieSession kieSession;

	@Before
	public void init() {
		ks = KieServices.Factory.get();
		kieContainer = ks.getKieClasspathContainer();
		kieSession = kieContainer.newKieSession("rental_history_update_session");
	}

	@Test
	public void testEmptyRentalHistory() {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setEmail("email@email.com");
		customer.setPassword("123");
		customer.setName("Petar");
		customer.setSurname("Petrovic");
		customer.setPhoneNumber("+123123123");
			RentalHistory rentalHistory = new RentalHistory();
			rentalHistory.setId(1L);
		customer.setRentalHistory(rentalHistory);
		
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
				luxaryCategory.setName("Luxary");
			categories.add(luxaryCategory);
		vehicle.setCategories(categories);
			List<Tag> tags = new ArrayList<Tag>();
			Tag tag = new Tag();
			tag.setId(1L);
			tag.setName("Super fast");
		vehicle.setTags(tags);
		vehicle.setAc(true);
		vehicle.setDoorNo(2);
		vehicle.setFuelConsumption(18L);
		vehicle.setSeatsNo(2);
		
		Rental rental = new Rental();
		rental.setId(1L);
		rental.setCustomer(customer);
		rental.setVehicle(vehicle);
		

		kieSession.insert(rental);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		for (Brand b : customer.getRentalHistory().getBrands().keySet()) {
			System.out.println(b.getName());
		}

		assertEquals(1, rental.getCustomer().getRentalHistory().getBrands().size());
	}
}
