import org.junit.*;
import static org.junit.Assert.*;

public class WordleTest {

    WordleModel m;

    @Before
    public void setUp() throws Exception {
        m = new WordleModel();
        m.setWord("hello");
    }

    @Test
    public void checkWinningWord() {
        m.checkPlay("hello");
        assertArrayEquals(new String[] {"green", "green", "green", "green", "green"}, m.getLetterStatus());
    }

    @Test
    public void checkAllDifferentLetters() {
        m.checkPlay("adapt");
        assertArrayEquals(new String[] {"grey", "grey", "grey", "grey", "grey"}, m.getLetterStatus());
    }

    @Test
    public void checkAllMisPlacedLetters() {
        m.checkPlay("elohl");
        assertArrayEquals(new String[] {"yellow", "yellow", "yellow", "yellow", "yellow"}, m.getLetterStatus());
    }

    @Test
    //check to see if the double letter is detected as yellow twice if it appears twice in actual word
    public void checkDoubleLetterDetected() {
        m.checkPlay("colal");
        assertArrayEquals(new String[] {"grey", "yellow", "green", "grey", "yellow"}, m.getLetterStatus());
    }

    @Test
    public void checkSingleLetterNotDetectedTwiceWhenInWrongPlace() {
        m.checkPlay("colol");
        assertArrayEquals(new String[] {"grey", "yellow", "green", "grey", "yellow"}, m.getLetterStatus());
    }

    @Test
    public void checkSingleLetterNotDetectedTwiceWhenInRightPlace() {
        m.checkPlay("helel");
        assertArrayEquals(new String[] {"green", "green", "green", "grey", "yellow"}, m.getLetterStatus());

    }

    @Test
    public void checkNotCaseSensitive() {
        m.checkPlay("HELLO");
        assertArrayEquals(new String[] {"green", "green", "green", "green", "green"}, m.getLetterStatus());
    }


}