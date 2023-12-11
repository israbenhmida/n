package com.example.projet.Entities;

import com.example.projet.Enums.TypeChambre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Chambre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idChambre ;
    @Column(unique = true)
    Long numeroChambre;
    @Enumerated(EnumType.STRING)
    TypeChambre typeC;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_bloc")
    Bloc bloc;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Reservation> reservations;
    //    @ManyToMany
//    @JoinTable(
//            name = "chambre_equipement",
//            joinColumns = @JoinColumn(name = "id_chambre"),
//            inverseJoinColumns = @JoinColumn(name = "id_equipement"))
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "chambre_equipement",
            joinColumns = @JoinColumn(name = "id_chambre"),
            inverseJoinColumns = @JoinColumn(name = "id_equipement"))
    private Set<Equipement> equipements = new HashSet<>();    private double prix;

    // Method to calculate the price of the room
    // Method to calculate the initial price of the room based on type
    @Transient
    public double calculateInitialPrice() {
        switch (typeC) {
            case SIMPLE:
                prix = 250.0;
                break;
            case DOUBLE:
                prix = 200.0;
                break;
            case TRIPLE:
                prix = 150.0;
                break;
            // Add more cases for other room types if needed
            default:
                // Default price if the room type is not recognized
                prix = 0.0;
        }

        // Add the prices of the included equipment
        for (Equipement equipement : equipements) {
            prix += equipement.getPrixEquipement();
        }

        // Set the calculated price to the prix attribute
        this.prix = prix;

        return prix;
    }

}
