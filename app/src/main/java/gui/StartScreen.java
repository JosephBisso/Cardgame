package GUI;

import Cardgame.Spiel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen {
    private JPanel Panel_Startscreen;
    private JButton playButton;
    private JButton loadButton;
    private JProgressBar progressBar_StartScreen;
    private JComboBox comboBox_chooseStyle;
    private JComboBox comboBox_chooseGame;
    private JButton createNewGameButton;
    private JRadioButton radioButton_2CardsPlay;
    private JRadioButton radioButton_PickStoppable;
    private JSpinner spinner_numberOpponent;
    private JSlider slider_Difficulty;
    private JPanel pannel_PreviewStyle;

    public StartScreen() {
        createNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Spiel spiel = new Spiel("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StartScreen");
        frame.setContentPane(new StartScreen().Panel_Startscreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
