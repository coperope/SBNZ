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
import java.util.List;

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
        Category cat6 = new Category();
        cat6.setName("Luxury");
        Category cat7 = new Category();
        cat7.setName("Premium");
        Category cat8 = new Category();
        cat8.setName("Sport");
        Category cat9 = new Category();
        cat9.setName("Coupe");
        Category cat10 = new Category();
        cat10.setName("Passenger van");
        Category cat11 = new Category();
        cat11.setName("Pick up");
        kieSession.insert(cat);
        kieSession.insert(cat2);
        kieSession.insert(cat3);
        kieSession.insert(cat4);
        kieSession.insert(cat5);
        kieSession.insert(cat6);
        kieSession.insert(cat7);
        kieSession.insert(cat8);
        kieSession.insert(cat9);
        kieSession.insert(cat10);
        kieSession.insert(cat11);
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
        vehicle.setLength(315);
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
        vehicle.setPassengerAreaVolume(2800);
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
        vehicle.setFuelConsumption(11);
        vehicle.setSeatsNo(5);
        vehicle.setDoorNo(4);
        vehicle.setCargoVolume(500);
        vehicle.setPassengerAreaVolume(3000);
        vehicle.setLength(451);
        vehicle.setWidth(200);
        vehicle.setHeight(171);
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

    @Test
    public void testLuxuryCategory() {
        Vehicle vehicle = new Vehicle();
        vehicle.setFuelConsumption(13);
        vehicle.setSeatsNo(5);
        vehicle.setDoorNo(4);
        vehicle.setCargoVolume(500);
        vehicle.setPassengerAreaVolume(3000);
        vehicle.setLength(451);
        vehicle.setWidth(200);
        vehicle.setHeight(171);
        vehicle.setPricePerDay(360);
        vehicle.setCategories(new ArrayList<Category>());
        List<ExtraFeatures> features = new ArrayList<ExtraFeatures>();
        features.add(new ExtraFeatures(1l, "Parking sensors"));
        vehicle.setFeatures(features);

        kieSession.insert(vehicle);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(vehicle);
        Category test = vehicle.getCategories().stream().filter(category -> category.getName().equals("Luxury")).findFirst().orElse(null);
        assertEquals("Luxury", test.getName());
    }

    @Test
    public void testPremiumCategory() {
        Vehicle vehicle = new Vehicle();
        vehicle.setFuelConsumption(13);
        vehicle.setSeatsNo(5);
        vehicle.setDoorNo(4);
        vehicle.setCargoVolume(500);
        vehicle.setPassengerAreaVolume(3000);
        vehicle.setLength(451);
        vehicle.setWidth(200);
        vehicle.setHeight(171);
        vehicle.setPricePerDay(100);
        vehicle.setCategories(new ArrayList<Category>());
        List<ExtraFeatures> features = new ArrayList<ExtraFeatures>();
        features.add(new ExtraFeatures(1l, "Parking sensors"));
        features.add(new ExtraFeatures(1l, "Rear camera"));
        features.add(new ExtraFeatures(1l, "Cruise control"));
        vehicle.setFeatures(features);

        kieSession.insert(vehicle);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(vehicle);
        Category test = vehicle.getCategories().stream().filter(category -> category.getName().equals("Premium")).findFirst().orElse(null);
        assertEquals("Premium", test.getName());
    }

    @Test
    public void testSportCategory() {
        Vehicle vehicle = new Vehicle();
        vehicle.setFuelConsumption(13);
        vehicle.setSeatsNo(4);
        vehicle.setDoorNo(2);
        vehicle.setCargoVolume(200);
        vehicle.setPassengerAreaVolume(2000);
        vehicle.setLength(420);
        vehicle.setWidth(186);
        vehicle.setHeight(125);
        vehicle.setPricePerDay(100);
        vehicle.setPower(321);
        vehicle.setWeight(1441);
        vehicle.setCategories(new ArrayList<Category>());
        List<ExtraFeatures> features = new ArrayList<ExtraFeatures>();
        features.add(new ExtraFeatures(1l, "Parking sensors"));
        features.add(new ExtraFeatures(1l, "Rear camera"));
        features.add(new ExtraFeatures(1l, "Cruise control"));
        vehicle.setFeatures(features);

        kieSession.insert(vehicle);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(vehicle);
        Category test = vehicle.getCategories().stream().filter(category -> category.getName().equals("Sport")).findFirst().orElse(null);
        assertEquals("Sport", test.getName());
    }

    @Test
    public void testCoupeCategory() {
        Vehicle vehicle = new Vehicle();
        vehicle.setFuelConsumption(11);
        vehicle.setSeatsNo(2);
        vehicle.setDoorNo(2);
        vehicle.setCargoVolume(180);
        vehicle.setPassengerAreaVolume(1500);
        vehicle.setLength(420);
        vehicle.setWidth(186);
        vehicle.setHeight(140);
        vehicle.setPricePerDay(100);
        vehicle.setPower(100);
        vehicle.setWeight(2000);
        vehicle.setCategories(new ArrayList<Category>());
        List<ExtraFeatures> features = new ArrayList<ExtraFeatures>();
        vehicle.setFeatures(features);

        kieSession.insert(vehicle);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(vehicle);
        Category test = vehicle.getCategories().stream().filter(category -> category.getName().equals("Coupe")).findFirst().orElse(null);
        assertEquals("Coupe", test.getName());
    }

    @Test
    public void testPassengerVanCategory() {
        Vehicle vehicle = new Vehicle();
        vehicle.setFuelConsumption(16);
        vehicle.setSeatsNo(12);
        vehicle.setDoorNo(4);
        vehicle.setCargoVolume(500);
        vehicle.setPassengerAreaVolume(4600);
        vehicle.setLength(560);
        vehicle.setWidth(200);
        vehicle.setHeight(202);
        vehicle.setPricePerDay(150);
        vehicle.setPower(500);
        vehicle.setWeight(4500);
        vehicle.setCategories(new ArrayList<Category>());
        List<ExtraFeatures> features = new ArrayList<ExtraFeatures>();
        vehicle.setFeatures(features);

        kieSession.insert(vehicle);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(vehicle);
        Category test = vehicle.getCategories().stream().filter(category -> category.getName().equals("Passenger van")).findFirst().orElse(null);
        assertEquals("Passenger van", test.getName());
    }

    @Test
    public void testPickUpCategory() {
        Vehicle vehicle = new Vehicle();
        vehicle.setFuelConsumption(15.5);
        vehicle.setSeatsNo(5);
        vehicle.setDoorNo(4);
        vehicle.setCargoVolume(2200);
        vehicle.setPassengerAreaVolume(1500);
        vehicle.setLength(570);
        vehicle.setWidth(220);
        vehicle.setHeight(203);
        vehicle.setPricePerDay(130);
        vehicle.setPower(200);
        vehicle.setWeight(2500);
        vehicle.setCategories(new ArrayList<Category>());
        List<ExtraFeatures> features = new ArrayList<ExtraFeatures>();
        vehicle.setFeatures(features);

        kieSession.insert(vehicle);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(vehicle);
        Category test = vehicle.getCategories().stream().filter(category -> category.getName().equals("Pick up")).findFirst().orElse(null);
        assertEquals("Pick up", test.getName());
    }
}
