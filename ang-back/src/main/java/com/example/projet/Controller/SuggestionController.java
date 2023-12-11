package com.example.projet.Controller;

import com.example.projet.Entities.Suggestion;

import com.example.projet.Services.FoyerImp;
import com.example.projet.Services.SuggestionImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("suggestion")
@Validated

public class SuggestionController {

    private final SuggestionImp suggestionService;
    FoyerImp foyerService ;

    @GetMapping
    public List<Suggestion> getAll() {
        return suggestionService.getAllSuggestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suggestion> getSuggestionById(@PathVariable Long id) {
        Optional<Suggestion> suggestion = suggestionService.getSuggestionById(id);
        return suggestion.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Suggestion> saveSuggestion(@RequestBody Suggestion suggestion) {
        suggestionService.saveSuggestion(suggestion);
        return new ResponseEntity<>(suggestion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Suggestion> updateSuggestion(@PathVariable("id") Long id, @RequestBody Suggestion suggestion) {
        suggestionService.updateSuggestion(id, suggestion);
        return new ResponseEntity<Suggestion>(suggestion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuggestion(@PathVariable Long id) {
        try {
            suggestionService.deleteSuggestion(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/tags/count")
    public ResponseEntity<Map<String, Long>> countSuggestionsByTag(@RequestParam("tags") List<String> tags) {
        Map<String, Long> tagCounts = suggestionService.countSuggestionsByTag(tags);
        return ResponseEntity.ok(tagCounts);
    }
    @PostMapping("/{suggestionId}/like")
    public ResponseEntity<?> likeSuggestion(@PathVariable Long suggestionId) {
        suggestionService.likeSuggestion(suggestionId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/popular")
    public ResponseEntity<List<Suggestion>> getPopularSuggestions() {
        List<Suggestion> popularSuggestions = suggestionService.getPopularSuggestions();
        return ResponseEntity.ok(popularSuggestions);
    }

    @PostMapping("/{foyerId}/add-suggestion")
    public ResponseEntity<Map<String, String>> addSuggestionToFoyer(
            @PathVariable Long foyerId,
            @RequestBody Suggestion suggestion
    ) {
        try {
            suggestionService.addSuggestionToFoyer(foyerId, suggestion);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Suggestion ajoutée avec succès au foyer.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    @PutMapping("/{idFoyer}/suggestion/{idSuggestion}")
    public ResponseEntity<Void> mettreAJourSuggestionDansFoyer(
            @PathVariable Long idFoyer,
            @PathVariable Long idSuggestion,
            @RequestBody Suggestion suggestion) {

        suggestionService.mettreAJourSuggestionDansFoyer(idFoyer, idSuggestion, suggestion);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

