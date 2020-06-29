package main.service;

import main.MainApp;
import main.dto.RentalDTO;
import main.events.NewRentalEvent;
import main.events.NewVehicleEvent;
import main.facts.*;
import main.repository.*;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.rule.EntryPoint;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.runtime.KieSession;

@Service
public class RentalService {
    private KieSession kieSession;

    @Autowired
    KieContainer kieContainer;

    @Autowired
    RentalRepo rentalRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    UserRepo userRepo;
    
    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    RentalHistoryRepo rentalHistoryRepo;

    @Autowired
    SearchHistoryRepo searchHistoryRepo;

    @Autowired
    ModelMapper modelMapper;

    public RentalDTO addRental(RentalDTO rentalDTO){
        // For testing purposes
        Rental rental = convertDTOToRental(rentalDTO);
        Customer customer = customerRepo.findById(rentalDTO.getCustomer().getId()).get();
        User owner = userRepo.findById(rentalDTO.getOwner().getId()).get();
        Vehicle vehicle = vehicleRepo.getOne(rentalDTO.getVehicle().getId());
        rental.setCustomer(customer);
        rental.setVehicle(vehicle);
        rental.setOwner(owner);

        kieSession = kieContainer.newKieSession("rental_history_update_session");
        //FactHandle handle = MainApp.taggingAndCategorisation.insert(vehicle);
        Rental rental1 = rentalRepo.save(rental);
        
        
        kieSession.insert(rental1);
        kieSession.fireAllRules();
        kieSession.dispose();
        Rental rental2 = rentalRepo.save(rental);
        
        owner.setNoOfRentals(owner.getNoOfRentals() + 1);
        userRepo.save(owner);

        NewRentalEvent event = new NewRentalEvent(rental2);

        //MainApp.recommendationSession.setGlobal("customerRepository", customerRepo);

        MainApp.recommendationSession.getAgenda().getAgendaGroup("events-group").setFocus();
        EntryPoint eventsEntryPoint = MainApp.recommendationSession.getEntryPoint("events-entry");
        eventsEntryPoint.insert(event);

        return convertRentalToDTO(rental1);
    }

    private RentalDTO convertRentalToDTO(Rental rental){
        return modelMapper.map(rental, RentalDTO.class);
    }

    private Rental convertDTOToRental(RentalDTO rentalDTO){
        return modelMapper.map(rentalDTO, Rental.class);
    }

	public List<RentalDTO> getOwnersActiveRentals(Long ownerID) {
		User owner = userRepo.findById(ownerID).get();
		
		List<RentalDTO> rentals = new ArrayList<RentalDTO>();
		
		for (Rental rental : rentalRepo.findAllByOwnerAndFinished(owner, false)) {
			rentals.add(convertRentalToDTO(rental));
		}

		return rentals;
	}

	public void finishRental(Long rentalID, List<Malfunction> malfunctions) {
		Rental rental = rentalRepo.findById(rentalID).get();
		rental.setMalfunctions(malfunctions);
		rental.setFinished(true);
		rentalRepo.save(rental);
	}
}
