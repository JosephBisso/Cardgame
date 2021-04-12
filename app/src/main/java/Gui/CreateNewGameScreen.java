package Gui;

import Cardgame.Spiel;
import Cardgame.SpielException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

public class CreateNewGameScreen extends JFrame {
    private Spiel spiel;

    private JButton createButton;
    private JButton cancelButton;
    private JTextField textField_gameName;
    private JTextField textField_cardsName;
    private JButton nextCardButton;
    private JCheckBox thisCardsHasRulesCheckBox;
    private JComboBox comboBox_cardsRuleOne;
    private JComboBox comboBox_cardsRuleTwo;
    private JPanel mainPanel_NewGame;
    private JComboBox comboBox_anzahlCard;
    private JLabel Label_numberOfCards;

    public CreateNewGameScreen(StartScreen startScreen) {
        spiel = new Spiel("");

        JFrame frame = new JFrame("CreateNewGameScreen");
        frame.setContentPane(mainPanel_NewGame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        for (Spiel.Rules rule : Spiel.Rules.values()) {
            comboBox_cardsRuleOne.addItem(rule.toString());
            comboBox_cardsRuleTwo.addItem(rule.toString());
        }

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spiel.setName(textField_gameName.getText());
                if (saveGame_worked()) {
                    startScreen.addSelectableGame(spiel);
                    startScreen.setShowedGame(spiel);
                    JOptionPane.showMessageDialog(null, "Game Added",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }
            }
        });
        thisCardsHasRulesCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (thisCardsHasRulesCheckBox.isSelected()) {
                    comboBox_cardsRuleOne.setEnabled(true);
                    comboBox_cardsRuleTwo.setEnabled(true);
                } else {
                    comboBox_cardsRuleOne.setEnabled(false);
                    comboBox_cardsRuleTwo.setEnabled(false);
                }
            }
        });
        nextCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCardToKarten(textField_cardsName.getText(),
                        Integer.parseInt((String) comboBox_anzahlCard.getSelectedItem()));
                if (thisCardsHasRulesCheckBox.isSelected() && comboBox_cardsRuleOne.getSelectedIndex() != 0) {
                    setRule(textField_cardsName.getText(), (String) comboBox_cardsRuleOne.getSelectedItem());
                    if (comboBox_cardsRuleTwo.getSelectedIndex() != 0) {
                        setRule(textField_cardsName.getText(), (String) comboBox_cardsRuleTwo.getSelectedItem());
                    }
                }
                JOptionPane.showMessageDialog(null, "Card Added",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                Label_numberOfCards.setText(Integer.toString(spiel.getNumberOfCards()));
                textField_cardsName.setText("");
                thisCardsHasRulesCheckBox.setSelected(false);

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    private void addCardToKarten(String wert, int anzahl) {
        try {
            spiel.addCard(wert, anzahl);
        } catch (SpielException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    private void setRule(String wert, String rule) {
        try {
            spiel.setRules(wert, rule);
        } catch (SpielException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    private boolean saveGame_worked() {
        try {
            StringBuilder pfad = new StringBuilder("app/src/main/resources/Games");
            pfad.append(spiel.getName());
            pfad.append(".spiel");
            File file = new File(pfad.toString());
            spiel.saveGame(file.getAbsolutePath());
            return true;
        } catch (SpielException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

}
