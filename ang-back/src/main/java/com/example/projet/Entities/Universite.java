package com.example.projet.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUniversite ;
    String nomUniversite;
    String adresse;

    @OneToOne

    Foyer foyer;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="universite")

    private List<Club> clubs;
}

