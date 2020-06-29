package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.facts.Malfunction;

public interface MalfunctionRepo extends JpaRepository<Malfunction, Long>{

}
