package com.example.projet.Services;

import com.example.projet.Entities.Etudiant;
import com.example.projet.Interfaces.IEtudiant;
import com.example.projet.Repositories.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EtudiantImp implements IEtudiant {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public List<Etudiant> getAll() {
        return etudiantRepository.findAll();
    }

    @Override
    public Optional<Etudiant> getEtudiantById(Long id) {
        return etudiantRepository.findById(id);
    }

    @Override
    public void saveEtudiant(Etudiant etudiant) {
        etudiantRepository.save(etudiant);
    }

    @Override
    public void updateEtudiant(Long id, Etudiant updatedEtudiant) {
        Etudiant existingEtudiant = etudiantRepository.findById(id).orElse(null);

        if (existingEtudiant != null) {
            existingEtudiant.setNomEt(updatedEtudiant.getNomEt());
            existingEtudiant.setPrenomEt(updatedEtudiant.getPrenomEt());
            existingEtudiant.setCin(updatedEtudiant.getCin());
            existingEtudiant.setDateNaissance(updatedEtudiant.getDateNaissance());
            existingEtudiant.setEcole(updatedEtudiant.getEcole());
            existingEtudiant.setReservations(updatedEtudiant.getReservations()); // Update the 'foyer' attribute

            etudiantRepository.save(existingEtudiant);
        } else {
            throw new IllegalArgumentException("Etudiant with ID " + id + " does not exist.");
        }
    }

    @Override
    public void deleteEtudiant(Long etudiantId) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);

        if (etudiant != null) {
            etudiantRepository.delete(etudiant);
        } else {
            throw new IllegalArgumentException("Etudiant with ID " + etudiantId + " does not exist.");
        }
    }}
