package com.example.projet.Controller;

import com.example.projet.Entities.Chambre;
import com.example.projet.Entities.Club;
import com.example.projet.Entities.Reservation;
import com.example.projet.Entities.Restaurant;
import com.example.projet.Interfaces.IRestaurant;
import com.example.projet.Services.ChambreImp;
import com.example.projet.Services.RestaurantImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantImp restaurantImp;


    @GetMapping("/getallrestau")
    public List<Restaurant> getAll() {
        return restaurantImp.getAll();
    }

    @GetMapping("/byid/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {

        return restaurantImp.getRestaurantById(id);
    }

    @PostMapping("/ajoutRes")
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant restaurant) {
        restaurantImp.saveRestaurant(restaurant);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        restaurantImp.updateRestaurant(id, restaurant);
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        try {
            restaurantImp.deleteRestaurant(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{restaurantId}/reservations")
    public ResponseEntity<List<Reservation>> getReervationsByRestaurant(@PathVariable Long restaurantId) {
        List<Reservation> reservations = restaurantImp.getReservationsByRestaurantId(restaurantId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping("/{idRestaurant}/reservations/{idReservation}")
    public ResponseEntity<Void> assignReservationToRestaurant(
            @PathVariable Long idRestaurant,
            @PathVariable Long idReservation) {
        restaurantImp.assignReservationToRestaurant(idRestaurant,idReservation);
        return ResponseEntity.ok().build();
    }
}
