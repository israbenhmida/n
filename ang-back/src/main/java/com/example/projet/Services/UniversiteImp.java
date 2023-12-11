package com.example.projet.Services;

import com.example.projet.Entities.Club;
import com.example.projet.Entities.Foyer;
import com.example.projet.Entities.Universite;
import com.example.projet.Interfaces.IUniversite;
import com.example.projet.Repositories.ClubRepository;
import com.example.projet.Repositories.UniversiteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversiteImp implements IUniversite {

    @Autowired
    private UniversiteRepository universiteRepository;
    @Autowired
    private ClubRepository clubRepository ;
    @Override
    public List<Universite> getAll() {return universiteRepository.findAll();}

    @Override
    public Optional<Universite> getUniversiteById(Long id) {
        return universiteRepository.findById(id);
    }

    @Override
    public void saveUniversite(Universite universite) {
        universiteRepository.save(universite);
    }

    @Override
    public void updateUniversite(Long id, Universite updatedUniversite) {
        Universite existingUniversite = universiteRepository.findById(id).orElse(null);

        if (existingUniversite != null) {
            existingUniversite.setNomUniversite(updatedUniversite.getNomUniversite());
            existingUniversite.setAdresse(updatedUniversite.getAdresse());
            existingUniversite.setFoyer(updatedUniversite.getFoyer()); // Update the 'foyer' attribute

            universiteRepository.save(existingUniversite);
        } else {
            throw new IllegalArgumentException("Universite with ID " + id + " does not exist.");
        }
    }


    @Override
    public void deleteUniversite(Long universiteId) {
        Universite universite = universiteRepository.findById(universiteId).orElse(null);

        if (universite != null) {
            universiteRepository.delete(universite);
        } else {
            throw new IllegalArgumentException("Universite with ID " + universiteId + " does not exist.");
        }
    }
    public double calculateAverageFoyerCapacity() {
        List<Universite> universites = universiteRepository.findAll();
        if (universites.isEmpty()) {
            return 0; // ou une valeur par défaut appropriée
        }

        int totalFoyerCapacity = 0;
        int totalFoyers = 0;

        for (Universite universite : universites) {
            Foyer foyer = universite.getFoyer();
            if (foyer != null) {
                totalFoyerCapacity += foyer.getCapaciteFoyer();
                totalFoyers++;
            }

        }

        return totalFoyers > 0 ? (double) totalFoyerCapacity / totalFoyers : 0;
    }

    public List<Universite> searchUniversitesByName(String nomUniversite) {
        return universiteRepository.findUniversitesByNomUniversiteContainingIgnoreCase(nomUniversite);
    }
    public void assignClubToUniversity(Long universityId, Long clubId) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(universityId);
        Optional<Club> optionalClub = clubRepository.findById(clubId);

        if (optionalUniversite.isPresent() && optionalClub.isPresent()) {
            Universite universite = optionalUniversite.get();
            Club club = optionalClub.get();

            universite.getClubs().add(club);
            club.setUniversite(universite);

            universiteRepository.save(universite);
            clubRepository.save(club);
        }
    }
    public void removeClubFromUniversity(Long universityId, Long clubId) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(universityId);
        Optional<Club> optionalClub = clubRepository.findById(clubId);

        if (optionalUniversite.isPresent() && optionalClub.isPresent()) {
            Universite universite = optionalUniversite.get();
            Club club = optionalClub.get();

            universite.getClubs().remove(club); // Remove the club from the university's list
            club.setUniversite(null); // Set the university for the club to null

            universiteRepository.save(universite);
            clubRepository.save(club);
        }
    }

    public List<Club> getClubsByUniversityId(Long universiteId) {
        Universite universite = universiteRepository.findById(universiteId).orElse(null);
        if (universite != null) {
            return universite.getClubs();
        } else {
            // Gérer le cas où l'université n'est pas trouvée
            return null; // Ou une liste vide, selon vos besoins
        }
    }

//    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite( String nomUniversite) {
//        return reservationRepository.findbyNomUni(nomUniversite);

}


