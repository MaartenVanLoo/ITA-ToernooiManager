package be.ita.toernooimanager.service.util.competitiontree;

import be.ita.toernooimanager.model.local.Club;
import be.ita.toernooimanager.model.local.Competitor;
import be.ita.toernooimanager.model.local.Country;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class CreateCompetitionTreeUnitTest {
    private Method getNameFromIntMethod() throws NoSuchMethodError, NoSuchMethodException {
        Method method= CreateCompetitionPool.class.getDeclaredMethod("nameFromInt",int.class);
        method.setAccessible(true);
        return method;
    }
    @Test
    public void createCompetitionTreeSizeValidation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Create clubs
        Method method = getNameFromIntMethod();
        Club club = new Club("A");

        //Create Country
        Country country = new Country("BE");

        //Create competitors
        List<Competitor> competitors = new ArrayList<>();
        Competitor competitor;
        HashMap<String, UUID> tree;

        //region tree size 1
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        //Check keys
        assertTrue(tree.containsKey("0:0"));
        //Check for correct leaf nodes
        assertNotNull(tree.get("0:0"));
        //endregion

        //region tree size 2
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        //Check keys
        assertTrue(tree.containsKey("0:0"));
        assertTrue(tree.containsKey("1:0"));
        assertTrue(tree.containsKey("1:1"));
        //Check for correct leaf nodes
        assertNull(tree.get("0:0"));
        assertNotNull(tree.get("1:0"));
        assertNotNull(tree.get("1:1"));
        //endregion

        //region tree size 3
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        //Check keys
        assertTrue(tree.containsKey("0:0"));
        assertTrue(tree.containsKey("1:0"));
        assertTrue(tree.containsKey("1:1"));
        assertTrue(tree.containsKey("2:0"));
        assertTrue(tree.containsKey("2:1"));
        //Check for correct leaf nodes
        assertNull(tree.get("0:0"));
        assertNull(tree.get("1:0"));
        assertNotNull(tree.get("1:1"));
        assertNotNull(tree.get("2:0"));
        assertNotNull(tree.get("2:1"));
        //endregion

        //region tree size 4
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        //Check keys
        assertTrue(tree.containsKey("0:0"));
        assertTrue(tree.containsKey("1:0"));
        assertTrue(tree.containsKey("1:1"));
        assertTrue(tree.containsKey("2:0"));
        assertTrue(tree.containsKey("2:1"));
        assertTrue(tree.containsKey("2:2"));
        assertTrue(tree.containsKey("2:3"));
        //Check for correct leaf nodes
        assertNull(tree.get("0:0"));
        assertNull(tree.get("1:0"));
        assertNull(tree.get("1:1"));
        assertNotNull(tree.get("2:0"));
        assertNotNull(tree.get("2:1"));
        assertNotNull(tree.get("2:2"));
        assertNotNull(tree.get("2:3"));
        //endregion

        //region tree size 5
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        //Check keys
        assertTrue(tree.containsKey("0:0"));
        assertTrue(tree.containsKey("1:0"));
        assertTrue(tree.containsKey("1:1"));
        assertTrue(tree.containsKey("2:0"));
        assertTrue(tree.containsKey("2:1"));
        assertTrue(tree.containsKey("2:2"));
        assertTrue(tree.containsKey("2:3"));
        assertTrue(tree.containsKey("3:0"));
        assertTrue(tree.containsKey("3:1"));
        //Check for correct leaf nodes
        assertNull(tree.get("0:0"));
        assertNull(tree.get("1:0"));
        assertNull(tree.get("1:1"));
        assertNull(tree.get("2:0"));
        assertNotNull(tree.get("2:1"));
        assertNotNull(tree.get("2:2"));
        assertNotNull(tree.get("2:3"));
        assertNotNull(tree.get("3:0"));
        assertNotNull(tree.get("3:1"));
        //endregion

        //region tree size 6
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        //Check keys
        assertTrue(tree.containsKey("0:0"));
        assertTrue(tree.containsKey("1:0"));
        assertTrue(tree.containsKey("1:1"));
        assertTrue(tree.containsKey("2:0"));
        assertTrue(tree.containsKey("2:1"));
        assertTrue(tree.containsKey("2:2"));
        assertTrue(tree.containsKey("2:3"));
        assertTrue(tree.containsKey("3:0"));
        assertTrue(tree.containsKey("3:1"));
        assertTrue(tree.containsKey("3:4"));
        assertTrue(tree.containsKey("3:5"));
        //Check for correct leaf nodes
        assertNull(tree.get("0:0"));
        assertNull(tree.get("1:0"));
        assertNull(tree.get("1:1"));
        assertNull(tree.get("2:0"));
        assertNotNull(tree.get("2:1"));
        assertNull(tree.get("2:2"));
        assertNotNull(tree.get("2:3"));
        assertNotNull(tree.get("3:0"));
        assertNotNull(tree.get("3:1"));
        assertNotNull(tree.get("3:4"));
        assertNotNull(tree.get("3:5"));
        //endregion

        //region tree size 7
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        //Check keys
        assertTrue(tree.containsKey("0:0"));
        assertTrue(tree.containsKey("1:0"));
        assertTrue(tree.containsKey("1:1"));
        assertTrue(tree.containsKey("2:0"));
        assertTrue(tree.containsKey("2:1"));
        assertTrue(tree.containsKey("2:2"));
        assertTrue(tree.containsKey("2:3"));
        assertTrue(tree.containsKey("3:0"));
        assertTrue(tree.containsKey("3:1"));
        assertTrue(tree.containsKey("3:2"));
        assertTrue(tree.containsKey("3:3"));
        assertTrue(tree.containsKey("3:4"));
        assertTrue(tree.containsKey("3:5"));
        //Check for correct leaf nodes
        assertNull(tree.get("0:0"));
        assertNull(tree.get("1:0"));
        assertNull(tree.get("1:1"));
        assertNull(tree.get("2:0"));
        assertNull(tree.get("2:1"));
        assertNull(tree.get("2:2"));
        assertNotNull(tree.get("2:3"));
        assertNotNull(tree.get("3:0"));
        assertNotNull(tree.get("3:1"));
        assertNotNull(tree.get("3:2"));
        assertNotNull(tree.get("3:3"));
        assertNotNull(tree.get("3:4"));
        assertNotNull(tree.get("3:5"));
        //endregion

        //region tree size 8
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        //Check keys
        assertTrue(tree.containsKey("0:0"));
        assertTrue(tree.containsKey("1:0"));
        assertTrue(tree.containsKey("1:1"));
        assertTrue(tree.containsKey("2:0"));
        assertTrue(tree.containsKey("2:1"));
        assertTrue(tree.containsKey("2:2"));
        assertTrue(tree.containsKey("2:3"));
        assertTrue(tree.containsKey("3:0"));
        assertTrue(tree.containsKey("3:1"));
        assertTrue(tree.containsKey("3:2"));
        assertTrue(tree.containsKey("3:3"));
        assertTrue(tree.containsKey("3:4"));
        assertTrue(tree.containsKey("3:5"));
        assertTrue(tree.containsKey("3:6"));
        assertTrue(tree.containsKey("3:7"));
        //Check for correct leaf nodes
        assertNull(tree.get("0:0"));
        assertNull(tree.get("1:0"));
        assertNull(tree.get("1:1"));
        assertNull(tree.get("2:0"));
        assertNull(tree.get("2:1"));
        assertNull(tree.get("2:2"));
        assertNull(tree.get("2:3"));
        assertNotNull(tree.get("3:0"));
        assertNotNull(tree.get("3:1"));
        assertNotNull(tree.get("3:2"));
        assertNotNull(tree.get("3:3"));
        assertNotNull(tree.get("3:4"));
        assertNotNull(tree.get("3:5"));
        assertNotNull(tree.get("3:6"));
        assertNotNull(tree.get("3:7"));
        //endregion

        //region tree size 9
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,2); // check rounds [0,1,2] to be empty
        //Check keys
        assertTrue(tree.containsKey("3:0"));
        assertTrue(tree.containsKey("3:1"));
        assertTrue(tree.containsKey("3:2"));
        assertTrue(tree.containsKey("3:3"));
        assertTrue(tree.containsKey("3:4"));
        assertTrue(tree.containsKey("3:5"));
        assertTrue(tree.containsKey("3:6"));
        assertTrue(tree.containsKey("3:7"));
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        //Check for correct leaf nodes
        assertNull(tree.get("3:0"));
        assertNotNull(tree.get("3:1"));
        assertNotNull(tree.get("3:2"));
        assertNotNull(tree.get("3:3"));
        assertNotNull(tree.get("3:4"));
        assertNotNull(tree.get("3:5"));
        assertNotNull(tree.get("3:6"));
        assertNotNull(tree.get("3:7"));
        assertNotNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        //endregion

        //region tree size 10
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,2); // check rounds [0,1,2] to be empty
        //Check keys
        assertTrue(tree.containsKey("3:0"));
        assertTrue(tree.containsKey("3:1"));
        assertTrue(tree.containsKey("3:2"));
        assertTrue(tree.containsKey("3:3"));
        assertTrue(tree.containsKey("3:4"));
        assertTrue(tree.containsKey("3:5"));
        assertTrue(tree.containsKey("3:6"));
        assertTrue(tree.containsKey("3:7"));
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        //Check for correct leaf nodes
        assertNull(tree.get("3:0"));
        assertNotNull(tree.get("3:1"));
        assertNotNull(tree.get("3:2"));
        assertNotNull(tree.get("3:3"));
        assertNull(tree.get("3:4"));
        assertNotNull(tree.get("3:5"));
        assertNotNull(tree.get("3:6"));
        assertNotNull(tree.get("3:7"));
        assertNotNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNotNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        //endregion

        //region tree size 11
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,2); // check rounds [0,1,2] to be empty
        //Check keys
        assertTrue(tree.containsKey("3:0"));
        assertTrue(tree.containsKey("3:1"));
        assertTrue(tree.containsKey("3:2"));
        assertTrue(tree.containsKey("3:3"));
        assertTrue(tree.containsKey("3:4"));
        assertTrue(tree.containsKey("3:5"));
        assertTrue(tree.containsKey("3:6"));
        assertTrue(tree.containsKey("3:7"));
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        //Check for correct leaf nodes
        assertNull(tree.get("3:0"));
        assertNotNull(tree.get("3:1"));
        assertNull(tree.get("3:2"));
        assertNotNull(tree.get("3:3"));
        assertNull(tree.get("3:4"));
        assertNotNull(tree.get("3:5"));
        assertNotNull(tree.get("3:6"));
        assertNotNull(tree.get("3:7"));
        assertNotNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNotNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNotNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        //endregion

        //region tree size 12
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,2); // check rounds [0,1,2] to be empty
        //Check keys
        assertTrue(tree.containsKey("3:0"));
        assertTrue(tree.containsKey("3:1"));
        assertTrue(tree.containsKey("3:2"));
        assertTrue(tree.containsKey("3:3"));
        assertTrue(tree.containsKey("3:4"));
        assertTrue(tree.containsKey("3:5"));
        assertTrue(tree.containsKey("3:6"));
        assertTrue(tree.containsKey("3:7"));
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        //Check for correct leaf nodes
        assertNull(tree.get("3:0"));
        assertNotNull(tree.get("3:1"));
        assertNull(tree.get("3:2"));
        assertNotNull(tree.get("3:3"));
        assertNull(tree.get("3:4"));
        assertNotNull(tree.get("3:5"));
        assertNull(tree.get("3:6"));
        assertNotNull(tree.get("3:7"));
        assertNotNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNotNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNotNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNotNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        //endregion

        //region tree size 13
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,2); // check rounds [0,1,2] to be empty
        //Check keys
        assertTrue(tree.containsKey("3:0"));
        assertTrue(tree.containsKey("3:1"));
        assertTrue(tree.containsKey("3:2"));
        assertTrue(tree.containsKey("3:3"));
        assertTrue(tree.containsKey("3:4"));
        assertTrue(tree.containsKey("3:5"));
        assertTrue(tree.containsKey("3:6"));
        assertTrue(tree.containsKey("3:7"));
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        //Check for correct leaf nodes
        assertNull(tree.get("3:0"));
        assertNull(tree.get("3:1"));
        assertNull(tree.get("3:2"));
        assertNotNull(tree.get("3:3"));
        assertNull(tree.get("3:4"));
        assertNotNull(tree.get("3:5"));
        assertNull(tree.get("3:6"));
        assertNotNull(tree.get("3:7"));
        assertNotNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNotNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNotNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNotNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNotNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        //endregion

        //region tree size 14
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,2); // check rounds [0,1,2] to be empty
        //Check keys
        assertTrue(tree.containsKey("3:0"));
        assertTrue(tree.containsKey("3:1"));
        assertTrue(tree.containsKey("3:2"));
        assertTrue(tree.containsKey("3:3"));
        assertTrue(tree.containsKey("3:4"));
        assertTrue(tree.containsKey("3:5"));
        assertTrue(tree.containsKey("3:6"));
        assertTrue(tree.containsKey("3:7"));
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        //Check for correct leaf nodes
        assertNull(tree.get("3:0"));
        assertNull(tree.get("3:1"));
        assertNull(tree.get("3:2"));
        assertNotNull(tree.get("3:3"));
        assertNull(tree.get("3:4"));
        assertNull(tree.get("3:5"));
        assertNull(tree.get("3:6"));
        assertNotNull(tree.get("3:7"));
        assertNotNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNotNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNotNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNotNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNotNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNotNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        //endregion

        //region tree size 15
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,2); // check rounds [0,1,2] to be empty
        //Check keys
        assertTrue(tree.containsKey("3:0"));
        assertTrue(tree.containsKey("3:1"));
        assertTrue(tree.containsKey("3:2"));
        assertTrue(tree.containsKey("3:3"));
        assertTrue(tree.containsKey("3:4"));
        assertTrue(tree.containsKey("3:5"));
        assertTrue(tree.containsKey("3:6"));
        assertTrue(tree.containsKey("3:7"));
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        //Check for correct leaf nodes
        assertNull(tree.get("3:0"));
        assertNull(tree.get("3:1"));
        assertNull(tree.get("3:2"));
        assertNull(tree.get("3:3"));
        assertNull(tree.get("3:4"));
        assertNull(tree.get("3:5"));
        assertNull(tree.get("3:6"));
        assertNotNull(tree.get("3:7"));
        assertNotNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNotNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNotNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNotNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNotNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNotNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNotNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        //endregion

        //region tree size 16
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,2); // check rounds [0,1,2] to be empty
        //Check keys
        assertTrue(tree.containsKey("3:0"));
        assertTrue(tree.containsKey("3:1"));
        assertTrue(tree.containsKey("3:2"));
        assertTrue(tree.containsKey("3:3"));
        assertTrue(tree.containsKey("3:4"));
        assertTrue(tree.containsKey("3:5"));
        assertTrue(tree.containsKey("3:6"));
        assertTrue(tree.containsKey("3:7"));
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        //Check for correct leaf nodes
        assertNull(tree.get("3:0"));
        assertNull(tree.get("3:1"));
        assertNull(tree.get("3:2"));
        assertNull(tree.get("3:3"));
        assertNull(tree.get("3:4"));
        assertNull(tree.get("3:5"));
        assertNull(tree.get("3:6"));
        assertNull(tree.get("3:7"));
        assertNotNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNotNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNotNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNotNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNotNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNotNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNotNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        assertNotNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        //endregion

        //region tree size 17
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNotNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNotNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNotNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNotNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNotNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNotNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        assertNotNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        //endregion

        //region tree size 18
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNotNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNotNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNotNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNotNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNotNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        assertNotNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        //endregion

        //region tree size 19
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNotNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNotNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNotNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNotNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        assertNotNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        //endregion

        //region tree size 20
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        assertTrue(tree.containsKey("5:24"));
        assertTrue(tree.containsKey("5:25"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNotNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNotNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNotNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        assertNotNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        assertNotNull(tree.get("5:24"));
        assertNotNull(tree.get("5:25"));
        //endregion

        //region tree size 21
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:4"));
        assertTrue(tree.containsKey("5:5"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        assertTrue(tree.containsKey("5:24"));
        assertTrue(tree.containsKey("5:25"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNotNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNotNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        assertNotNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:4"));
        assertNotNull(tree.get("5:5"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        assertNotNull(tree.get("5:24"));
        assertNotNull(tree.get("5:25"));
        //endregion

        //region tree size 22
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:4"));
        assertTrue(tree.containsKey("5:5"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        assertTrue(tree.containsKey("5:20"));
        assertTrue(tree.containsKey("5:21"));
        assertTrue(tree.containsKey("5:24"));
        assertTrue(tree.containsKey("5:25"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNotNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        assertNotNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:4"));
        assertNotNull(tree.get("5:5"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        assertNotNull(tree.get("5:20"));
        assertNotNull(tree.get("5:21"));
        assertNotNull(tree.get("5:24"));
        assertNotNull(tree.get("5:25"));
        //endregion

        //region tree size 23
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:4"));
        assertTrue(tree.containsKey("5:5"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:12"));
        assertTrue(tree.containsKey("5:13"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        assertTrue(tree.containsKey("5:20"));
        assertTrue(tree.containsKey("5:21"));
        assertTrue(tree.containsKey("5:24"));
        assertTrue(tree.containsKey("5:25"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        assertNotNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:4"));
        assertNotNull(tree.get("5:5"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:12"));
        assertNotNull(tree.get("5:13"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        assertNotNull(tree.get("5:20"));
        assertNotNull(tree.get("5:21"));
        assertNotNull(tree.get("5:24"));
        assertNotNull(tree.get("5:25"));
        //endregion

        //region tree size 24
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:4"));
        assertTrue(tree.containsKey("5:5"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:12"));
        assertTrue(tree.containsKey("5:13"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        assertTrue(tree.containsKey("5:20"));
        assertTrue(tree.containsKey("5:21"));
        assertTrue(tree.containsKey("5:24"));
        assertTrue(tree.containsKey("5:25"));
        assertTrue(tree.containsKey("5:28"));
        assertTrue(tree.containsKey("5:29"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNotNull(tree.get("4:1"));
        assertNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        assertNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:4"));
        assertNotNull(tree.get("5:5"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:12"));
        assertNotNull(tree.get("5:13"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        assertNotNull(tree.get("5:20"));
        assertNotNull(tree.get("5:21"));
        assertNotNull(tree.get("5:24"));
        assertNotNull(tree.get("5:25"));
        assertNotNull(tree.get("5:28"));
        assertNotNull(tree.get("5:29"));
        //endregion

        //region tree size 25
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:2"));
        assertTrue(tree.containsKey("5:3"));
        assertTrue(tree.containsKey("5:4"));
        assertTrue(tree.containsKey("5:5"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:12"));
        assertTrue(tree.containsKey("5:13"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        assertTrue(tree.containsKey("5:20"));
        assertTrue(tree.containsKey("5:21"));
        assertTrue(tree.containsKey("5:24"));
        assertTrue(tree.containsKey("5:25"));
        assertTrue(tree.containsKey("5:28"));
        assertTrue(tree.containsKey("5:29"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNull(tree.get("4:1"));
        assertNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNotNull(tree.get("4:9"));
        assertNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        assertNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:2"));
        assertNotNull(tree.get("5:3"));
        assertNotNull(tree.get("5:4"));
        assertNotNull(tree.get("5:5"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:12"));
        assertNotNull(tree.get("5:13"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        assertNotNull(tree.get("5:20"));
        assertNotNull(tree.get("5:21"));
        assertNotNull(tree.get("5:24"));
        assertNotNull(tree.get("5:25"));
        assertNotNull(tree.get("5:28"));
        assertNotNull(tree.get("5:29"));
        //endregion

        //region tree size 26
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:2"));
        assertTrue(tree.containsKey("5:3"));
        assertTrue(tree.containsKey("5:4"));
        assertTrue(tree.containsKey("5:5"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:12"));
        assertTrue(tree.containsKey("5:13"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        assertTrue(tree.containsKey("5:18"));
        assertTrue(tree.containsKey("5:19"));
        assertTrue(tree.containsKey("5:20"));
        assertTrue(tree.containsKey("5:21"));
        assertTrue(tree.containsKey("5:24"));
        assertTrue(tree.containsKey("5:25"));
        assertTrue(tree.containsKey("5:28"));
        assertTrue(tree.containsKey("5:29"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNull(tree.get("4:1"));
        assertNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNotNull(tree.get("4:5"));
        assertNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNull(tree.get("4:9"));
        assertNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        assertNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:2"));
        assertNotNull(tree.get("5:3"));
        assertNotNull(tree.get("5:4"));
        assertNotNull(tree.get("5:5"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:12"));
        assertNotNull(tree.get("5:13"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        assertNotNull(tree.get("5:18"));
        assertNotNull(tree.get("5:19"));
        assertNotNull(tree.get("5:20"));
        assertNotNull(tree.get("5:21"));
        assertNotNull(tree.get("5:24"));
        assertNotNull(tree.get("5:25"));
        assertNotNull(tree.get("5:28"));
        assertNotNull(tree.get("5:29"));
        //endregion

        //region tree size 27
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:2"));
        assertTrue(tree.containsKey("5:3"));
        assertTrue(tree.containsKey("5:4"));
        assertTrue(tree.containsKey("5:5"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:10"));
        assertTrue(tree.containsKey("5:11"));
        assertTrue(tree.containsKey("5:12"));
        assertTrue(tree.containsKey("5:13"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        assertTrue(tree.containsKey("5:18"));
        assertTrue(tree.containsKey("5:19"));
        assertTrue(tree.containsKey("5:20"));
        assertTrue(tree.containsKey("5:21"));
        assertTrue(tree.containsKey("5:24"));
        assertTrue(tree.containsKey("5:25"));
        assertTrue(tree.containsKey("5:28"));
        assertTrue(tree.containsKey("5:29"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNull(tree.get("4:1"));
        assertNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNull(tree.get("4:5"));
        assertNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNull(tree.get("4:9"));
        assertNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNull(tree.get("4:12"));
        assertNotNull(tree.get("4:13"));
        assertNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:2"));
        assertNotNull(tree.get("5:3"));
        assertNotNull(tree.get("5:4"));
        assertNotNull(tree.get("5:5"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:10"));
        assertNotNull(tree.get("5:11"));
        assertNotNull(tree.get("5:12"));
        assertNotNull(tree.get("5:13"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        assertNotNull(tree.get("5:18"));
        assertNotNull(tree.get("5:19"));
        assertNotNull(tree.get("5:20"));
        assertNotNull(tree.get("5:21"));
        assertNotNull(tree.get("5:24"));
        assertNotNull(tree.get("5:25"));
        assertNotNull(tree.get("5:28"));
        assertNotNull(tree.get("5:29"));
        //endregion

        //region tree size 28
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:2"));
        assertTrue(tree.containsKey("5:3"));
        assertTrue(tree.containsKey("5:4"));
        assertTrue(tree.containsKey("5:5"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:10"));
        assertTrue(tree.containsKey("5:11"));
        assertTrue(tree.containsKey("5:12"));
        assertTrue(tree.containsKey("5:13"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        assertTrue(tree.containsKey("5:18"));
        assertTrue(tree.containsKey("5:19"));
        assertTrue(tree.containsKey("5:20"));
        assertTrue(tree.containsKey("5:21"));
        assertTrue(tree.containsKey("5:24"));
        assertTrue(tree.containsKey("5:25"));
        assertTrue(tree.containsKey("5:26"));
        assertTrue(tree.containsKey("5:27"));
        assertTrue(tree.containsKey("5:28"));
        assertTrue(tree.containsKey("5:29"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNull(tree.get("4:1"));
        assertNull(tree.get("4:2"));
        assertNotNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNull(tree.get("4:5"));
        assertNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNull(tree.get("4:9"));
        assertNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNull(tree.get("4:12"));
        assertNull(tree.get("4:13"));
        assertNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:2"));
        assertNotNull(tree.get("5:3"));
        assertNotNull(tree.get("5:4"));
        assertNotNull(tree.get("5:5"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:10"));
        assertNotNull(tree.get("5:11"));
        assertNotNull(tree.get("5:12"));
        assertNotNull(tree.get("5:13"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        assertNotNull(tree.get("5:18"));
        assertNotNull(tree.get("5:19"));
        assertNotNull(tree.get("5:20"));
        assertNotNull(tree.get("5:21"));
        assertNotNull(tree.get("5:24"));
        assertNotNull(tree.get("5:25"));
        assertNotNull(tree.get("5:26"));
        assertNotNull(tree.get("5:27"));
        assertNotNull(tree.get("5:28"));
        assertNotNull(tree.get("5:29"));
        //endregion

        //region tree size 29
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:2"));
        assertTrue(tree.containsKey("5:3"));
        assertTrue(tree.containsKey("5:4"));
        assertTrue(tree.containsKey("5:5"));
        assertTrue(tree.containsKey("5:6"));
        assertTrue(tree.containsKey("5:7"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:10"));
        assertTrue(tree.containsKey("5:11"));
        assertTrue(tree.containsKey("5:12"));
        assertTrue(tree.containsKey("5:13"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        assertTrue(tree.containsKey("5:18"));
        assertTrue(tree.containsKey("5:19"));
        assertTrue(tree.containsKey("5:20"));
        assertTrue(tree.containsKey("5:21"));
        assertTrue(tree.containsKey("5:24"));
        assertTrue(tree.containsKey("5:25"));
        assertTrue(tree.containsKey("5:26"));
        assertTrue(tree.containsKey("5:27"));
        assertTrue(tree.containsKey("5:28"));
        assertTrue(tree.containsKey("5:29"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNull(tree.get("4:1"));
        assertNull(tree.get("4:2"));
        assertNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNull(tree.get("4:5"));
        assertNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNull(tree.get("4:9"));
        assertNull(tree.get("4:10"));
        assertNotNull(tree.get("4:11"));
        assertNull(tree.get("4:12"));
        assertNull(tree.get("4:13"));
        assertNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:2"));
        assertNotNull(tree.get("5:3"));
        assertNotNull(tree.get("5:4"));
        assertNotNull(tree.get("5:5"));
        assertNotNull(tree.get("5:6"));
        assertNotNull(tree.get("5:7"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:10"));
        assertNotNull(tree.get("5:11"));
        assertNotNull(tree.get("5:12"));
        assertNotNull(tree.get("5:13"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        assertNotNull(tree.get("5:18"));
        assertNotNull(tree.get("5:19"));
        assertNotNull(tree.get("5:20"));
        assertNotNull(tree.get("5:21"));
        assertNotNull(tree.get("5:24"));
        assertNotNull(tree.get("5:25"));
        assertNotNull(tree.get("5:26"));
        assertNotNull(tree.get("5:27"));
        assertNotNull(tree.get("5:28"));
        assertNotNull(tree.get("5:29"));
        //endregion

        //region tree size 30
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:2"));
        assertTrue(tree.containsKey("5:3"));
        assertTrue(tree.containsKey("5:4"));
        assertTrue(tree.containsKey("5:5"));
        assertTrue(tree.containsKey("5:6"));
        assertTrue(tree.containsKey("5:7"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:10"));
        assertTrue(tree.containsKey("5:11"));
        assertTrue(tree.containsKey("5:12"));
        assertTrue(tree.containsKey("5:13"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        assertTrue(tree.containsKey("5:18"));
        assertTrue(tree.containsKey("5:19"));
        assertTrue(tree.containsKey("5:20"));
        assertTrue(tree.containsKey("5:21"));
        assertTrue(tree.containsKey("5:22"));
        assertTrue(tree.containsKey("5:23"));
        assertTrue(tree.containsKey("5:24"));
        assertTrue(tree.containsKey("5:25"));
        assertTrue(tree.containsKey("5:26"));
        assertTrue(tree.containsKey("5:27"));
        assertTrue(tree.containsKey("5:28"));
        assertTrue(tree.containsKey("5:29"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNull(tree.get("4:1"));
        assertNull(tree.get("4:2"));
        assertNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNull(tree.get("4:5"));
        assertNull(tree.get("4:6"));
        assertNotNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNull(tree.get("4:9"));
        assertNull(tree.get("4:10"));
        assertNull(tree.get("4:11"));
        assertNull(tree.get("4:12"));
        assertNull(tree.get("4:13"));
        assertNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:2"));
        assertNotNull(tree.get("5:3"));
        assertNotNull(tree.get("5:4"));
        assertNotNull(tree.get("5:5"));
        assertNotNull(tree.get("5:6"));
        assertNotNull(tree.get("5:7"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:10"));
        assertNotNull(tree.get("5:11"));
        assertNotNull(tree.get("5:12"));
        assertNotNull(tree.get("5:13"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        assertNotNull(tree.get("5:18"));
        assertNotNull(tree.get("5:19"));
        assertNotNull(tree.get("5:20"));
        assertNotNull(tree.get("5:21"));
        assertNotNull(tree.get("5:22"));
        assertNotNull(tree.get("5:23"));
        assertNotNull(tree.get("5:24"));
        assertNotNull(tree.get("5:25"));
        assertNotNull(tree.get("5:26"));
        assertNotNull(tree.get("5:27"));
        assertNotNull(tree.get("5:28"));
        assertNotNull(tree.get("5:29"));
        //endregion

        //region tree size 31
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:2"));
        assertTrue(tree.containsKey("5:3"));
        assertTrue(tree.containsKey("5:4"));
        assertTrue(tree.containsKey("5:5"));
        assertTrue(tree.containsKey("5:6"));
        assertTrue(tree.containsKey("5:7"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:10"));
        assertTrue(tree.containsKey("5:11"));
        assertTrue(tree.containsKey("5:12"));
        assertTrue(tree.containsKey("5:13"));
        assertTrue(tree.containsKey("5:14"));
        assertTrue(tree.containsKey("5:15"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        assertTrue(tree.containsKey("5:18"));
        assertTrue(tree.containsKey("5:19"));
        assertTrue(tree.containsKey("5:20"));
        assertTrue(tree.containsKey("5:21"));
        assertTrue(tree.containsKey("5:22"));
        assertTrue(tree.containsKey("5:23"));
        assertTrue(tree.containsKey("5:24"));
        assertTrue(tree.containsKey("5:25"));
        assertTrue(tree.containsKey("5:26"));
        assertTrue(tree.containsKey("5:27"));
        assertTrue(tree.containsKey("5:28"));
        assertTrue(tree.containsKey("5:29"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNull(tree.get("4:1"));
        assertNull(tree.get("4:2"));
        assertNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNull(tree.get("4:5"));
        assertNull(tree.get("4:6"));
        assertNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNull(tree.get("4:9"));
        assertNull(tree.get("4:10"));
        assertNull(tree.get("4:11"));
        assertNull(tree.get("4:12"));
        assertNull(tree.get("4:13"));
        assertNull(tree.get("4:14"));
        assertNotNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:2"));
        assertNotNull(tree.get("5:3"));
        assertNotNull(tree.get("5:4"));
        assertNotNull(tree.get("5:5"));
        assertNotNull(tree.get("5:6"));
        assertNotNull(tree.get("5:7"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:10"));
        assertNotNull(tree.get("5:11"));
        assertNotNull(tree.get("5:12"));
        assertNotNull(tree.get("5:13"));
        assertNotNull(tree.get("5:14"));
        assertNotNull(tree.get("5:15"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        assertNotNull(tree.get("5:18"));
        assertNotNull(tree.get("5:19"));
        assertNotNull(tree.get("5:20"));
        assertNotNull(tree.get("5:21"));
        assertNotNull(tree.get("5:22"));
        assertNotNull(tree.get("5:23"));
        assertNotNull(tree.get("5:24"));
        assertNotNull(tree.get("5:25"));
        assertNotNull(tree.get("5:26"));
        assertNotNull(tree.get("5:27"));
        assertNotNull(tree.get("5:28"));
        assertNotNull(tree.get("5:29"));
        //endregion

        //region tree size 32
        competitor = new Competitor("","",0,0,club,country);
        competitor.setId(UUID.randomUUID());
        competitors.add(competitor);
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,3); // check rounds [0,1,2,3] to be empty
        //Check keys
        assertTrue(tree.containsKey("4:0"));
        assertTrue(tree.containsKey("4:1"));
        assertTrue(tree.containsKey("4:2"));
        assertTrue(tree.containsKey("4:3"));
        assertTrue(tree.containsKey("4:4"));
        assertTrue(tree.containsKey("4:5"));
        assertTrue(tree.containsKey("4:6"));
        assertTrue(tree.containsKey("4:7"));
        assertTrue(tree.containsKey("4:8"));
        assertTrue(tree.containsKey("4:9"));
        assertTrue(tree.containsKey("4:10"));
        assertTrue(tree.containsKey("4:11"));
        assertTrue(tree.containsKey("4:12"));
        assertTrue(tree.containsKey("4:13"));
        assertTrue(tree.containsKey("4:14"));
        assertTrue(tree.containsKey("4:15"));
        assertTrue(tree.containsKey("5:0"));
        assertTrue(tree.containsKey("5:1"));
        assertTrue(tree.containsKey("5:2"));
        assertTrue(tree.containsKey("5:3"));
        assertTrue(tree.containsKey("5:4"));
        assertTrue(tree.containsKey("5:5"));
        assertTrue(tree.containsKey("5:6"));
        assertTrue(tree.containsKey("5:7"));
        assertTrue(tree.containsKey("5:8"));
        assertTrue(tree.containsKey("5:9"));
        assertTrue(tree.containsKey("5:10"));
        assertTrue(tree.containsKey("5:11"));
        assertTrue(tree.containsKey("5:12"));
        assertTrue(tree.containsKey("5:13"));
        assertTrue(tree.containsKey("5:14"));
        assertTrue(tree.containsKey("5:15"));
        assertTrue(tree.containsKey("5:16"));
        assertTrue(tree.containsKey("5:17"));
        assertTrue(tree.containsKey("5:18"));
        assertTrue(tree.containsKey("5:19"));
        assertTrue(tree.containsKey("5:20"));
        assertTrue(tree.containsKey("5:21"));
        assertTrue(tree.containsKey("5:22"));
        assertTrue(tree.containsKey("5:23"));
        assertTrue(tree.containsKey("5:24"));
        assertTrue(tree.containsKey("5:25"));
        assertTrue(tree.containsKey("5:26"));
        assertTrue(tree.containsKey("5:27"));
        assertTrue(tree.containsKey("5:28"));
        assertTrue(tree.containsKey("5:29"));
        assertTrue(tree.containsKey("5:30"));
        assertTrue(tree.containsKey("5:31"));
        //Check for correct leaf nodes
        assertNull(tree.get("4:0"));
        assertNull(tree.get("4:1"));
        assertNull(tree.get("4:2"));
        assertNull(tree.get("4:3"));
        assertNull(tree.get("4:4"));
        assertNull(tree.get("4:5"));
        assertNull(tree.get("4:6"));
        assertNull(tree.get("4:7"));
        assertNull(tree.get("4:8"));
        assertNull(tree.get("4:9"));
        assertNull(tree.get("4:10"));
        assertNull(tree.get("4:11"));
        assertNull(tree.get("4:12"));
        assertNull(tree.get("4:13"));
        assertNull(tree.get("4:14"));
        assertNull(tree.get("4:15"));
        assertNotNull(tree.get("5:0"));
        assertNotNull(tree.get("5:1"));
        assertNotNull(tree.get("5:2"));
        assertNotNull(tree.get("5:3"));
        assertNotNull(tree.get("5:4"));
        assertNotNull(tree.get("5:5"));
        assertNotNull(tree.get("5:6"));
        assertNotNull(tree.get("5:7"));
        assertNotNull(tree.get("5:8"));
        assertNotNull(tree.get("5:9"));
        assertNotNull(tree.get("5:10"));
        assertNotNull(tree.get("5:11"));
        assertNotNull(tree.get("5:12"));
        assertNotNull(tree.get("5:13"));
        assertNotNull(tree.get("5:14"));
        assertNotNull(tree.get("5:15"));
        assertNotNull(tree.get("5:16"));
        assertNotNull(tree.get("5:17"));
        assertNotNull(tree.get("5:18"));
        assertNotNull(tree.get("5:19"));
        assertNotNull(tree.get("5:20"));
        assertNotNull(tree.get("5:21"));
        assertNotNull(tree.get("5:22"));
        assertNotNull(tree.get("5:23"));
        assertNotNull(tree.get("5:24"));
        assertNotNull(tree.get("5:25"));
        assertNotNull(tree.get("5:26"));
        assertNotNull(tree.get("5:27"));
        assertNotNull(tree.get("5:28"));
        assertNotNull(tree.get("5:29"));
        assertNotNull(tree.get("5:30"));
        assertNotNull(tree.get("5:31"));

        checkEmptyRounds(tree,4); // check rounds [0,1,2,3] to be empty
        //endregion

        //region tree size 64
        for (int i=0; i <32;i++) {
            competitor = new Competitor("", "", 0, 0, club, country);
            competitor.setId(UUID.randomUUID());
            competitors.add(competitor);
        }
        tree = CreateCompetitionTree.createCompetitionTree(competitors);
        checkEmptyRounds(tree,5); // check rounds [0,1,2,3,4,5] to be empty
        //Check keys
        assertTrue(tree.containsKey("6:0"));
        assertTrue(tree.containsKey("6:1"));
        assertTrue(tree.containsKey("6:2"));
        assertTrue(tree.containsKey("6:3"));
        assertTrue(tree.containsKey("6:4"));
        assertTrue(tree.containsKey("6:5"));
        assertTrue(tree.containsKey("6:6"));
        assertTrue(tree.containsKey("6:7"));
        assertTrue(tree.containsKey("6:8"));
        assertTrue(tree.containsKey("6:9"));
        assertTrue(tree.containsKey("6:10"));
        assertTrue(tree.containsKey("6:11"));
        assertTrue(tree.containsKey("6:12"));
        assertTrue(tree.containsKey("6:13"));
        assertTrue(tree.containsKey("6:14"));
        assertTrue(tree.containsKey("6:15"));
        assertTrue(tree.containsKey("6:16"));
        assertTrue(tree.containsKey("6:17"));
        assertTrue(tree.containsKey("6:18"));
        assertTrue(tree.containsKey("6:19"));
        assertTrue(tree.containsKey("6:20"));
        assertTrue(tree.containsKey("6:21"));
        assertTrue(tree.containsKey("6:22"));
        assertTrue(tree.containsKey("6:23"));
        assertTrue(tree.containsKey("6:24"));
        assertTrue(tree.containsKey("6:25"));
        assertTrue(tree.containsKey("6:26"));
        assertTrue(tree.containsKey("6:27"));
        assertTrue(tree.containsKey("6:28"));
        assertTrue(tree.containsKey("6:29"));
        assertTrue(tree.containsKey("6:30"));
        assertTrue(tree.containsKey("6:31"));
        assertTrue(tree.containsKey("6:32"));
        assertTrue(tree.containsKey("6:33"));
        assertTrue(tree.containsKey("6:34"));
        assertTrue(tree.containsKey("6:35"));
        assertTrue(tree.containsKey("6:36"));
        assertTrue(tree.containsKey("6:37"));
        assertTrue(tree.containsKey("6:38"));
        assertTrue(tree.containsKey("6:39"));
        assertTrue(tree.containsKey("6:40"));
        assertTrue(tree.containsKey("6:41"));
        assertTrue(tree.containsKey("6:42"));
        assertTrue(tree.containsKey("6:43"));
        assertTrue(tree.containsKey("6:44"));
        assertTrue(tree.containsKey("6:45"));
        assertTrue(tree.containsKey("6:46"));
        assertTrue(tree.containsKey("6:47"));
        assertTrue(tree.containsKey("6:48"));
        assertTrue(tree.containsKey("6:49"));
        assertTrue(tree.containsKey("6:50"));
        assertTrue(tree.containsKey("6:51"));
        assertTrue(tree.containsKey("6:52"));
        assertTrue(tree.containsKey("6:53"));
        assertTrue(tree.containsKey("6:54"));
        assertTrue(tree.containsKey("6:55"));
        assertTrue(tree.containsKey("6:56"));
        assertTrue(tree.containsKey("6:57"));
        assertTrue(tree.containsKey("6:58"));
        assertTrue(tree.containsKey("6:59"));
        assertTrue(tree.containsKey("6:60"));
        assertTrue(tree.containsKey("6:61"));
        assertTrue(tree.containsKey("6:62"));
        assertTrue(tree.containsKey("6:62"));
        //Check for correct leaf nodes
        assertNotNull(tree.get("6:0"));
        assertNotNull(tree.get("6:1"));
        assertNotNull(tree.get("6:2"));
        assertNotNull(tree.get("6:3"));
        assertNotNull(tree.get("6:4"));
        assertNotNull(tree.get("6:5"));
        assertNotNull(tree.get("6:6"));
        assertNotNull(tree.get("6:7"));
        assertNotNull(tree.get("6:8"));
        assertNotNull(tree.get("6:9"));
        assertNotNull(tree.get("6:10"));
        assertNotNull(tree.get("6:11"));
        assertNotNull(tree.get("6:12"));
        assertNotNull(tree.get("6:13"));
        assertNotNull(tree.get("6:14"));
        assertNotNull(tree.get("6:15"));
        assertNotNull(tree.get("6:16"));
        assertNotNull(tree.get("6:17"));
        assertNotNull(tree.get("6:18"));
        assertNotNull(tree.get("6:19"));
        assertNotNull(tree.get("6:20"));
        assertNotNull(tree.get("6:21"));
        assertNotNull(tree.get("6:22"));
        assertNotNull(tree.get("6:23"));
        assertNotNull(tree.get("6:24"));
        assertNotNull(tree.get("6:25"));
        assertNotNull(tree.get("6:26"));
        assertNotNull(tree.get("6:27"));
        assertNotNull(tree.get("6:28"));
        assertNotNull(tree.get("6:29"));
        assertNotNull(tree.get("6:30"));
        assertNotNull(tree.get("6:31"));
        assertNotNull(tree.get("6:32"));
        assertNotNull(tree.get("6:33"));
        assertNotNull(tree.get("6:34"));
        assertNotNull(tree.get("6:35"));
        assertNotNull(tree.get("6:36"));
        assertNotNull(tree.get("6:37"));
        assertNotNull(tree.get("6:38"));
        assertNotNull(tree.get("6:39"));
        assertNotNull(tree.get("6:40"));
        assertNotNull(tree.get("6:41"));
        assertNotNull(tree.get("6:42"));
        assertNotNull(tree.get("6:43"));
        assertNotNull(tree.get("6:44"));
        assertNotNull(tree.get("6:45"));
        assertNotNull(tree.get("6:46"));
        assertNotNull(tree.get("6:47"));
        assertNotNull(tree.get("6:48"));
        assertNotNull(tree.get("6:49"));
        assertNotNull(tree.get("6:50"));
        assertNotNull(tree.get("6:51"));
        assertNotNull(tree.get("6:52"));
        assertNotNull(tree.get("6:53"));
        assertNotNull(tree.get("6:54"));
        assertNotNull(tree.get("6:55"));
        assertNotNull(tree.get("6:56"));
        assertNotNull(tree.get("6:57"));
        assertNotNull(tree.get("6:58"));
        assertNotNull(tree.get("6:59"));
        assertNotNull(tree.get("6:60"));
        assertNotNull(tree.get("6:61"));
        assertNotNull(tree.get("6:62"));
        assertNotNull(tree.get("6:62"));
        //endregion
    }

    @Test
    public void createCompetitionTreeDuplicateCountry() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Create clubs
        Method method = getNameFromIntMethod();
        List<Club> clubs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            clubs.add(new Club((String) method.invoke(null,i)));
        }

        //Create Countries
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("BE"));
        countries.add(new Country("NL"));

        HashMap<String, UUID> tree;

        {
            //Create competitors (2 from other country)
            List<Competitor> competitors = new ArrayList<>();
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(2), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(2), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(2), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(1)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(1)));
            for (Competitor c : competitors) {
                c.setId(UUID.randomUUID());
            }

            tree = CreateCompetitionTree.createCompetitionTree(competitors);

            //find where countries == NL
            List<UUID> nl = competitors.stream().filter((c) -> c.getCountry().getCountryName().equals("NL")).map(Competitor::getId).toList();
            List<String> keys = tree.entrySet().stream().filter((entry) -> nl.contains(entry.getValue())).map(Map.Entry::getKey).toList();
            //validate keys are in opposite halves
            List<Integer> rounds = keys.stream().map((key) -> Integer.parseInt(key.split(":")[0])).toList();
            List<Integer> rows = keys.stream().map((key) -> Integer.parseInt(key.split(":")[1])).toList();

            assertEquals(2,rounds.size()); // 2 of country NL must be present
            assertEquals(2,rows.size()); // 2 of country NL must be present

            long lowerHalf = keys.stream().filter(this::inLowerHalf).count();
            long upperHalf = keys.stream().filter(this::inUpperHalf).count();
            assertEquals(1, lowerHalf);
            assertEquals(1, upperHalf);
        }

        {
            //Create competitors (3 from other country)
            List<Competitor> competitors = new ArrayList<>();
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(2), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(2), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(2), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(1)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(2), countries.get(1)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(1)));
            for (Competitor c : competitors) {
                c.setId(UUID.randomUUID());
            }

            tree = CreateCompetitionTree.createCompetitionTree(competitors);

            //find where countries == NL
            List<UUID> nl = competitors.stream().filter((c) -> c.getCountry().getCountryName().equals("NL")).map(Competitor::getId).toList();
            List<String> keys = tree.entrySet().stream().filter((entry) -> nl.contains(entry.getValue())).map(Map.Entry::getKey).toList();
            //validate keys are in opposite halves
            List<Integer> rounds = keys.stream().map((key) -> Integer.parseInt(key.split(":")[0])).toList();
            List<Integer> rows = keys.stream().map((key) -> Integer.parseInt(key.split(":")[1])).toList();

            assertEquals(3,rounds.size()); // 3 of country NL must be present
            assertEquals(3,rows.size()); // 3 of country NL must be present


            //two must be in the same half, one in the other;
            long lowerHalf = keys.stream().filter(this::inLowerHalf).count();
            long upperHalf = keys.stream().filter(this::inUpperHalf).count();
            assertTrue(   lowerHalf == 2 && upperHalf == 1 ||
                            lowerHalf == 1 && upperHalf == 2);
        }

        {
            //Create competitors (4 from other country)
            List<Competitor> competitors = new ArrayList<>();
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(2), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(2), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(2), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(1)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(1)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(1)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(1)));
            for (Competitor c : competitors) {
                c.setId(UUID.randomUUID());
            }

            tree = CreateCompetitionTree.createCompetitionTree(competitors);

            //find where countries == NL
            List<UUID> nl = competitors.stream().filter((c) -> c.getCountry().getCountryName().equals("NL")).map(Competitor::getId).toList();
            List<String> keys = tree.entrySet().stream().filter((entry) -> nl.contains(entry.getValue())).map(Map.Entry::getKey).toList();
            //validate keys are in opposite halves
            List<Integer> rounds = keys.stream().map((key) -> Integer.parseInt(key.split(":")[0])).toList();
            List<Integer> rows = keys.stream().map((key) -> Integer.parseInt(key.split(":")[1])).toList();

            assertEquals(4,rounds.size()); // 4 of country NL must be present
            assertEquals(4,rows.size()); // 4 of country NL must be present


            //two must be in the same half, the other 2 in the other;
            long lowerHalf = keys.stream().filter(this::inLowerHalf).count();
            long upperHalf = keys.stream().filter(this::inUpperHalf).count();
            assertEquals(2, lowerHalf);
            assertEquals(2, upperHalf);
        }
    }

    @Test
    public void createCompetitionTreeDuplicateClub() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Create clubs
        Method method = getNameFromIntMethod();
        List<Club> clubs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            clubs.add(new Club((String) method.invoke(null,i)));
        }

        //Create Countries
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("BE"));

        HashMap<String, UUID> tree;

        {
            //Create competitors (2 from other club)
            List<Competitor> competitors = new ArrayList<>();
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(0)));
            for (Competitor c : competitors) {
                c.setId(UUID.randomUUID());
            }

            tree = CreateCompetitionTree.createCompetitionTree(competitors);

            //find where club == "B"
            List<UUID> clubB = competitors.stream().filter((c) -> c.getClub().getClubName().equals("B")).map(Competitor::getId).toList();
            List<String> keys = tree.entrySet().stream().filter((entry) -> clubB.contains(entry.getValue())).map(Map.Entry::getKey).toList();

            //validate keys are in opposite halves
            List<Integer> rounds = keys.stream().map((key) -> Integer.parseInt(key.split(":")[0])).toList();
            List<Integer> rows = keys.stream().map((key) -> Integer.parseInt(key.split(":")[1])).toList();

            assertEquals(2,rounds.size()); // 2 of club B must be present
            assertEquals(2,rows.size()); // 2 of club B must be present

            long lowerHalf = keys.stream().filter(this::inLowerHalf).count();
            long upperHalf = keys.stream().filter(this::inUpperHalf).count();
            assertEquals(1, lowerHalf);
            assertEquals(1, upperHalf);
        }

        {
            //Create competitors (3 from other club)
            List<Competitor> competitors = new ArrayList<>();
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(0)));
            for (Competitor c : competitors) {
                c.setId(UUID.randomUUID());
            }

            tree = CreateCompetitionTree.createCompetitionTree(competitors);

            //find where club == "B"
            List<UUID> clubB = competitors.stream().filter((c) -> c.getClub().getClubName().equals("B")).map(Competitor::getId).toList();
            List<String> keys = tree.entrySet().stream().filter((entry) -> clubB.contains(entry.getValue())).map(Map.Entry::getKey).toList();

            //validate keys are in opposite halves
            List<Integer> rounds = keys.stream().map((key) -> Integer.parseInt(key.split(":")[0])).toList();
            List<Integer> rows = keys.stream().map((key) -> Integer.parseInt(key.split(":")[1])).toList();

            assertEquals(3,rounds.size()); // 3 of club B must be present
            assertEquals(3,rows.size()); // 3 of club B must be present

            //two must be in the same half, one in the other;
            long lowerHalf = keys.stream().filter(this::inLowerHalf).count();
            long upperHalf = keys.stream().filter(this::inUpperHalf).count();
            assertTrue(   lowerHalf == 2 && upperHalf == 1 ||
                    lowerHalf == 1 && upperHalf == 2);
        }

        {
            //Create competitors (4 from other club)
            List<Competitor> competitors = new ArrayList<>();
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(0), countries.get(0)));
            competitors.add(new Competitor("", "", 0, 0, clubs.get(1), countries.get(0)));
            for (Competitor c : competitors) {
                c.setId(UUID.randomUUID());
            }

            tree = CreateCompetitionTree.createCompetitionTree(competitors);

            //find where club == "B"
            List<UUID> clubB = competitors.stream().filter((c) -> c.getClub().getClubName().equals("B")).map(Competitor::getId).toList();
            List<String> keys = tree.entrySet().stream().filter((entry) -> clubB.contains(entry.getValue())).map(Map.Entry::getKey).toList();

            //validate keys are in opposite halves
            List<Integer> rounds = keys.stream().map((key) -> Integer.parseInt(key.split(":")[0])).toList();
            List<Integer> rows = keys.stream().map((key) -> Integer.parseInt(key.split(":")[1])).toList();

            assertEquals(4,rounds.size()); // 4 of club B must be present
            assertEquals(4,rows.size()); // 4 of club B must be present


            //two must be in the same half, the other 2 in the other;
            long lowerHalf = keys.stream().filter(this::inLowerHalf).count();
            long upperHalf = keys.stream().filter(this::inUpperHalf).count();
            assertEquals(2, lowerHalf);
            assertEquals(2, upperHalf);
        }
    }

    private void checkEmptyRounds(HashMap<String, UUID> tree, int round){
        for (int i = 0; i <= round; i ++){
            int upper = (int)Math.pow(2,i);
            for (int j = 0; j < upper; j++){
                String key = i + ":" + j;
                assertTrue(tree.containsKey(key));
                assertNull(tree.get(key));
            }
        }
    }

    private boolean inLowerHalf(String key){
        assertEquals(2, key.split(":").length);
        int round = Integer.parseInt(key.split(":")[0]);
        int row = Integer.parseInt(key.split(":")[1]);
        return row < (int)Math.pow(2,round-1);
    }
    private boolean inUpperHalf(String key){
        return !inLowerHalf(key);
    }
}
