package Gui;

import Cardgame.Spiel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CommandDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox_toCommandedMotiv;
    private JTextField textField_error;

    private static boolean motivCommanded = false;
    private static String commandedMotiv;

    public CommandDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        for (Spiel.Motiv motiv : Spiel.Motiv.values()) {
            if (!(motiv == Spiel.Motiv.joker)) {
                comboBox_toCommandedMotiv.addItem(motiv.toEnglish());
            }
        }
    }

    private void onOK() {
        commandedMotiv = (String) comboBox_toCommandedMotiv.getSelectedItem();
        if (commandedMotiv.equals("None")) {
            textField_error.setText("Please choose a Motiv or cancel");
            textField_error.setDisabledTextColor(Color.RED);
        } else {
            motivCommanded = true;
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void exec() {
        CommandDialog dialog = new CommandDialog();
        dialog.pack();
        dialog.setVisible(true);
    }

    public String getCommandedMotiv() {
        return commandedMotiv;
    }

    public boolean isMotivCommanded() {
        return motivCommanded;
    }
}
