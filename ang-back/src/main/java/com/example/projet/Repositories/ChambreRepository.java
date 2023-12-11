package com.example.projet.Repositories;

import com.example.projet.Entities.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc")
    List<Chambre> findByBlocId(Long idBloc);
    boolean existsByNumeroChambre(Long numeroChambre);

}
