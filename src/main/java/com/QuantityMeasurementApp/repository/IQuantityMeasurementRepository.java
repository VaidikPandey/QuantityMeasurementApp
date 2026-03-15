package com.QuantityMeasurementApp.repository;

import java.util.List;
import com.QuantityMeasurementApp.model.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {

	// Save a measurement entity
	void save(QuantityMeasurementEntity entity);

	// Get all measurements
	List<QuantityMeasurementEntity> findAll();

	// Get measurements by operation type (e.g. "ADD", "COMPARE")
	List<QuantityMeasurementEntity> findByOperation(String operation);

	// Get measurements by measurement type (e.g. "LENGTH", "WEIGHT")
	List<QuantityMeasurementEntity> findByMeasurementType(String measurementType);

	// Get total count of measurements
	int getTotalCount();

	// Delete all measurements
	void deleteAll();

	// Default method for pool statistics
	default String getPoolStatistics() {
		return "Pool statistics not available for this repository";
	}

	// Default method for releasing resources
	default void releaseResources() {
		// default: nothing to release
	}
}