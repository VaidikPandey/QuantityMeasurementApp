package com.QuantityMeasurementApp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@PostMapping("/compare")
	public ResponseEntity<Boolean> compare(@RequestBody QuantityInputDTO input) {
		boolean result = service.compare(input.getQ1(), input.getQ2());
		return ResponseEntity.ok(result);
	}

	@PostMapping("/convert")
	public ResponseEntity<QuantityDTO> convert(@RequestBody QuantityInputDTO input) {
		QuantityDTO result = service.convert(input.getQ1(), input.getTargetUnit());
		return ResponseEntity.ok(result);
	}

	@PostMapping("/add")
	public ResponseEntity<QuantityDTO> add(@RequestBody QuantityInputDTO input) {
		QuantityDTO result = service.add(input.getQ1(), input.getQ2());
		return ResponseEntity.ok(result);
	}

	@PostMapping("/subtract")
	public ResponseEntity<QuantityDTO> subtract(@RequestBody QuantityInputDTO input) {
		QuantityDTO result = service.subtract(input.getQ1(), input.getQ2());
		return ResponseEntity.ok(result);
	}

	@PostMapping("/divide")
	public ResponseEntity<Double> divide(@RequestBody QuantityInputDTO input) {
		double result = service.divide(input.getQ1(), input.getQ2());
		return ResponseEntity.ok(result);
	}

	@GetMapping("/history/operation/{operation}")
	public ResponseEntity<List<QuantityMeasurementEntity>> getByOperation(@PathVariable String operation) {
		return ResponseEntity.ok(service.getHistoryByOperation(operation));
	}

	@GetMapping("/history/type/{type}")
	public ResponseEntity<List<QuantityMeasurementEntity>> getByType(@PathVariable String type) {
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