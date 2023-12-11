package com.example.projet.Repositories;

import com.example.projet.Entities.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BibliothequeRepository extends JpaRepository<Bibliotheque,Long> {

}
