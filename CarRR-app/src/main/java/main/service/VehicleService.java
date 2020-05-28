package main.service;

import main.MainApp;
import main.dto.VehicleDTO;
import main.facts.Category;
import main.facts.NewVehicleEvent;
import main.facts.Tag;
import main.facts.Vehicle;
import main.repository.CategoryRepo;
import main.repository.CustomerRepo;
import main.repository.TagRepo;
import main.repository.VehicleRepo;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.modelmapper.ModelMapper;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.EntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    CustomerRepo customerRepo;

    // Add a new vehicle and determine it's categories and tags.
    public VehicleDTO addVehicle(VehicleDTO vehicleDTO) {
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
        Vehicle vehicle = convertDTOToVehicle(vehicleDTO);
        vehicle.setTags(new ArrayList<Tag>());
        vehicle.setCategories(new ArrayList<Category>());

        //FactHandle handle = MainApp.taggingAndCategorisation.insert(vehicle);

        kieSession.insert(vehicle);
        kieSession.fireAllRules();
        kieSession.dispose();

        // try {
		//	Thread.sleep(150); // kind of stupid... should we do this or create session, insert, fire, dispose...
		// } catch (InterruptedException e) {
		//	e.printStackTrace();
		// }
        // remove from kiesession
        // MainApp.taggingAndCategorisation.delete(handle); // cant delete handles of deleted tags and categories...

        vehicleRepo.save(vehicle);
        
        NewVehicleEvent event = new NewVehicleEvent(vehicle);
        
        //MainApp.recommendationSession.setGlobal("customerRepository", customerRepo);

        MainApp.recommendationSession.getAgenda().getAgendaGroup("events-group").setFocus();
        EntryPoint eventsEntryPoint = MainApp.recommendationSession.getEntryPoint("events-entry");

        eventsEntryPoint.insert(event);
        //eventsEntryPoint.insert(customerRepo);
        return vehicleDTO;
    }

    public List<VehicleDTO> getAllVehicles(){
        List<Vehicle> vehicles = vehicleRepo.findAll();
        return vehicles
                .stream()
                .map(this::convertVehicleToDTO)
                .collect(Collectors.toList());
    }

    private VehicleDTO convertVehicleToDTO(Vehicle vehicle){
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    private Vehicle convertDTOToVehicle(VehicleDTO vehicleDTO){
        return modelMapper.map(vehicleDTO, Vehicle.class);
    }

}
