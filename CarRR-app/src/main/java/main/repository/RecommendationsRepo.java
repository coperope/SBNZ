package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.facts.Recommendations;

public interface RecommendationsRepo  extends JpaRepository<Recommendations, Long> {
}
