package com.example.projet.Services;


import com.example.projet.Entities.Foyer;
import com.example.projet.Entities.Suggestion;
import com.example.projet.Interfaces.ISuggestion;
import com.example.projet.Repositories.FoyerRepository;
import com.example.projet.Repositories.SuggestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class SuggestionImp implements ISuggestion{

    private SuggestionRepository suggestionRepository;
    FoyerRepository foyerRepository ;

    @Override
    public List<Suggestion> getAllSuggestions() {
        return suggestionRepository.findAll();
    }

    @Override
    public Optional<Suggestion> getSuggestionById(Long id) {
        return suggestionRepository.findById(id);
    }

    @Override
    public void saveSuggestion(Suggestion suggestion) {
        suggestionRepository.save(suggestion);
    }

    @Override
    public void updateSuggestion(Long id, Suggestion updatedSuggestion) {
        Suggestion existingSuggestion = suggestionRepository.findById(id).orElse(null);

        if (existingSuggestion != null) {
            existingSuggestion.setTitreSuggestion(updatedSuggestion.getTitreSuggestion());
            existingSuggestion.setContenuSuggestion(updatedSuggestion.getContenuSuggestion());
            existingSuggestion.setTagSuggestion(updatedSuggestion.getTagSuggestion());
            existingSuggestion.setFoyer(updatedSuggestion.getFoyer()); // Update the 'suggestion' attribute

            suggestionRepository.save(existingSuggestion);
        } else {
            throw new IllegalArgumentException("Suggestion with ID " + id + " does not exist.");
        }
    }


    @Override
    public void deleteSuggestion(Long suggestionId) {
        Suggestion suggestion = suggestionRepository.findById(suggestionId).orElse(null);

        if (suggestion != null) {
            suggestionRepository.delete(suggestion);
        } else {
            throw new IllegalArgumentException("Suggestion with ID " + suggestionId + " does not exist.");
        }
    }
    @Override
    public Map<String, Long> countSuggestionsByTag(Collection<String> tagSuggestions) {
        Map<String, Long> tagCountMap = new HashMap<>();

        for (String tag : tagSuggestions) {
            Long count = suggestionRepository.countByTagSuggestion(tag);
            tagCountMap.put(tag, count);
        }

        return tagCountMap;
    }
    @Override
    public void likeSuggestion(Long suggestionId) {
        // Récupérer la suggestion depuis la base de données
        Suggestion suggestion = suggestionRepository.findById(suggestionId).orElse(null);

        // Vérifier si la suggestion existe et mettre à jour le nombre de likes
        if (suggestion != null) {
            suggestion.setLikes(suggestion.getLikes() + 1); // Mettre à jour le nombre de likes
            suggestionRepository.save(suggestion); // Enregistrer la mise à jour dans la base de données
        }
    }

    @Override
    public List<Suggestion> getPopularSuggestions() {
        return suggestionRepository.findByOrderByLikesDesc();
    }
    @Override
    public void addSuggestionToFoyer(Long foyerId, Suggestion suggestion) {
        Foyer foyer = foyerRepository.findById(foyerId).orElse(null);
        suggestion.setFoyer(foyer);
        foyer.getSuggestions().add(suggestion);
        foyerRepository.save(foyer);
    }
    @Override
    public List<Suggestion> getSuggestionsByFoyerId(Long foyerId) {
        return suggestionRepository.findByFoyerIdFoyer(foyerId);
    }
    @Override
    public void mettreAJourSuggestionDansFoyer(Long idFoyer, Long idSuggestion, Suggestion suggestion) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        Suggestion existingsuggestion = suggestionRepository.findById(idSuggestion).orElse(null);
        existingsuggestion.setTitreSuggestion(suggestion.getTitreSuggestion());
        existingsuggestion.setContenuSuggestion(suggestion.getContenuSuggestion());
        existingsuggestion.setTagSuggestion(suggestion.getTagSuggestion());
        suggestionRepository.save(existingsuggestion);
    }

}



