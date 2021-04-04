package Cardgame;

import java.util.ArrayList;

public class Karte {

    private String wert;
    private String farbe;
    private String motiv;
    private ArrayList<String> rules;
    private int anzahl;
    private String style;

    public Karte(String wert, String farbe, String motiv) throws SpielException {

        if (farbe.equals("rot") && (motiv.equals("herz") || motiv.equals("losange"))) {
            this.farbe = farbe;
            this.motiv = motiv;
        } else if (farbe.equals("schwarz") && (motiv.equals("pique") || motiv.equals("chou"))) {
            this.farbe = farbe;
            this.motiv = motiv;
        } else if (farbe.equals("alle2") && motiv.equals("all4")) {
            anzahl = 4;
            this.farbe = farbe;
            this.motiv = motiv;
        } else if (farbe.equals("alle2") && motiv.equals("joker")) {
            anzahl = 2;
            this.farbe = farbe;
            this.motiv = motiv;
        } else if (!(farbe.equals("rot") || farbe.equals("schwarz"))){
            throw new SpielException("Karte: ungültige Farbe");
        } else {
            throw new SpielException("Karte: Farbe passt nicht zum Motiv");
        }
        this.wert = wert;
        rules = new ArrayList<String>();
    }

    public String getWert() {
        return wert;
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
        return rules.toArray(new String[rules.size()]);
    }

    public String printRule() {
        String regeln = "";
        String[] allRules = getRules();
        if (allRules.length == 0) {
            return regeln;
        }
        for (String regel : getRules()) {
            regeln += regel + ";";
        }
        return regeln;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

}
