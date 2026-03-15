package com.QuantityMeasurementApp.model;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

	private String operation;
	private String result;
	private boolean error;
	private String message;

	public QuantityMeasurementEntity(String operation, String result) {

		this.operation = operation;
		this.result = result;
		this.error = false;
	}

	public QuantityMeasurementEntity(String operation, String message, boolean error) {

		this.operation = operation;
		this.message = message;
		this.error = error;
	}

	public boolean hasError() {
		return error;
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}
}