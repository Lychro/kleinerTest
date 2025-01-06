package com.example.api;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/beitrag")

public class BeitragController {

    private final BeitragService beitragService;

    @GetMapping("/health-check")
    public String getHealthCheck(){
        return "Situation Normal All Fired Up!";
    }

    public BeitragController(BeitragService beitragService) {
        this.beitragService = beitragService;
    }



    // POST: Beitrag berechnen
    @PostMapping("/berechnen")
    public ResponseEntity<?> berechneBeitrag(@RequestBody BeitragRequest request) {
        // Validierung der Eingaben
        if (request.getVersicherung() == null || request.getDeckung() == null ||
                request.getPlz() == null || request.getAlter() <= 0) {
            return ResponseEntity.badRequest().body("Fehlende oder ungültige Parameter.");
        }

        // Berechnung der Versicherungsprämie
        BeitragResponse response = beitragService.berechneBeitrag(request);
        return ResponseEntity.ok(response);
    }

    // GET: Beitrag basierend auf der ID abrufen
    @GetMapping("/{id}")
    public ResponseEntity<?> getBeitrag(@PathVariable int id) {
        Optional<BeitragResponse> beitrag = Optional.ofNullable(beitragService.getBeitragById(id));

        // Fehlerbehandlung, falls keine Berechnung zur ID gefunden wird
        if (beitrag.isEmpty()) {
            return ResponseEntity.status(404).body("Beitragsberechnung mit der ID " + id + " nicht gefunden.");
        }
        return ResponseEntity.ok(beitrag.get());
    }

    @PostMapping("/test-berechnung")
    public BeitragResponse testBerechnung() {
        BeitragRequest request = new BeitragRequest();
        request.setVersicherung("Haus");
        request.setAlter(40);
        request.setPlz("23456");
        request.setDeckung("Basis");

        return beitragService.berechneBeitrag(request);
    }


}