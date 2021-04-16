package Cardgame;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
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
    private Karte commandedCard;
    private int toPick = 0;

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

    public void addToPick(int anzahl) {
        toPick += anzahl;
    }

    public void resetToPick() {
        toPick = 0;
    }

    public int getToPick() {
        return toPick;
    }

    public void setLasPlayedCard(Karte lasPlayedCard) {
        this.lasPlayedCard = lasPlayedCard;
    }

    public Karte getTopCard() {
        if (playableCards.size() < 1) {
            int counter = 0;
            while (playedCards.size() != 1 ||
                    (counter < playableCards.size() && counter < playedCards.size())) {
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
        setCommandedCard(motiv);
        ArrayList<Karte> karten = new ArrayList<>();
        Collections.addAll(karten, card);
        playedCards.addAll(karten);
    }

    private void reverse(Karte[] card) {
        ArrayList<Karte> karten = new ArrayList<>();
        Collections.addAll(karten, card);
        playedCards.addAll(karten);
        Collections.reverse(listPlayers);
    }

    public static Players[] reverse(Players[] players) {
        ArrayList<Players> player = new ArrayList<>();
        Collections.addAll(player, players);
        Collections.reverse(player);
        return player.toArray(new Players[0]);
    }

    private void skip(Karte[] card) {
        ArrayList<Karte> karten = new ArrayList<>();
        Collections.addAll(karten, card);
        playedCards.addAll(karten);
    }

    public int add2 (Players player) {
        ArrayList<Karte> karten = new ArrayList<>();
        Collections.addAll(karten, player.getCards());
        boolean found1 = false,
                found2 = false,
                found3 = false;
        for (Karte possible_addStopCard : karten) {
            if (possible_addStopCard.getRules().length == 0) {
                continue;
            }
            ArrayList <String> rules = new ArrayList<>();
            Collections.addAll(rules, possible_addStopCard.getRules());
            if (rules.contains(Spiel.Rules.stopAdd.toString())) {
                found1 = true;
                break;
            }
            if (rules.contains(Spiel.Rules.add2.toString())) {
                found2 = true;
                break;
            }
            if (rules.contains(Spiel.Rules.add4.toString())) {
                found3 = true;
                break;
            }
        }
        if (found3) return 3; // player have a add4 card. Doesn't pick if he plays it
        if (found2) return 2; // player have a add2 card. Doesn't pick if he plays it
        if (found1) return 1; // player have a stopAdd card. Doesn't pick if he plays it

        return 0; // player pick 2 Cards
    }

    public int add4 (Players player) {
        ArrayList<Karte> karten = new ArrayList<>();
        Collections.addAll(karten, player.getCards());
        boolean found5 = false,
                found6 = false,
                found7 = false;
        for (Karte possible_addStopCard : karten) {
            if (possible_addStopCard.getRules().length == 0) {
                continue;
            }
            ArrayList <String> rules = new ArrayList<>();
            Collections.addAll(rules, possible_addStopCard.getRules());
            if (rules.contains(Spiel.Rules.stopAdd.toString())) {
                found5 = true;
                break;
            }
            if (rules.contains(Spiel.Rules.add2.toString())) {
                found6 = true;
                break;
            }
            if (rules.contains(Spiel.Rules.add4.toString())) {
                found7 = true;
                break;
            }
        }
        if (found7) return 7; // player have a add4 card. Doesn't pick if he plays it
        if (found6) return 6; // player have a add2 card. Doesn't pick if he plays it
        if (found5) return 5; // player have a stopAdd card. Doesn't pick if he plays it

        return 4; // player pick 4 Cards
    }

    public boolean allPlayersHaveCard() {
        for (Players player : listPlayers) {
            if (!player.hasCard()) {
                return false;
            }
        }
        return true;
    }

    public boolean atLeast2PlayerHaveCards() {
        ArrayList<Boolean> playerHasCards = new ArrayList<>();
        for (Players players : listPlayers) {
            playerHasCards.add(players.hasCard());
        }
        return Collections.frequency(playerHasCards, true) >= 2;
    }

    public void addPlayedCards(Karte[] playedCards) {
        for (Karte zuSpielendeKarte : playedCards) {
            if (zuSpielendeKarte.hasRule()) {
                try {
                    for (String string_rule : zuSpielendeKarte.getRules()) {
                        if (Spiel.getEquivalentRule(string_rule).getRuleID() == 1) {
                            beTransparent(playedCards);
                            return;
                        }
                    }

                } catch (SpielException e) {
                    e.printStackTrace();
                }
            }
        }
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

    public void setCommandedCard(String motiv) throws SpielException{
        for (Spiel.Motiv motive : Spiel.Motiv.values()) {
            if (motive.toEnglish().equals(motiv)) {
                commandedCard = new Karte("commanded Card",
                        motive.getFarbe_exeptJoker(), motive.toString());
                return;
            }
        }
        throw new SpielException("Es könnte keine commanded Card festgelegt werden");
    }

    public Karte getCommandedCard() {
        return commandedCard;
    }

    public void resetCommandedCard() {
        commandedCard = null;
    }
}
