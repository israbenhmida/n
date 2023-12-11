package com.example.projet.Repositories;

import com.example.projet.Entities.Bloc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlocRepository extends JpaRepository<Bloc,Long> {
    List<Bloc> findByFoyerIdFoyer(Long foyerId);
}
