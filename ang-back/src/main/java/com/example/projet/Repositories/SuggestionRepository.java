package com.example.projet.Repositories;

import com.example.projet.Entities.Bloc;
import com.example.projet.Entities.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion,Long> {
    List<Suggestion> findByFoyerIdFoyer(Long foyerId);
    Long countByTagSuggestion(String tagSuggestion);
    List<Suggestion> findByOrderByLikesDesc();

}
