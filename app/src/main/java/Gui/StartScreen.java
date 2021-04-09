package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends Frame {
    private JPanel mainPanel;
    private JProgressBar progressBar_StartScreen;
    private JComboBox comboBox_Styles;
    private JPanel preview_JPanel;
    private JComboBox comboBox1;
    private JButton downloadGameButton;
    private JButton createGameButton;
    private JButton playButton;

    public StartScreen() {
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayScreen playScreen = new PlayScreen();
                playScreen.exec();

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StartScreen");
        frame.setContentPane(new StartScreen().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
