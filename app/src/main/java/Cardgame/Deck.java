package Cardgame;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private static int deckID = 0;
    private String deckName_id = "Deck;";
    private final Spiel playingGame;
    private boolean moreThanOneCard_isEnabled = false;
    private String activeRule = "";
    private ArrayList<Players> listPlayers;
    private ArrayList<Karte> playableCards;
    private ArrayList<Karte> playedCards;
    private enum Style {
        one, two, three, four
    }

    public Deck(Spiel spiel) {
        playingGame = spiel;
        deckName_id += ++deckID + ";" + spiel.getName();
        listPlayers = new ArrayList<>();
        playableCards = new ArrayList<>();
        Collections.addAll(playableCards, spiel.getCards());
        mix(playableCards);
        playedCards = new ArrayList<>();
    }

    public String getActiveRule() {
        return activeRule;
    }

    public void setActiveRule(String activeRule) {
        this.activeRule += activeRule + ";";
    }

    public ArrayList<Karte> getPlayableCards() {
        return playableCards;
    }

    public ArrayList<Karte> getPlayedCards() {
        return playedCards;
    }

    public ArrayList<Players> getListPlayers() {
        return listPlayers;
    }

    public void addPlayer (Players player) {
        listPlayers.add(player);
    }

    public String getPlayingGame() {
        return playingGame.getName();
    }

    public String getDeckName_id() {
        return deckName_id;
    }

    public Karte getTopCard() {
        if (playableCards.size() < 1) {
            int counter = 0;
            while (playedCards.size() != 1) {
                playableCards.add(playedCards.get(counter));
                playedCards.remove(counter++);
            }
            mix(playableCards);
        }
        Karte card = playableCards.get(playableCards.size() - 1);
        playableCards.remove(playableCards.size() - 1);
        return card;
    }

    public void mix(ArrayList<?> list) {
        Collections.shuffle(list);
    }

    public void playGame(int numberCard_forPlayers) {
        listPlayers.get(0).pick(1);
        for (Players player: listPlayers) {
            player.pick(numberCard_forPlayers);
        }
        boolean[] playerHasCard = new boolean[listPlayers.size()];
        while (Spieler.boolAreAllTrue(playerHasCard)) {

        }
    }

    public void turn() {
        for (Players player : listPlayers) {

        }
    }
}
