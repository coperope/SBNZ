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
	}

}
