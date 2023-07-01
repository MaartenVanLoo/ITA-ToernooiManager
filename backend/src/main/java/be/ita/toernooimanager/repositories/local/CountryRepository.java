package be.ita.toernooimanager.repositories.local;

import be.ita.toernooimanager.model.local.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
    Optional<Country> findByCountryNameOrAliasesContainingIgnoreCase(String countryName, String alias);
}
