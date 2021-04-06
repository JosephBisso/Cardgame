package Cardgame;

import java.util.ArrayList;

public class Spieler implements Players {
    Deck playingDeck;
    ArrayList<Karte> karten;

    public Spieler(Deck deck) {
        deck.addPlayer(this);
        playingDeck = deck;
        karten = new ArrayList<>();
    }

    public Karte[] play(Karte[] toPlayKarte) {
        boolean[] found = new boolean[toPlayKarte.length];
        int counter = 0;
        ArrayList<Karte> zuSpielendeKarten = new ArrayList<>();
        for (Karte card : karten) {
            for (Karte toPlayCard : toPlayKarte) {
                if (card.equals(toPlayCard)) {
                    found[counter++] = true;
                    zuSpielendeKarten.add(card);
                    if (boolAreAllTrue(found)) {
                        karten.removeAll(zuSpielendeKarten);
                        return toPlayKarte;
                    }
                }
            }
        }
        return new Karte[0];
    }

    public boolean hasCard() {
        return karten.size() != 0;
    }

    public void pick(int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            karten.add(playingDeck.getTopCard());
        }
    }

    public static boolean boolAreAllTrue(boolean[] bool) {
        for(boolean b : bool) if(!b) return false;
        return true;
    }

}
