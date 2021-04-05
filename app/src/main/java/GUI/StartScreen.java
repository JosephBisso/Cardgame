package GUI;

import javax.swing.*;

public class StartScreen  {
    private JPanel panel_Content;
    private JButton button_play;
    private JProgressBar progressBar;
    private JComboBox comboBox_existingGame;
    private JComboBox comboBox_Style;
    private JButton button_createGame;
    private JLabel label_selectGame;
    private JLabel label_selectStyle;

    public static void main(String[] args) {
        JFrame frame = new JFrame("StartScreen");
        frame.setContentPane(new StartScreen().panel_Content);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
