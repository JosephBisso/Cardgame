package Gui;

import Cardgame.Deck;
import Cardgame.Spiel;
import Cardgame.SpielException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class StartScreen extends Frame {
    private JPanel mainPanel;
    private JProgressBar progressBar_StartScreen;
    private JComboBox comboBox_Styles;
    private JPanel preview_JPanel;
    private JComboBox comboBox_selectGame;
    private JButton downloadGameButton;
    private JButton createGameButton;
    private JButton playButton;
    private JLabel previewLabel;

    public StartScreen() {
        String pfad = "app/src/main/resources/Games";
        File folder = new File(pfad);
        File[] listSpiele = folder.listFiles();
        for (File spiel : listSpiele) {
            comboBox_selectGame.addItem(spiel.getName().replace(".spiel", ""));
        }
        comboBox_selectGame.setSelectedIndex(1);

        JFrame frame = new JFrame("StartScreen");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Deck deck = createGame_and_getDeck();
                if (deck == null) {
                    JOptionPane.showMessageDialog(null, "Das Spiel kann nicht gestartet werden",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    PlayScreen playScreen = new PlayScreen(frame, deck);
                }
            }
        });
        comboBox_Styles.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Icon defaultIcon = previewLabel.getIcon();
                    switch (Integer.parseInt((String) e.getItem())) {
                        case 1 -> {
                            previewLabel.setIcon(defaultIcon);

                        }
                        case 2 -> {
                            //previewLabel.setIcon(new ImageIcon(
                            //        "src/main/resources/Cards Set One/diamonds_king.png"));
                        }
                    }

                }

            }
        });
        createGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateNewGameScreen newGameScreen = new CreateNewGameScreen(getThis());
            }
        });
    }

    public void addSelectableGame(Spiel spiel) {
        comboBox_selectGame.addItem(spiel.getName());
    }

    public void setShowedGame(Object Game) {
        comboBox_selectGame.setSelectedItem(((Spiel) Game).getName());
    }

    private StartScreen getThis() {
        return this;
    }

    private Deck createGame_and_getDeck() {
        try {
            Spiel spiel = new Spiel("");

            StringBuilder pfad = new StringBuilder("app/src/main/resources/Games/");
            pfad.append(comboBox_selectGame.getSelectedItem());
            pfad.append(".spiel");

            spiel = spiel.loadGame(new File(pfad.toString()).getAbsolutePath());
            Deck deck = spiel.createDeck();
            deck.setStyle(comboBox_Styles.getSelectedIndex() + 1);

            return deck;

        } catch (SpielException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }


}
