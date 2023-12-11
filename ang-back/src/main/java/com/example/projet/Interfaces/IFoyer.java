package com.example.projet.Interfaces;


import com.example.projet.Entities.Foyer;

import java.util.List;
import java.util.Optional;

public interface IFoyer {

    List<Foyer> getAllFoyers();

    Optional<Foyer> getFoyerById(Long id);

    void saveFoyer(Foyer foyer);

    void updateFoyer(Long id, Foyer updatedFoyer);

    void deleteFoyer(Long foyerId);


}
