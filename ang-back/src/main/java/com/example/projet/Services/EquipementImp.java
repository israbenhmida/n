package com.example.projet.Services;

import com.example.projet.Entities.Equipement;
import com.example.projet.Interfaces.IEquipement;
import com.example.projet.Repositories.EquipementRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class EquipementImp implements IEquipement {

    private EquipementRepository equipementRepository;

    @Override
    public List<Equipement> getAllEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    public Optional<Equipement> getEquipementById(Long id) {
        return equipementRepository.findById(id);
    }

    @Override
    public Equipement saveEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }
    @Override
    public Equipement updateEquipement(Long id, Equipement updatedEquipement) {
        Optional<Equipement> optionalEquipement = getEquipementById(id);
        if (optionalEquipement.isPresent()) {
            Equipement equipement = optionalEquipement.get();
            equipement.setNom(updatedEquipement.getNom());
            equipement.setPrixEquipement(updatedEquipement.getPrixEquipement());
            // Update other fields as needed
            return saveEquipement(equipement);
        }
        return null; // Handle appropriately if the equipment is not found
    }
    @Override
    public void deleteEquipement(Long id) {
        equipementRepository.deleteById(id);
    }

    @Override
    public void diminuerStock(Long id) {
        Optional<Equipement> optionalEquipement = getEquipementById(id);
        if (optionalEquipement.isPresent()) {
            Equipement equipement = optionalEquipement.get();
            equipement.diminuerStock();
            saveEquipement(equipement);
        }
    }

    @Override
    public void augmenterStock(Long id) {
        Optional<Equipement> optionalEquipement = getEquipementById(id);
        if (optionalEquipement.isPresent()) {
            Equipement equipement = optionalEquipement.get();
            equipement.augmenterStock();
            saveEquipement(equipement);
        }
    }

    @Override
    public List<Equipement> getAllEquipements(Sort sort) {
        return equipementRepository.findAll(sort);
    }
}
