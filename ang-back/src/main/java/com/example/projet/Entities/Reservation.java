package com.example.projet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idReservation ;

    Date anneUniversitaire;

    Boolean estValide;
    @ManyToOne
    @JsonIgnore
    Restaurant restaurant;
    @ManyToOne
    Chambre chambre;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Etudiant> etudiants;

}

