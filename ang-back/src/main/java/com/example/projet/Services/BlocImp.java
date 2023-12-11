package com.example.projet.Services;


import com.example.projet.Entities.Bloc;
import com.example.projet.Entities.Foyer;
import com.example.projet.Interfaces.IBloc;
import com.example.projet.Repositories.BlocRepository;
import com.example.projet.Repositories.FoyerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class BlocImp implements IBloc {

    private BlocRepository blocRepository;
    FoyerRepository foyerRepository;

    @Override
    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Optional<Bloc> getBlocById(Long id) {
        return blocRepository.findById(id);
    }

    @Override
    public void saveBloc(Bloc bloc) {
        blocRepository.save(bloc);
    }

    @Override
    public void updateBloc(Long id, Bloc updatedBloc) {
        Bloc existingBloc = blocRepository.findById(id).orElse(null);

        if (existingBloc != null) {
            existingBloc.setNomBloc(updatedBloc.getNomBloc());
            existingBloc.setCapaciteBloc(updatedBloc.getCapaciteBloc());
            existingBloc.setChambres(updatedBloc.getChambres());
            blocRepository.save(existingBloc);
        } else {
            throw new IllegalArgumentException("Bloc with ID " + id + " does not exist.");
        }
    }


    @Override
    public void deleteBloc(Long blocId) {
        Bloc bloc = blocRepository.findById(blocId).orElse(null);

        if (bloc != null) {
            blocRepository.delete(bloc);
        } else {
            throw new IllegalArgumentException("Bloc with ID " + blocId + " does not exist.");
        }
    }

    @Override
    public void addBloctoFoyer(Long idFoyer, Bloc bloc) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        bloc.setFoyer(foyer);
        foyer.getBlocs().add(bloc);
        foyerRepository.save(foyer);
    }

    @Override
    public List<Bloc> getBlocsByFoyerId(Long foyerId) {
        // Effectuer la requête pour récupérer les blocs associés au foyer
        return blocRepository.findByFoyerIdFoyer(foyerId);
    }
    @Override
    public void mettreAJourblocDansFoyer(Long idFoyer, Long idBloc, Bloc bloc) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        Bloc existingbloc = blocRepository.findById(idBloc).orElse(null);
        existingbloc.setNomBloc(bloc.getNomBloc());
        existingbloc.setCapaciteBloc(bloc.getCapaciteBloc());
        blocRepository.save(existingbloc);
    }
}
