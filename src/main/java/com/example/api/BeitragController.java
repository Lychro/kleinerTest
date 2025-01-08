package com.example.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}