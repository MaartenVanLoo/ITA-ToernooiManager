package be.ita.toernooimanager.service.shiai;

import be.ita.toernooimanager.model.shiai.MatchesEntity;
import be.ita.toernooimanager.repositories.shiai.MatchesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MatchesService{
    private final MatchesRepository matchesRepository;

    public List<MatchesEntity> findAll(){
        return matchesRepository.findAll();
    }
}
