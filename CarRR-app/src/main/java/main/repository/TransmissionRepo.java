package main.repository;

import main.facts.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionRepo extends JpaRepository<Transmission, Long> {
}
