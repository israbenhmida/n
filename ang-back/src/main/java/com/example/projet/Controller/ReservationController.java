package com.example.projet.Controller;


import com.example.projet.Entities.Reservation;
import com.example.projet.Entities.Restaurant;
import com.example.projet.Services.ReservationImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationImp reservationService;



    @GetMapping("getAllReservation")
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @GetMapping("getReservation/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping("/saveReservation")
    public Reservation saveReservation(@RequestBody Reservation reservation) {

        return reservationService.saveReservation(reservation);
    }


    @PutMapping("updateReservation/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long id, @RequestBody Reservation reservation) {
        reservationService.updateReservation(id, reservation);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @DeleteMapping("deleteReservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        try {
            reservationService.deleteReservation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/ajouterReservation/{restaurantId}")
    public void AjouterReservation(@PathVariable Long restaurantId, @RequestBody Reservation reservation)
    {
         reservationService.AjouterReservationAvecRestaurant(restaurantId,reservation);
    }

}
