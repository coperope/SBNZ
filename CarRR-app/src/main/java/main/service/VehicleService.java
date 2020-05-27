package main.service;

import main.dto.VehicleDTO;
import main.facts.Brand;
import main.facts.Category;
import main.facts.Tag;
import main.facts.Vehicle;
import main.repository.CarModelRepo;
import main.repository.CategoryRepo;
import main.repository.TagRepo;
import main.repository.VehicleRepo;
import org.junit.experimental.categories.Categories;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    protected final String ksessionName = "categorisation_tagging_session";
    private KieServices ks = KieServices.Factory.get();
    private KieContainer kieContainer = ks.getKieClasspathContainer();
    private KieSession kieSession;

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    TagRepo tagRepo;

    // Add a new vehicle and determine it's categories and tags.
    public Vehicle addVehicle(Vehicle vehicle) {
        kieSession = kieContainer.newKieSession("categorisation_tagging_session");
        List<Category> categories = categoryRepo.findAll();
        List<Tag> tags = tagRepo.findAll();
        for (Category c: categories) {
            kieSession.insert(c);
        }
        for (Tag t: tags) {
            kieSession.insert(t);
        }

        // Initialize vehicles tags and categories as empty.
        vehicle.setTags(new ArrayList<Tag>());
        vehicle.setCategories(new ArrayList<Category>());

        kieSession.insert(vehicle);
        kieSession.fireAllRules();
        kieSession.dispose();

        vehicleRepo.save(vehicle);
        return vehicle;
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepo.findAll();
    }

}
