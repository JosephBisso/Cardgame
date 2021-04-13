package Cardgame;

import java.util.ArrayList;

public class AI implements Players {

    private Deck playingDeck;
    private ArrayList<Karte> karten;
    private static int ID = 0;
    private String name;


    public AI (Deck deck) {
        playingDeck = deck;
        name = "AI_" + ++ID;
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
                    if (Spieler.boolAreAllTrue(found)) {
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

    public int getAnzahlCards() {
        return karten.size();
    }

    public Karte[] getCards() {
        return karten.toArray(new Karte[0]);
    }

    public String getName() {
        return name;
    }
}
