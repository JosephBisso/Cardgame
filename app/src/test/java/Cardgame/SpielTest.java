package Cardgame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;


public class SpielTest {

    @TempDir
    File tempDir;
    private Spiel testSpiel;

    @BeforeEach
    public void createGame() {
        testSpiel = new Spiel("Versuch");
    }

    @Test
    public void addCard_with4Cards_CardsAreAdded() throws SpielException {
        testSpiel.addCard("6", 4);
        assertEquals (testSpiel.getCards().length, 4);
    }

    @Test
    public void addCard_withOneCard_CardIsAdded() throws SpielException {
        testSpiel.addCard("Loic", "rot", "herz");
        Karte[] cards = testSpiel.getCards();
        assertEquals(cards[cards.length - 1].getFarbe(), "rot");
    }

    @Test
    public void getActiveRules_withThreeRules_RulesAreAdded() throws SpielException {
        testSpiel.addCard("A", "schwarz", "pique");
        testSpiel.addCard("7", "rot", "losange");
        testSpiel.setRules("A", "skip");
        testSpiel.setRules("7", "add2" );
        testSpiel.setRules("7", "stopAdd" );
        TestUtil.assertArrayEqualsUnordered(testSpiel.getActiveRules(), new String[] {"skip", "add2", "stopAdd"});
    }

    @Test
    public void getActiveRules_withPairsOfCardsAndManyRules_RulesAreAdded() throws SpielException {
        testSpiel.addCard("2", 4);
        testSpiel.addCard("Q", 2);
        testSpiel.addCard("Loic", "schwarz", "pique");
        testSpiel.addCard("10", 4);
        testSpiel.setRules("2", "transparent" );
        testSpiel.setRules("10", "reverse" );
        testSpiel.setRules("Loic", "stopAdd" );
        TestUtil.assertArrayEqualsUnordered(testSpiel.getActiveRules(),
                new String[] {"transparent", "reverse", "stopAdd"});
    }

    @Test
    public void saveGame_FileExists() throws SpielException {
        testSpiel.addCard("J", 2);
        testSpiel.setRules("J", "add2" );
        File targetFile = new File(tempDir, "Test1.spiel");
        testSpiel.saveGame(targetFile.getAbsolutePath());
        assertTrue(targetFile.exists());
    }

    @Test
    public void saveGame_withManyCardAndRule_showsAll() throws SpielException {
        testSpiel.addCard("A", 4);
        testSpiel.addCard("10", 4);
        testSpiel.addCard("Loic", "schwarz", "pique");
        testSpiel.addCard("Joker", 2);
        testSpiel.setRules("A", "skip");
        testSpiel.setRules("10", "reverse");
        testSpiel.setRules("Joker", "add4");
        testSpiel.setRules("Joker", "stopAdd");
        File targetFile = new File("src/test/resources/Probe1.spiel");
        testSpiel.saveGame(targetFile.getAbsolutePath());
        assertTrue(targetFile.exists());
    }

    @Test
    public void loadGame_withFullGame_canSaveTheGameagain() throws SpielException {
        testSpiel = testSpiel.loadGame("src/test/resources/J Commands.spiel");
        File targetFile = new File(tempDir, "Test3.spiel");
        testSpiel.saveGame(targetFile.getAbsolutePath());
        assertNotNull(testSpiel.loadGame(targetFile.getAbsolutePath()));
    }

    @Test
    public void loadGame_withJCommands_hasAllCards() throws SpielException {
        testSpiel = testSpiel.loadGame("src/test/resources/J Commands.spiel");
        assertEquals(testSpiel.getCards().length, 54);
    }

    @Test
    public void loadGame_withJCommands_hasAllRules() throws SpielException {
        testSpiel = testSpiel.loadGame("src/test/resources/J Commands.spiel");
        TestUtil.assertArrayEqualsUnordered(testSpiel.getActiveRules(),
                new String[] {"skip", "transparent", "add2", "stopAdd", "reverse",
                        "command", "add4", "passePartout"});
    }

}
