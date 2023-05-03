package be.ita.toernooimanager.service;

import be.ita.toernooimanager.model.MatchesEntity;
import be.ita.toernooimanager.repositories.MatchesRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MatchesService{
    private final MatchesRepository matchesRepository;

    public List<MatchesEntity> findAll(){
        return matchesRepository.findAll();
    }
}
