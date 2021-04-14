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

public class GamePlayScreen extends JFrame {
    private Deck deck;
    private Spieler player;
    private ArrayList<String> AINamen;
    private ArrayList<String> copyAINamen;

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

    public GamePlayScreen(Deck deck) {
        this.deck = deck;
        AINamen = new ArrayList<>();
        index_cardsToBePlayed = new ArrayList<>();
        arrayList_cardsToBePlayed = new ArrayList<>();
        copyAINamen = new ArrayList<>();
        JFrame frame = new JFrame("GamePlayScreen");
        frame.setContentPane(mainPanel_Gameplay);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.pack();
        frame.setVisible(true);

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
        if (karten.length > label_yourCardsIMG.length) {
            nextSlotsButton.setEnabled(true);
        } else {
            nextSlotsButton.setEnabled(false);
        }
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
        if (deck.getCommandedCard() != null) {
            if (!arrayList_cardsToBePlayed.get(0).matches(deck.getCommandedCard())) {
                JOptionPane.showMessageDialog(null,
                        arrayList_cardsToBePlayed.get(0).print() +
                                "\ndoes not matches with\n" +
                                deck.getLasPlayedCard().print() + "\nWhich was COMMANDED!!",
                        "Error", JOptionPane.WARNING_MESSAGE);
                cardsToBePlayed = "";
                label_addedCards.setText(cardsToBePlayed);
                index_cardsToBePlayed.clear();
                arrayList_cardsToBePlayed.clear();
                return;
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

    private void doRule(Karte[] cards) throws SpielException{
        for (Karte karte : cards) {
            if (!karte.hasRule()) return;
            for (String rules : karte.getRules()) {
                switch (Spiel.getEquivalentRule(rules).getRuleID()) {
                    case 2 -> {
                        CommandDialog commandDialog = new CommandDialog();
                        commandDialog.exec();
                        if (commandDialog.isMotivCommanded()) {
                            deck.setCommandedCard(commandDialog.getCommandedMotiv());
                        } else {
                        }
                        return;
                    }
                    case 3 -> {
                        Collections.reverse(AINamen);
                    }
                }
            }
        }
    }

    private void doRule_forAI(Karte[] cards, AI AIx) throws SpielException{
        for (Karte karte : cards) {
            if (!karte.hasRule()) return;
            for (String rules : karte.getRules()) {
                switch (Spiel.getEquivalentRule(rules).getRuleID()) {
                    case 2 -> {
                        deck.setCommandedCard(Spiel.getEquivalentMotiv(AIx.getMostFrequentMotiv()).toEnglish());
                    }
                    case 3 -> {
                        Collections.reverse(AINamen);
                    }
                }
            }
        }
    }

    private void turn(Players[] players) {
        playSelectedCardButton.setEnabled(false);
        pickButton.setEnabled(false);
        resetButton.setEnabled(false);

        JOptionPane.showMessageDialog(null,"AIs Turn",
                "Wait ", JOptionPane.WARNING_MESSAGE);

        for (String AIName : AINamen) {
            AI AIx = (AI) getAI(AIName);
            if (AIx.hasCard()) {
                Karte[] AIPossibleCards = AIx.preparedPossibleCardsToPlay();
                if (AIx.hasCardtoPlayTurn()) {
                    AIx.play(AIPossibleCards);
                    try {
                        doRule_forAI(AIPossibleCards, AIx);
                    } catch (SpielException e) {
                        e.printStackTrace();
                    }
                } else {
                    AIx.pick(1);
                }
            }
            if (!AIx.hasCard()) break;
        }
        numberCardPlayer1.setText(Integer.toString(getAI(copyAINamen.get(0)).getAnzahlCards()));
        numberCardPlayer2.setText(Integer.toString(getAI(copyAINamen.get(1)).getAnzahlCards()));
        numberCardPlayer3.setText(Integer.toString(getAI(copyAINamen.get(2)).getAnzahlCards()));

        if (getAI(copyAINamen.get(0)).getLastCardPlayed() == null) {
            label_LastCardPLayedP1.setText("**picked**");
        } else {
            label_LastCardPLayedP1.setText(getAI(copyAINamen.get(0)).getLastCardPlayed().print());
        }
        if (getAI(copyAINamen.get(1)).getLastCardPlayed() == null) {
            label_LastCardPLayedP2.setText("**picked**");
        } else {
            label_LastCardPLayedP2.setText(getAI(copyAINamen.get(1)).getLastCardPlayed().print());
        }
        if (getAI(copyAINamen.get(2)).getLastCardPlayed() == null) {
            label_LastCardPLayedP3.setText("**picked**");
        } else {
            label_LastCardPLayedP3.setText(getAI(copyAINamen.get(2)).getLastCardPlayed().print());
        }

        playSelectedCardButton.setEnabled(true);
        pickButton.setEnabled(true);
        resetButton.setEnabled(true);
        for (Players players1 : array_players) {
            if (!players1.hasCard()) {
                JOptionPane.showMessageDialog(null, players1.getName() + " won the game",
                        "End", JOptionPane.WARNING_MESSAGE);
                playSelectedCardButton.setEnabled(false);
                pickButton.setEnabled(false);
                resetButton.setEnabled(false);
                textField_whoWon.setText(players1.getName() + "won the game");
                textField_whoWon.setFont(new Font("Big", Font.BOLD, 15));
                textField_whoWon.setDisabledTextColor(Color.BLUE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"Yout Turn",
                "Be Careful", JOptionPane.WARNING_MESSAGE);
    }
}
