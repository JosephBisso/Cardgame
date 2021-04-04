package GUI;

import javax.swing.*;

public class StartScreen extends JFrame {
    private JPanel panel_Content;
    private JButton button_play;
    private JProgressBar progressBar;
    private JComboBox comboBox_existingGame;
    private JComboBox comboBox_Style;
    private JButton button_createGame;
    private JLabel label_selectGame;
    private JLabel label_selectStyle;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public StartScreen() {
        add(panel_Content);
    }
}
