package com.example.projet.Repositories;

import com.example.projet.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Reservation findReservationByIdReservation(Long id);


}
