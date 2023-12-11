package com.example.projet.Services;

import com.example.projet.Entities.Bibliotheque;
import com.example.projet.Entities.Bloc;
import com.example.projet.Interfaces.IBibliotheque;
import com.example.projet.Repositories.BibliothequeRepository;
import com.example.projet.Repositories.BlocRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class BibliothequeImp implements IBibliotheque {
    private BibliothequeRepository bibliothequeRepository;
    private BlocRepository blocRepository;

    @Override
    public List<Bibliotheque> getAll() {
        return bibliothequeRepository.findAll();
    }

    @Override
    public Optional<Bibliotheque> getBibliothequeById(Long id) {
        return bibliothequeRepository.findById(id);
    }

    @Override
    public void saveBibliotheque(Bibliotheque bibliotheque) {
        bibliothequeRepository.save(bibliotheque);
    }

    @Override
    public void updateBibliotheque(Long id, Bibliotheque updatedBibliotheque) {
        Bibliotheque existingBibliotheque = bibliothequeRepository.findById(id).orElse(null);

        if (existingBibliotheque != null) {
            existingBibliotheque.setTitreBibliotheque(updatedBibliotheque.getTitreBibliotheque());
            existingBibliotheque.setDescriptionBibliotheque(updatedBibliotheque.getDescriptionBibliotheque());
            existingBibliotheque.setBloc(updatedBibliotheque.getBloc()); // Update the 'bibliotheque' attribute

            bibliothequeRepository.save(existingBibliotheque);
        } else {
            throw new IllegalArgumentException("Bibliotheque with ID " + id + " does not exist.");
        }
    }


    @Override
    public void deleteBibliotheque(Long bibliothequeId) {
        Bibliotheque bibliotheque = bibliothequeRepository.findById(bibliothequeId).orElse(null);

        if (bibliotheque != null) {
            bibliothequeRepository.delete(bibliotheque);
        } else {
            throw new IllegalArgumentException("Bibliotheque with ID " + bibliothequeId + " does not exist.");
        }
    }

    @Override
    public void ajouterBibliothequeAuBloc(Long idBloc, Bibliotheque bibliotheque) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        bibliotheque.setBloc(bloc);
        bloc.setBibliotheque(bibliotheque);
        blocRepository.save(bloc);
    }



    @Override
    public void mettreAJourBibliothequeDansBloc(Long idBloc, Long idBibliotheque, Bibliotheque bibliotheque) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        Bibliotheque existingBibliotheque = bibliothequeRepository.findById(idBibliotheque).orElse(null);
        existingBibliotheque.setTitreBibliotheque(bibliotheque.getTitreBibliotheque());
        existingBibliotheque.setDescriptionBibliotheque(bibliotheque.getDescriptionBibliotheque());

        bibliothequeRepository.save(existingBibliotheque);
    }

    @Override
    public Bibliotheque getBibliothequeByBlocId(Long idBloc) {
        Optional<Bloc> optionalBloc = blocRepository.findById(idBloc);

        if (optionalBloc.isPresent()) {
            return optionalBloc.get().getBibliotheque();
        } else {
            throw new RuntimeException("Bloc non trouvé avec l'ID : " + idBloc);
        }
    }

}



