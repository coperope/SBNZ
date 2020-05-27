package main;

import java.util.Arrays;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApp {

	private static Logger log = LoggerFactory.getLogger(MainApp.class);
//	public static KieSession taggingAndCategorisation;
	
    
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MainApp.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);

		StringBuilder sb = new StringBuilder("Application beans:\n");
		for (String beanName : beanNames) {
			sb.append(beanName + "\n");
		}
		log.info(sb.toString());
	}
	
//	@Autowired
//	CategoryRepo categoryRepo;
//
//    @Autowired
//    TagRepo tagRepo;
    
//	@PostConstruct
//    public void listen() { 
//		Timer timer = new Timer();
//	    TimerTask delayedThreadStartTask = new TimerTask() {
//	    	
//	    	@Override
//	        public void run() {
//	        	List<Category> categories = categoryRepo.findAll();
//	            List<Tag> tags = tagRepo.findAll();
//	            
//	        	for (Category c: categories) {
//	        		System.out.println(c);
//	        		taggingAndCategorisation.insert(c);
//				}
//				for (Tag t: tags) {
//					taggingAndCategorisation.insert(t);
//				}
//				taggingAndCategorisation.fireUntilHalt();
//	    	}
//	    };
//		
//	    timer.schedule(delayedThreadStartTask, 20 * 1000);
//    }

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
}
