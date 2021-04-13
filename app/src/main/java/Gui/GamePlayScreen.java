package Gui;

import Cardgame.Deck;
import Cardgame.Karte;
import Cardgame.Players;
import Cardgame.Spieler;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GamePlayScreen extends JFrame {
    private Deck deck;
    private Spieler player;
    private ArrayList<String> AINamen;

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
    private JLabel label_CardIMG6;
    private JLabel label_CardIMG7;
    private JLabel label_CardIMG8;
    private JLabel label_CardIMG9;
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
    private JTextArea textArea1;
    private JTabbedPane tabbedPane_yourCards;
    private JPanel panel_scrollbar;

    private JLabel[] label_yourCardsIMG = {label_CardIMG0, label_CardIMG1, label_CardIMG2, label_CardIMG3,label_CardIMG4,
            label_CardIMG5, label_CardIMG6, label_CardIMG7, label_CardIMG8, label_CardIMG9};
    private JLabel[] label_yourCards = {label_Card0, label_Card1, label_Card2, label_Card3, label_Card4, label_Card5,
            label_Card6, label_Card7, label_Card8, label_Card9, label_Card10, label_Card11, label_Card12, label_Card13, label_Card14, label_Card15,
            label_Card16, label_Card17, label_Card18, label_Card19};
    private Karte[] playerKarten;
    private String cardsToBePlayed = "";
    private ArrayList<Integer> index_cardsToBePlayed;
    private ArrayList<Karte> arrayList_cardsToBePlayed;

    public GamePlayScreen(Deck deck) {
        this.deck = deck;
        AINamen = new ArrayList<>();
        index_cardsToBePlayed = new ArrayList<>();
        arrayList_cardsToBePlayed = new ArrayList<>();
        JFrame frame = new JFrame("GamePlayScreen");
        frame.setContentPane(mainPanel_Gameplay);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.pack();
        frame.setVisible(true);

        initializeGame();
        label_CardIMG0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[0].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(0);
                }

            }
        });
        label_CardIMG1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[1].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(1);
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardsToBePlayed = "";
                label_addedCards.setText(cardsToBePlayed);
                index_cardsToBePlayed.clear();
            }
        });
        label_CardIMG2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[2].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(2);
                }
            }
        });
        label_CardIMG3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[3].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(3);
                }
            }
        });
        label_CardIMG4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[4].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(4);
                }
            }
        });
        label_CardIMG5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[5].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(5);
                }
            }
        });
        label_CardIMG6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[6].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(6);
                }
            }
        });
        label_CardIMG7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[7].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(7);
                }
            }
        });
        label_CardIMG8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[8].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(8);
                }
            }
        });
        label_CardIMG9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[9].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(9);
                }
            }
        });
        pickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.pick(1);
                setLabels(player.getCards());
            }
        });
        playSelectedCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerPlaysSelectedCards();
            }
        });
        label_Card6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[6].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(6);
                }
            }
        });
        label_Card7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[7].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(7);
                }
            }
        });
        label_Card8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[8].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(8);
                }
            }
        });
        label_Card9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cardsToBePlayed += playerKarten[9].print() + "\n";
                    label_addedCards.setText(cardsToBePlayed);
                    index_cardsToBePlayed.add(9);
                }
            }
        });
    }

    public void initializeGame() {
        for (Players players : deck.getListPlayers()) {
            if (players instanceof Spieler) {
                players.pick(5);
                player = (Spieler) players;
            } else {
                players.pick(4);
                AINamen.add(players.getName());
            }
        }
        numberCardPlayer1.setText(Integer.toString(getAI(AINamen.get(0)).getAnzahlCards()));
        numberCardPlayer2.setText(Integer.toString(getAI(AINamen.get(1)).getAnzahlCards()));
        numberCardPlayer3.setText(Integer.toString(getAI(AINamen.get(2)).getAnzahlCards()));
        setIconTableCard();

        setLabels(player.getCards());
        panel_yourCardIMG.setAutoscrolls(true);
    }

    private Players getAI(String name) {
        for (Players player : deck.getListPlayers()) {
            if (player.getName().equals(name)) return player;
        }
        return null;
    }

    private void setLabels(Karte[] karten) {
        playerKarten = karten;
        for(int i = 0; i < karten.length; i++) {
            if (!label_yourCards[i].isEnabled()) label_yourCards[i].setEnabled(true);
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
                if (j < label_yourCardsIMG.length) {
                    label_yourCardsIMG[j].setVisible(false);
                }

            }
        }
    }

    private void setIconTableCard () {
        if (deck.getPlayedCards().size() == 0) {
            label_tableCards.setIcon(deck.giveOneCard().getStyle());
        }
        label_tableCards.setIcon(deck.getLasPlayedCard().getStyle());
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
        for (int i : index_cardsToBePlayed) {
            label_yourCardsIMG[i].setIcon(new ImageIcon());
        }
        player.play(arrayList_cardsToBePlayed.toArray(new Karte[0]));
        cardsToBePlayed = "";
        label_addedCards.setText(cardsToBePlayed);
        index_cardsToBePlayed.clear();
        arrayList_cardsToBePlayed.clear();
        setLabels(player.getCards());
        setIconTableCard();
    }
}
