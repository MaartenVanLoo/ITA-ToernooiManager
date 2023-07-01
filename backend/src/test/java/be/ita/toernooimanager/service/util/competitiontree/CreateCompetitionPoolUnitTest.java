package be.ita.toernooimanager.service.util.competitiontree;

import be.ita.toernooimanager.model.local.Club;
import be.ita.toernooimanager.model.local.Competitor;
import be.ita.toernooimanager.model.local.Country;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class CreateCompetitionPoolUnitTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    private Method getNameFromIntMethod() throws NoSuchMethodError, NoSuchMethodException {
        Method method= CreateCompetitionPool.class.getDeclaredMethod("nameFromInt",int.class);
        method.setAccessible(true);
        return method;
    }

    @Test
    public void nameFromIntTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getNameFromIntMethod();
        //value below '0'
        assertEquals("",method.invoke(null,-1));
        assertEquals("A",method.invoke(null,0));
        assertEquals("B",method.invoke(null,1));
        assertEquals("C",method.invoke(null,2));
        assertEquals("D",method.invoke(null,3));
        assertEquals("E",method.invoke(null,4));
        assertEquals("F",method.invoke(null,5));
        assertEquals("G",method.invoke(null,6));
        assertEquals("H",method.invoke(null,7));
        assertEquals("I",method.invoke(null,8));
        assertEquals("J",method.invoke(null,9));
        assertEquals("K",method.invoke(null,10));
        assertEquals("L",method.invoke(null,11));
        assertEquals("M",method.invoke(null,12));
        assertEquals("N",method.invoke(null,13));
        assertEquals("O",method.invoke(null,14));
        assertEquals("P",method.invoke(null,15));
        assertEquals("Q",method.invoke(null,16));
        assertEquals("R",method.invoke(null,17));
        assertEquals("S",method.invoke(null,18));
        assertEquals("T",method.invoke(null,19));
        assertEquals("U",method.invoke(null,20));
        assertEquals("V",method.invoke(null,21));
        assertEquals("W",method.invoke(null,22));
        assertEquals("X",method.invoke(null,23));
        assertEquals("Y",method.invoke(null,24));
        assertEquals("Z",method.invoke(null,25));
        assertEquals("AA",method.invoke(null,26));
        assertEquals("AB",method.invoke(null,27));
        assertEquals("AC",method.invoke(null,28));
        assertEquals("AD",method.invoke(null,29));
        assertEquals("AZ",method.invoke(null,26+25));
        assertEquals("BA",method.invoke(null,26+26));
        assertEquals("ZZ",method.invoke(null,26*26+25));
        assertEquals("AAA",method.invoke(null,26*26+26));
    }

    @Test
    public void createCompetitionPoolAllDifferentTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //Create clubs
        Method method = getNameFromIntMethod();
        List<Club> clubs = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            clubs.add(new Club((String) method.invoke(null,i)));
        }

        //Create Countries
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("BE"));
        countries.add(new Country("NL"));
        countries.add(new Country("SE"));
        countries.add(new Country("DE"));
        countries.add(new Country("UK"));
        countries.add(new Country("CZ"));
        countries.add(new Country("CH"));
        countries.add(new Country("AT"));
        countries.add(new Country("LU"));

        //Create competitors
        List<Competitor> competitors = new ArrayList<>();
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(3),countries.get(3)));
        competitors.add(new Competitor("","",0,0,clubs.get(4),countries.get(4)));
        competitors.add(new Competitor("","",0,0,clubs.get(5),countries.get(5)));
        competitors.add(new Competitor("","",0,0,clubs.get(6),countries.get(6)));
        competitors.add(new Competitor("","",0,0,clubs.get(7),countries.get(7)));
        competitors.add(new Competitor("","",0,0,clubs.get(8),countries.get(8)));
        for (Competitor c: competitors) {
            c.setId(UUID.randomUUID());
        }

        //Method to test:
        HashMap<String, UUID> pools = CreateCompetitionPool.createCompetitionPool(competitors,3);

        //validate output
        assertEquals(3,pools.keySet().stream().filter((s)-> s.startsWith("A:")).count());
        assertEquals(3,pools.keySet().stream().filter((s)-> s.startsWith("B:")).count());
        assertEquals(3,pools.keySet().stream().filter((s)-> s.startsWith("C:")).count());
    }
    @Test
    public void createCompetitionPoolAllDifferentClubsTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //Create clubs
        Method method = getNameFromIntMethod();
        List<Club> clubs = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            clubs.add(new Club((String) method.invoke(null,i)));
        }

        //Create Countries
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("BE"));
        countries.add(new Country("NL"));
        countries.add(new Country("SE"));

        //Create competitors
        List<Competitor> competitors = new ArrayList<>();
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(3),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(4),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(5),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(6),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(7),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(8),countries.get(0)));
        for (Competitor c: competitors) {
            c.setId(UUID.randomUUID());
        }

        //Method to test:
        HashMap<String, UUID> pools = CreateCompetitionPool.createCompetitionPool(competitors,3);

        //validate output
        assertEquals(3,pools.keySet().stream().filter((s)-> s.startsWith("A:")).count());
        assertEquals(3,pools.keySet().stream().filter((s)-> s.startsWith("B:")).count());
        assertEquals(3,pools.keySet().stream().filter((s)-> s.startsWith("C:")).count());

        List<Competitor> pool;
        List<String> poolA = pools.keySet().stream().filter((s)-> s.startsWith("A:")).sorted().toList();
        List<String> poolB = pools.keySet().stream().filter((s)-> s.startsWith("B:")).sorted().toList();
        List<String> poolC = pools.keySet().stream().filter((s)-> s.startsWith("C:")).sorted().toList();

        //validate pool A:
        pool = poolA.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertNotEquals(pool.get(0).getCountry(), pool.get(1).getCountry());
        assertNotEquals(pool.get(1).getCountry(), pool.get(2).getCountry());
        assertNotEquals(pool.get(0).getCountry(), pool.get(2).getCountry());

        //validate pool B:
        pool = poolB.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertNotEquals(pool.get(0).getCountry(), pool.get(1).getCountry());
        assertNotEquals(pool.get(1).getCountry(), pool.get(2).getCountry());
        assertNotEquals(pool.get(0).getCountry(), pool.get(2).getCountry());

        //validate pool C:
        pool = poolC.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertNotEquals(pool.get(0).getCountry(), pool.get(1).getCountry());
        assertNotEquals(pool.get(1).getCountry(), pool.get(2).getCountry());
        assertNotEquals(pool.get(0).getCountry(), pool.get(2).getCountry());
    }
    @Test
    public void createCompetitionPoolDuplicateClubsTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
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
        countries.add(new Country("SE"));

        //Create competitors
        List<Competitor> competitors = new ArrayList<>();
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        for (Competitor c: competitors) {
            c.setId(UUID.randomUUID());
        }

        //Method to test:
        HashMap<String, UUID> pools = CreateCompetitionPool.createCompetitionPool(competitors,3);

        //validate output
        assertEquals(3,pools.keySet().stream().filter((s)-> s.startsWith("A:")).count());
        assertEquals(3,pools.keySet().stream().filter((s)-> s.startsWith("B:")).count());
        assertEquals(3,pools.keySet().stream().filter((s)-> s.startsWith("C:")).count());

        List<Competitor> pool;
        List<String> poolA = pools.keySet().stream().filter((s)-> s.startsWith("A:")).sorted().toList();
        List<String> poolB = pools.keySet().stream().filter((s)-> s.startsWith("B:")).sorted().toList();
        List<String> poolC = pools.keySet().stream().filter((s)-> s.startsWith("C:")).sorted().toList();

        //validate pool A:
        pool = poolA.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertNotEquals(pool.get(0).getClub(), pool.get(1).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(0).getClub(), pool.get(2).getClub());

        //validate pool B:
        pool = poolB.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertNotEquals(pool.get(0).getClub(), pool.get(1).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(0).getClub(), pool.get(2).getClub());

        //validate pool C:
        pool = poolC.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertNotEquals(pool.get(0).getClub(), pool.get(1).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(0).getClub(), pool.get(2).getClub());
    }
    @Test
    public void createCompetitionPoolUnequalPoolSizeTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        //Create clubs
        Method method = getNameFromIntMethod();
        List<Club> clubs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            clubs.add(new Club((String) method.invoke(null,i)));
        }

        //Create Countries
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("BE"));
        countries.add(new Country("NL"));
        countries.add(new Country("SE"));

        //Create competitors
        List<Competitor> competitors = new ArrayList<>();
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(3),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(3),countries.get(0)));
        for (Competitor c: competitors) {
            c.setId(UUID.randomUUID());
        }

        //Method to test:
        HashMap<String, UUID> pools = CreateCompetitionPool.createCompetitionPool(competitors,3);

        //validate output
        assertEquals(4,pools.keySet().stream().filter((s)-> s.startsWith("A:")).count());
        assertEquals(4,pools.keySet().stream().filter((s)-> s.startsWith("B:")).count());
        assertEquals(3,pools.keySet().stream().filter((s)-> s.startsWith("C:")).count());

        List<Competitor> pool;
        List<String> poolA = pools.keySet().stream().filter((s)-> s.startsWith("A:")).sorted().toList();
        List<String> poolB = pools.keySet().stream().filter((s)-> s.startsWith("B:")).sorted().toList();
        List<String> poolC = pools.keySet().stream().filter((s)-> s.startsWith("C:")).sorted().toList();

        //validate pool A:
        pool = poolA.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertNotEquals(pool.get(0).getClub(), pool.get(1).getClub());
        assertNotEquals(pool.get(0).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(0).getClub(), pool.get(3).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(3).getClub());
        assertNotEquals(pool.get(2).getClub(), pool.get(3).getClub());

        //validate pool B:
        pool = poolB.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertNotEquals(pool.get(0).getClub(), pool.get(1).getClub());
        assertNotEquals(pool.get(0).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(0).getClub(), pool.get(3).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(3).getClub());
        assertNotEquals(pool.get(2).getClub(), pool.get(3).getClub());

        //validate pool C:
        pool = poolC.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertNotEquals(pool.get(0).getClub(), pool.get(1).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(0).getClub(), pool.get(2).getClub());
    }

    @Test
    public void createCompetitionPoolToManyFromSameClub() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
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
        countries.add(new Country("SE"));

        //Create competitors
        List<Competitor> competitors = new ArrayList<>();
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        for (Competitor c: competitors) {
            c.setId(UUID.randomUUID());
        }

        //Method to test:
        HashMap<String, UUID> pools = CreateCompetitionPool.createCompetitionPool(competitors,3);

        //validate output
        assertEquals(3,pools.keySet().stream().filter((s)-> s.startsWith("A:")).count());
        assertEquals(3,pools.keySet().stream().filter((s)-> s.startsWith("B:")).count());
        assertEquals(3,pools.keySet().stream().filter((s)-> s.startsWith("C:")).count());

        List<Competitor> pool;
        List<String> poolA = pools.keySet().stream().filter((s)-> s.startsWith("A:")).sorted().toList();
        List<String> poolB = pools.keySet().stream().filter((s)-> s.startsWith("B:")).sorted().toList();
        List<String> poolC = pools.keySet().stream().filter((s)-> s.startsWith("C:")).sorted().toList();

        boolean foundDuplicatePool = false;
        //validate pool A:
        pool = poolA.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertFalse(pool.get(0).getClub().equals(pool.get(1).getClub()) && foundDuplicatePool); //Found a second pool with equal clubs, not possible!
        if (pool.get(0).getClub().equals(pool.get(1).getClub())) foundDuplicatePool = true;
        assertNotEquals(pool.get(0).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(2).getClub());
        //validate pool B:
        pool = poolB.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertFalse(pool.get(0).getClub().equals(pool.get(1).getClub()) && foundDuplicatePool); //Found a second pool with equal clubs, not possible!
        if (pool.get(0).getClub().equals(pool.get(1).getClub())) foundDuplicatePool = true;
        assertNotEquals(pool.get(0).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(2).getClub());

        //validate pool C:
        pool = poolC.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertFalse(pool.get(0).getClub().equals(pool.get(1).getClub()) && foundDuplicatePool); //Found a second pool with equal clubs, not possible!
        if (pool.get(0).getClub().equals(pool.get(1).getClub())) foundDuplicatePool = true;
        assertNotEquals(pool.get(0).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(2).getClub());

        assertTrue(foundDuplicatePool); //Must have found a duplicate pool
    }

    @Test
    public void createCompetitionPoolToManyFromSameClubs() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
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
        countries.add(new Country("SE"));

        //Create competitors
        List<Competitor> competitors = new ArrayList<>();
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(2)));
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(1)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(0),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(1),countries.get(0)));
        competitors.add(new Competitor("","",0,0,clubs.get(2),countries.get(0)));
        for (Competitor c: competitors) {
            c.setId(UUID.randomUUID());
        }

        //Method to test:
        HashMap<String, UUID> pools = CreateCompetitionPool.createCompetitionPool(competitors,3);

        //validate output
        assertEquals(4,pools.keySet().stream().filter((s)-> s.startsWith("A:")).count());
        assertEquals(4,pools.keySet().stream().filter((s)-> s.startsWith("B:")).count());
        assertEquals(4,pools.keySet().stream().filter((s)-> s.startsWith("C:")).count());

        List<Competitor> pool;
        List<String> poolA = pools.keySet().stream().filter((s)-> s.startsWith("A:")).sorted().toList();
        List<String> poolB = pools.keySet().stream().filter((s)-> s.startsWith("B:")).sorted().toList();
        List<String> poolC = pools.keySet().stream().filter((s)-> s.startsWith("C:")).sorted().toList();

        //validate pool A:
        pool = poolA.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertEquals(pool.get(0).getClub(), pool.get(1).getClub());  //First 2 must be from the same club!
        assertNotEquals(pool.get(0).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(0).getClub(), pool.get(3).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(3).getClub());
        assertNotEquals(pool.get(2).getClub(), pool.get(3).getClub());
        //validate pool B:
        pool = poolB.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertEquals(pool.get(0).getClub(), pool.get(1).getClub());  //First 2 must be from the same club!
        assertNotEquals(pool.get(0).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(0).getClub(), pool.get(3).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(3).getClub());
        assertNotEquals(pool.get(2).getClub(), pool.get(3).getClub());

        //validate pool C:
        pool = poolC.stream().map(pools::get).map((uuid) -> competitors.stream().filter((c) -> c.getId() == uuid).findFirst().get()).toList();
        assertEquals(pool.get(0).getClub(), pool.get(1).getClub());  //First 2 must be from the same club!
        assertNotEquals(pool.get(0).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(0).getClub(), pool.get(3).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(2).getClub());
        assertNotEquals(pool.get(1).getClub(), pool.get(3).getClub());
        assertNotEquals(pool.get(2).getClub(), pool.get(3).getClub());
    }
}
