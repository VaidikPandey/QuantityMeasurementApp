package com.QuantityMeasurementApp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.QuantityMeasurementApp.model.QuantityMeasurementEntity;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {

	// find by operation type e.g. "ADD", "COMPARE"
	List<QuantityMeasurementEntity> findByOperation(String operation);

	// find by measurement type e.g. "LENGTH", "WEIGHT"
	List<QuantityMeasurementEntity> findByMeasurementType(String measurementType);

	// find all errors
	List<QuantityMeasurementEntity> findByErrorTrue();

	// count by operation
	long countByOperation(String operation);

	// find all history for a specific user
	List<QuantityMeasurementEntity> findByUserEmailOrderByIdDesc(String userEmail);

	// find user history by operation
	List<QuantityMeasurementEntity> findByUserEmailAndOperation(String userEmail, String operation);

	// delete all history for a specific user
	@Transactional
	void deleteByUserEmail(String userEmail);
}