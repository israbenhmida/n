package com.example.projet.Services;

import com.example.projet.Entities.Bloc;
import com.example.projet.Entities.Chambre;
import com.example.projet.Entities.Equipement;
import com.example.projet.Entities.Reservation;
import com.example.projet.Interfaces.IChambre;
import com.example.projet.Repositories.BlocRepository;
import com.example.projet.Repositories.ChambreRepository;
import com.example.projet.Repositories.EquipementRepository;
import com.example.projet.Repositories.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ChambreImp implements IChambre {

    private ChambreRepository chambreRepository;
    private EquipementRepository equipementRepository;
    private ReservationRepository reservationRepository;
    private BlocRepository blocRepository;

    @Override
    public List<Chambre> getAllChambre() {
        return chambreRepository.findAll();
    }

    @Override
    public Optional<Chambre> getChambreById(Long id) {
        return chambreRepository.findById(id);
    }

    @Override
    public void saveChambre(Chambre chambre) {
        chambreRepository.save(chambre);
    }

    @Override
    public boolean isNumeroChambreExists(Long numeroChambre) {
        return chambreRepository.existsByNumeroChambre(numeroChambre);
    }

    @Override
    public void updateChambre(Long id, Chambre updatedChambre) {
        Chambre existingChambre = chambreRepository.findById(id).orElse(null);

        if (existingChambre != null) {
            existingChambre.setNumeroChambre(updatedChambre.getNumeroChambre());
//            existingChambre.setIdChambre(updatedChambre.getIdChambre());
            existingChambre.setBloc(updatedChambre.getBloc()); // Update the 'foyer' attribute

            chambreRepository.save(existingChambre);
        } else {
            throw new IllegalArgumentException("Chambre with ID " + id + " does not exist.");
        }
    }


    @Override
    public void deleteChambre(Long chambreId) {
        Chambre chambre = chambreRepository.findById(chambreId).orElse(null);

        if (chambre != null) {
            chambreRepository.delete(chambre);
        } else {
            throw new IllegalArgumentException("Chambre with ID " + chambreId + " does not exist.");
        }
    }
    @Override
    public Chambre addEquipementToChambre(Long chambreId, Long equipementId) {
        Chambre chambre = chambreRepository.findById(chambreId).orElse(null);
        Equipement equipement = equipementRepository.findById(equipementId).orElse(null);

        if (chambre != null && equipement != null) {
            chambre.getEquipements().add(equipement);
            equipement.getChambres().add(chambre);
            chambreRepository.save(chambre);
        }

        return chambre;
    }

    @Override
    public Chambre removeEquipementFromChambre(Long chambreId, Long equipementId) {
        Chambre chambre = chambreRepository.findById(chambreId).orElse(null);
        Equipement equipement = equipementRepository.findById(equipementId).orElse(null);

        if (chambre != null && equipement != null) {
            chambre.getEquipements().remove(equipement);
            equipement.getChambres().remove(chambre);
            chambreRepository.save(chambre);
        }

        return chambre;
    }

    @Override
    public void addChambreToBloc(Long idBloc, Chambre chambre) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        chambre.setBloc(bloc);
        chambre.calculateInitialPrice();
        bloc.getChambres().add(chambre);
        chambreRepository.save(chambre);
    }
    @Override
    public void removeChambreFromBloc(Long idBloc, Long idChambre) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        Chambre chambreToRemove = chambreRepository.findById(idChambre).orElse(null);

        if (bloc != null && chambreToRemove != null) {
            bloc.getChambres().remove(chambreToRemove);
            chambreRepository.delete(chambreToRemove);
        }
    }
    @Override
    public List<Chambre> getChambresByBlocId(Long idBloc) {
        return chambreRepository.findByBlocId(idBloc);
    }

    @Override
    public void mettreAJourChambreDansBloc(Long idBloc, Long idChambre, Chambre chambre) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        Chambre existingChambre = chambreRepository.findById(idChambre).orElse(null);
        existingChambre.setNumeroChambre(chambre.getNumeroChambre());
        existingChambre.setTypeC(chambre.getTypeC());
        // Update other fields as needed
        chambreRepository.save(existingChambre);
    }

    public Chambre addReservationToChambre(Long chambreId, Reservation reservation) {
        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new EntityNotFoundException("Chambre introuvable avec l'ID : " + chambreId));

        if (chambre.getReservations() == null) {
            chambre.setReservations(new HashSet<>());
        }

        reservation.setChambre(chambre);
        chambre.getReservations().add(reservation);

        return chambreRepository.save(chambre);
    }
    public Chambre deleteReservationFromChambre(Long chambreId, Long reservationId) {
        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new EntityNotFoundException("Chambre not found with ID: " + chambreId));

        if (chambre.getReservations() != null) {
            chambre.getReservations().removeIf(reservation -> reservation.getIdReservation().equals(reservationId));
        }

        return chambreRepository.save(chambre);
    }

}

