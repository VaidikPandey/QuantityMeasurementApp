package com.QuantityMeasurementApp.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "quantity_measurement_entity")
public class QuantityMeasurementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String operation;

	@Column(name = "measurement_type", nullable = false)
	private String measurementType;

	@Column(nullable = true)
	private String result;

	@Column(name = "is_error")
	private boolean error;

	@Column
	private String userEmail;

	private String message;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public QuantityMeasurementEntity(String operation, String measurementType, String result) {
		this.operation = operation;
		this.measurementType = measurementType;
		this.result = result;
		this.error = false;
		this.createdAt = LocalDateTime.now();
	}

	public QuantityMeasurementEntity(String operation, String measurementType, String message, boolean error) {
		this.operation = operation;
		this.measurementType = measurementType;
		this.message = message;
		this.error = error;
		this.createdAt = LocalDateTime.now();
	}

	public QuantityMeasurementEntity(String operation, String measurementType, String result, String userEmail) {
		this.operation = operation;
		this.measurementType = measurementType;
		this.result = result;
		this.error = false;
		this.createdAt = LocalDateTime.now();
		this.userEmail = userEmail;
	}

	public QuantityMeasurementEntity(String operation, String measurementType, String message, boolean error,
			String userEmail) {
		this.operation = operation;
		this.measurementType = measurementType;
		this.message = message;
		this.error = error;
		this.createdAt = LocalDateTime.now();
		this.userEmail = userEmail;
	}

	// Default constructor required by JPA
	public QuantityMeasurementEntity() {
	}

	public Long getId() {
		return id;
	}

	public String getOperation() {
		return operation;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public String getResult() {
		return result;
	}

	public boolean hasError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getUserEmail() {
		return userEmail;
	}

	@Override
	public String toString() {
		return "Entity[op=" + operation + ", type=" + measurementType + ", result=" + result + "]";
	}
}