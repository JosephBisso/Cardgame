package Cardgame;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Spiel {

    private String name;
    private ArrayList<Karte> cards;
    private enum Rules  {
            transparent, command, reverse, skip, add2, add4, passePartout, stopAdd;
    };
    private enum Farben {
            rot, schwarz, alle2;
    };
    private enum Motiv {
            herz, pique, chou, losange, joker, alle4;
    };

    public Spiel(String name) {
        this.name = name;
        cards = new ArrayList<Karte>() ;
    }

    public String getName() {
        return name;
    }

    public void addCard(String card_wert, String farbe, String motiv) throws SpielException {
        cards.add(new Karte(card_wert, farbe, motiv));
    }

    public void addCard(String card_wert, int anzahl) throws SpielException {
        switch (anzahl) {
            case 2: {
                cards.add(new Karte(card_wert, Farben.alle2.toString(), Motiv.joker.toString()));
            }
            case 4: {
                cards.add(new Karte(card_wert, Farben.alle2.toString(), Motiv.alle4.toString()));
            }
            default: {
                throw new SpielException("Die gewünschte Anzahl von Karten ist ungültig");
            }
        }
    }


    public Karte[] getCards() {
        return  cards.toArray(new Karte[cards.size()]);
    }

    public void setRules (Karte card, String rule) throws SpielException {
        for (Rules regel : Rules.values()) {
            if (regel.toString().equals(rule)) {
                card.addRules(rule);
                return;
            }
        }
        throw new SpielException("Die gewünschte Regel ist ungültig");
    }

    public String[] getAvticeRules() throws SpielException{
        ArrayList<String> activeRules = new ArrayList<String>();

        if (cards.isEmpty()) {
            throw new SpielException("Es sind keine Karten im Spiel");
        }
        for(Karte card : cards.toArray(new Karte[cards.size()])) {
            String[] cardRules = card.getRules();
            if (cardRules.length != 0) {
                for (String regel : cardRules) {
                    if (!activeRules.contains(regel)) {
                        activeRules.add(regel);
                    }
                }
            }
        }
        return activeRules.toArray(new String[activeRules.size()]);
    }

    public void saveGame(String pfad) throws SpielException {
        try (PrintWriter writer = new PrintWriter(pfad)) {
            writer.printf("GAME;%s\n", name);

            Karte[] karte = cards.toArray(new Karte[cards.size()]);
            if (karte.length == 0) {
                throw new SpielException("Keine Karte im Spiel vorhanden");
            }
            for (Karte card : karte) {
                if (card.getAnzahl() == 2 || card.getAnzahl() == 4) {
                    writer.printf("CARD;%d;%s\n", card.getAnzahl(), card.getWert());
                } else {
                    writer.printf("CARD;%s;%s;%s\n", card.getWert(), card.getFarbe(), card.getMotiv());
                }
                if (card.getRules().length != 0) {
                    writer.printf("CARDS RULE;%s\n", card.printRule());
                }
            }
        } catch (IOException i) {
            throw new SpielException(i.getMessage());
        }
    }

    public Spiel loadGame(String name) {
        return this;
    }

    public Deck createDeck() {
        return new Deck(this);
    }

}

