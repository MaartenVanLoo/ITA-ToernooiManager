package be.ita.toernooimanager;

import be.ita.toernooimanager.model.local.*;
import be.ita.toernooimanager.model.local.acl.Privilege;
import be.ita.toernooimanager.service.Exceptions.AlreadyExistsException;
import be.ita.toernooimanager.service.local.*;
import be.ita.toernooimanager.service.local.acl.PrivilegeService;
import be.ita.toernooimanager.service.local.acl.RoleService;
import be.ita.toernooimanager.service.local.acl.UserService;
import be.ita.toernooimanager.service.local.config.CategoryConfigService;
import be.ita.toernooimanager.service.local.config.CompetitionConfigService;
import be.ita.toernooimanager.service.local.config.PouleSettingsService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
@Lazy(false)
@Profile("!prod")
public class DataLoader {
    private static final String pouleSettingsFile = "/PouleSettings.json";
    private static final String competitionsConfigFile = "/configs/CompetitionConfig.json";
    private static final String categoryConfigFile = "/configs/CategoryConfig.json";

    PrivilegeService privilegeService;
    RoleService roleService;
    UserService userService;
    PouleSettingsService pouleSettingsService;
    CompetitionConfigService competitionConfigService;
    CategoryConfigService categoryConfigService;

    TournamentService tournamentService;
    CompetitionService competitionService;
    TatamiService tatamiService;
    ClubService clubService;
    CountryService countryService;
    CompetitorService competitorService;

    @PostConstruct
    public void initData() throws AlreadyExistsException {
        log.info("Data loader started...");
        createPrivileges();
        createRoles();
        createUsers();
        loadSettings();

        //Create new tournament
        Tournament tournament = createTournament();
        List<Competition> competitions  = createCompetition(tournament);
        List<Tatami> tatamis = createTatamis();

        //Create clubs
        List<Club> clubs = createClubs();
        List<Country> countries = createCountries();

        List<Competitor> competitors = createCompetitors(clubs);
        log.info("Data loader complete...");
    }

    private void createPrivileges() {
        try {
            //Admin user (more control if ever needed)
            privilegeService.createPrivilege("admin_read", "");
            privilegeService.createPrivilege("admin_write", "");
            //Centrale tafel
            privilegeService.createPrivilege("manager_read", "");
            privilegeService.createPrivilege("manager_write", "");
            //Info pc's => only read?
            privilegeService.createPrivilege("info_read", "");
            //Weeg pc's
            privilegeService.createPrivilege("weeg_read","");
            privilegeService.createPrivilege("weeg_write","");
            //Tatami's
            privilegeService.createPrivilege("tatami_read","");
            privilegeService.createPrivilege("tatami_write","");
            //Everyone who is logged in
            privilegeService.createPrivilege("logon","");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void createRoles(){
        try{
            Set<Privilege> infoPrivileges = new HashSet<>();
            infoPrivileges.add(privilegeService.getPrivilegeByName("info_read"));
            infoPrivileges.add(privilegeService.getPrivilegeByName("logon"));
            roleService.createRole("Info","", infoPrivileges);

            Set<Privilege> weegPrivileges = new HashSet<>();
            weegPrivileges.add(privilegeService.getPrivilegeByName("weeg_read"));
            weegPrivileges.add(privilegeService.getPrivilegeByName("weeg_write"));
            weegPrivileges.add(privilegeService.getPrivilegeByName("logon"));
            roleService.createRole("Weeg","", weegPrivileges);

            Set<Privilege> tatamiPrivileges = new HashSet<>();
            tatamiPrivileges.add(privilegeService.getPrivilegeByName("tatami_read"));
            tatamiPrivileges.add(privilegeService.getPrivilegeByName("tatami_write"));
            tatamiPrivileges.add(privilegeService.getPrivilegeByName("logon"));
            roleService.createRole("Tatami","", tatamiPrivileges);

            Set<Privilege> managerPrivileges = new HashSet<>();
            managerPrivileges.add(privilegeService.getPrivilegeByName("manager_read"));
            managerPrivileges.add(privilegeService.getPrivilegeByName("manager_write"));
            managerPrivileges.add(privilegeService.getPrivilegeByName("info_read"));
            managerPrivileges.add(privilegeService.getPrivilegeByName("weeg_read"));
            managerPrivileges.add(privilegeService.getPrivilegeByName("weeg_write"));
            managerPrivileges.add(privilegeService.getPrivilegeByName("tatami_read"));
            managerPrivileges.add(privilegeService.getPrivilegeByName("tatami_write"));
            managerPrivileges.add(privilegeService.getPrivilegeByName("logon"));
            roleService.createRole("Manager","", managerPrivileges);

            Set<Privilege> adminPrivileges = new HashSet<>();
            adminPrivileges.add(privilegeService.getPrivilegeByName("admin_read"));
            adminPrivileges.add(privilegeService.getPrivilegeByName("admin_write"));
            adminPrivileges.addAll(managerPrivileges);
            roleService.createRole("Admin","", adminPrivileges);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void createUsers(){
        try{
            userService.createUser("Info","",roleService.getRoleByName("Info"));
            userService.createUser("Weeg","",roleService.getRoleByName("Weeg"));
            userService.createUser("Tatami1","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami2","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami3","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami4","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami5","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami6","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami7","",roleService.getRoleByName("Tatami"));
            userService.createUser("Tatami8","",roleService.getRoleByName("Tatami"));
            userService.createUser("Manager","",roleService.getRoleByName("Manager"));
            userService.createUser("Admin","",roleService.getRoleByName("Admin"));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadSettings(){
        loadPouleSettings();
        loadCompetitionConfigSettings();
        loadCategoryConfigSettings();
    }
    private void loadPouleSettings(){
        URL url = this.getClass().getResource(DataLoader.pouleSettingsFile);
        if (url == null){
            log.warn("Could not load Poule Settings");
            return;
        }
        pouleSettingsService.load(url.getPath());
        //pouleSettingsService.save(url.getPath());
    }
    private void loadCompetitionConfigSettings(){
        URL url = this.getClass().getResource(DataLoader.competitionsConfigFile);
        if (url == null){
            log.warn("Could not load Competitions Config");
            return;
        }
        competitionConfigService.load(url.getPath());
        //competitionConfigService.save(url.getPath());
    }
    private void loadCategoryConfigSettings(){
        URL url = this.getClass().getResource(DataLoader.categoryConfigFile);
        if (url == null){
            log.warn("Could not load Category Config");
            return;
        }
        categoryConfigService.load(url.getPath());
        //competitionConfigService.save(url.getPath());

    }

    private Tournament createTournament(){
        tournamentService.removeAll();
        return tournamentService.createTournament("Test Ippon Trophy");
    }
    private List<Competition> createCompetition(Tournament tournament) throws AlreadyExistsException {
        competitionService.removeAll();
        List<Competition> competitions = new ArrayList<>();
        competitions.add(competitionService.createCompetition(tournament.getId(),"U11"));
        competitions.add(competitionService.createCompetition(tournament.getId(),"U13"));
        competitions.add(competitionService.createCompetition(tournament.getId(),"U15"));
        competitions.add(competitionService.createCompetition(tournament.getId(),"U18"));
        competitions.add(competitionService.createCompetition(tournament.getId(),"U21/U21+"));
        competitionService.startWeighing(competitions.get(2).getId());
        return competitions;
    }
    private List<Tatami> createTatamis() throws AlreadyExistsException {
        tatamiService.removeAll();
        List<Tatami> tatamis = new ArrayList<>();
        tatamis.add(tatamiService.createTatami(1));
        tatamis.add(tatamiService.createTatami(2));
        tatamis.add(tatamiService.createTatami(3));
        tatamis.add(tatamiService.createTatami(4));
        tatamis.add(tatamiService.createTatami(5));
        tatamis.add(tatamiService.createTatami(6));
        tatamis.add(tatamiService.createTatami(7));
        tatamis.add(tatamiService.createTatami(8));
        return tatamis;
    }

    private List<Club> createClubs() throws AlreadyExistsException {
        clubService.removeAll();
        List<Club> clubs = new ArrayList<>();
        Club club;

        club = clubService.createClub("Merksem Judo Club");
        club = clubService.addAlias(club.getClubName(), "MJC");
        clubs.add(club);

        club = clubService.createClub("Top Judo Antwerpen");
        club = clubService.addAlias(club.getClubName(), "TJA");
        clubs.add(club);

        clubs.add(clubService.createClub("Satori kwai Mortsel"));
        clubs.add(clubService.createClub("Bujin Wilrijk"));
        return clubs;
    }
    private List<Country> createCountries() throws AlreadyExistsException {
        countryService.removeAll();
        List<Country> countries = new ArrayList<>();
        Country country;

        country = countryService.createCountry("Belgium");
        country = countryService.addAlias(country.getCountryName(), "BE");
        countries.add(country);

        country = countryService.createCountry("Netherlands");
        country = countryService.addAlias(country.getCountryName(), "NL");
        countries.add(country);

        country = countryService.createCountry("Sweden");
        country = countryService.addAlias(country.getCountryName(), "SE");
        countries.add(country);

        countries.add(countryService.createCountry("Luxembourg"));
        countries.add(countryService.createCountry("France"));
        return countries;
    }


    private List<Competitor> createCompetitors(List<Club> clubs){
        competitorService.removeAll();
        List<Competitor> competitors = new ArrayList<>();

        competitors.add(competitorService.createCompetitor("Maarten","Van Loo",1998,1,"MJC","Belgium"));

        return competitors;
    }
}
