import org.junit.*;
import static org.junit.Assert.*;
public class SquadTest {

    @Test
    public void squad_instantiatesCorrectly_true() {
        Squad testSquad = new Squad("nerdsClub", 5, "Computer illiteracy");
        assertEquals(true, testSquad instanceof Squad);
    }

    @Test
    public void getName_squadInstantiatesWithName_String() {
        Squad testSquad = new Squad("nerdsClub", 5, "Computer illiteracy");
        assertEquals("nerdsClub", testSquad.getName());
    }

    @Test
    public void getSize_squadInstantiatesWithSize_int() {
        Squad testSquad = new Squad("nerdsClubs", 5, "Computer illiteracy");
        assertEquals(5, testSquad.getSize());
    }

    @Test
    public void getCause_squadInstantiatesWithCause_String() {
        Squad testSquad = new Squad("nerdsClubs", 5, "Computer illiteracy");
        assertEquals("nerdsClubs", testSquad.getCause());
    }

    @Test
    public void all_returnsAllInstancesOfSquad_true() {
        Squad firstSquad = new Squad("nerdsClubs", 5, "Computer illiteracy");
        Squad secondSquad = new Squad("Geeks", 4, "Java literacy");
        assertEquals(true, Squad.all().contains(firstSquad));
        assertEquals(true, Squad.all().contains(secondSquad));
    }

    @Test
    public void clear_emptiesAllSquadsFromList_0() {
        Squad testSquad = new Squad("nerdsClubs", 5, "Computer illiteracy");
        Squad.clear();
        assertEquals(Squad.all().size(), 0);
    }

    @Test
    public void getId_squadsInstantiatesWithAnId_1() {
        Squad.clear();
        Squad testSquad = new Squad("nerdsClubs", 5, "Computer illiteracy");
        assertEquals(1, testSquad.getId());
    }

    @Test
    public void find_returnsSquadWithSameId_secondSquad() {
        Squad.clear();
        Squad firstSquad = new Squad("nerdsClubs", 5, "Computer illiteracy");
        Squad secondSquad = new Squad("Geeks", 4, "Java literacy ");
        assertEquals(Squad.find(secondSquad.getId()), secondSquad);
    }

    @Test
    public void getHeroes_initiallyReturnsEmptyList_ArrayList() {
        Squad.clear();
        Squad testSquad = new Squad("nerdsClubs", 5, "Computer iliteracy");
        assertEquals(0, testSquad.getHeroes().size());
    }

    @Test
    public void addHero_addsHeroToList_true() {
        Squad testSquad = new Squad("nerdsClubs", 5, "Computer illiteracy");
        Hero testHero = new Hero("prodigy", 20, "intelligent", "Power failure");
        testSquad.addHero(testHero);
        assertTrue(testSquad.getHeroes().contains(testHero));
    }

    @Test
    public void heroAlreadyExists_checksIfHeroExistsinSquads_true() {
        Squad testSquad = new Squad("nerdsClubs", 5, "Computer illiteracy");
        Hero testHero = new Hero("prodigy", 20, "intelligent", "power outage");
        testSquad.addHero(testHero);
        assertTrue(testSquad.heroAlreadyExists(testHero));


    }
}