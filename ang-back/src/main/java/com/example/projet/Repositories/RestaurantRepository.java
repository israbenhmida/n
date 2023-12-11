package com.example.projet.Repositories;

import com.example.projet.Entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Restaurant findRestaurantByIdRestaurant(Long idRestaurant) ;

}
