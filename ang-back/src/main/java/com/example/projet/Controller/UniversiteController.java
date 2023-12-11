package com.example.projet.Controller;

import com.example.projet.Entities.Club;
import com.example.projet.Entities.Universite;
import com.example.projet.Services.UniversiteImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/universites")
@CrossOrigin(origins = "http://localhost:4200")
public class UniversiteController {

    private final UniversiteImp universiteService;


    @GetMapping
    public List<Universite> getAll() {
        return universiteService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Universite> getUniversiteById(@PathVariable Long id) {
        Optional<Universite> universite = universiteService.getUniversiteById(id);
        return universite.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Universite> saveUniversite(@RequestBody Universite universite) {
        universiteService.saveUniversite(universite);
        return new ResponseEntity<>(universite, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Universite> updateUniversite(@PathVariable("id") Long id, @RequestBody Universite universite) {
        universiteService.updateUniversite(id, universite);
        return new ResponseEntity<Universite>(universite, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversite(@PathVariable Long id) {
        try {
            universiteService.deleteUniversite(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }




    }
    @GetMapping("/averageFoyerCapacity")
    public ResponseEntity<Double> getAverageFoyerCapacity() {
        double averageCapacity = universiteService.calculateAverageFoyerCapacity();
        return ResponseEntity.ok(averageCapacity);
    }


    @GetMapping("/universites/search")
    public List<Universite> searchUniversitesByName(@RequestParam String name) {
        return universiteService.searchUniversitesByName(name);
    }
    @PostMapping("/{idUniversite}/clubs/{idClub}")
    public ResponseEntity<Void> assignClubToUniversity(
            @PathVariable Long idUniversite,
            @PathVariable Long idClub) {
        universiteService.assignClubToUniversity(idUniversite, idClub);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{idUniversite}/clubs/{idClub}")
    public ResponseEntity<Void> removeClubFromUniversity(
            @PathVariable Long idUniversite,
            @PathVariable Long idClub) {
        universiteService.removeClubFromUniversity(idUniversite, idClub);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{universityId}/clubs")
    public ResponseEntity<List<Club>> getClubsByUniversity(@PathVariable Long universityId) {
        List<Club> clubs = universiteService.getClubsByUniversityId(universityId);
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }
}
