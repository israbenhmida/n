package com.example.projet.Interfaces;

import com.example.projet.Entities.Suggestion;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ISuggestion {
    List<Suggestion> getAllSuggestions();

    Optional<Suggestion> getSuggestionById(Long id);

    void saveSuggestion(Suggestion suggestion);

    void updateSuggestion(Long id, Suggestion updatedSuggestion);

    void deleteSuggestion(Long suggestionId);

    Map<String, Long> countSuggestionsByTag(Collection<String> tagSuggestions);

    void likeSuggestion(Long suggestionId);

    List<Suggestion> getPopularSuggestions();

    void addSuggestionToFoyer(Long foyerId, Suggestion suggestion);

    List<Suggestion> getSuggestionsByFoyerId(Long foyerId);

    void mettreAJourSuggestionDansFoyer(Long idFoyer, Long idSuggestion, Suggestion suggestion);
}
