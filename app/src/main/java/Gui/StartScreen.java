package Gui;

import Cardgame.Deck;
import Cardgame.Spiel;
import Cardgame.SpielException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class StartScreen extends Frame {
    private JPanel mainPanel;
    private JComboBox comboBox_Styles;
    private JPanel preview_JPanel;
    private JComboBox comboBox_selectGame;
    private JButton loadGameButton;
    private JButton createGameButton;
    private JButton playButton;
    private JLabel previewLabel;

    private File loadedGame;

    public static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static final Dimension SCREENSIZE = toolkit.getScreenSize();
    public static Image windowIcon;
    public static final JFrame frame = new JFrame("Card Game");

    public StartScreen() {
        String pfad = "app/src/main/resources/Games";
        File folder = new File(pfad);
        File[] listSpiele = folder.listFiles();
        for (File spiel : listSpiele) {
            comboBox_selectGame.addItem(spiel.getName().replace(".spiel", ""));
        }
        comboBox_selectGame.setSelectedIndex(1);
        ImageIcon defaultIcon = (ImageIcon) previewLabel.getIcon();
        windowIcon = defaultIcon.getImage();

        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(windowIcon);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        frame.pack();
        frame.setVisible(true);
        GuiUtil.showOnMiddleScreen(frame);

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
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".spiel Datei", "spiel");
                chooser.setFileFilter(filter);
                File file = new File("app/src/test/resources/ ");
                chooser.setSelectedFile(new File(file.getAbsolutePath()));
                int returnval = chooser.showOpenDialog(null);
                if (returnval == JFileChooser.APPROVE_OPTION) {
                    loadedGame = chooser.getSelectedFile();
                    String gameName = loadedGame.getName().replace(".spiel", "");
                    comboBox_selectGame.addItem(gameName);
                    comboBox_selectGame.setSelectedItem(gameName);
                }
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
