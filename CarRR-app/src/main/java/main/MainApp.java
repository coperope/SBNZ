package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import main.facts.Category;
import main.facts.Customer;
import main.facts.CustomerPreferences;
import main.facts.ExtraFeatures;
import main.facts.Recommendations;
import main.facts.RentalHistory;
import main.facts.SearchHistory;
import main.facts.Tag;
import main.facts.Vehicle;
import main.repository.BrandRepo;
import main.repository.CategoryRepo;
import main.repository.CustomerPreferencesRepo;
import main.repository.CustomerRepo;
import main.repository.RecommendationsRepo;
import main.repository.RentalHistoryRepo;
import main.repository.SearchHistoryRepo;
import main.repository.TagRepo;
import main.repository.UserRepo;
import main.repository.VehicleRepo;

@SpringBootApplication
public class MainApp {

	private static Logger log = LoggerFactory.getLogger(MainApp.class);
//	public static KieSession taggingAndCategorisation;
	//public static EntryPoint eventsEntryPoint;
    public static KieSession recommendationSession;
    public static String kjarPath;
    
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MainApp.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);

		StringBuilder sb = new StringBuilder("Application beans:\n");
		for (String beanName : beanNames) {
			sb.append(beanName + "\n");
		}
		log.info(sb.toString());
		
		kjarPath = args[0];
		System.out.println("Kjar path: " + kjarPath);
	}
	
	@Autowired
	CategoryRepo categoryRepo;
    @Autowired
    TagRepo tagRepo;
    @Autowired 
    VehicleRepo vehicleRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    SearchHistoryRepo searchHistoryRepo;
    @Autowired
    RentalHistoryRepo rentalHistoryRepo;
    @Autowired
    CustomerPreferencesRepo customerPreferencesRepo;
    @Autowired
    RecommendationsRepo recommendationsRepo;
    @Autowired
    BrandRepo brandRepo;
    
	@PostConstruct
    public void startSessions() { 
		for(Customer customer: customerRepo.findAll()) {
			SearchHistory s = searchHistoryRepo.save(new SearchHistory());
			RentalHistory rh = rentalHistoryRepo.save(new RentalHistory());
			CustomerPreferences cp = customerPreferencesRepo.save(new CustomerPreferences());
			Recommendations r = recommendationsRepo.save(new Recommendations());

			customer.setSearchHistory(s);
			customer.setRecommendations(r);
			customer.setRentalHistory(rh);
			customer.setPreferences(cp);

			customerRepo.save(customer);
		}
		
		
		KieServices ks = KieServices.Factory.get();
	    KieContainer kieContainer = ks.getKieClasspathContainer();
		KieSession taggingAndCategorisation = kieContainer.newKieSession("categorisation_tagging_session");
		
		List<Category> categories = categoryRepo.findAll();
	    List<Tag> tags = tagRepo.findAll();
	    List<Vehicle> vehicles = vehicleRepo.findAll();
	    
		for (Category c: categories) {
			taggingAndCategorisation.insert(c);
		}
		for (Tag t: tags) {
			taggingAndCategorisation.insert(t);
		}
		for (Vehicle v: vehicles) {
			v.setTags(new ArrayList<Tag>());
	        v.setCategories(new ArrayList<Category>());
	        v.setFeatures(new ArrayList<ExtraFeatures>());
			taggingAndCategorisation.insert(v);
		}
		
		taggingAndCategorisation.fireAllRules();
		taggingAndCategorisation.dispose();
		
		for (Vehicle v: vehicles) {
			vehicleRepo.save(v);
		}

		recommendationSession = kieContainer.newKieSession("recommendation_rules_session");
		recommendationSession.getAgenda().getAgendaGroup("events-group").setFocus();
		//eventsEntryPoint = recommendationSession.getEntryPoint("events-entry");
		recommendationSession.setGlobal("customerRepository", customerRepo);
		recommendationSession.setGlobal("vehicleRepo", vehicleRepo);
		recommendationSession.setGlobal("recommendationsRepo", recommendationsRepo);
		recommendationSession.setGlobal("userRepository", userRepo);


		recommendationSession.insert(customerRepo);
        new Thread() {
            @Override
            public void run() {
            	recommendationSession.fireUntilHalt();
            }
        }.start();
    }

	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("sbnz.integracija", "CarRR-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10000);
		

//		taggingAndCategorisation = kContainer.newKieSession("categorisation_tagging_session");
		
		return kContainer;
	}

	/*
	 * KieServices ks = KieServices.Factory.get(); KieContainer kContainer =
	 * ks.newKieContainer(ks.newReleaseId("drools-spring-v2",
	 * "drools-spring-v2-kjar", "0.0.1-SNAPSHOT")); KieScanner kScanner =
	 * ks.newKieScanner(kContainer); kScanner.start(10_000); KieSession kSession =
	 * kContainer.newKieSession();
	 */

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@PreDestroy
	public void destroy() {
		recommendationSession.halt();
		recommendationSession.destroy();
	}
}
