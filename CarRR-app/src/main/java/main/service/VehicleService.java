package main.service;

import main.MainApp;
import main.dto.BrandDTO;
import main.dto.SearchDTO;
import main.dto.VehicleDTO;
import main.facts.Brand;
import main.facts.Category;
import main.facts.Tag;
import main.facts.Vehicle;
import main.repository.CategoryRepo;
import main.repository.TagRepo;
import main.repository.VehicleRepo;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.modelmapper.ModelMapper;
import org.kie.api.runtime.rule.FactHandle;
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
        return vehicleDTO;
    }

    public List<VehicleDTO> getAllVehicles(){
        List<Vehicle> vehicles = vehicleRepo.findAll();
        return vehicles
                .stream()
                .map(this::convertVehicleToDTO)
                .collect(Collectors.toList());
    }

    public List<Vehicle> searchVehicles(SearchDTO searchDTO){
        List<BrandDTO> brandsDTO = searchDTO.getBrands();
        List<Long> brands = brandsDTO.stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());
        brands = brands.isEmpty() ? null : brands;
        List<Long> models = searchDTO.getModels().stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());
        models = models.isEmpty() ? null : models;
        List<Long> fuels = searchDTO.getFuels().stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());
        fuels = fuels.isEmpty() ? null : fuels;
        List<Long> transmissions = searchDTO.getTransmissions().stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());
        transmissions = transmissions.isEmpty() ? null : transmissions;
        System.out.println(brands);
        System.out.println(brandsDTO);
        System.out.println(searchDTO);
        List<Category> categories = searchDTO.getCategories();
        return vehicleRepo.getBySearchParams(brands, models, fuels, transmissions, searchDTO.getDoorNo().isEmpty() ? null : searchDTO.getDoorNo() , searchDTO.getSeatsNo().isEmpty() ? null : searchDTO.getSeatsNo());
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
