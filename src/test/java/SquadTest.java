import org.junit.*;
import static org.junit.Assert.*;
public class SquadTest {

    @Test
    public void squad_instantiatesCorrectly_true() {
        Squad testSquad = new Squad("Chauvinists",4,"Equality of sexes");
        assertEquals(true, testSquad instanceof Squad);
    }
}
