package com.example.projet.Interfaces;


import com.example.projet.Entities.Reservation;

import java.util.List;

public interface IReservation {
    List<Reservation> getAll();
    Reservation getReservationById (Long id);
    Reservation saveReservation (Reservation reservation);
    void updateReservation (Long id, Reservation reservation);
    void deleteReservation (Long reservationId);
    public Reservation addReservationToRestaurant(Long reservationId , Long restaurantId);
    public void AjouterReservationAvecRestaurant (Long restaurantId,Reservation reservation);


}

