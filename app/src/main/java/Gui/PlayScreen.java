package Gui;

import Cardgame.AI;
import Cardgame.Deck;
import Cardgame.Spieler;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayScreen extends JFrame {
    private JPanel mainPanel_play;
    private JButton startButton;
    private JButton cancelButton;
    private JCheckBox enableManyCardAtOnceCheckBox;
    private JCheckBox enablePickStopableBox;
    private JSlider slider1;
    private JPanel playParameter_JPanel;
    private JComboBox comboBox_anzahlOponnent;

    public PlayScreen(JFrame frame_StartScreen, Deck deck) {
        JFrame frame = new JFrame("PlayScreen");
        frame.setContentPane(mainPanel_play);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        comboBox_anzahlOponnent.setSelectedItem("3");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frame_StartScreen.dispose();

                deck.addPlayer(new Spieler(deck));
                int anzahlPlayers = Integer.parseInt(comboBox_anzahlOponnent.getSelectedItem().toString());
                for (int i = 0; i < anzahlPlayers; i++) {
                    deck.addPlayer(new AI(deck));
                }
                JOptionPane.showMessageDialog(null, "Game Starts. You play first",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                GamePlayScreen gameplay = new GamePlayScreen(deck);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

}
