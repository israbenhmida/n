package com.example.projet.Controller;

import com.example.projet.Entities.Bibliotheque;
import com.example.projet.Entities.Bloc;
import com.example.projet.Interfaces.IBloc;
import com.example.projet.Entities.Bloc;
import com.example.projet.Services.BibliothequeImp;
import com.example.projet.Services.BlocImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("bloc")
public class BlocController {
 private final BibliothequeImp bibliothequeService;

 private final BlocImp blocService;

 @GetMapping
 public List<Bloc> getAllBlocs() {
  return blocService.getAllBlocs();
 }

 @GetMapping("/{id}")
 public ResponseEntity<Bloc> getBlocById(@PathVariable Long id) {
  Optional<Bloc> bloc = blocService.getBlocById(id);
  return bloc.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
 }

 @PostMapping
 public ResponseEntity<Bloc> saveBloc(@RequestBody Bloc bloc) {
  blocService.saveBloc(bloc);
  return new ResponseEntity<>(bloc, HttpStatus.CREATED);
 }

 @PutMapping("/{id}")
 public ResponseEntity<Bloc> updateBloc(@PathVariable("id") Long id, @RequestBody Bloc bloc) {
  blocService.updateBloc(id, bloc);
  return new ResponseEntity<>(bloc, HttpStatus.OK);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteBloc(@PathVariable Long id) {
  try {
   blocService.deleteBloc(id);
   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } catch (IllegalArgumentException e) {
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }
 @PostMapping("/{idFoyer}/add-bloc")
 public ResponseEntity<Object> addBloctoFoyer(
         @PathVariable Long idFoyer,
         @RequestBody Bloc bloc
 ) {
  try {
   blocService.addBloctoFoyer(idFoyer, bloc);
   Map<String, String> response = new HashMap<>();
   response.put("message", "Bloc ajouté avec succès au foyer.");
   return ResponseEntity.ok(response);
  } catch (RuntimeException e) {
   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }
 }
 @PutMapping("/{idFoyer}/bloc/{idBloc}")
 public ResponseEntity<Void> mettreAJourBlocDansFoyer(
         @PathVariable Long idFoyer,
         @PathVariable Long idBloc,
         @RequestBody Bloc bloc) {

  blocService.mettreAJourblocDansFoyer(idFoyer, idBloc, bloc);
  return new ResponseEntity<>(HttpStatus.OK);
 }

 @GetMapping("/{idBloc}/bibliotheque")
 public ResponseEntity<Bibliotheque> getBibliothequeByBlocId(@PathVariable Long idBloc) {
  try {
   Bibliotheque bibliotheque = bibliothequeService.getBibliothequeByBlocId(idBloc);
   return new ResponseEntity<>(bibliotheque, HttpStatus.OK);
  } catch (RuntimeException e) {
   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }
 }
}

