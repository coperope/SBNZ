package main.repository;

import main.facts.Customer;
import main.facts.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    User findByEmail(String email);
}
