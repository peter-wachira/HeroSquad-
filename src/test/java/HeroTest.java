import org.junit.*;
import static org.junit.Assert.*;

public class HeroTest {

    @Test
    public void Hero_instantiatesCorrectly_true() {
        Hero myHero = new Hero("Vick",65,"madem","madem");
        assertEquals(true, myHero instanceof Hero);
    }
    @Test
    public void getName_forHeroesName_String() {
        Hero myHero = new Hero("Vick",65,"madem","madem");
        String expected = "Vick";
        assertEquals(expected, myHero.getName());
    }
    @Test
    public void getAge_forHeroesName_String() {
        Hero myHero = new Hero("Vick",65,"madem","madem");
        int expected = 65;
        assertEquals(expected, myHero.getAge());
    }


}
