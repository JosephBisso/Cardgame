package Cardgame;

import java.util.ArrayList;
import java.util.Collections;

public class AI implements Players {

    private Deck playingDeck;
    private ArrayList<Karte> karten;
    private static int ID = 0;
    private String name;
    private boolean hasCardtoPlay = false;
    private Karte lastCardPlayed;


    public AI (Deck deck) {
        playingDeck = deck;
        name = "AI_" + ++ID;
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
                    if (Spieler.boolAreAllTrue(found)) {
                        karten.removeAll(zuSpielendeKarten);
                        playingDeck.addPlayedCards(zuSpielendeKarten.toArray(new Karte[0]));
                        lastCardPlayed = card;
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

    public Karte getLastCardPlayed() {
        return lastCardPlayed;
    }

    public Karte[] preparedPossibleCardsToPlay() {
        hasCardtoPlay = false;
        ArrayList<Karte> toPlayKarten = new ArrayList<>();
        for (Karte karte : karten) {
            if (playingDeck.getCommandedCard() == null) {
                if (karte.matches(playingDeck.getLasPlayedCard())) {
                    toPlayKarten.add(karte);
                    break;
                }
            } else {
                if (karte.matches(playingDeck.getCommandedCard())) {
                    toPlayKarten.add(karte);
                    playingDeck.resetCommandedCard();
                    break;
                }
            }

        }
        for (Karte karte : karten) {
            if (!toPlayKarten.isEmpty()) {
                if (karte.matchesForPlay(toPlayKarten.get(0)) && !toPlayKarten.contains(karte)) {
                    toPlayKarten.add(karte);
                }
            }
        }
        if (toPlayKarten.size() > 0) {
            hasCardtoPlay = true;
        } else {
            lastCardPlayed = null;
        }
        return toPlayKarten.toArray(new Karte[0]);
    }

    public boolean hasCardtoPlayTurn() {
        return hasCardtoPlay;
    }

    public String getMostFrequentMotiv() throws SpielException{
        ArrayList<String> motive = new ArrayList<>();
        for (Karte karte : karten) motive.add(karte.getMotiv());
        ArrayList<Integer> frequenz = new ArrayList<>();
        int fHerz = Collections.frequency(motive, Spiel.Motiv.herz.toString());
        int fLosange = Collections.frequency(motive, Spiel.Motiv.losange.toString());
        int fPique = Collections.frequency(motive, Spiel.Motiv.pique.toString());
        int fChou = Collections.frequency(motive, Spiel.Motiv.chou.toString());
        Collections.addAll(frequenz, fHerz, fLosange, fPique, fChou);
        switch (frequenz.indexOf(Collections.max(frequenz))) {
            case 0 -> {return Spiel.Motiv.herz.toString();}
            case 1 -> {return Spiel.Motiv.losange.toString();}
            case 2 -> {return Spiel.Motiv.pique.toString();}
            case 3 -> {return Spiel.Motiv.chou.toString();}
            default -> {throw new SpielException("Es kann keine Frequen berechnet werden");}
        }
    }
}
