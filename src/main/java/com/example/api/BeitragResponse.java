package com.example.api;

import java.util.Map;

public class BeitragResponse {
    private String id;
    private String versicherung;
    private double beitrag;
    private Map<String, Double> details;

    public BeitragResponse(String id, String versicherung, double beitrag, Map<String, Double> details) {
        this.id = id;
        this.versicherung = versicherung;
        this.beitrag = beitrag;
        this.details = details;
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getVersicherung() {
        return versicherung;
    }

    public double getBeitrag() {
        return beitrag;
    }

    public Map<String, Double> getDetails() {
        return details;
    }
}
