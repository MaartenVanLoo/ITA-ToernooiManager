package be.ita.toernooimanager.service.local;

import be.ita.toernooimanager.model.local.Club;
import be.ita.toernooimanager.model.local.Competitor;
import be.ita.toernooimanager.repositories.local.ClubRepository;
import be.ita.toernooimanager.repositories.local.CompetitorRepository;
import be.ita.toernooimanager.service.Exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Validated
public class CompetitorService {
    CompetitorRepository competitorRepository;

    ClubRepository clubRepository; //TODO: user clubService instead?

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Competitor createCompetitor(String firstName, String lastName, String birthYear, Integer belt, Club club, String country){
        //TODO: how about duplicates when 1 competitor want's to fight in 2 competitions => link competitor to competition?
        Competitor competitor = new Competitor(firstName,lastName,birthYear,belt, club,country);
        competitorRepository.save(competitor);
        return competitor;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Competitor createCompetitor(String firstName, String lastName, String birthYear, Integer belt, String clubName, String country){
        Club club = clubRepository.findByClubNameOrAliasesContainingIgnoreCase(clubName, clubName).orElseThrow(()->new ResourceNotFoundException("Could not find club with name: " + clubName));
        Competitor competitor = createCompetitor(firstName,lastName,birthYear,belt,club,country);
        return competitor;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void removeAll(){
        log.info("Delete all competitors");
        competitorRepository.deleteAll();
    }
}
