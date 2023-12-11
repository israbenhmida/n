package com.example.projet.Services;

import com.example.projet.Entities.*;
import com.example.projet.Entities.Reservation;
import com.example.projet.Interfaces.IRestaurant;
import com.example.projet.Repositories.ReservationRepository;
import com.example.projet.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantImp implements IRestaurant {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Long idRestaurant) {
        return restaurantRepository.findRestaurantByIdRestaurant(idRestaurant) ;
    }


    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public void updateRestaurant(Long idRes, Restaurant restaurant) {
        Restaurant existingrestaurant = restaurantRepository.findById(idRes).orElse(null);

        if (existingrestaurant != null) {
            existingrestaurant.setIdRestaurant(restaurant.getIdRestaurant());
            existingrestaurant.setNomRestaurant(restaurant.getNomRestaurant());
            existingrestaurant.setReservations(restaurant.getReservations());

            restaurantRepository.save(existingrestaurant);
        } else {
            throw new IllegalArgumentException("Restaurant with ID " + idRes + " does not exist.");
        }

    }

    @Override
    public void deleteRestaurant(Long idRes) {
        Restaurant restaurant = restaurantRepository.findById(idRes).orElse(null);

        if (restaurant != null) {
            restaurantRepository.delete(restaurant);
        } else {
            throw new IllegalArgumentException("restaurant with ID " + idRes + " does not exist.");
        }
    }


    public List<Reservation> getReservationsByRestaurantId(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if (restaurant != null) {
            return restaurant.getReservations();
        } else {
            return null;
        }
    }


    public void assignReservationToRestaurant(Long restaurantId, Long reservationId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        if (optionalRestaurant.isPresent() && optionalReservation.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            Reservation reservation = optionalReservation.get();

            restaurant.getReservations().add(reservation);
            reservation.setRestaurant(restaurant);

            restaurantRepository.save(restaurant);
            reservationRepository.save(reservation);

        }





    }
    
    
    

}
