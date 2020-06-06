package main.service;

import main.MainApp;
import main.dto.RentalDTO;
import main.events.NewRentalEvent;
import main.events.NewVehicleEvent;
import main.facts.Rental;
import main.repository.RentalRepo;
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
    ModelMapper modelMapper;

    public RentalDTO addRental(RentalDTO rentalDTO){
        Rental rental = convertDTOToRental(rentalDTO);
        Rental rental1 = rentalRepo.save(rental);

        kieSession = kieContainer.newKieSession("rental_history_update_session");
        //FactHandle handle = MainApp.taggingAndCategorisation.insert(vehicle);

        kieSession.insert(rental1);
        kieSession.fireAllRules();
        kieSession.dispose();



        NewRentalEvent event = new NewRentalEvent(rental1);

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
}
