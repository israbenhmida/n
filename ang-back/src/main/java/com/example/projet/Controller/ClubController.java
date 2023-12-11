package com.example.projet.Controller;

import com.example.projet.Entities.Club;
import com.example.projet.Services.ClubImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("club")
public class ClubController {

    private final ClubImp clubService;



    @GetMapping("getAllClub")
    public List<Club> getAllClubs() {
        return clubService.getAll();
    }


    @GetMapping("getClub/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        Optional<Club> Club = clubService.getClubById(id);
        return Club.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/saveClub")
    public ResponseEntity<Club> saveClub(@RequestBody Club Club) {
        clubService.saveClub(Club);
        return new ResponseEntity<>(Club, HttpStatus.CREATED);
    }

    @PutMapping("updateClub/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable("id") Long id, @RequestBody Club Club) {
        clubService.updateClub(id, Club);
        return new ResponseEntity<>(Club, HttpStatus.OK);
    }

    @DeleteMapping("deleteClub/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        try {
            clubService.deleteClub(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}


