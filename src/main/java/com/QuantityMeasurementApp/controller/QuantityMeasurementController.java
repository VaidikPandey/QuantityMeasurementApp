package com.QuantityMeasurementApp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.QuantityMeasurementApp.model.QuantityDTO;
import com.QuantityMeasurementApp.model.QuantityInputDTO;
import com.QuantityMeasurementApp.model.QuantityMeasurementEntity;
import com.QuantityMeasurementApp.service.IQuantityMeasurementService;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // get logged in user's email from JWT
    private String getEmail(Authentication auth) {
        return auth.getName();
    }

    @PostMapping("/compare")
    public ResponseEntity<Boolean> compare(@RequestBody QuantityInputDTO input,
            Authentication auth) {
        return ResponseEntity.ok(service.compare(input.getQ1(), input.getQ2(), getEmail(auth)));
    }

    @PostMapping("/convert")
    public ResponseEntity<QuantityDTO> convert(@RequestBody QuantityInputDTO input,
            Authentication auth) {
        return ResponseEntity.ok(service.convert(input.getQ1(), input.getTargetUnit(), getEmail(auth)));
    }

    @PostMapping("/add")
    public ResponseEntity<QuantityDTO> add(@RequestBody QuantityInputDTO input,
            Authentication auth) {
        return ResponseEntity.ok(service.add(input.getQ1(), input.getQ2(), getEmail(auth)));
    }

    @PostMapping("/subtract")
    public ResponseEntity<QuantityDTO> subtract(@RequestBody QuantityInputDTO input,
            Authentication auth) {
        return ResponseEntity.ok(service.subtract(input.getQ1(), input.getQ2(), getEmail(auth)));
    }

    @PostMapping("/divide")
    public ResponseEntity<Double> divide(@RequestBody QuantityInputDTO input,
            Authentication auth) {
        return ResponseEntity.ok(service.divide(input.getQ1(), input.getQ2(), getEmail(auth)));
    }

    // user specific history
    @GetMapping("/history/me")
    public ResponseEntity<List<QuantityMeasurementEntity>> getMyHistory(Authentication auth) {
        return ResponseEntity.ok(service.getUserHistory(getEmail(auth)));
    }

    @GetMapping("/history/me/{operation}")
    public ResponseEntity<List<QuantityMeasurementEntity>> getMyHistoryByOperation(
            @PathVariable String operation, Authentication auth) {
        return ResponseEntity.ok(service.getUserHistoryByOperation(getEmail(auth), operation));
    }

    @DeleteMapping("/history/me")
    public ResponseEntity<Void> clearMyHistory(Authentication auth) {
        service.clearUserHistory(getEmail(auth));
        return ResponseEntity.ok().build();
    }

    // admin - all users history
    @GetMapping("/history/operation/{operation}")
    public ResponseEntity<List<QuantityMeasurementEntity>> getByOperation(
            @PathVariable String operation) {
        return ResponseEntity.ok(service.getHistoryByOperation(operation));
    }

    @GetMapping("/history/type/{type}")
    public ResponseEntity<List<QuantityMeasurementEntity>> getByType(
            @PathVariable String type) {
        return ResponseEntity.ok(service.getHistoryByMeasurementType(type));
    }

    @GetMapping("/history/errored")
    public ResponseEntity<List<QuantityMeasurementEntity>> getErrors() {
        return ResponseEntity.ok(service.getErrorHistory());
    }

    @GetMapping("/count/{operation}")
    public ResponseEntity<Long> getCount(@PathVariable String operation) {
        return ResponseEntity.ok(service.getCountByOperation(operation));
    }
}