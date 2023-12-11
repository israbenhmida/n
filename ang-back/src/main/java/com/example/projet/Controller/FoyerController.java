package com.example.projet.Controller;

import com.example.projet.Entities.Bloc;
import com.example.projet.Entities.Foyer;
import com.example.projet.Entities.Suggestion;
import com.example.projet.Services.BlocImp;
import com.example.projet.Services.FoyerImp;
import com.example.projet.Services.SuggestionImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("foyer")
public class FoyerController {

    private final FoyerImp foyerService;
    BlocImp blocService ;
    SuggestionImp suggestionService ;

    @GetMapping
    public List<Foyer> getAll() {
        return foyerService.getAllFoyers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Foyer> getFoyerById(@PathVariable Long id) {
        Optional<Foyer> foyer = foyerService.getFoyerById(id);
        return foyer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Foyer> saveFoyer(@RequestBody Foyer foyer) {
        foyerService.saveFoyer(foyer);
        return new ResponseEntity<>(foyer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Foyer> updateFoyer(@PathVariable("id") Long id, @RequestBody Foyer foyer) {
        foyerService.updateFoyer(id, foyer);
        return new ResponseEntity<Foyer>(foyer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoyer(@PathVariable Long id) {
        try {
            foyerService.deleteFoyer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{foyerId}/blocs")
    public List<Bloc> getBlocsByFoyerId(@PathVariable Long foyerId) {
        return blocService.getBlocsByFoyerId(foyerId);
    }

    @GetMapping("/{foyerId}/suggestions")
    public List<Suggestion> getSuggestionsByFoyerId(@PathVariable Long foyerId) {
        return suggestionService.getSuggestionsByFoyerId(foyerId);
    }
}
