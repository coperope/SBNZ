package main.service;

import main.MainApp;
import main.dto.BrandDTO;
import main.dto.SearchDTO;
import main.dto.VehicleDTO;
import main.events.NewRentalEvent;
import main.events.NewSearchEvent;
import main.events.NewVehicleEvent;
import main.facts.*;
import main.repository.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.modelmapper.ModelMapper;
import org.kie.api.runtime.rule.EntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    protected final String ksessionName = "categorisation_tagging_session";
    //private KieServices ks = KieServices.Factory.get();
    //private KieContainer kieContainer = ks.getKieClasspathContainer();
    private KieSession kieSession;

    @Autowired
    KieContainer kieContainer;
   
    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    CategoryRepo categoryRepo;
    
    @Autowired
    UserRepo userRepo;

    @Autowired
    TagRepo tagRepo;

    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ExtraFeaturesRepo extraFeaturesRepo;

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

        for (ExtraFeatures feature: vehicle.getFeatures()) {
            extraFeaturesRepo.save(feature);
        }

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
    
    public List<VehicleDTO> getOwnersVehicles(Long ownersId) {
    	User owner = userRepo.findById(ownersId).orElse(null);
    	if (owner == null) {
			return null;
		}
    	List<Vehicle> vehicles = vehicleRepo.findByOwner(owner);
    	return vehicles
                 .stream()
                 .map(this::convertVehicleToDTO)
                 .collect(Collectors.toList());
	}

	public List<VehicleDTO> searchVehicles(SearchDTO searchDTO) {
		List<BrandDTO> brandsDTO = searchDTO.getBrands();
		List<Long> brands = brandsDTO.stream().map(x -> x.getId()).collect(Collectors.toList());
		brands = brands.isEmpty() ? null : brands;
		List<Long> models = searchDTO.getModels().stream().map(x -> x.getId()).collect(Collectors.toList());
		models = models.isEmpty() ? null : models;
		List<Long> fuels = searchDTO.getFuels().stream().map(x -> x.getId()).collect(Collectors.toList());
		fuels = fuels.isEmpty() ? null : fuels;
		List<Long> transmissions = searchDTO.getTransmissions().stream().map(x -> x.getId())
				.collect(Collectors.toList());
		transmissions = transmissions.isEmpty() ? null : transmissions;
		List<Long> categories = searchDTO.getCategories().stream().map(x -> x.getId()).collect(Collectors.toList());
		categories = categories.isEmpty() ? null : categories;
		List<Long> tags = searchDTO.getTags().stream().map(x -> x.getId()).collect(Collectors.toList());
		tags = tags.isEmpty() ? null : tags;
		// Update search history
		kieSession = kieContainer.newKieSession("rental_history_update_session");

		kieSession.insert(searchDTO);
		Customer customer = customerRepo.getOne(searchDTO.getCustomer().getId());
        System.out.println("*****************************************");
        System.out.println(customer);
        System.out.println("*****************************************");
		kieSession.insert(customer);
		kieSession.fireAllRules();
		kieSession.dispose();
		Customer customerSaved = customerRepo.save(customer);

		NewSearchEvent event = new NewSearchEvent(searchDTO);

		//MainApp.recommendationSession.setGlobal("customerRepository", customerRepo);

		MainApp.recommendationSession.getAgenda().getAgendaGroup("events-group").setFocus();
		EntryPoint eventsEntryPoint = MainApp.recommendationSession.getEntryPoint("events-entry");
		eventsEntryPoint.insert(event);

        List<Vehicle> foundVehicles = vehicleRepo.getBySearchParams(brands, models, fuels, transmissions,
                searchDTO.getDoorNo().isEmpty() ? null : searchDTO.getDoorNo(),
                searchDTO.getSeatsNo().isEmpty() ? null : searchDTO.getSeatsNo(), categories, tags, searchDTO.getFuelConsumptions().isEmpty() ? null : searchDTO.getFuelConsumptions());
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();
        for (Vehicle vehicle: foundVehicles) {
            vehicleDTOS.add(convertVehicleToDTO(vehicle));
        }
		return vehicleDTOS;
	}

    private Brand convertDTOtoBrand(BrandDTO brand){
        Brand b = modelMapper.map(brand, Brand.class);
        //bdto.setModels(brand.getModels());
        return b;
    }

    private VehicleDTO convertVehicleToDTO(Vehicle vehicle){
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    private Vehicle convertDTOToVehicle(VehicleDTO vehicleDTO){
        return modelMapper.map(vehicleDTO, Vehicle.class);
    }
}
