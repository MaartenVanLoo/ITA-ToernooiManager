package be.ita.toernooimanager.service;

import be.ita.toernooimanager.model.CompetitorsEntity;
import be.ita.toernooimanager.repositories.CompetitorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompetitorsService {
    private final CompetitorsRepository competitorsRepository;

    public List<CompetitorsEntity> findAll(){
        return competitorsRepository.findAll();
    }
}
