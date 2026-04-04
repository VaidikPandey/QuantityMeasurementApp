package com.QuantityMeasurementApp.service;

import java.util.List;
import com.QuantityMeasurementApp.model.QuantityDTO;
import com.QuantityMeasurementApp.model.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {

	// core operations
	boolean compare(QuantityDTO q1, QuantityDTO q2, String userEmail);

	QuantityDTO convert(QuantityDTO q, String targetUnit, String userEmail);

	QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String userEmail);

	QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String userEmail);

	double divide(QuantityDTO q1, QuantityDTO q2, String userEmail);

	// history operations - all users (admin)
	List<QuantityMeasurementEntity> getHistoryByOperation(String operation);

	List<QuantityMeasurementEntity> getHistoryByMeasurementType(String measurementType);

	List<QuantityMeasurementEntity> getErrorHistory();

	long getCountByOperation(String operation);

	// history operations - specific user
	List<QuantityMeasurementEntity> getUserHistory(String userEmail);

	List<QuantityMeasurementEntity> getUserHistoryByOperation(String userEmail, String operation);

	void clearUserHistory(String userEmail);
}