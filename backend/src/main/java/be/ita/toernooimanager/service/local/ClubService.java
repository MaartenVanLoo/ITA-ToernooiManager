package be.ita.toernooimanager.service.local;


import be.ita.toernooimanager.model.local.Club;
import be.ita.toernooimanager.repositories.local.ClubRepository;
import be.ita.toernooimanager.service.Exceptions.AlreadyExistsException;
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
public class ClubService {
    ClubRepository clubRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Club createClub(String name) throws AlreadyExistsException {
        if (clubRepository.findByClubName(name).isPresent()){
            throw new AlreadyExistsException("A club with name: " + name + " already exists.");
        }
        if (clubRepository.findByClubNameOrAliasesContainingIgnoreCase(name, name).isPresent()){
            throw new AlreadyExistsException("A club with this alias: " + name + " already exists");
        }
        Club club = new Club(name);
        club = clubRepository.save(club);
        return club;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Club addAlias(String clubName, String alias) throws AlreadyExistsException {
        Optional<Club> optClub = clubRepository.findByClubName(clubName);
        if (optClub.isEmpty()){
            throw new AlreadyExistsException("A club with name: " + clubName + " doesn't exists.");
        }
        if (clubRepository.findByAliasesContainingIgnoreCase(alias).isPresent()){
            throw new AlreadyExistsException("A club with this alias: " + alias + " already exists");
        }
        Club club = optClub.get();

        club.getAliases().add(alias);
        club = clubRepository.save(club);
        return club;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void removeAll(){
        log.info("Delete all clubs");
        clubRepository.deleteAll();
    }
}
