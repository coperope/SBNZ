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
    	
    	// All tags
    	Tag tag = new Tag();
    	tag.setName("High fuel consumption");
    	kieSession.insert(tag);
    	Tag tag2 = new Tag();
    	tag2.setName("Medium fuel consumption");
    	kieSession.insert(tag2);
    	Tag tag3 = new Tag();
    	tag3.setName("Low fuel consumption");
    	kieSession.insert(tag3);
    	Tag tag4 = new Tag();
    	tag4.setName("Sport engine power");
    	kieSession.insert(tag4);
    	Tag tag5 = new Tag();
    	tag5.setName("High engine power");
    	kieSession.insert(tag5);
    	Tag tag6 = new Tag();
    	tag6.setName("Medium engine power");
    	kieSession.insert(tag6);
    	Tag tag7 = new Tag();
    	tag7.setName("Low engine power");
    	kieSession.insert(tag7);
    	Tag tag8 = new Tag();
    	tag8.setName("Family car");
    	kieSession.insert(tag8);
    	Tag tag9 = new Tag();
    	tag9.setName("Small family car");
    	kieSession.insert(tag9);
    	Tag tag10 = new Tag();
    	tag10.setName("Big family car");
    	kieSession.insert(tag10);
    	Tag tag11 = new Tag();
    	tag11.setName("Long range");
    	kieSession.insert(tag11);
    	Tag tag12 = new Tag();
    	tag12.setName("Medium range");
    	kieSession.insert(tag12);
    	Tag tag13 = new Tag();
    	tag13.setName("Short range");
    	kieSession.insert(tag13);
    	Tag tag14 = new Tag();
    	tag14.setName("Unlimited mileage");
    	kieSession.insert(tag14);
    	Tag tag15 = new Tag();
    	tag15.setName("High mileage price");
    	kieSession.insert(tag15);
    	Tag tag16 = new Tag();
    	tag16.setName("Medium mileage price");
    	kieSession.insert(tag16);
    	Tag tag17 = new Tag();
    	tag17.setName("Low mileage price");
    	kieSession.insert(tag17);
    	Tag tag18 = new Tag();
    	tag18.setName("Automatic");
    	kieSession.insert(tag18);
    	Tag tag19 = new Tag();
    	tag19.setName("Manual");
    	kieSession.insert(tag19);

    }
    
    @Test
    public void testHighFuelConsumptionTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setFuelConsumption(13.1);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();

		System.out.println(vehicle);

		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("High fuel consumption")).findFirst().orElse(null);		
		assertEquals("High fuel consumption", test.getName());
    }
    
    @Test
    public void testMediumFuelConsumptionTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setFuelConsumption(10l);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Medium fuel consumption")).findFirst().orElse(null);		
		assertEquals("Medium fuel consumption", test.getName());
    }
    
    @Test
    public void testLowFuelConsumptionTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setFuelConsumption(8l);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Low fuel consumption")).findFirst().orElse(null);		
		assertEquals("Low fuel consumption", test.getName());
    }
    
    @Test
    public void testSportEnginePowerTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setPower(321);
    	vehicle.setWeight(1441);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Sport engine power")).findFirst().orElse(null);		
		assertEquals("Sport engine power", test.getName());
    }
    
    @Test
    public void testHighEnginePowerTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setPower(321);
    	vehicle.setWeight(1441);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("High engine power")).findFirst().orElse(null);		
		assertEquals("High engine power", test.getName());
    }
    
    @Test
    public void testSportAndHighEnginePowerTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setPower(321);
    	vehicle.setWeight(1441);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("High engine power")).findFirst().orElse(null);		
		assertEquals("High engine power", test.getName());
		test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Sport engine power")).findFirst().orElse(null);		
		assertEquals("Sport engine power", test.getName());
    }
    
    @Test
    public void testMediumEnginePowerTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setPower(66);
    	vehicle.setWeight(1030);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Medium engine power")).findFirst().orElse(null);		
		assertEquals("Medium engine power", test.getName());
    }
    
    @Test
    public void testLowEnginePowerTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setPower(47);
    	vehicle.setWeight(1080);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Low engine power")).findFirst().orElse(null);		
		assertEquals("Low engine power", test.getName());
    }
    
    @Test
    public void testSmallFamilyTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setSeatsNo(5);
    	vehicle.setPassengerAreaVolume(2500);
    	vehicle.setCargoVolume(300);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Small family car")).findFirst().orElse(null);		
		assertEquals("Small family car", test.getName());
		test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Family car")).findFirst().orElse(null);		
		assertEquals("Family car", test.getName());
    }
    
    @Test
    public void testBigFamilyTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setSeatsNo(5);
    	vehicle.setPassengerAreaVolume(3000);
    	vehicle.setCargoVolume(400);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Big family car")).findFirst().orElse(null);		
		assertEquals("Big family car", test.getName());
		test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Family car")).findFirst().orElse(null);		
		assertEquals("Family car", test.getName());    
	}
    
    @Test
    public void testLongRangeTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setFuelConsumption(5);
    	vehicle.setTankVolume(50);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Long range")).findFirst().orElse(null);		
		assertEquals("Long range", test.getName());
    }
    
    @Test
    public void testMediumRangeTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setFuelConsumption(7);
    	vehicle.setTankVolume(50);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Medium range")).findFirst().orElse(null);		
		assertEquals("Medium range", test.getName());
    }
    
    @Test
    public void testShortRangeTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setFuelConsumption(10);
    	vehicle.setTankVolume(40);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Short range")).findFirst().orElse(null);		
		assertEquals("Short range", test.getName());
    }
    
    @Test
    public void testUnlimitedMileageTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setMileageLimit(-1);
    	vehicle.setMileagePrice(0);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Unlimited mileage")).findFirst().orElse(null);		
		assertEquals("Unlimited mileage", test.getName());
    }
    
    @Test
    public void testHighMileagePriceTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setMileageLimit(100);
    	vehicle.setMileagePrice(1.3);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("High mileage price")).findFirst().orElse(null);		
		assertEquals("High mileage price", test.getName());
    }
    
    @Test
    public void testMediumMileagePriceTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setMileageLimit(100);
    	vehicle.setMileagePrice(0.9);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Medium mileage price")).findFirst().orElse(null);		
		assertEquals("Medium mileage price", test.getName());
    }
    
    @Test
    public void testLowMileagePriceTag() {
    	Vehicle vehicle = new Vehicle();
    	vehicle.setMileageLimit(100);
    	vehicle.setMileagePrice(0.4);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Low mileage price")).findFirst().orElse(null);		
		assertEquals("Low mileage price", test.getName());
    }
    
    @Test
    public void testAutomaticTransmissionTag() {
    	Transmission transmission = new Transmission();
    	transmission.setName("Automatic");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setTransmission(transmission);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Automatic")).findFirst().orElse(null);		
		assertEquals("Automatic", test.getName());
    }
    
    @Test
    public void testManualTransmissionTag() {
    	Transmission transmission = new Transmission();
    	transmission.setName("5 gears manual");
    	Vehicle vehicle = new Vehicle();
    	vehicle.setTransmission(transmission);
    	vehicle.setTags(new ArrayList<Tag>());
    	
    	kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		System.out.println(vehicle);
		Tag test = vehicle.getTags().stream().filter(tag -> tag.getName().equals("Manual")).findFirst().orElse(null);		
		assertEquals("Manual", test.getName());
    }
}
