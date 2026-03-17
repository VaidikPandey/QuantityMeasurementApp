package com.QuantityMeasurementApp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.QuantityMeasurementApp.model.QuantityMeasurementEntity;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {

	// Find by operation type e.g. "ADD", "COMPARE"
	List<QuantityMeasurementEntity> findByOperation(String operation);

	// Find by measurement type e.g. "LENGTH", "WEIGHT"
	List<QuantityMeasurementEntity> findByMeasurementType(String measurementType);

	// Find all errors
	List<QuantityMeasurementEntity> findByErrorTrue();

	// Count by operation
	long countByOperation(String operation);
}