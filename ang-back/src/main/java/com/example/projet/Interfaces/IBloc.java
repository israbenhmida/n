package com.example.projet.Interfaces;

import com.example.projet.Entities.Bibliotheque;
import com.example.projet.Entities.Bloc;
import com.example.projet.Entities.Chambre;
import com.example.projet.Entities.Foyer;
import com.example.projet.Enums.TypeChambre;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IBloc {

    List<Bloc> getAllBlocs();

    Optional<Bloc> getBlocById(Long id);

    void saveBloc(Bloc bloc);

    void updateBloc(Long id, Bloc updatedBloc);

    void deleteBloc(Long blocId);


    void addBloctoFoyer(Long idFoyer, Bloc bloc);


    List<Bloc> getBlocsByFoyerId(Long foyerId);

    void mettreAJourblocDansFoyer(Long idFoyer, Long idBloc, Bloc bloc);
}