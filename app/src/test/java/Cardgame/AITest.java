package Cardgame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;

public class AITest {

    @TempDir
    File tempDir;
    private Spiel testSpiel;
    private Deck testDeck;

    @BeforeEach
    public void createGame() throws SpielException {
        testSpiel = testSpiel.loadGame("src/test/resources/J Commands.spiel");
        testDeck = testSpiel.createDeck();
    }

    @Test
    public void equals_withSameCard_isEqual() {
        AI ai1 = new AI(testDeck);
        ai1.pick(4);
    }
}