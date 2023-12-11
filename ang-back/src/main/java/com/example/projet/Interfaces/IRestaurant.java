package com.example.projet.Interfaces;

import com.example.projet.Entities.Reservation;
import com.example.projet.Entities.Restaurant;

import java.util.List;

public interface IRestaurant {
    List<Restaurant> getAll();
    Restaurant getRestaurantById(Long idRestaurant);
    void saveRestaurant(Restaurant restaurant);
    void updateRestaurant(Long idRestaurant, Restaurant restaurant);
    void deleteRestaurant(Long idRestaurant);
    List<Reservation> getReservationsByRestaurantId(Long restaurantId);
    void assignReservationToRestaurant(Long universityId, Long reservationId);
}
