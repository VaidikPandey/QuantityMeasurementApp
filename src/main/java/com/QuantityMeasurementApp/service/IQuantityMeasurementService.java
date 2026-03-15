package com.QuantityMeasurementApp.service;

import com.QuantityMeasurementApp.model.QuantityDTO;

public interface IQuantityMeasurementService {

	boolean compare(QuantityDTO q1, QuantityDTO q2);

	QuantityDTO convert(QuantityDTO q, String targetUnit);

	QuantityDTO add(QuantityDTO q1, QuantityDTO q2);

	QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2);

	double divide(QuantityDTO q1, QuantityDTO q2);
}