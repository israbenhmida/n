package com.example.projet.Interfaces;

import com.example.projet.Entities.Club;
import com.example.projet.Entities.Universite;
import java.util.List;

public interface IUniversite {

    List<Universite> getAll();
    Object getUniversiteById(Long id);
    void saveUniversite(Universite eleve);
    void updateUniversite(Long id, Universite eleve);
    void deleteUniversite(Long eleveId);
    public List<Club> getClubsByUniversityId(Long universiteId);
}
