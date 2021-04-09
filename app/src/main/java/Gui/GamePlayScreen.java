package Gui;

import javax.swing.*;

public class GamePlayScreen extends JFrame{
    private JPanel mainPanel_Gameplay;
    private JTabbedPane tabbedPane_Cards;
    private JList list_YourCards;
    private JLabel numberCardPlayer1;
    private JLabel numberCardPlayer2;
    private JLabel numberCardPlayer3;
    private JPanel Table_cards;
    private JPanel panel_TabbedCard_yourCards;
    private JButton exitButton;
    private JButton saveGameButton;
    private JButton tryAgainButton;

    public void exec() {
        JFrame frame = new JFrame("GamePlayScreen");
        frame.setContentPane(new GamePlayScreen().mainPanel_Gameplay);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
