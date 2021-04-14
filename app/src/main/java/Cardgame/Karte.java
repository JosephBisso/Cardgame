package Cardgame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Karte {

    private final String WERT;
    private final String farbe;
    private final String motiv;
    private ArrayList<String> rules;
    private int anzahl;
    private ImageIcon style;

    public Karte(String WERT, String farbe, String motiv) throws SpielException {
        if (WERT.equals("COMMAND")) {
            this.farbe = farbe;
            this.motiv = motiv;
            this.WERT = WERT;
            rules = new ArrayList<>();
            return;
        }
        if (farbe.equals("rot") && (motiv.equals("herz") || motiv.equals("losange"))) {
            this.farbe = farbe;
            this.motiv = motiv;
        } else if (farbe.equals("schwarz") && (motiv.equals("pique") || motiv.equals("chou"))) {
            this.farbe = farbe;
            this.motiv = motiv;
        } else if (motiv.equals("joker")) {
            this.farbe = farbe;
            this.motiv = motiv;
        } else if (!(farbe.equals("rot") || farbe.equals("schwarz"))){
            throw new SpielException("Karte: ungültige Farbe");
        } else {
            throw new SpielException("Karte: Farbe passt nicht zum Motiv");
        }
        this.WERT = WERT;
        rules = new ArrayList<>();
    }

    public String getWERT() {
        return WERT;
    }

    public String getFarbe() {
        return farbe;
    }

    public String getMotiv() {
        return motiv;
    }

    public void addRules(String rule) {
        rules.add(rule);
    }

    public String[] getRules() {
        return rules.toArray(new String[0]);
    }

    public String printRule() {
        StringBuilder regeln = new StringBuilder("Rule for Card " + WERT + ";");
        String[] allRules = getRules();
        if (allRules.length == 0) {
            return "";
        }
        for (String regel : getRules()) {
            regeln.append(regel);
            regeln.append(";");
        }
        return regeln.toString();
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public void setStyle(ImageIcon style) {
        this.style = style;
    }

    public ImageIcon getStyle() {
        return style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Karte)) return false;
        Karte karte = (Karte) o;
        return Objects.equals(WERT, karte.WERT) && Objects.equals(farbe, karte.farbe) && Objects.equals(motiv, karte.motiv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(WERT, farbe, motiv);
    }

    public boolean hasRule() {
        return rules.size() != 0;
    }

    public int compareTo(Object o)  {
        if (this == o) return 0;
        try {
            Karte card = (Karte) o;
            if (Character.isDigit(this.WERT.charAt(0)) && Character.isLetter(card.getWERT().charAt(0))){
                return -1;
            } else if (Character.isLetter(this.WERT.charAt(0)) && Character.isDigit(card.getWERT().charAt(0))){
                return 1;
            } else if (Character.isLetterOrDigit(this.WERT.charAt(0)) && Character.isLetterOrDigit(card.getWERT().charAt(0))) {
                return Character.compare(this.WERT.charAt(0), card.getWERT().charAt(0));
            }
        } catch (NullPointerException e) {
            return 1;
        } catch (ClassCastException e) {
            return 0;
        }
        return 0;
    }

    public boolean matches(Karte card) {
        if (WERT.equals(card.getWERT())) return true;
        if (motiv.equals(card.getMotiv())) return true;
        if (motiv.equals(Spiel.Motiv.joker.toString()) ||
                card.getMotiv().equals(Spiel.Motiv.joker.toString())) {
            if (farbe.equals(card.getFarbe()) || motiv.equals(card.getMotiv())) return true;
        }
        ArrayList<String> rules_toBePlayed = new ArrayList<>();
        Collections.addAll(rules_toBePlayed, card.getRules());
        if (rules_toBePlayed.contains(Spiel.Rules.transparent.toString()) ||
            rules_toBePlayed.contains((Spiel.Rules.passePartout.toString()))) return true;
        ArrayList<String> rules = new ArrayList<>();
        Collections.addAll(rules, getRules());
        if (rules.contains((Spiel.Rules.transparent.toString())) ||
                rules.contains((Spiel.Rules.passePartout.toString()))) return true;
        return (rules.contains(Spiel.Rules.add2.toString()) || rules.contains(Spiel.Rules.add4.toString()))
                && rules_toBePlayed.contains(Spiel.Rules.stopAdd);

    }

    public boolean matchesForPlay(Karte card) {
        return this.WERT.equals(card.getWERT());
    }

    public String print() {
        try {
            return "CARD: " +  WERT + ";    MOTIV: " +
                    Spiel.getEquivalentMotiv(motiv).toEnglish() + ";    COLOR: " +
                    Spiel.getEquivalentFarbe(farbe).toEnglish();
        } catch (SpielException e) {
            e.printStackTrace();
            return "";
        }

    }
}
