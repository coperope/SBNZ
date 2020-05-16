package main.repository;

import main.facts.RentalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalHistoryRepo extends JpaRepository<RentalHistory, Long> {
}
