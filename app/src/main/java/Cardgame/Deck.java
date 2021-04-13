package Cardgame;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Deck {

    private int style;
    private static int deckID = 0;
    private String deckName_id = "Deck;";
    private Spiel playingGame;
    private boolean moreThanOneCard_isEnabled = false;
    private String activeRule = "";
    private ArrayList<Players> listPlayers;
    private ArrayList<Karte> playableCards;
    private ArrayList<Karte> playedCards;
    private Karte lasPlayedCard;
    private int toPick = 0;

    public Deck() {

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

    public void setStyle(int styleFromCombo) throws SpielException {
        style = styleFromCombo;
        String pfad;
        String restPfad;

        switch (styleFromCombo) {
            case 1 -> {
                pfad = "app/src/main/resources/Card Set One";
                File folder = new File(pfad);
                File[] listCards = folder.listFiles();

                for (Karte karte : playableCards) {
                    restPfad = Spiel.getEquivalentMotiv(karte.getMotiv()).toEnglish() +
                            "_" + karte.getWERT() + ".png";

                    for (File cardsFile : listCards) {
                        if (!karte.getWERT().equals("Joker") &&
                               cardsFile.getName().equals(restPfad.toLowerCase())) {

                            karte.setStyle(new ImageIcon(cardsFile.getAbsolutePath()));
                        } else if (karte.getWERT().equals("Joker")) {
                            restPfad = Spiel.getEquivalentMotiv(karte.getMotiv()).toEnglish() +
                                    "_" + Spiel.getEquivalentFarbe(karte.getFarbe()).toEnglish() +
                                    ".png";
                            if (cardsFile.getName().equals(restPfad.toLowerCase())) {
                                karte.setStyle(new ImageIcon(cardsFile.getAbsolutePath()));
                            }
                        }
                    }
                }
            }
        }
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

    public Karte getLasPlayedCard() {
        lasPlayedCard = playedCards.get(playedCards.size() - 1);
        return lasPlayedCard;
    }

    private void addToPick(int anzahl) {
        toPick += anzahl;
    }

    private void resetToPick() {
        toPick = 0;
    }

    public void setLasPlayedCard(Karte lasPlayedCard) {
        this.lasPlayedCard = lasPlayedCard;
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
        while (allPlayersHaveCard()) {

        }
    }

    public void turn() {
        for (Players player : listPlayers) {

        }
        setLasPlayedCard(playedCards.get(playedCards.size() - 1));
    }

    private void beTransparent(Karte[] card) {
        ArrayList<Karte> karten = new ArrayList<>();
        Collections.addAll(karten, card);
        playedCards.addAll(0, karten);
    }

    private void commands(Karte[] card, String motiv) throws SpielException {
        try {
            setLasPlayedCard(new Karte("COMMAND", "all2", motiv));
            ArrayList<Karte> karten = new ArrayList<>();
            Collections.addAll(karten, card);
            playedCards.addAll(karten);
        } catch (SpielException e) {
            throw e;
        }
    }

    private void reverse(Karte[] card) {
        ArrayList<Karte> karten = new ArrayList<>();
        Collections.addAll(karten, card);
        playedCards.addAll(karten);
        Collections.reverse(listPlayers);
    }

    private void skip(Karte[] card) {
        ArrayList<Karte> karten = new ArrayList<>();
        Collections.addAll(karten, card);
        playedCards.addAll(karten);
    }

    private boolean add2 (Karte[] card, Players player, Karte possible_addStopCard) {
        ArrayList<Karte> karten = new ArrayList<>();
        Collections.addAll(karten, card);
        playedCards.addAll(karten);
        if (possible_addStopCard.getRules().length == 0) {
            player.pick(2);
            return true;
        }
        ArrayList <String> rules = new ArrayList<>();
        Collections.addAll(rules, possible_addStopCard.getRules());
        if (rules.contains(Spiel.Rules.stopAdd.toString()))  {
            playedCards.add(possible_addStopCard);
            if (rules.contains(Spiel.Rules.add2.toString())) {
                addToPick(2);
            } else if (rules.contains(Spiel.Rules.add4.toString())) {
                addToPick(4);
            }
            return false;
        }
        return true;
    }

    private boolean add4 (Karte[] card, Players player, Karte possible_addStopCard) {
        ArrayList<Karte> karten = new ArrayList<>();
        Collections.addAll(karten, card);
        playedCards.addAll(karten);
        if (possible_addStopCard.getRules().length == 0) {
            player.pick(4);
            return true;
        }
        ArrayList <String> rules = new ArrayList<>();
        Collections.addAll(rules, possible_addStopCard.getRules());
        if (rules.contains(Spiel.Rules.stopAdd.toString()))  {
            playedCards.add(possible_addStopCard);
            if (rules.contains(Spiel.Rules.add2.toString())) {
                addToPick(2);
            } else if (rules.contains(Spiel.Rules.add4.toString())) {
                addToPick(4);
            }
            return false;
        }
        return true;
    }

    private boolean allPlayersHaveCard() {
        for (Players player : listPlayers) {
            if (!player.hasCard()) {
                return false;
            }
        }
        return true;
    }

    public void addPlayedCards(Karte[] playedCards) {
        Collections.addAll(this.playedCards, playedCards);
        lasPlayedCard = playedCards[playedCards.length -1];
    }

    public Karte giveOneCard() {
        for (Karte karte : playableCards) {
            if (karte.getRules().length == 0) {
                lasPlayedCard = karte;
                playableCards.remove(karte);
                playedCards.add(karte);
                 return lasPlayedCard;
            }
        }
        return null;
    }

}
