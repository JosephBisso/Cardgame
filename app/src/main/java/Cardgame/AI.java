package Cardgame;

import java.util.ArrayList;

public class AI implements Players {
    Deck playingDeck;
    ArrayList<Karte> Karten;

    public Karte[] play(Karte[] toPlayKarte) {
        boolean[] found = new boolean[toPlayKarte.length];
        int counter = 0;
        for (Karte card : Karten) {
            for (Karte toPlayCard : toPlayKarte) {
                if (card.equals(toPlayCard)) {
                    found[counter++] = true;
                    if (Spieler.boolAreAllTrue(found)) return toPlayKarte;
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
}
