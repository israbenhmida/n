package com.example.projet.Controller;


import com.example.projet.Interfaces.IEtudiant;
import com.example.projet.Entities.Etudiant;
import com.example.projet.Services.EtudiantImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor

@RequestMapping("/etudiant")
@CrossOrigin(origins = "http://localhost:4200")
public class EtudiantController {
    private final EtudiantImp etudiantService;



    @GetMapping("getAllEtudiant")
    public List<Etudiant> getAll() {
        return etudiantService.getAll();
    }

    @GetMapping("getEtudiant/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        Optional<Etudiant> etudiant = etudiantService.getEtudiantById(id);
        return etudiant.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/saveEtudiant")
    public ResponseEntity<Etudiant> saveEtudiant(@RequestBody Etudiant etudiant) {
        etudiantService.saveEtudiant(etudiant);
        return new ResponseEntity<>(etudiant, HttpStatus.CREATED);
    }

    @PutMapping("updateEtudiant/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable("id") Long id, @RequestBody Etudiant etudiant) {
        etudiantService.updateEtudiant(id, etudiant);
        return new ResponseEntity<>(etudiant, HttpStatus.OK);
    }

    @DeleteMapping("deleteEtudiant/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        try {
            etudiantService.deleteEtudiant(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
