package Cardgame;

import java.util.ArrayList;
import java.util.Collections;

public class Spieler implements Players {
    private final String NAME = "spieler";
    private Deck playingDeck;
    private ArrayList<Karte> karten;

    public Spieler(Deck deck) {
        playingDeck = deck;
        karten = new ArrayList<>();
    }

    public Karte[] play(Karte[] toPlayKarte) {
        boolean[] found = new boolean[toPlayKarte.length];
        int counter = 0;
        ArrayList<Karte> zuSpielendeKarten = new ArrayList<>();
        for (Karte toPlayCard : toPlayKarte) {
            for (Karte card : karten) {
                if (card.equals(toPlayCard)) {
                    found[counter++] = true;
                    zuSpielendeKarten.add(card);
                    if (boolAreAllTrue(found)) {
                        karten.removeAll(zuSpielendeKarten);
                        playingDeck.addPlayedCards(zuSpielendeKarten.toArray(new Karte[0]));
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

    public int getAnzahlCards() {
        return karten.size();
    }

    public Karte[] getCards() {
        return karten.toArray(new Karte[0]);
    }

    public String getName() {
        return NAME;
    }
}
