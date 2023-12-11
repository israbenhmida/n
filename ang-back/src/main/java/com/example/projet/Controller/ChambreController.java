package com.example.projet.Controller;

import com.example.projet.Entities.Bloc;
import com.example.projet.Entities.Chambre;
import com.example.projet.Entities.Reservation;
import com.example.projet.Services.BlocImp;
import com.example.projet.Services.ChambreImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("chambre")
public class ChambreController {

    private final ChambreImp chambreService;

    @GetMapping
    public List<Chambre> getAllChambre() {
        return chambreService.getAllChambre();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chambre> getChambreById(@PathVariable Long id) {
        Optional<Chambre> chambreOptional = chambreService.getChambreById(id);

        return chambreOptional.map(chambre -> {
            // Calculate the price before returning the room information
            chambre.calculateInitialPrice();
            return new ResponseEntity<>(chambre, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Chambre> saveChambre(@RequestBody Chambre chambre) {
        // Set the initial price based on the room type
        chambre.calculateInitialPrice();

        // Save the chambre
        chambreService.saveChambre(chambre);

        return new ResponseEntity<>(chambre, HttpStatus.CREATED);
    }
    @GetMapping("/exists/{numeroChambre}")
    public ResponseEntity<Boolean> isNumeroChambreExists(@PathVariable Long numeroChambre) {
        boolean exists = chambreService.isNumeroChambreExists(numeroChambre);
        return ResponseEntity.ok(exists);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Chambre> updateChambre(@PathVariable("id") Long id, @RequestBody Chambre chambre) {
        chambre.calculateInitialPrice();
        chambreService.updateChambre(id, chambre);
        return new ResponseEntity<Chambre>(chambre, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChambre(@PathVariable Long id) {
        try {
            chambreService.deleteChambre(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{chambreId}/equipements/{equipementId}")
    public ResponseEntity<Void> addEquipementToChambre(
            @PathVariable Long chambreId,
            @PathVariable Long equipementId) {
        chambreService.addEquipementToChambre(chambreId, equipementId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{chambreId}/equipements/{equipementId}")
    public ResponseEntity<Void> removeEquipementFromChambre(
            @PathVariable Long chambreId,
            @PathVariable Long equipementId) {
        chambreService.removeEquipementFromChambre(chambreId, equipementId);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/addtobloc/{idBloc}")
    public ResponseEntity<Void> addChambreToBloc(@PathVariable Long idBloc, @RequestBody Chambre chambre) {
        chambreService.addChambreToBloc(idBloc, chambre);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/removefrombloc/{idBloc}/{idChambre}")
    public ResponseEntity<Void> removeChambreFromBloc(
            @PathVariable Long idBloc, @PathVariable Long idChambre) {
        chambreService.removeChambreFromBloc(idBloc, idChambre);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/bybloc/{idBloc}")
    public ResponseEntity<List<Chambre>> getChambresByBlocId(@PathVariable Long idBloc) {
        List<Chambre> chambres = chambreService.getChambresByBlocId(idBloc);
        return ResponseEntity.ok(chambres);
    }

    @PutMapping("/updateinbloc/{idBloc}/{idChambre}")
    public ResponseEntity<Void> mettreAJourChambreDansBloc(
            @PathVariable Long idBloc, @PathVariable Long idChambre, @RequestBody Chambre chambre) {
        chambreService.mettreAJourChambreDansBloc(idBloc, idChambre, chambre);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{chambreId}/reservations")
    public ResponseEntity<Chambre> addReservationToChambre(@PathVariable Long chambreId, @RequestBody Reservation reservation) {
        Chambre chambre = chambreService.addReservationToChambre(chambreId, reservation);
        return new ResponseEntity<>(chambre, HttpStatus.CREATED);
    }
    @DeleteMapping("/{chambreId}/reservations/{reservationId}")
    public ResponseEntity<Chambre> deleteReservationFromChambre(@PathVariable Long chambreId, @PathVariable Long reservationId) {
        Chambre chambre = chambreService.deleteReservationFromChambre(chambreId, reservationId);
        return new ResponseEntity<>(chambre, HttpStatus.OK);
    }

}
