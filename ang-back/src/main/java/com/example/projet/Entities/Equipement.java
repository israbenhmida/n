package com.example.projet.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Equipement implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long idEquipement;
        String nom;
        private double prixEquipement;
        private int nombreTotal;
        @JsonIgnore
        @ManyToMany(mappedBy = "equipements")
        private Set<Chambre> chambres = new HashSet<>();

        // Méthode pour diminuer le nombre d'équipements en stock
        public <T> Optional<T> map(Function<Equipement, T> mapper) {
                return Optional.ofNullable(mapper.apply(this));
        }

        public Equipement orElseThrow() {
                return this;
        }
        public void diminuerStock() {
                if (nombreTotal > 0) {
                        nombreTotal--;
                } else {
                        // Gérer le cas où le stock est déjà épuisé
                        throw new IllegalStateException("Le stock est épuisé pour cet équipement.");
                }
        }

        // Méthode pour augmenter le nombre d'équipements en stock
        public void augmenterStock() {
                nombreTotal++;
        }


}




