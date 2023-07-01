package be.ita.toernooimanager.controller.local;

import be.ita.toernooimanager.controller.DTO.CompetitorGetDto;
import be.ita.toernooimanager.model.local.Competition;
import be.ita.toernooimanager.model.local.Competitor;
import be.ita.toernooimanager.service.Exceptions.ResourceNotFoundException;
import be.ita.toernooimanager.service.local.CompetitionService;
import be.ita.toernooimanager.service.local.CompetitorService;
import be.ita.toernooimanager.service.local.TournamentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * API for weight computers
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/weight")
@RequiredArgsConstructor
public class WeightController {
    private final ModelMapper modelMapper;
    TournamentService tournamentService;
    CompetitionService competitionService;
    CompetitorService competitorService;
    @GetMapping("/competitor/{weightId}")
    @PreAuthorize("hasAuthority('weeg_read')")
    public ResponseEntity<CompetitorGetDto> getCompetitor(@PathVariable("weightId") Integer weightId,
        @RequestHeader("tournament_id") UUID tournamentId,
        @RequestHeader("competition_id") UUID competitionId) throws IllegalAccessException {
        log.info("Weight get competitor: " + weightId);

        //Check if requested tournament id is present
        if (tournamentId == null || tournamentService.getTournament(tournamentId) == null)
            throw new ResourceNotFoundException("Tournament id not found");

        //Check if requested competition id exists and competition is running
        if (competitionId == null)
            throw new ResourceNotFoundException("Competition id not found");
        Competition competition = competitionService.getCompetition(competitionId);
        if (competition == null){ //Not needed, service will already throw an error!
            throw new ResourceNotFoundException("Competition id not found");
        }
        if (!competition.isWeighing()){
            throw new IllegalAccessException("This competition is not yet weighing. Contact the administrator to start.");
        }

        //Get competitor
        Competitor competitor = competitorService.getCompetitorByWeightId(weightId);

        //Map competitor
        return ResponseEntity.ok(modelMapper.map(competitor, CompetitorGetDto.class));
    }
}
