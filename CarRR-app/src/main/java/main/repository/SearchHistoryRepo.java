package main.repository;

import main.facts.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchHistoryRepo extends JpaRepository<SearchHistory, Long> {
}
