package Cardgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Spiel {

    private String name;
    private ArrayList<Karte> cards;
    public enum Rules  {
            transparent, command, reverse, skip, add2, add4, passePartout, stopAdd
    }
    public enum Farben {
            rot, schwarz;
        public String toEnglish() {
            if (this == rot) return "red";
            return "black";
        }
    }
    public enum Motiv {
            herz, pique, chou, losange, joker;

        public String toEnglish() {
                if (this == herz) return "hearts";
                if (this == pique)return "spades";
                if (this == chou) return "clubs";
                if (this == losange) return "diamonds";
                return "joker";
            }
        }

    public Spiel(String name) {
        this.name = name;
        cards = new ArrayList<>() ;
    }

    public static Motiv getEquivalentMotiv(String motiv) throws SpielException {
        for (Motiv motive : Motiv.values()) {
            if (motiv.equals(motive.toString())) return motive;
        }
        throw new SpielException("Das Motiv " + motiv + " exiestiert nicht im Spiel");
    }

    public static Farben getEquivalentFarbe(String farbe) throws SpielException {
        for (Farben farben : Farben.values()) {
            if (farbe.equals(farben.toString())) return farben;
        }
        throw new SpielException("Die Farbe " + farbe +  " exiestiert nicht im Spiel");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCards() {
        return cards.size();
    }

    public void addCard(String card_wert, String farbe, String motiv) throws SpielException {
        cards.add(new Karte(card_wert, farbe, motiv));
    }

    public void addCard(String card_wert, int anzahl) throws SpielException {
        switch (anzahl) {
            case 2 -> {
                cards.add(new Karte(card_wert, Farben.rot.toString(), Motiv.joker.toString()));
                cards.add(new Karte(card_wert, Farben.schwarz.toString(), Motiv.joker.toString()));
            }
            case 4 -> {
                cards.add(new Karte(card_wert, Farben.rot.toString(), Motiv.herz.toString()));
                cards.add(new Karte(card_wert, Farben.rot.toString(), Motiv.losange.toString()));
                cards.add(new Karte(card_wert, Farben.schwarz.toString(), Motiv.pique.toString()));
                cards.add(new Karte(card_wert, Farben.schwarz.toString(), Motiv.chou.toString()));
            }
            default -> throw new SpielException("Die gewünschte Anzahl von Karten ist ungültig");
        }
    }


    public Karte[] getCards() {
        return  cards.toArray(new Karte[0]);
    }

    public void setRules (String WERT, String rule) throws SpielException {
        ArrayList<Karte> CardsForRule = new ArrayList<>();
        for (Karte card : cards) {
            if (card.getWERT().equals(WERT)) {
                CardsForRule.add(card);
            }
        }
        for (Rules regel : Rules.values()) {
            if (regel.toString().equals(rule)) {
                for (Karte card : CardsForRule) {
                    card.addRules(rule);
                }
                return;
            }
        }
        throw new SpielException("Die gewünschte Regel ist ungültig");
    }

    public String[] getActiveRules() throws SpielException{
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
            int counter;
            for (int i = 0; i < karte.length; i++) {
                if (i > 0 && karte[i].getWERT().equals(karte[i-1].getWERT())) continue;
                counter = 1;
                for (int j = i; j < karte.length; j++) {
                    if ( j + 1 == karte.length) continue;
                    if (karte[j].getWERT().equals(karte[j + 1].getWERT())) {
                        counter++;
                    } else {
                        break;
                    }
                }
                if (counter == 2 || counter == 4) {
                    writer.printf("CARD;%d;%s\n", counter, karte[i].getWERT());
                } else {
                    writer.printf("CARD;%s;%s;%s\n", karte[i].getWERT(), karte[i].getFarbe(), karte[i].getMotiv());
                }
                if (karte[i].getRules().length != 0) {
                    writer.printf(karte[i].printRule() + "\n");
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
                    boolean found = false;
                    for (int i = 1; i < sLine.length; i++) {
                        if (!sLine[i].isEmpty()) {
                            for (Karte card : spiel.getCards()) {
                                if (card.getWERT().equals(sLine[0])) {
                                    spiel.setRules(card.getWERT(), sLine[i]);
                                    found = true;
                                }
                            }
                            if (!found) {
                                throw new SpielException("Diese Karte ist nicht im Spiel vorhanden");
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

