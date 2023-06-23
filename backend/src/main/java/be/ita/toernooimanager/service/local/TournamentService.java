package be.ita.toernooimanager.service.local;

import be.ita.toernooimanager.model.local.Tournament;
import be.ita.toernooimanager.repositories.local.TournamentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;



@Slf4j
@Service
@AllArgsConstructor
@Validated
public class TournamentService {
    TournamentRepository tournamentRepository;
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Tournament createTournament(String name) {
        Tournament tournament = new Tournament(name);
        tournamentRepository.save(tournament);
        log.info("New tournament: " + tournament.getName() + "[" + tournament.getId()+ "]");
        return tournament;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void removeAll(){
        log.info("Delete all tournaments");
        tournamentRepository.deleteAll();
    }
}
