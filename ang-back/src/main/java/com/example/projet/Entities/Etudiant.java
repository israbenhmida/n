package com.example.projet.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEtudiant ;
    String nomEt;
    String prenomEt;
    Long cin;
    String ecole;
    Date dateNaissance;
    @ManyToMany(mappedBy="etudiants", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
