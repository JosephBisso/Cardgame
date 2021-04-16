package Cardgame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private Spiel testSpiel;
    private Deck testDeck;
    private Players player;

    @BeforeEach
    public void createGame() throws SpielException{
        testSpiel = new Spiel("Versuch");
        testSpiel.addCard("6", 4);
        testSpiel.addCard("7", 4);
    }

    @Test
    public void add2_playerDontHavePowerfullCards_return0() {
        testDeck = testSpiel.createDeck();
        player = new Spieler(testDeck);
        player.pick(4);
        assertEquals(testDeck.add2(player), 0);
    }
    @Test
    public void add2_playerJustHaveNormalRulesCards_return0() throws SpielException {
        testSpiel.setRules("6", Spiel.Rules.transparent.toString());
        testDeck = testSpiel.createDeck();
        player = new Spieler(testDeck);
        player.pick(4);
        assertEquals(testDeck.add2(player), 0);
    }

    @Test
    public void add2_playerHaveStopAddCards_return1() throws SpielException {
        testSpiel.setRules("6", Spiel.Rules.stopAdd.toString());
        testDeck = testSpiel.createDeck();
        player = new Spieler(testDeck);
        player.pick(4);
        assertEquals(testDeck.add2(player), 1);
    }

    @Test
    public void add2_playerHaveAdd2Cards_return2() throws SpielException {
        testSpiel.setRules("6", Spiel.Rules.add2.toString());
        testDeck = testSpiel.createDeck();
        player = new Spieler(testDeck);
        player.pick(4);
        assertEquals(testDeck.add2(player), 2);
    }

    @Test
    public void add2_playerHaveAdd4Cards_return3() throws SpielException {
        testSpiel.setRules("6", Spiel.Rules.add4.toString());
        testDeck = testSpiel.createDeck();
        player = new Spieler(testDeck);
        player.pick(4);
        assertEquals(testDeck.add2(player), 3);
    }

    ///

    @Test
    public void add4_playerDontHavePowerfullCards_return4() {
        testDeck = testSpiel.createDeck();
        player = new Spieler(testDeck);
        player.pick(4);
        assertEquals(testDeck.add4(player), 4);
    }
    @Test
    public void add4_playerJustHaveNormalRulesCards_return4() throws SpielException {
        testSpiel.setRules("6", Spiel.Rules.reverse.toString());
        testDeck = testSpiel.createDeck();
        player = new Spieler(testDeck);
        player.pick(4);
        assertEquals(testDeck.add4(player), 4);
    }

    @Test
    public void add4_playerHaveStopAddCards_return5() throws SpielException {
        testSpiel.setRules("6", Spiel.Rules.stopAdd.toString());
        testDeck = testSpiel.createDeck();
        player = new Spieler(testDeck);
        player.pick(4);
        assertEquals(testDeck.add4(player), 5);
    }

    @Test
    public void add4_playerHaveAdd2Cards_return6() throws SpielException {
        testSpiel.setRules("6", Spiel.Rules.add2.toString());
        testDeck = testSpiel.createDeck();
        player = new Spieler(testDeck);
        player.pick(4);
        assertEquals(testDeck.add4(player), 6);
    }

    @Test
    public void add4_playerHaveAdd4Cards_return7() throws SpielException {
        testSpiel.setRules("6", Spiel.Rules.add4.toString());
        testDeck = testSpiel.createDeck();
        player = new Spieler(testDeck);
        player.pick(4);
        assertEquals(testDeck.add4(player), 7);
    }
}