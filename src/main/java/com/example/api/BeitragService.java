package com.example.api;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BeitragService {
    private final Map<Integer, BeitragResponse> database = new HashMap<>(); // Key ist nun die KundenNr
    private final AtomicInteger nextCustomerId = new AtomicInteger(1); // Atomarer Integer für Thread-Sicherheit

    private static final Map<String, Double> BASIS_FEES = Map.of(
            "Auto", 50.0,
            "Haus", 40.0,
            "Gesundheit", 60.0
    );

    private static final Map<String, Double> COVERAGE_SURCHARGE = Map.of(
            "Basis", 0.0,
            "Premium", 0.15,
            "VIP", 0.30
    );

    public BeitragResponse berechneBeitrag(BeitragRequest request) {
        double basis = BASIS_FEES.getOrDefault(request.getVersicherung(), 0.0);
        double alterZuschlag = (request.getAlter() < 25) ? 0.20 : (request.getAlter() <= 50) ? 0.10 : 0.05;
        double plzZuschlag = request.getPlz().startsWith("1") || request.getPlz().startsWith("2") ? 0.10 :
                (request.getPlz().startsWith("3") || request.getPlz().startsWith("4") || request.getPlz().startsWith("5")) ? 0.05 : 0.0;
        double deckungsZuschlag = COVERAGE_SURCHARGE.getOrDefault(request.getDeckung(), 0.0);

        double beitrag = basis * (1 + alterZuschlag + plzZuschlag + deckungsZuschlag);

        int customerId = nextCustomerId.getAndIncrement(); // Sichere Inkrementierung der Kunden-ID

        BeitragResponse response = new BeitragResponse(customerId, request.getVersicherung(), beitrag, // KundenId im Response
                Map.of("basisbeitrag", basis, "alterszuschlag", basis * alterZuschlag,
                        "plz-zuschlag", basis * plzZuschlag, "deckungszuschlag", basis * deckungsZuschlag));

        database.put(customerId, response); // Speichern mit der Kunden-ID als Key
        return response;
    }

    public BeitragResponse getBeitragById(int id) { // ID ist nun Integer
        return database.get(id);
    }
}