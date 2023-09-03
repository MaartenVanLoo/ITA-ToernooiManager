package be.ita.toernooimanager.service.local;

import be.ita.toernooimanager.model.local.Competition;
import be.ita.toernooimanager.model.local.Tournament;
import be.ita.toernooimanager.model.local.config.CompetitionConfig;
import be.ita.toernooimanager.repositories.local.CompetitionRepository;
import be.ita.toernooimanager.service.Exceptions.AlreadyExistsException;
import be.ita.toernooimanager.service.Exceptions.ResourceNotFoundException;
import be.ita.toernooimanager.service.local.config.CompetitionConfigService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
@Validated
public class CompetitionService {
    TournamentService tournamentService;
    CompetitionConfigService competitionConfigService;

    CompetitionRepository competitionRepository;

    //region Create
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Competition createCompetition(UUID tournamentId, String config) throws AlreadyExistsException {
        return createCompetition(tournamentId,config,null);
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Competition createCompetition(UUID tournamentId, String config, String competitionName) throws AlreadyExistsException {
        Tournament tournament = tournamentService.getTournament(tournamentId);

        CompetitionConfig competitionConfig = competitionConfigService.getCompetitionConfig(config);

        if (competitionName == null ||competitionName.equals("")) competitionName = competitionConfig.getCompetitionName();

        if (competitionRepository.findByName(competitionName).isPresent()){
            throw new AlreadyExistsException("Competition with name:" +competitionName + "already exists.");
        }
        Competition competition = Competition.fromConfig(tournament, competitionConfig, competitionName);
        competitionRepository.save(competition);
        log.info("New competition: " + competition.getName() + "[" + competition.getId()+ "]");
        return competition;
    }
    //endregion

    //region Get
    @Transactional(propagation = Propagation.REQUIRED)
    public Competition getCompetition(UUID id){
        Competition competition = competitionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id.toString()));
        return competition;
    }
    //endregion

    //region Delete
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeAll(){
        log.info("Delete all competitions");
        competitionRepository.deleteAll();
    }
    //endregion

    //region Update
    public void startWeighing(UUID competitionId){
        Competition competition= this.getCompetition(competitionId);
        competition.setWeighing(true);
        competitionRepository.save(competition);
    }
}
