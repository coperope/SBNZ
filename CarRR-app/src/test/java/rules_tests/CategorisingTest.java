package rules_tests;

import main.facts.Category;
import main.facts.ExtraFeatures;
import main.facts.Tag;
import main.facts.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.AbstractList;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CategorisingTest {
    protected final String ksessionName = "categorisation_tagging_session";
    private KieServices ks;
    private KieContainer kieContainer;
    private KieSession kieSession;

    @Before
    public void init() {
        ks = KieServices.Factory.get();
        kieContainer = ks.getKieClasspathContainer();
        kieSession = kieContainer.newKieSession("categorisation_tagging_session");
        Category cat = new Category();
        cat.setName("Economy");

        Category cat2 = new Category();
        cat2.setName("Compact");

        Category cat3 = new Category();
        cat3.setName("Intermediate");

        Category cat4 = new Category();
        cat4.setName("Standard");

        Category cat5 = new Category();
        cat5.setName("Full size");
        kieSession.insert(cat);
        kieSession.insert(cat2);
        kieSession.insert(cat3);
        kieSession.insert(cat4);
    }

    @Test
    public void testEconomyCategory() {
        Vehicle vehicle = new Vehicle();
        vehicle.setFuelConsumption(6.3);
        vehicle.setSeatsNo(3);
        vehicle.setDoorNo(4);
        vehicle.setCargoVolume(420);
        vehicle.setPassengerAreaVolume(1000);
        vehicle.setLength(395);
        vehicle.setWidth(182);
        vehicle.setHeight(158);
        vehicle.setPricePerDay(24);
        vehicle.setCategories(new ArrayList<Category>());
        vehicle.setFeatures(new ArrayList<ExtraFeatures>());

        kieSession.insert(vehicle);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(vehicle);
        Category test = vehicle.getCategories().stream().filter(category -> category.getName().equals("Economy")).findFirst().orElse(null);
        assertEquals("Economy", test.getName());
    }

    @Test
    public void testCompactCategory() {
        Vehicle vehicle = new Vehicle();
        vehicle.setFuelConsumption(8.2);
        vehicle.setSeatsNo(3);
        vehicle.setDoorNo(4);
        vehicle.setCargoVolume(318);
        vehicle.setPassengerAreaVolume(2000);
        vehicle.setLength(395);
        vehicle.setWidth(182);
        vehicle.setHeight(158);
        vehicle.setPricePerDay(30);
        vehicle.setCategories(new ArrayList<Category>());
        vehicle.setFeatures(new ArrayList<ExtraFeatures>());

        kieSession.insert(vehicle);
        int num = kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(vehicle);
        System.out.println("Rules fired = " + num);
        Category test = vehicle.getCategories().stream().filter(category -> category.getName().equals("Compact")).findFirst().orElse(null);
        assertEquals("Compact", test.getName());
    }

    @Test
    public void testStandardCategory() {
        Vehicle vehicle = new Vehicle();
        vehicle.setFuelConsumption(10.14);
        vehicle.setSeatsNo(5);
        vehicle.setDoorNo(4);
        vehicle.setCargoVolume(500);
        vehicle.setPassengerAreaVolume(3000);
        vehicle.setLength(451);
        vehicle.setWidth(200);
        vehicle.setHeight(162);
        vehicle.setPricePerDay(72);
        vehicle.setCategories(new ArrayList<Category>());
        vehicle.setFeatures(new ArrayList<ExtraFeatures>());

        kieSession.insert(vehicle);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(vehicle);
        Category test = vehicle.getCategories().stream().filter(category -> category.getName().equals("Standard")).findFirst().orElse(null);
        assertEquals("Standard", test.getName());
    }

    @Test
    public void testIntermediateCategory() {
        Vehicle vehicle = new Vehicle();
        vehicle.setFuelConsumption(10.27);
        vehicle.setSeatsNo(5);
        vehicle.setDoorNo(4);
        vehicle.setCargoVolume(500);
        vehicle.setPassengerAreaVolume(3000);
        vehicle.setLength(451);
        vehicle.setWidth(200);
        vehicle.setHeight(162);
        vehicle.setPricePerDay(50);
        vehicle.setCategories(new ArrayList<Category>());
        vehicle.setFeatures(new ArrayList<ExtraFeatures>());

        kieSession.insert(vehicle);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(vehicle);
        Category test = vehicle.getCategories().stream().filter(category -> category.getName().equals("Intermediate")).findFirst().orElse(null);
        assertEquals("Intermediate", test.getName());
    }
    @Test
    public void testFullSizeCategory() {
        Vehicle vehicle = new Vehicle();
        vehicle.setFuelConsumption(10.85);
        vehicle.setSeatsNo(5);
        vehicle.setDoorNo(4);
        vehicle.setCargoVolume(500);
        vehicle.setPassengerAreaVolume(3000);
        vehicle.setLength(451);
        vehicle.setWidth(200);
        vehicle.setHeight(162);
        vehicle.setPricePerDay(50);
        vehicle.setCategories(new ArrayList<Category>());
        vehicle.setFeatures(new ArrayList<ExtraFeatures>());

        kieSession.insert(vehicle);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(vehicle);
        Category test = vehicle.getCategories().stream().filter(category -> category.getName().equals("Full size")).findFirst().orElse(null);
        assertEquals("Full size", test.getName());
    }
}
