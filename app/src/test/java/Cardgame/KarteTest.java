package Cardgame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;

public class KarteTest {

    @TempDir
    File tempDir;
    private Spiel testSpiel;

    @BeforeEach
    public void createGame() {
        testSpiel = new Spiel("Versuch");
    }

    @Test
    public void equals_withSameCard_isEqual() throws SpielException{
        testSpiel.addCard("Loic", "rot", "herz");
        assertTrue(testSpiel.getCards()[0].equals(new Karte("Loic", "rot", "herz")));
    }

    @Test
    public void matches_withMatchingNumber_Matches() throws SpielException {
        testSpiel.addCard("Loic", "rot", "herz");
        assertTrue(testSpiel.getCards()[0].matches(new Karte("Loic", "schwarz", "pique")));
    }

    @Test
    public void matches_withMatchingMotiv_Matches() throws SpielException {
        testSpiel.addCard("Loic", "rot", "herz");
        assertTrue(testSpiel.getCards()[0].matches(new Karte("King", "rot", "herz")));
    }

    @Test
    public void matches_withTransparentCard_Matches() throws SpielException {
        testSpiel.addCard("Loic", "rot", "herz");
        testSpiel.setRules("Loic", "transparent");
        assertTrue(testSpiel.getCards()[0].matches(new Karte("King", "schwarz", "pique")));
    }
}
