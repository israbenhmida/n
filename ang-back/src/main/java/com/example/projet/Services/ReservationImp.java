package com.example.projet.Services;

import com.example.projet.Entities.Reservation;
import com.example.projet.Entities.Restaurant;
import com.example.projet.Interfaces.IReservation;
import com.example.projet.Repositories.ReservationRepository;
import com.example.projet.Repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class ReservationImp implements IReservation {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findReservationByIdReservation(id);
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void updateReservation(Long id, Reservation updatedReservation) {
        Reservation existingReservation = reservationRepository.findById(id).orElse(null);
        reservationRepository.save(updatedReservation);

        if (existingReservation != null) {
            System.out.println("exist:"+existingReservation);
            existingReservation.setIdReservation(updatedReservation.getIdReservation());
            existingReservation.setAnneUniversitaire(updatedReservation.getAnneUniversitaire());
            existingReservation.setEstValide(updatedReservation.getEstValide());
            existingReservation.setEtudiants(updatedReservation.getEtudiants()); // Update the 'foyer' attribute

            reservationRepository.save(existingReservation);
        } else {
            throw new IllegalArgumentException("Reservation with ID " + id + " does not exist.");
        }
    }

    @Override
    public void deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);

        if (reservation != null) {
            reservationRepository.delete(reservation);
        } else {
            throw new IllegalArgumentException("Reservation with ID " + reservationId + " does not exist.");
        }
    }



    public Reservation addReservationToRestaurant(Long reservationId, Long restaurantId) {
        Restaurant restaurant=restaurantRepository.findById(restaurantId).orElse(null);
        Reservation reservation=reservationRepository.findById(reservationId).orElse(null);
        restaurant.getReservations().add(reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public void AjouterReservationAvecRestaurant(Long restaurantId, Reservation reservation) {

        Restaurant restaurant=restaurantRepository.findById(restaurantId).orElse(null);
        Reservation re=reservationRepository.save(reservation) ;


        if (restaurant != null) {
            restaurant.getReservations().add(re);
            re.setRestaurant(restaurant);
            restaurantRepository.save(restaurant);
            reservationRepository.save(re);
        }

    }


}
