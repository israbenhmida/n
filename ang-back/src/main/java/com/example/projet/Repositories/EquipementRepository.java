package com.example.projet.Repositories;

import com.example.projet.Entities.Chambre;
import com.example.projet.Entities.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipementRepository extends JpaRepository<Equipement,Long> {
}
