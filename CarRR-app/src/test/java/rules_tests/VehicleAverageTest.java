package rules_tests;

import main.MainApp;
import main.facts.Rental;
import main.facts.User;
import main.facts.Vehicle;
import main.repository.RentalRepo;
import main.repository.UserRepo;
import main.repository.VehicleRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { VehicleRepo.class, UserRepo.class, RentalRepo.class, MainApp.class })
@EnableConfigurationProperties
public class VehicleAverageTest {

    protected final String ksessionName = "recommendation_rules_session";
    private KieServices ks;
    private KieContainer kieContainer;
    private KieSession kieSession;

    @Autowired
    VehicleRepo vehicleRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RentalRepo rentalRepo;

    @Before
    public void init() {
        ks = KieServices.Factory.get();
        kieContainer = ks.getKieClasspathContainer();
        kieSession = kieContainer.newKieSession(ksessionName);
    }

    @Test
    public void vehiclesAboveAverageTest() {

        User owner = userRepo.findById(2L).orElse(null);
        List<Rental> rentals = rentalRepo.findAllByOwnerAndFinished(owner, true);
        for (Rental rent: rentals) {
            kieSession.insert(rent);
        }

        kieSession.insert(owner);
        kieSession.getAgenda().getAgendaGroup("topRentals").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
