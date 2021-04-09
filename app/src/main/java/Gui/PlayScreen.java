package Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayScreen extends JFrame {
    private JPanel mainPanel_play;
    private JButton startButton;
    private JButton cancelButton;
    private JCheckBox enableManyCardAtOnceCheckBox;
    private JCheckBox enablePickStopableBox;
    private JSlider slider1;
    private JSpinner spinner_numberOponnent;
    private JPanel playParameter_JPanel;

    public PlayScreen() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePlayScreen gameplay = new GamePlayScreen();
                gameplay.exec();
                dispose();
            }
        });
    }

    public void exec() {
        JFrame frame = new JFrame("PlayScreen");
        frame.setContentPane(new PlayScreen().mainPanel_play);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
