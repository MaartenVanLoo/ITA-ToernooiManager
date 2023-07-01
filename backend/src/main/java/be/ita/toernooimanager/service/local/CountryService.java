package be.ita.toernooimanager.service.local;

import be.ita.toernooimanager.model.local.Club;
import be.ita.toernooimanager.model.local.Country;
import be.ita.toernooimanager.repositories.local.ClubRepository;
import be.ita.toernooimanager.repositories.local.CountryRepository;
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
public class CountryService {
    CountryRepository countryRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Country createCountry(String name) throws AlreadyExistsException {
        if (countryRepository.findByCountryName(name).isPresent()){
            throw new AlreadyExistsException("A club with name: " + name + " already exists.");
        }
        if (countryRepository.findByCountryNameOrAliasesContainingIgnoreCase(name, name).isPresent()){
            throw new AlreadyExistsException("A club with this alias: " + name + " already exists");
        }
        Country country = new Country(name);
        country = countryRepository.save(country);
        return country;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Country addAlias(String countryName, String alias) throws AlreadyExistsException {
        Optional<Country> optCountry = countryRepository.findByCountryName(countryName);
        if (optCountry.isEmpty()){
            throw new AlreadyExistsException("A country with name: " + countryName + " doesn't exists.");
        }
        if (countryRepository.findByAliasesContainingIgnoreCase(alias).isPresent()){
            throw new AlreadyExistsException("A country with this alias: " + alias + " already exists");
        }
        Country country = optCountry.get();

        country.getAliases().add(alias);
        country = countryRepository.save(country);
        return country;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void removeAll(){
        log.info("Delete all countries");
        countryRepository.deleteAll();
    }
}
