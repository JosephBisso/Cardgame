package Cardgame;

import java.util.ArrayList;

public class Spieler implements Players {
    Deck playingDeck;
    ArrayList<Karte> Karten;

    public Spieler(Deck deck) {
        deck.addPlayer(this);
        playingDeck = deck;
        Karten = new ArrayList<>();
    }

    public Karte[] play(Karte[] toPlayKarte) {
        boolean[] found = new boolean[toPlayKarte.length];
        int counter = 0;
        for (Karte card : Karten) {
            for (Karte toPlayCard : toPlayKarte) {
                if (card.equals(toPlayCard)) {
                    found[counter++] = true;
                    if (boolAreAllTrue(found)) return toPlayKarte;
                }
            }
        }
        return new Karte[0];
    }

    public boolean hasCard() {
        return Karten.size() != 0;
    }

    public void pick(int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            Karten.add(playingDeck.getTopCard());
        }
    }

    public static boolean boolAreAllTrue(boolean[] bool) {
        for(boolean b : bool) if(!b) return false;
        return true;
    }

}
