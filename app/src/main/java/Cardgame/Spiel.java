package Cardgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Spiel {

    private final String name;
    private ArrayList<Karte> cards;
    enum Rules  {
            transparent, command, reverse, skip, add2, add4, passePartout, stopAdd
    }
    private enum Farben {
            rot, schwarz, alle2
    }
    private enum Motiv {
            herz, pique, chou, losange, joker, alle4
    }

    public Spiel(String name) {
        this.name = name;
        cards = new ArrayList<>() ;
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
        return  cards.toArray(new Karte[0]);
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
        ArrayList<String> activeRules = new ArrayList<>();

        if (cards.isEmpty()) {
            throw new SpielException("Es sind keine Karten im Spiel");
        }
        for(Karte card : cards.toArray(new Karte[0])) {
            String[] cardRules = card.getRules();
            if (cardRules.length != 0) {
                for (String regel : cardRules) {
                    if (!activeRules.contains(regel)) {
                        activeRules.add(regel);
                    }
                }
            }
        }
        return activeRules.toArray(new String[0]);
    }

    public void saveGame(String pfad) throws SpielException {
        try (PrintWriter writer = new PrintWriter(pfad)) {
            writer.printf("GAME;%s\n", name);

            Karte[] karte = cards.toArray(new Karte[0]);
            if (karte.length == 0) {
                throw new SpielException("Keine Karte im Spiel vorhanden");
            }
            for (Karte card : karte) {
                if (card.getAnzahl() == 2 || card.getAnzahl() == 4) {
                    writer.printf("CARD;%d;%s\n", card.getAnzahl(), card.getWERT());
                } else {
                    writer.printf("CARD;%s;%s;%s\n", card.getWERT(), card.getFarbe(), card.getMotiv());
                }
                if (card.getRules().length != 0) {
                    writer.printf(card.printRule() + "\n");
                }
            }
        } catch (IOException i) {
            throw new SpielException(i.getMessage());
        }
    }

    public Spiel loadGame(String pfad) throws SpielException {
        File file = new File(pfad);
        Spiel spiel = new Spiel("");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] sLine = line.split(";");

                if (sLine[0].equals("GAME")) {
                   spiel = new Spiel(sLine[1]);
                } else if (sLine[0].equals(("CARD"))) {
                    if (sLine.length == 3) {
                        try {
                            spiel.addCard(sLine[2], Integer.parseInt(sLine[1]));
                        } catch (NumberFormatException e) {
                            throw new SpielException("Die Anzahl der gewünschten Karte muss eine Zahl sein");
                        }
                    } else if (sLine.length == 4) {
                        spiel.addCard(sLine[1], sLine[2], sLine[3]);
                    }
                } else if (sLine[0].contains("Rule for Card ")) {
                    sLine[0] = sLine[0].replace("Rule for Card ", "").trim();
                    for (int i = 1; i < sLine.length; i++) {
                        if (!sLine[i].isEmpty()) {
                            for (Karte card : spiel.getCards()) {
                                if (card.getWERT().equals(sLine[0])) {
                                    spiel.setRules(card, sLine[i]);
                                } else {
                                    throw new SpielException("Diese Karte ist nicht im Spiel vorhanden");
                                }
                            }
                        }
                    }
                }



            }

            return  spiel;
        } catch (FileNotFoundException e) {
            throw new SpielException("Die Datei konnte nicht geöffnet werden");
        }
    }

    public Deck createDeck() {
        return new Deck(this);
    }

    public static void doRule() {

    }

}

