package be.ita.toernooimanager.service.local;

import be.ita.toernooimanager.model.local.Tournament;
import be.ita.toernooimanager.repositories.local.TournamentRepository;
import be.ita.toernooimanager.service.Exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
@Validated
public class TournamentService {
    TournamentRepository tournamentRepository;


    //region Create
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Tournament createTournament(String name) {
        Tournament tournament = new Tournament(name);
        tournamentRepository.save(tournament);
        log.info("New tournament: " + tournament.getName() + "[" + tournament.getId()+ "]");
        return tournament;
    }
    //endregion

    //region Get
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Tournament getTournament(UUID id){
        Tournament tournament = tournamentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id.toString()));
        return tournament;
    }
    //endregion

    //region Delete
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void removeAll(){
        log.info("Delete all tournaments");
        tournamentRepository.deleteAll();
    }
    //endregion
}
