package com.QuantityMeasurementApp.service;

import java.util.List;
import com.QuantityMeasurementApp.model.QuantityMeasurementEntity;
import com.QuantityMeasurementApp.model.QuantityDTO;

public interface IQuantityMeasurementService {

	// Core operations
	boolean compare(QuantityDTO q1, QuantityDTO q2);

	QuantityDTO convert(QuantityDTO q, String targetUnit);

	QuantityDTO add(QuantityDTO q1, QuantityDTO q2);

	QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2);

	double divide(QuantityDTO q1, QuantityDTO q2);

	// History operations
	List<QuantityMeasurementEntity> getHistoryByOperation(String operation);

	List<QuantityMeasurementEntity> getHistoryByMeasurementType(String measurementType);

	List<QuantityMeasurementEntity> getErrorHistory();

	long getCountByOperation(String operation);
}