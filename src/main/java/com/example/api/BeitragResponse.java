package com.example.api;

import java.util.Map;

public class BeitragResponse {
    private final int id; // ID ist nun Integer
    private final String versicherung;
    private final double beitrag;
    private final Map<String, Double> zuschlaege;

    public BeitragResponse(int id, String versicherung, double beitrag, Map<String, Double> zuschlaege) {
        this.id = id;
        this.versicherung = versicherung;
        this.beitrag = beitrag;
        this.zuschlaege = zuschlaege;
    }

    // Getter-Methoden (wichtig für die Serialisierung z.B. nach JSON)
    public int getId() { return id; }
    public String getVersicherung() { return versicherung; }
    public double getBeitrag() { return beitrag; }
    public Map<String, Double> getZuschlaege() { return zuschlaege; }
}