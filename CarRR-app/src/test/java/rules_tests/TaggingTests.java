package rules_tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import main.facts.Tag;
import main.facts.Transmission;
import main.facts.Vehicle;

public class TaggingTests {
	
    protected final String ksessionName = "categorisation_tagging_session";
    private KieServices ks;
    private KieContainer kieContainer;
    private KieSession kieSession;
    
    @Before
    public void init() {
    	ks = KieServices.Factory.get();
    	kieContainer = ks.getKieClasspathContainer();
    	kieSession = kieContainer.newKieSession("categorisation_tagging_session");
    }
    
    @Test
    public void testHighFuelConsumptionTag() {
    	Tag tag = new Tag();
    	tag.setName("High fuel consumption");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setFuelConsumption(13l);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("High fuel consumption", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testMediumFuelConsumptionTag() {
    	Tag tag = new Tag();
    	tag.setName("Medium fuel consumption");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setFuelConsumption(10l);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("Medium fuel consumption", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testLowFuelConsumptionTag() {
    	Tag tag = new Tag();
    	tag.setName("Low fuel consumption");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setFuelConsumption(8l);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("Low fuel consumption", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testSportEnginePowerTag() {
    	Tag tag = new Tag();
    	tag.setName("Sport engine power");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setPower(321);
    	vehicle.setWeight(1441);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("Sport engine power", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testHighEnginePowerTag() {
    	Tag tag = new Tag();
    	tag.setName("High engine power");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setPower(321);
    	vehicle.setWeight(1441);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("High engine power", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testSportAndHighEnginePowerTag() {
    	Tag tag = new Tag();
    	tag.setName("Sport engine power");
    	Tag tag2 = new Tag();
    	tag2.setName("High engine power");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setPower(321);
    	vehicle.setWeight(1441);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(tag2);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(2, vehicle.getTags().size());
    }
    
    @Test
    public void testMediumEnginePowerTag() {
    	Tag tag = new Tag();
    	tag.setName("Medium engine power");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setPower(66);
    	vehicle.setWeight(1030);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("Medium engine power", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testLowEnginePowerTag() {
    	Tag tag = new Tag();
    	tag.setName("Low engine power");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setPower(47);
    	vehicle.setWeight(1080);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("Low engine power", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testSmallFamilyTag() {
    	Tag tag = new Tag();
    	tag.setName("Family car");
    	Tag tag2 = new Tag();
    	tag2.setName("Small family car");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setSeatsNo(5);
    	vehicle.setPassengerAreaVolume(2500);
    	vehicle.setCargoVolume(300);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(tag2);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(2, vehicle.getTags().size());
    }
    
    @Test
    public void testBigFamilyTag() {
    	Tag tag = new Tag();
    	tag.setName("Family car");
    	Tag tag2 = new Tag();
    	tag2.setName("Big family car");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setSeatsNo(5);
    	vehicle.setPassengerAreaVolume(3000);
    	vehicle.setCargoVolume(400);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(tag2);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(2, vehicle.getTags().size());
    }
    
    @Test
    public void testLongRangeTag() {
    	Tag tag = new Tag();
    	tag.setName("Long range");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setFuelConsumption(5);
    	vehicle.setTankVolume(50);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("Long range", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testMediumRangeTag() {
    	Tag tag = new Tag();
    	tag.setName("Medium range");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setFuelConsumption(7);
    	vehicle.setTankVolume(50);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("Medium range", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testShortRangeTag() {
    	Tag tag = new Tag();
    	tag.setName("Short range");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setFuelConsumption(10);
    	vehicle.setTankVolume(40);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("Short range", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testUnlimitedMileageTag() {
    	Tag tag = new Tag();
    	tag.setName("Unlimited mileage");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setMileageLimit(0);
    	vehicle.setMileagePrice(0);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("Unlimited mileage", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testHighMileagePriceTag() {
    	Tag tag = new Tag();
    	tag.setName("High mileage price");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setMileageLimit(100);
    	vehicle.setMileagePrice(1.3);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("High mileage price", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testMediumMileagePriceTag() {
    	Tag tag = new Tag();
    	tag.setName("Medium mileage price");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setMileageLimit(100);
    	vehicle.setMileagePrice(0.9);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("Medium mileage price", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testLowMileagePriceTag() {
    	Tag tag = new Tag();
    	tag.setName("Low mileage price");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setMileageLimit(100);
    	vehicle.setMileagePrice(0.4);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("Low mileage price", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testAutomaticTransmissionTag() {
    	Tag tag = new Tag();
    	tag.setName("Automatic");
    	Transmission transmission = new Transmission();
    	transmission.setName("Automatic");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setTransmission(transmission);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("Automatic", vehicle.getTags().get(0).getName());
    }
    
    @Test
    public void testManualTransmissionTag() {
    	Tag tag = new Tag();
    	tag.setName("Manual");
    	Transmission transmission = new Transmission();
    	transmission.setName("5 gears manual");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setTransmission(transmission);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(tag);
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		assertEquals(1, vehicle.getTags().size());
		assertEquals("Manual", vehicle.getTags().get(0).getName());
    }
}
