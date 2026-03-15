package com.QuantityMeasurementApp.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class QuantityMeasurementEntity implements Serializable {

	private Long id;
	private String operation;
	private String measurementType;
	private String result;
	private boolean error;
	private String message;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Entity[op=" + operation + ", type=" + measurementType + ", result=" + result + "]";
	}
}