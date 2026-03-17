package com.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.QuantityMeasurementApp.controller.*;
import com.QuantityMeasurementApp.model.*;
import com.QuantityMeasurementApp.repository.*;
import com.QuantityMeasurementApp.service.*;

public class QuantityMeasurementAppTest {

	IQuantityMeasurementService service;
	QuantityMeasurementController controller;

	@BeforeEach
	void setup() {

		IQuantityMeasurementRepository repo = QuantityMeasurementCacheRepository.getInstance();

		service = new QuantityMeasurementServiceImpl(repo);

		controller = new QuantityMeasurementController(service);
	}

	// ENTITY TEST

	@Test
	void testQuantityDTOCreation() {

		QuantityDTO q = new QuantityDTO(1.0, "FEET", "LENGTH");

		assertEquals(1.0, q.getValue());

		assertEquals("FEET", q.getUnit());
	}

	// SERVICE TESTS

	@Test
	void testServiceCompare() {

		QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");

		QuantityDTO q2 = new QuantityDTO(12.0, "INCH", "LENGTH");

		assertTrue(service.compare(q1, q2));
	}

	@Test
	void testServiceConversion() {

		QuantityDTO q = new QuantityDTO(1.0, "FEET", "LENGTH");

		QuantityDTO result = service.convert(q, "INCH");

		assertEquals(12.0, result.getValue());
	}

	@Test
	void testServiceAddition() {

		QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");

		QuantityDTO q2 = new QuantityDTO(12.0, "INCH", "LENGTH");

		QuantityDTO result = service.add(q1, q2);

		assertEquals(2.0, result.getValue());
	}

	@Test
	void testServiceSubtraction() {

		QuantityDTO q1 = new QuantityDTO(10.0, "FEET", "LENGTH");

		QuantityDTO q2 = new QuantityDTO(12.0, "INCH", "LENGTH");

		QuantityDTO result = service.subtract(q1, q2);

		assertEquals(9.0, result.getValue());
	}

	@Test
	void testServiceDivision() {

		QuantityDTO q1 = new QuantityDTO(10.0, "FEET", "LENGTH");

		QuantityDTO q2 = new QuantityDTO(2.0, "FEET", "LENGTH");

		double result = service.divide(q1, q2);

		assertEquals(5.0, result);
	}

	// CONTROLLER TEST

	@Test
	void testControllerObject() {

		assertNotNull(controller);
	}

	// REPOSITORY TEST

	@Test
	void testRepositorySave() {

		IQuantityMeasurementRepository repo = QuantityMeasurementCacheRepository.getInstance();

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity("TEST", "GENERAL","SUCCESS");

		repo.save(entity);

		assertFalse(repo.findAll().isEmpty());
	}

	// INTEGRATION TEST

	@Test
	void testEndToEnd() {

		QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");

		QuantityDTO q2 = new QuantityDTO(12.0, "INCH", "LENGTH");

		assertTrue(service.compare(q1, q2));

		QuantityDTO result = service.add(q1, q2);

		assertEquals(2.0, result.getValue());
	}
}