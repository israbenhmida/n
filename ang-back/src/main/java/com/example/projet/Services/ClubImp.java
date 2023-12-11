package com.example.projet.Services;

import com.example.projet.Entities.Club;
import com.example.projet.Interfaces.IClub;
import com.example.projet.Repositories.ClubRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClubImp implements IClub {

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public List<Club> getAll() {
        return clubRepository.findAll();
    }

    @Override
    public Optional<Club> getClubById(Long id) {
        return clubRepository.findById(id);
    }

    @Override
    public void saveClub(Club Club) {
        clubRepository.save(Club);
    }

    @Override
    public void updateClub(Long id, Club updatedClub) {
        Club existingClub = clubRepository.findById(id).orElse(null);

        if (existingClub != null) {
            existingClub.setIdClub(updatedClub.getIdClub());
            existingClub.setNomClub(updatedClub.getNomClub());
            existingClub.setDateCreation(updatedClub.getDateCreation());
            existingClub.setLienSocial(updatedClub.getLienSocial());
            existingClub.setUniversite(updatedClub.getUniversite());

            clubRepository.save(existingClub);
        } else {
            throw new IllegalArgumentException("Club with ID " + id + " does not exist.");
        }
    }

    @Override
    public void deleteClub(Long ClubId) {
        Club Club = clubRepository.findById(ClubId).orElse(null);

        if (Club != null) {
            clubRepository.delete(Club);
        } else {
            throw new IllegalArgumentException("Club with ID " + ClubId + " does not exist.");
        }
    }}





