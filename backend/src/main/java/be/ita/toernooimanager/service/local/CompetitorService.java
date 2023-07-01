package be.ita.toernooimanager.service.local;

import be.ita.toernooimanager.model.local.Club;
import be.ita.toernooimanager.model.local.Competitor;
import be.ita.toernooimanager.model.local.Country;
import be.ita.toernooimanager.repositories.local.ClubRepository;
import be.ita.toernooimanager.repositories.local.CompetitorRepository;
import be.ita.toernooimanager.repositories.local.CountryRepository;
import be.ita.toernooimanager.service.Exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
@Validated
public class CompetitorService {
    CompetitorRepository competitorRepository;

    ClubRepository clubRepository; //TODO: user clubService instead?
    CountryRepository countryRepository; //TODO: user countryService instead?

    final Random rand = new Random();


    //region Create
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Competitor createCompetitor(String firstName, String lastName, String birthYear, Integer belt, Club club, Country country){
        //TODO: how about duplicates when 1 competitor want's to fight in 2 competitions => link competitor to competition?
        Competitor competitor = new Competitor(firstName,lastName,birthYear,belt, club,country);
        competitor.setWeightId(getNextWeightId());

        competitorRepository.save(competitor);
        return competitor;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Competitor createCompetitor(String firstName, String lastName, String birthYear, Integer belt, String clubName, String countryName){
        Club club = clubRepository.findByClubNameOrAliasesContainingIgnoreCase(clubName, clubName).orElseThrow(()->new ResourceNotFoundException("Could not find club with name: " + clubName));
        Country country = countryRepository.findByCountryNameOrAliasesContainingIgnoreCase(countryName, countryName).orElseThrow(()->new ResourceNotFoundException("Could not find country with name: " + countryName));
        Competitor competitor = createCompetitor(firstName,lastName,birthYear,belt,club,country);
        return competitor;
    }
    //endregion

    //region Delete
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void removeAll(){
        log.info("Delete all competitors");
        competitorRepository.deleteAll();
    }
    //endregion

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Competitor getCompetitorByWeightId(Integer weightId){
        return competitorRepository.findByWeightId(weightId).orElseThrow(()->new ResourceNotFoundException("No competitor with this id can be found"));
    }
    private int getNextWeightId(){
        if (competitorRepository.count() >= 9999) throw new RuntimeException("No more unique weight id's can be generated");
        //Determine proper weight id:
        int weightId = rand.nextInt(9999)+1; // value in range [1, 9999]
        while (competitorRepository.findByWeightId(weightId).isPresent()){
            weightId = rand.nextInt(9999)+1;
        }
        return weightId;
    }
}
