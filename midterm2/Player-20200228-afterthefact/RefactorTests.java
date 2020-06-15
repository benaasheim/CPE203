import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RefactorTests {
    public static final double DELTA = 0.00001;
    private ByteArrayOutputStream os;
    private PrintStream ps;
    private String Output1;
    private String Output2;

    @Before
    public void setup() throws IOException
    {
        // Set System.out to print to a ByteArray (so we can get a String)
        os = new ByteArrayOutputStream();
        ps = new PrintStream(os);
        System.setOut(ps);

        String[] args = {"game1.txt"};
        Game.main(args);
        Output1 = os.toString("UTF8");

//        // Reset the streams for the next file.
        os = new ByteArrayOutputStream();
        ps = new PrintStream(os);
        System.setOut(ps);


        args[0] = "game2.txt";
        Game.main(args);
        Output2 = os.toString("UTF8");

//        // Reset the streams for the next file.
        os = new ByteArrayOutputStream();
        ps = new PrintStream(os);
        System.setOut(ps);


    }

    //Make sure PlayerKind is not used anymore
    @Test
    public void noPlayerKind(){
        try{
            int num= Class.forName("PlayerKind").getEnumConstants().length;
            assertTrue(num==0);
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }
    //Further Test for refactoring not shown

    @Test
    public void test_toString() throws FileNotFoundException {
        Player p1 = new Greedy("GreedyP","Haha!!");
        assertEquals("toString()","Player: GreedyP, GREEDY",p1.toString());
        Player p2 = new Lazy("Mary");
        assertEquals("toString()","Player: Mary, LAZY",p2.toString());
        Player p3 = new Human("Ali");
        assertEquals("toString()","Player: Ali, HUMAN",p3.toString());
    }

    @Test(timeout=1000)
    public void test_game_withGreedy()
    {
        assertTrue("Game with Greedy Player: ", Output1.contains("Haha"));
    }

   @Test(timeout=1000)
    public void test_game_withLazy()
    {
        assertTrue("Game with Lazy Player: ", Output2.contains("Player: Irene, LAZY has left the game."));
    }








}
