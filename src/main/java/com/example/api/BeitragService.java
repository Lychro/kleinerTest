package com.example.api;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BeitragService {
    private final Map<Integer, BeitragResponse> database = new HashMap<>(); // Key ist die KundenNr
    private int nextId = 1;
    private static final Map<String, Double> grundbetraege = Map.of(
            "Auto", 50.0,
            "Haus", 40.0,
            "Gesundheit", 60.0
    );

    private static final Map<String, Double> deckungs_art = Map.of(
            "Basis", 0.0,
            "Premium", 0.15,
            "VIP", 0.30
    );

    public BeitragResponse berechneBeitrag(BeitragRequest request) {
        double basis = grundbetraege.getOrDefault(request.getVersicherung(), 0.0);
        double alterZuschlag = (request.getAlter() < 25) ? 0.20 : (request.getAlter() <= 50) ? 0.10 : 0.05;
        double plzZuschlag;

        String plzStart = request.getPlz().substring(0, 1);

        switch (plzStart) {
            case "1":
            case "2":
                plzZuschlag = 0.10;
                break;
            case "3":
            case "4":
            case "5":
                plzZuschlag = 0.05;
                break;
            default:
                plzZuschlag = 0.0;
                break;
        }
        double deckungsZuschlag = deckungs_art.getOrDefault(request.getDeckung(), 0.0);

        double beitrag = basis * (1 + alterZuschlag + plzZuschlag + deckungsZuschlag);

        int customerId = nextId++;
        BeitragResponse response = new BeitragResponse(customerId, request.getVersicherung(), beitrag, // KundenId im Response
                Map.of("basisbeitrag", basis, "alterszuschlag", basis * alterZuschlag,"plz-zuschlag", basis * plzZuschlag, "deckungszuschlag", basis * deckungsZuschlag));

        database.put(customerId, response);
        return response;
    }
}