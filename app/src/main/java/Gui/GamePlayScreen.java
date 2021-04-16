package Gui;

import Cardgame.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GamePlayScreen extends JFrame {
    private Deck deck;
    private Spieler player;
    private ArrayList<String> AINamen;
    private ArrayList<String> copyAINamen;

    private JFrame frame;
    private JPanel mainPanel_Gameplay;
    private JLabel numberCardPlayer1;
    private JLabel numberCardPlayer2;
    private JLabel numberCardPlayer3;
    private JPanel Table_cards;
    private JLabel label_tableCards;
    private JButton pickButton;
    private JPanel panel_yourCardIMG;
    private JLabel label_CardIMG0;
    private JLabel label_CardIMG1;
    private JLabel label_CardIMG2;
    private JLabel label_CardIMG3;
    private JLabel label_CardIMG4;
    private JLabel label_CardIMG5;
    private JButton playSelectedCardButton;
    private JList list_yourdCards;
    private JTextArea label_addedCards;
    private JLabel label_Card0;
    private JLabel label_Card1;
    private JLabel label_Card2;
    private JLabel label_Card3;
    private JLabel label_Card4;
    private JLabel label_Card5;
    private JLabel label_Card6;
    private JLabel label_Card7;
    private JLabel label_Card8;
    private JLabel label_Card9;
    private JButton resetButton;
    private JLabel label_Card10;
    private JLabel label_Card11;
    private JLabel label_Card12;
    private JLabel label_Card13;
    private JLabel label_Card14;
    private JLabel label_Card15;
    private JLabel label_Card16;
    private JLabel label_Card17;
    private JLabel label_Card18;
    private JLabel label_Card19;
    private JTextArea textArea_CardOnTable;
    private JTabbedPane tabbedPane_yourCards;
    private JPanel panel_yourCards;
    private JButton nextSlotsButton;
    private JButton previousSlotsButton;
    private JTextField textField_whoWon;
    private JLabel label_LastCardPLayedP1;
    private JLabel label_LastCardPLayedP2;
    private JLabel label_LastCardPLayedP3;
    private JPanel pannel_p1;
    private JPanel pannel_p2;
    private JPanel pannel_p3;
    private JLabel label_arrowOderOfPlay;
    private JPanel panel_upperPart;
    private JPanel panel_upperUpperPart;
    private JPanel panel_lowerPart;
    private JPanel panel_scrollbar;

    private JLabel[] label_yourCardsIMG = {label_CardIMG0, label_CardIMG1, label_CardIMG2, label_CardIMG3,label_CardIMG4,
            label_CardIMG5};
    private JLabel[] label_yourCards = {label_Card0, label_Card1, label_Card2, label_Card3, label_Card4, label_Card5,
            label_Card6, label_Card7, label_Card8, label_Card9, label_Card10, label_Card11, label_Card12, label_Card13, label_Card14, label_Card15,
            label_Card16, label_Card17, label_Card18, label_Card19};
    private Karte[] playerKarten;
    private String cardsToBePlayed = "";
    private ArrayList<Integer> index_cardsToBePlayed;
    private ArrayList<Karte> arrayList_cardsToBePlayed;
    private Players[] array_players;
    private boolean spielerHasPlayed = false;
    private  int numberSlot = 0;
    Color defaultBackgroundColor;
    private int counter = 0;
    private int playerHaveToPick = -99;
    private boolean gameEnded = false;

    public GamePlayScreen(Deck deck) {
        this.deck = deck;
        AINamen = new ArrayList<>();
        index_cardsToBePlayed = new ArrayList<>();
        arrayList_cardsToBePlayed = new ArrayList<>();
        copyAINamen = new ArrayList<>();
        frame = new JFrame("GamePlayScreen");

        panel_yourCardIMG.setBackground(new Color(173, 216, 230));
        panel_yourCardIMG.setForeground(Color.BLACK);
        panel_yourCards.setBackground(new Color(173, 216, 230));
        panel_yourCards.setForeground(Color.BLACK);
        textArea_CardOnTable.setBackground(new Color(173, 216, 230));
        textArea_CardOnTable.setForeground(Color.BLACK);
        Table_cards.setBackground(new Color(173, 216, 230));
        Table_cards.setForeground(Color.BLACK);
        mainPanel_Gameplay.setBackground(Color.DARK_GRAY);
        mainPanel_Gameplay.setForeground(Color.WHITE);
        panel_upperPart.setBackground(Color.DARK_GRAY);
        panel_upperPart.setForeground(Color.WHITE);
        panel_lowerPart.setBackground(Color.DARK_GRAY);
        panel_lowerPart.setForeground(Color.WHITE);



        frame.setContentPane(mainPanel_Gameplay);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(StartScreen.windowIcon);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        frame.pack();
        GuiUtil.showFullScreen(frame);
        frame.setVisible(true);

        defaultBackgroundColor = pannel_p1.getBackground();
        initializeGame();
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardsToBePlayed = "";
                label_addedCards.setText(cardsToBePlayed);
                index_cardsToBePlayed.clear();
            }
        });
        pickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getCards().length == 20) {
                    JOptionPane.showMessageDialog(null,"You reached the maximal amount" +
                                    "of cards (20)! You turn will br skipped",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!label_addedCards.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"Reset you Cards to be play " +
                                    "before picking",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                player.pick(1);
                setLabels(player.getCards());
                cardsToBePlayed = "";
                label_addedCards.setText(cardsToBePlayed);
                index_cardsToBePlayed.clear();
                turn(array_players);
                setIconTableCard();
            }
        });
        playSelectedCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerPlaysSelectedCards();
            }
        });
        label_CardIMG0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(0 + numberSlot);
                }

            }
        });
        label_CardIMG1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(1 + numberSlot);
                }
            }
        });
        label_CardIMG2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(2 + numberSlot);
                }
            }
        });
        label_CardIMG3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(3 + numberSlot);
                }
            }
        });
        label_CardIMG4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(4 + numberSlot);
                }
            }
        });
        label_CardIMG5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(5 + numberSlot);
                }
            }
        });
        label_Card0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(0 + numberSlot);
                }
            }
        });
        label_Card1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(1 + numberSlot);
                }
            }
        });
        label_Card2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(2 + numberSlot);
                }
            }
        });
        label_Card3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(3 + numberSlot);
                }
            }
        });
        label_Card4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(4 + numberSlot);
                }
            }
        });
        label_Card5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(5 + numberSlot);
                }
            }
        });
        label_Card6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(6 + numberSlot);
                }
            }
        });
        label_Card7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(7 + numberSlot);
                }
            }
        });
        label_Card8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(8 + numberSlot);
                }
            }
        });
        label_Card9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(9 + numberSlot);
                }
            }
        });
        label_Card10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                   preparedCardToBePlayed(10);
                }
            }
        });
        label_Card11.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(11);
                }
            }
        });
        label_Card12.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(12);
                }
            }
        });
        label_Card13.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(13);
                }
            }
        });
        label_Card14.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(14);
                }
            }
        });
        label_Card15.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(15);
                }
            }
        });
        label_Card16.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(16);
                }
            }
        });
        label_Card17.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(17);
                }
            }
        });
        label_Card18.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(18);
                }
            }
        });
        label_Card19.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    preparedCardToBePlayed(19);
                }
            }
        });
        nextSlotsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!previousSlotsButton.isEnabled()) previousSlotsButton.setEnabled(true);
                numberSlot += 6;
                setLabels_nextPrevious_onlyIMG(player.getCards());
            }
        });
        previousSlotsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playerKarten.length > 6 && !nextSlotsButton.isEnabled()) {
                    nextSlotsButton.setEnabled(true);
                }
                numberSlot -= 6;
                if (numberSlot < 0) numberSlot = 0;
                setLabels_nextPrevious_onlyIMG(player.getCards());
            }
        });
    }

    private void preparedCardToBePlayed(int index) {
        if (!index_cardsToBePlayed.contains(index)) {
            cardsToBePlayed += playerKarten[index].print() + "\n";
            label_addedCards.setText(cardsToBePlayed);
            index_cardsToBePlayed.add(index);
        }
    }
    public void initializeGame() {
        array_players = new Players[deck.getListPlayers().size()];
        int i = 0;
        for (Players players : deck.getListPlayers()) {
            array_players[i++] = players;
            if (players instanceof Spieler) {
                players.pick(5);
                array_players[0] = players;
                player = (Spieler) players;
            } else {
                players.pick(4);
                AINamen.add(players.getName());
            }
        }
        numberCardPlayer1.setText(Integer.toString(getAI(AINamen.get(0)).getAnzahlCards()));
        numberCardPlayer2.setText(Integer.toString(getAI(AINamen.get(1)).getAnzahlCards()));
        numberCardPlayer3.setText(Integer.toString(getAI(AINamen.get(2)).getAnzahlCards()));
        copyAINamen = (ArrayList<String>) AINamen.clone();
        setIconTableCard();

        setLabels(player.getCards());
        panel_yourCardIMG.setAutoscrolls(true);
    }

    private AI getAI(String name) {
        for (Players player : deck.getListPlayers()) {
            if (player.getName().equals(name)) return (AI) player;
        }
        return null;
    }

    private void setLabels(Karte[] karten) {
        playerKarten = karten;
        previousSlotsButton.setEnabled(false);
        numberSlot = 0;
        nextSlotsButton.setEnabled(karten.length > label_yourCardsIMG.length);
        for(int i = 0; i < karten.length; i++) {
            if (!(label_yourCards[i].isEnabled() && label_yourCards[i].isVisible())) {
                label_yourCards[i].setVisible(true);
                label_yourCards[i].setEnabled(true);
            }
            label_yourCards[i].setText(karten[i].print());
            if (i < label_yourCardsIMG.length) {
                if (!label_yourCardsIMG[i].isVisible()) label_yourCardsIMG[i].setVisible(true);
                label_yourCardsIMG[i].setIcon(karten[i].getStyle());
            }
        }
        if (karten.length < label_yourCards.length) {
            for (int j = karten.length ; j < label_yourCards.length; j++) {
                label_yourCards[j].setText("none");
                label_yourCards[j].setEnabled(false);
                label_yourCards[j].setVisible(false);
                if (j < label_yourCardsIMG.length) {
                    label_yourCardsIMG[j].setVisible(false);
                }

            }
        }
    }

    private void setLabels_nextPrevious_onlyIMG(Karte[] karten) {
        if (numberSlot == 0) previousSlotsButton.setEnabled(false);
        for (int i = numberSlot; i < karten.length; i++) {
            int j = i - numberSlot;
            if (j < label_yourCardsIMG.length) {
                if (!label_yourCardsIMG[j].isVisible()) label_yourCardsIMG[j].setVisible(true);
                label_yourCardsIMG[j].setIcon(karten[i].getStyle());
            }
        }
        if (karten.length - numberSlot < label_yourCardsIMG.length) {
            nextSlotsButton.setEnabled(false);
            for (int j = karten.length - numberSlot ; j < label_yourCardsIMG.length; j++) {
                label_yourCardsIMG[j].setVisible(false);
            }
        }
    }

    private void setIconTableCard () {
        Karte card_onTable;
        if (deck.getPlayedCards().size() == 0) {
            card_onTable = deck.giveOneCard();
        } else {
            card_onTable = deck.getLasPlayedCard();
        }
        label_tableCards.setIcon(card_onTable.getStyle());
        String descriptionCard = card_onTable.print() + "\n\n";
        if (card_onTable.getRules().length != 0) {
            descriptionCard += card_onTable.printRule() + "\n\n";
        }
        if (deck.getCommandedCard() != null) {
            descriptionCard += "** " + deck.getCommandedCard().print() + " **\n\n";
        }
        textArea_CardOnTable.setText(descriptionCard);
    }

    private void playerPlaysSelectedCards() {
        if (index_cardsToBePlayed.isEmpty()) {
            JOptionPane.showMessageDialog(null,"No Cards have been " +
                            "selected. Pick a Card, if you want to skip playing!",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (int i : index_cardsToBePlayed) {
            arrayList_cardsToBePlayed.add(playerKarten[i]);
        }
        if (playerHaveToPick == 3 || playerHaveToPick == 2 || playerHaveToPick == 1) {
            if (!playerPlayedWisely()) {
                cardsToBePlayed = "";
                label_addedCards.setText(cardsToBePlayed);
                index_cardsToBePlayed.clear();
                arrayList_cardsToBePlayed.clear();
            }
        } else if (deck.getCommandedCard() != null) {
            if (!arrayList_cardsToBePlayed.get(0).matches(deck.getCommandedCard()) ||
                    (arrayList_cardsToBePlayed.get(0).hasRule(Spiel.Rules.command) &&
                    !arrayList_cardsToBePlayed.get(0).matches(deck.getLasPlayedCard()))) {
                JOptionPane.showMessageDialog(null,
                        arrayList_cardsToBePlayed.get(0).print() +
                                "\ndoes not matches with\n" +
                                deck.getCommandedCard().print() + "\nWhich was COMMANDED!!",
                        "Error", JOptionPane.WARNING_MESSAGE);
                cardsToBePlayed = "";
                label_addedCards.setText(cardsToBePlayed);
                index_cardsToBePlayed.clear();
                arrayList_cardsToBePlayed.clear();
                return;
            } else if(arrayList_cardsToBePlayed.get(0).hasRule(Spiel.Rules.transparent)) {

            } else {
                deck.resetCommandedCard();
            }
        } else {
            for (int i = 0; i < arrayList_cardsToBePlayed.size() - 1; i++) {
                if (!arrayList_cardsToBePlayed.get(i).matchesForPlay(arrayList_cardsToBePlayed.get(i + 1))) {
                    JOptionPane.showMessageDialog(null,
                            arrayList_cardsToBePlayed.get(i).print() +
                                    "\ncan not be combined-played with\n" +
                                    arrayList_cardsToBePlayed.get(i + 1).print(),
                            "Error", JOptionPane.WARNING_MESSAGE);
                    cardsToBePlayed = "";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.clear();
                    arrayList_cardsToBePlayed.clear();
                    return;
                }
            }
            if (!deck.getLasPlayedCard().matches(arrayList_cardsToBePlayed.get(0))) {
                JOptionPane.showMessageDialog(null,
                        arrayList_cardsToBePlayed.get(0).print() +
                                "\ndoes not matches with\n" +
                                deck.getLasPlayedCard().print() + "\nOn the Table",
                        "Error", JOptionPane.WARNING_MESSAGE);
                cardsToBePlayed = "";
                label_addedCards.setText(cardsToBePlayed);
                index_cardsToBePlayed.clear();
                arrayList_cardsToBePlayed.clear();
                return;
            }
        }
        for (int i : index_cardsToBePlayed) {
            if (i < label_yourCardsIMG.length) {
                label_yourCardsIMG[i].setIcon(new ImageIcon());
            }
        }
        player.play(arrayList_cardsToBePlayed.toArray(new Karte[0]));
        try {
            doRule(arrayList_cardsToBePlayed.toArray(new Karte[0]));
        } catch (SpielException e) {
            e.printStackTrace();
        }
        cardsToBePlayed = "";
        label_addedCards.setText(cardsToBePlayed);
        index_cardsToBePlayed.clear();
        arrayList_cardsToBePlayed.clear();
        setLabels(player.getCards());
        setIconTableCard();
        spielerHasPlayed = true;

        turn(array_players);
        setIconTableCard();
    }

    private boolean playerPlayedWisely() {
        switch (playerHaveToPick) {
            case 3 -> {
                if (!arrayList_cardsToBePlayed.get(0).hasRule(Spiel.Rules.add4)) {
                    JOptionPane.showMessageDialog(null,"You pick " +
                                    deck.getToPick() + " Cards","Be Careful",
                            JOptionPane.WARNING_MESSAGE);
                    player.pick(deck.getToPick());
                    deck.resetToPick();
                    return false;
                }
            }
            case 2 -> {
                if (!arrayList_cardsToBePlayed.get(0).hasRule(Spiel.Rules.add2)) {
                    JOptionPane.showMessageDialog(null,"You pick " +
                                    deck.getToPick() + " Cards","Be Careful",
                            JOptionPane.WARNING_MESSAGE);
                    player.pick(deck.getToPick());
                    deck.resetToPick();
                    return false;
                }
            }
            case 1 -> {
                if (!arrayList_cardsToBePlayed.get(0).hasRule(Spiel.Rules.stopAdd)) {
                    JOptionPane.showMessageDialog(null,"You pick " +
                                    deck.getToPick() + " Cards","Be Careful",
                            JOptionPane.WARNING_MESSAGE);
                    player.pick(deck.getToPick());
                    deck.resetToPick();
                    return false;
                }
            }
        }
        return true;
    }

    private void doRule(Karte[] cards) throws SpielException{
        for (Karte karte : cards) {
            if (!karte.hasRule()) return;
            for (String rules : karte.getRules()) {
                switch (Spiel.getEquivalentRule(rules).getRuleID()) {
                    case 2 -> {
                        if (deck.getToPick() > 0) {
                            deck.resetToPick();
                        } else {
                            CommandDialog commandDialog = new CommandDialog();
                            commandDialog.exec();
                            if (commandDialog.isMotivCommanded()) {
                                deck.setCommandedCard(commandDialog.getCommandedMotiv());
                            }
                            return;
                        }
                    }
                    case 3 ->  {
                        array_players = Deck.reverse(array_players);
                        if (label_arrowOderOfPlay.getText().equals(">>>")) {
                            label_arrowOderOfPlay.setText("<<<");
                        } else {
                            label_arrowOderOfPlay.setText(">>>");
                        }
                    }
                    case 4 -> {
                        ArrayList<Players> tempPLayerList = new ArrayList<>();
                        Collections.addAll(tempPLayerList, array_players);
                        counter = tempPLayerList.indexOf(player);
                        if (counter != 0) {
                            if (counter + 1 >= array_players.length) {
                                counter = 0;
                            }
                            if (array_players[counter].hasCard()) {
                                ((AI) array_players[counter]).set_gotSkipped(true);
                            }
                        } else {
                            counter = 1;
                            ((AI) array_players[counter]).set_gotSkipped(true);
                        }
                        setNumberCardPlayer_andLastPLayedCard((AI) array_players[counter++]);
                    }
                    case 5 -> doAdd2_or_4(2);
                    case 6 -> doAdd2_or_4(4);
                }
            }
        }
    }

    private String doRule_forAI(Karte[] cards, AI AIx) throws SpielException{
        for (Karte karte : cards) {
            if (!karte.hasRule()) return "";
            for (String rules : karte.getRules()) {
                switch (Spiel.getEquivalentRule(rules).getRuleID()) {
                    case 2 -> {
                        if (deck.getToPick() > 0) {
                            deck.resetToPick();
                        } else {
                            deck.setCommandedCard(Spiel.getEquivalentMotiv(AIx.getMostFrequentMotiv()).toEnglish());
                            return " commanded: " + deck.getCommandedCard().print();
                        }
                    }
                    case 3 -> {
                        array_players = Deck.reverse(array_players);
                        if (label_arrowOderOfPlay.getText().equals(">>>")) {
                            label_arrowOderOfPlay.setText("<<<");
                        } else {
                          label_arrowOderOfPlay.setText(">>>");
                        }
                        return " reversed the Order of plays!";
                    }
                    case 4 -> {
                        try {
                            if (array_players[++counter] instanceof AI) {
                                if (array_players[counter].hasCard()) {
                                    ((AI) array_players[counter]).set_gotSkipped(true);
                                    setNumberCardPlayer_andLastPLayedCard((AI) array_players[counter]);
                                    return " used Skip on the next AI";
                                }
                            } else {
                                setNumberCardPlayer_andLastPLayedCard(AIx);
                                JOptionPane.showMessageDialog(null,
                                        "You got skipped by " + AIx.getName() ,
                                        "Be Careful", JOptionPane.WARNING_MESSAGE);
                                pickButton.setEnabled(false);

                                ArrayList<Players> tempPLayerList = new ArrayList<>();
                                Collections.addAll(tempPLayerList, array_players);
                                counter = tempPLayerList.indexOf(player);
                                if (counter == array_players.length - 1) {
                                    counter = -1;
                                }
                                return " used Skip on YOU";
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            counter = 0;
                            if (array_players[counter] instanceof AI) {
                                if ( array_players[counter] == AIx) counter++;
                                ((AI) array_players[counter]).set_gotSkipped(true);
                                setNumberCardPlayer_andLastPLayedCard((AI) array_players[counter]);
                                return " used Skip on the next AI";
                            } else {
                                setNumberCardPlayer_andLastPLayedCard(AIx);
                                JOptionPane.showMessageDialog(null,
                                      "You got skipped by " + AIx.getName() ,
                                        "Be Careful", JOptionPane.WARNING_MESSAGE);
                                pickButton.setEnabled(false);

                                ArrayList<Players> tempPLayerList = new ArrayList<>();
                                Collections.addAll(tempPLayerList, array_players);
                                counter = tempPLayerList.indexOf(player);
                                if (counter == array_players.length - 1) {
                                    counter = -1;
                                }
                                return " used Skip on YOU";
                            }
                        }
                    }
                    case 5 -> {
                        doAdd2_or_4(2);
                        return " used A Pick 2 Card";
                    }
                    case 6 -> {
                        doAdd2_or_4(4);
                        return " used A Pick 4 Card";
                    }
                }
            }
        }
        return "";
    }

    private void doAdd2_or_4(int anzahlCardToAdd) {
        if (anzahlCardToAdd == 2) {
            deck.addToPick(2);
            return;
        }
        deck.addToPick(4);
    }

    private int doAddCard_forPlayerToPick() {
        switch (deck.add2(player)) {
            case 3 -> {
                JOptionPane.showMessageDialog(null,"Play wisely or You'll pick " +
                        deck.getToPick(), "Be Careful", JOptionPane.WARNING_MESSAGE);
                pickButton.setEnabled(false);
                return 3;
            }
            case 2 -> {
                JOptionPane.showMessageDialog(null,"Play wisely or You'll pick " +
                        deck.getToPick(), "Be Careful", JOptionPane.WARNING_MESSAGE);
                pickButton.setEnabled(false);
                return 2;
            }
            case 1 -> {
                JOptionPane.showMessageDialog(null,"Play wisely or You'll pick " +
                        deck.getToPick(), "Be Careful", JOptionPane.WARNING_MESSAGE);
                pickButton.setEnabled(false);
                return 1;
            }
            default -> {return 0;}
        }
    }

    private void turn(Players[] players) {
        playSelectedCardButton.setEnabled(false);
        pickButton.setEnabled(false);
        resetButton.setEnabled(false);

        if (gameEnded) return;

        JOptionPane.showMessageDialog(null,"AIs Turn",
                "Wait ", JOptionPane.WARNING_MESSAGE);

        ArrayList<Players> list_player =new ArrayList<>();
        Collections.addAll(list_player, players);

        while (counter < players.length){
            Players player = players[counter];
            if (player instanceof Spieler) {
                if (!player.hasCard()) break;
                counter++;
                continue;
            }
            AI AIx = getAI(player.getName());
            if (AIx.hasCard()) {
                Karte[] AIPossibleCards = AIx.preparedPossibleCardsToPlay();
                if (AIx.hasCardtoPlayTurn()) {
                    AIx.play(AIPossibleCards);
                    try {
                        String ruleDone = doRule_forAI(AIPossibleCards, AIx);
                        if (!ruleDone.isEmpty()) {
                            textField_whoWon.setText(AIx.getName() + ruleDone);
                        } else {
                            textField_whoWon.setText("");
                        }
                    } catch (SpielException e) {
                        e.printStackTrace();
                    }
                } else if (deck.getToPick() > 0 && !AIx.HasCardToBlockAdd()) {
                    AIx.pick(deck.getToPick());
                    textField_whoWon.setText(AIx.getName() + " picked " + deck.getToPick() + " Cards!!");
                    deck.resetToPick();
                } else {
                    AIx.pick(1);
                }
            }
            setNumberCardPlayer_andLastPLayedCard(AIx);
            alertOneCardLeft(AIx);
            setIconTableCard();
            if (!AIx.hasCard()) {
                list_player.remove(AIx);
                array_players = list_player.toArray(new Players[0]);
                if (deck.atLeast2PlayerHaveCards()) {
                    counter++;
                    continue;
                }
                break;
            }
            counter++;

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!deck.allPlayersHaveCard()) {
            if (!player.hasCard()) {
                if (list_player.contains(player)) {
                    JOptionPane.showMessageDialog(null,
                            "Nice Game!!",
                            "Game Over", JOptionPane.WARNING_MESSAGE);
                    list_player.remove(player);
                    turn(list_player.toArray(new Players[0]));
                }
            }
            ArrayList<Boolean> playersHaveCards = new ArrayList<Boolean>();
            for (int i = 0; i < deck.getListPlayers().size(); i++) {
                Players players1 = deck.getListPlayers().get(i);
                playersHaveCards.add(players1.hasCard());
            }
            if (Collections.frequency(playersHaveCards, true) == 1)  {
                JOptionPane.showMessageDialog(null,
                        deck.getListPlayers().get(playersHaveCards.indexOf(true)).getName() + " loosed the Game" +
                                "\nYou can close the game",
                        "Game Over", JOptionPane.WARNING_MESSAGE);
                gameEnded = true;
            }
            if (!list_player.contains(player) && !gameEnded) {
                counter = 0;
                setIconTableCard();
                turn(players);
            }
        }
        if (gameEnded) return;
        counter = 0;
        playSelectedCardButton.setEnabled(true);
        pickButton.setEnabled(true);
        resetButton.setEnabled(true);

        JOptionPane.showMessageDialog(null,"Your Turn",
                "Be Careful", JOptionPane.WARNING_MESSAGE);

        if (deck.getToPick() > 0) {
            playerHaveToPick = doAddCard_forPlayerToPick();
            if (playerHaveToPick == 0) {
                JOptionPane.showMessageDialog(null,"You pick " +
                        deck.getToPick() + " Cards","Be Careful",
                        JOptionPane.WARNING_MESSAGE);
                player.pick(deck.getToPick());
                deck.resetToPick();
                setLabels(player.getCards());
                turn(players);
            }
        } else {
            playerHaveToPick = -99;
        }
    }

    private void setNumberCardPlayer_andLastPLayedCard(AI AIx) {
        if (AIx.getName().equals(getAI(copyAINamen.get(0)).getName())) {
            numberCardPlayer1.setText(Integer.toString(AIx.getAnzahlCards()));
            if (AIx.getLastCardPlayed() == null) {
                if (AIx.gotSkipped()) {
                    label_LastCardPLayedP1.setText("**got skipped**");
                } else {
                    label_LastCardPLayedP1.setText("**picked**");
                }
            } else {
                label_LastCardPLayedP1.setText(AIx.getLastCardPlayed().print());
            }
        } else if (AIx.getName().equals(getAI(copyAINamen.get(1)).getName())) {
            numberCardPlayer2.setText(Integer.toString(AIx.getAnzahlCards()));
            if (AIx.getLastCardPlayed() == null) {
                if (AIx.gotSkipped()) {
                    label_LastCardPLayedP2.setText("**got skipped**");
                } else {
                    label_LastCardPLayedP2.setText("**picked**");
                }
            } else {
                label_LastCardPLayedP2.setText(AIx.getLastCardPlayed().print());
            }
        } else if (AIx.getName().equals(getAI(copyAINamen.get(2)).getName())) {
            numberCardPlayer3.setText(Integer.toString(AIx.getAnzahlCards()));
            if (AIx.getLastCardPlayed() == null) {
                if (AIx.gotSkipped()) {
                    label_LastCardPLayedP3.setText("**got skipped**");
                } else {
                    label_LastCardPLayedP3.setText("**picked**");
                }
            } else {
                label_LastCardPLayedP3.setText(AIx.getLastCardPlayed().print());
            }
        }
    }
    private void alertOneCardLeft(AI AIx) {
        if (AIx.getName().equals(getAI(copyAINamen.get(0)).getName())) {
            if (AIx.getAnzahlCards() == 1) {
                pannel_p1.setBackground(Color.GREEN);
            } else if (AIx.getAnzahlCards() == 0) {
                pannel_p1.setBackground(Color.CYAN);
            } else {
                pannel_p1.setBackground(defaultBackgroundColor);
            }
        } else if (AIx.getName().equals(getAI(copyAINamen.get(1)).getName())) {
            if (AIx.getAnzahlCards() == 1) {
                pannel_p2.setBackground(Color.GREEN);
            } else if (AIx.getAnzahlCards() == 0) {
                pannel_p2.setBackground(Color.CYAN);
            } else {
                pannel_p2.setBackground(defaultBackgroundColor);
            }
        } else if (AIx.getName().equals(getAI(copyAINamen.get(2)).getName())) {
            if (AIx.getAnzahlCards() == 1) {
                pannel_p3.setBackground(Color.GREEN);
            } else if (AIx.getAnzahlCards() == 0) {
                pannel_p3.setBackground(Color.CYAN);
            } else {
                pannel_p3.setBackground(defaultBackgroundColor);
            }
        }
    }
}
