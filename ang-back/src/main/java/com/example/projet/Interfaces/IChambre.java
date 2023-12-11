package com.example.projet.Interfaces;

import com.example.projet.Entities.Bloc;
import com.example.projet.Entities.Chambre;
import com.example.projet.Enums.TypeChambre;

import java.util.List;

public interface IChambre {

    List<Chambre> getAllChambre();
    Object getChambreById(Long id);
    void saveChambre(Chambre chambre);

    boolean isNumeroChambreExists(Long numeroChambre);

    void updateChambre(Long id, Chambre chambre);
    void deleteChambre(Long chambreId);
    Chambre addEquipementToChambre(Long idChambre, Long idEquipement);
    Chambre removeEquipementFromChambre(Long idChambre, Long idEquipement);
    void addChambreToBloc(Long idBloc, Chambre chambre);
    void removeChambreFromBloc(Long idBloc, Long idChambre);

    List<Chambre> getChambresByBlocId(Long idBloc);
    void mettreAJourChambreDansBloc(Long idBloc, Long idChambre, Chambre chambre);

}
