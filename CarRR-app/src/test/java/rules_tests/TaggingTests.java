package rules_tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import main.facts.Tag;
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
    

}
