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
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
public class VehicleService {
	protected final String ksessionName = "categorisation_tagging_session";
	// private KieServices ks = KieServices.Factory.get();
	// private KieContainer kieContainer = ks.getKieClasspathContainer();
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
	RentalRepo rentalRepo;

	@Autowired
	ExtraFeaturesRepo extraFeaturesRepo;

	// Add a new vehicle and determine it's categories and tags.
	public VehicleDTO addVehicle(VehicleDTO vehicleDTO) {
		kieSession = kieContainer.newKieSession("categorisation_tagging_session");
		List<Category> categories = categoryRepo.findAll();
		List<Tag> tags = tagRepo.findAll();
		for (Category c : categories) {
			kieSession.insert(c);
		}
		for (Tag t : tags) {
			kieSession.insert(t);
		}

		// Initialize vehicles tags and categories as empty.
		Vehicle vehicle = convertDTOToVehicle(vehicleDTO);
		vehicle.setTags(new ArrayList<Tag>());
		vehicle.setCategories(new ArrayList<Category>());

		// FactHandle handle = MainApp.taggingAndCategorisation.insert(vehicle);

		kieSession.insert(vehicle);
		kieSession.fireAllRules();
		kieSession.dispose();

		// try {
		// Thread.sleep(150); // kind of stupid... should we do this or create session,
		// insert, fire, dispose...
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// remove from kiesession
		// MainApp.taggingAndCategorisation.delete(handle); // cant delete handles of
		// deleted tags and categories...

		for (ExtraFeatures feature : vehicle.getFeatures()) {
			extraFeaturesRepo.save(feature);
		}

		User owner = userRepo.findById(vehicleDTO.getOwner().getId()).get();
		vehicle.setOwner(owner);

		vehicleRepo.save(vehicle);

		NewVehicleEvent event = new NewVehicleEvent(vehicle);

		// MainApp.recommendationSession.setGlobal("customerRepository", customerRepo);

		MainApp.recommendationSession.getAgenda().getAgendaGroup("events-group").setFocus();
		EntryPoint eventsEntryPoint = MainApp.recommendationSession.getEntryPoint("events-entry");

		eventsEntryPoint.insert(event);
		// eventsEntryPoint.insert(customerRepo);
		return vehicleDTO;
	}

	public List<VehicleDTO> getAllVehicles() {
		List<Vehicle> vehicles = vehicleRepo.findAll();
		return vehicles.stream().map(this::convertVehicleToDTO).collect(Collectors.toList());
	}

	public List<VehicleDTO> getOwnersVehicles(Long ownersId) {
		User owner = userRepo.findById(ownersId).orElse(null);
		if (owner == null) {
			return null;
		}
		List<Vehicle> vehicles = vehicleRepo.findByOwner(owner);
		return vehicles.stream().map(this::convertVehicleToDTO).collect(Collectors.toList());
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

		kieSession.insert(modelMapper);
		kieSession.insert(searchDTO);
		Customer customer = customerRepo.getOne(searchDTO.getCustomer().getId());
		System.out.println(customer);
		kieSession.insert(customer);
		kieSession.fireAllRules();
		kieSession.dispose();
		Customer customerSaved = customerRepo.save(customer);

		NewSearchEvent event = new NewSearchEvent(searchDTO, customerSaved);

		// MainApp.recommendationSession.setGlobal("customerRepository", customerRepo);

		MainApp.recommendationSession.getAgenda().getAgendaGroup("events-group").setFocus();
		EntryPoint eventsEntryPoint = MainApp.recommendationSession.getEntryPoint("events-entry");
		eventsEntryPoint.insert(event);

		List<Vehicle> foundVehicles = vehicleRepo.getBySearchParams(brands, models, fuels, transmissions,
				searchDTO.getDoorNo().isEmpty() ? null : searchDTO.getDoorNo(),
				searchDTO.getSeatsNo().isEmpty() ? null : searchDTO.getSeatsNo(), categories, tags,
				searchDTO.getFuelConsumptions().isEmpty() ? null : searchDTO.getFuelConsumptions());
		List<VehicleDTO> vehicleDTOS = new ArrayList<>();
		for (Vehicle vehicle : foundVehicles) {
			vehicleDTOS.add(convertVehicleToDTO(vehicle));
		}
		return vehicleDTOS;
	}

	public List<VehicleDTO> getUserRecommendationsCombined(Long userID) {

		Customer customer = customerRepo.findById(userID).orElse(null);
		if (customer == null) {
			return null;
		}

		Recommendations recommendations = customer.getRecommendations();
		Map<Vehicle, Integer> combined = new HashMap<Vehicle, Integer>();
		int i = 0;
		for (Vehicle vehicle : recommendations.getRentalMap().keySet()) {
			if (recommendations.getRentalMap().get(vehicle) > 0) {
				if (combined.containsKey(vehicle)) {
					combined.put(vehicle, combined.get(vehicle) + recommendations.getRentalMap().get(vehicle));
				} else {
					combined.put(vehicle, recommendations.getRentalMap().get(vehicle));
				}
				if (++i > 9) {
					break;
				}
			}
		}
		i = 0;
		for (Vehicle vehicle : recommendations.getSearchMap().keySet()) {
			if (recommendations.getSearchMap().get(vehicle) > 0) {
				if (combined.containsKey(vehicle)) {
					combined.put(vehicle, combined.get(vehicle) + recommendations.getSearchMap().get(vehicle));
				} else {
					combined.put(vehicle, recommendations.getSearchMap().get(vehicle));
				}
				if (++i > 9) {
					break;
				}
			}
		}
		i = 0;
		for (Vehicle vehicle : recommendations.getPreferencesMap().keySet()) {
			if (recommendations.getPreferencesMap().get(vehicle) > 0) {
				if (combined.containsKey(vehicle)) {
					combined.put(vehicle, combined.get(vehicle) + recommendations.getPreferencesMap().get(vehicle));
				} else {
					combined.put(vehicle, recommendations.getPreferencesMap().get(vehicle));
				}
				if (++i > 9) {
					break;
				}
			}
		}
		LinkedHashMap<Vehicle, Integer> sortedMap = combined.entrySet().stream()
				.sorted(Entry.<Vehicle, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		List<VehicleDTO> vehicleDTOS = new ArrayList<>();
		for (Vehicle vehicle : sortedMap.keySet()) {
			vehicleDTOS.add(convertVehicleToDTO(vehicle));
		}
		return vehicleDTOS;
	}

	public List<VehicleDTO> getUserRecommendationsPreferences(Long userID) {

		Customer customer = customerRepo.findById(userID).orElse(null);
		if (customer == null) {
			return null;
		}

		Recommendations recommendations = customer.getRecommendations();
		Map<Vehicle, Integer> combined = new HashMap<Vehicle, Integer>();
		int i = 0;
		for (Vehicle vehicle : recommendations.getPreferencesMap().keySet()) {
			if (recommendations.getPreferencesMap().get(vehicle) > 0) {
				if (combined.containsKey(vehicle)) {
					combined.put(vehicle, combined.get(vehicle) + recommendations.getPreferencesMap().get(vehicle));
				} else {
					combined.put(vehicle, recommendations.getPreferencesMap().get(vehicle));
				}
				if (++i > 9) {
					break;
				}
			}
		}
		LinkedHashMap<Vehicle, Integer> sortedMap = combined.entrySet().stream()
				.sorted(Entry.<Vehicle, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		List<VehicleDTO> vehicleDTOS = new ArrayList<>();
		for (Vehicle vehicle : sortedMap.keySet()) {
			vehicleDTOS.add(convertVehicleToDTO(vehicle));
		}
		return vehicleDTOS;
	}

	public List<VehicleDTO> getUserRecommendationsSearches(Long userID) {

		Customer customer = customerRepo.findById(userID).orElse(null);
		if (customer == null) {
			return null;
		}

		Recommendations recommendations = customer.getRecommendations();
		Map<Vehicle, Integer> combined = new HashMap<Vehicle, Integer>();
		int i = 0;
		for (Vehicle vehicle : recommendations.getSearchMap().keySet()) {
			if (recommendations.getSearchMap().get(vehicle) > 0) {
				if (combined.containsKey(vehicle)) {
					combined.put(vehicle, combined.get(vehicle) + recommendations.getSearchMap().get(vehicle));
				} else {
					combined.put(vehicle, recommendations.getSearchMap().get(vehicle));
				}
				if (++i > 9) {
					break;
				}
			}
		}
		LinkedHashMap<Vehicle, Integer> sortedMap = combined.entrySet().stream()
				.sorted(Entry.<Vehicle, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		List<VehicleDTO> vehicleDTOS = new ArrayList<>();
		for (Vehicle vehicle : sortedMap.keySet()) {
			vehicleDTOS.add(convertVehicleToDTO(vehicle));
		}
		return vehicleDTOS;
	}

	public List<VehicleDTO> getUserRecommendationsRentals(Long userID) {

		Customer customer = customerRepo.findById(userID).orElse(null);
		if (customer == null) {
			return null;
		}

		Recommendations recommendations = customer.getRecommendations();
		Map<Vehicle, Integer> combined = new HashMap<Vehicle, Integer>();
		int i = 0;
		for (Vehicle vehicle : recommendations.getRentalMap().keySet()) {
			if (recommendations.getRentalMap().get(vehicle) > 0) {
				if (combined.containsKey(vehicle)) {
					combined.put(vehicle, combined.get(vehicle) + recommendations.getRentalMap().get(vehicle));
				} else {
					combined.put(vehicle, recommendations.getRentalMap().get(vehicle));
				}
				if (++i > 9) {
					break;
				}
			}
		}
		LinkedHashMap<Vehicle, Integer> sortedMap = combined.entrySet().stream()
				.sorted(Entry.<Vehicle, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		List<VehicleDTO> vehicleDTOS = new ArrayList<>();
		for (Vehicle vehicle : sortedMap.keySet()) {
			vehicleDTOS.add(convertVehicleToDTO(vehicle));
		}
		return vehicleDTOS;
	}

	public void changeVehicleEventsGlobal(int number) {
		MainApp.recommendationSession.setGlobal("numberOfNewVehicles", number);
	}

	public void changeSearchEventsGlobal(int number) {
		MainApp.recommendationSession.setGlobal("numberOfNewSearches", number);
	}

	private Brand convertDTOtoBrand(BrandDTO brand) {
		Brand b = modelMapper.map(brand, Brand.class);
		// bdto.setModels(brand.getModels());
		return b;
	}

	private VehicleDTO convertVehicleToDTO(Vehicle vehicle) {
		return modelMapper.map(vehicle, VehicleDTO.class);
	}

	private Vehicle convertDTOToVehicle(VehicleDTO vehicleDTO) {
		return modelMapper.map(vehicleDTO, Vehicle.class);
	}

	public List<VehicleDTO> getOwnersVehiclesAboveAverage(Long ownersId) {
		kieSession = kieContainer.newKieSession("recommendation_rules_session");

		User owner = userRepo.findById(ownersId).get();
		List<Rental> rentals = rentalRepo.findAllByOwnerAndFinished(owner, true);
		for (Rental rent : rentals) {
			kieSession.insert(rent);
		}
		Set<Long> set = new HashSet<>();

		kieSession.insert(set);
		kieSession.insert(owner);
		kieSession.getAgenda().getAgendaGroup("topRentals").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		
		List<VehicleDTO> vehicleDTOs = new ArrayList<VehicleDTO>();
		for (Long vehicleId : set) {
			vehicleDTOs.add(convertVehicleToDTO(vehicleRepo.findById(vehicleId).get()));
		}

		return vehicleDTOs;
	}
}
