package Gui;

import Cardgame.Deck;
import Cardgame.Players;
import Cardgame.Spieler;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel panel_yourCards;
    private JPanel panel_yourCardIMG;
    private JLabel label_listCard0;
    private JLabel label_listCard1;
    private JLabel label_listCard2;
    private JLabel label_listCard3;
    private JLabel label_listCard4;
    private JLabel label_listCard5;
    private JLabel label_listCard6;
    private JLabel label_listCard7;
    private JLabel label_listCard8;
    private JLabel label_listCard9;
    private JScrollBar scrollBar1;
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

    public GamePlayScreen(Deck deck) {
        this.deck = deck;
        AINamen = new ArrayList<>();
        JFrame frame = new JFrame("GamePlayScreen");
        frame.setContentPane(mainPanel_Gameplay);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.pack();
        frame.setVisible(true);

        initializeGame();
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
        label_tableCards.setIcon(deck.getTopCard().getStyle());

        JLabel[] label_yourCards = {label_CardIMG0, label_CardIMG1, label_CardIMG2, label_CardIMG3,label_CardIMG4,
                    label_CardIMG5, label_CardIMG6, label_CardIMG7, label_CardIMG8, label_CardIMG9},
                label = {label_listCard0, label_listCard1, label_listCard2, label_listCard3, label_listCard4,
                label_listCard5, label_listCard6, label_listCard7, label_listCard8, label_listCard9};
        for (int i = 0; i < player.getAnzahlCards(); i++) {
            label[i].setText(player.getCards()[i].print());
            //label.setEnabled(false);
            label_yourCards[i].setIcon(player.getCards()[i].getStyle());
        }
        panel_yourCardIMG.setAutoscrolls(true);
    }

    private Players getAI(String name) {
        for (Players player : deck.getListPlayers()) {
            if (player.getName().equals(name)) return player;
        }
        return null;
    }

}
