package com.example.projet.Interfaces;

import com.example.projet.Entities.Club;

import java.util.List;

public interface IClub {

    List<Club> getAll();
    Object getClubById (Long id);
    void saveClub (Club Club);
    void updateClub (Long id, Club Club);
    void deleteClub (Long ClubId);




}
