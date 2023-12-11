package com.example.projet.Interfaces;


import com.example.projet.Entities.Etudiant;

import java.util.List;

public interface IEtudiant {
    List<Etudiant> getAll();
    Object getEtudiantById (Long id);
    void saveEtudiant (Etudiant etudiant);
    void updateEtudiant (Long id, Etudiant etudiant);
    void deleteEtudiant (Long etudiantId);


}
