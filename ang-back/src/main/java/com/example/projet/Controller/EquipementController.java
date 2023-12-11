package com.example.projet.Controller;

import com.example.projet.Entities.Equipement;
import com.example.projet.Entities.Chambre;
//import com.example.projet.services.IEquipement;

import com.example.projet.Interfaces.IChambre;
import com.example.projet.Interfaces.IEquipement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipement")
public class EquipementController {

    @Autowired
    private IEquipement equipementService;

    @Autowired
    private IChambre chambreService;

    @GetMapping
    public List<Equipement> getAllEquipements(@RequestParam(required = false) String sortOrder) {
        Sort.Direction direction = Sort.Direction.ASC; // Default sorting direction

        if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }

        Sort sort = Sort.by(direction, "prixEquipement"); // Replace "yourSortingField" with the actual field to sort by

        return equipementService.getAllEquipements(sort);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Equipement> getEquipementById(@PathVariable Long id) {
        Optional<Equipement> equipement = equipementService.getEquipementById(id);
        return equipement.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Equipement saveEquipement(@RequestBody Equipement equipement) {
        return equipementService.saveEquipement(equipement);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Equipement> updateEquipement(@PathVariable Long id, @RequestBody Equipement updatedEquipement) {
        Equipement updated = equipementService.updateEquipement(id, updatedEquipement);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public void deleteEquipement(@PathVariable Long id) {
        equipementService.deleteEquipement(id);
    }

    @PostMapping("/{id}/diminuerStock")
    public void diminuerStock(@PathVariable Long id) {
        equipementService.diminuerStock(id);
    }

    @PostMapping("/{id}/augmenterStock")
    public void augmenterStock(@PathVariable Long id) {
        equipementService.augmenterStock(id);
    }

    @PostMapping("/add-to-chambre/{equipementId}/{chambreId}")
    public ResponseEntity<String> addEquipementToChambre(@PathVariable Long equipementId, @PathVariable Long chambreId) {
        Optional<Equipement> equipementOptional = equipementService.getEquipementById(equipementId);
        Optional<Chambre> chambreOptional = (Optional<Chambre>) chambreService.getChambreById(chambreId);

        if (equipementOptional.isPresent() && chambreOptional.isPresent()) {
            Equipement equipement = equipementOptional.get();
            Chambre chambre = chambreOptional.get();

            // Add the equipement to the chambre
            chambre.getEquipements().add(equipement);
            chambreService.saveChambre(chambre); // Assuming you have a method like saveChambre in your ChambreService

            return ResponseEntity.ok("Equipement added to Chambre successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
