package main.repository;

import main.facts.CustomerPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPreferencesRepo extends JpaRepository<CustomerPreferences, Long> {

}
