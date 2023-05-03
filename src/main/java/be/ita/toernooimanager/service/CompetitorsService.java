package be.ita.toernooimanager.service;

import be.ita.toernooimanager.model.CompetitorsEntity;
import be.ita.toernooimanager.repositories.CompetitorsRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CompetitorsService {
    private final CompetitorsRepository competitorsRepository;

    public List<CompetitorsEntity> findAll(){
        return competitorsRepository.findAll();
    }
}
