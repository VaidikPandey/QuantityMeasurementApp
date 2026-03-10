package com.QuantityMeasurementApp;

public enum WeightUnit implements IMeasurable {

	KILOGRAM(1.0), GRAM(0.001), POUND(0.453592);

	private final double factorToKg;

	WeightUnit(double factorToKg) {
		this.factorToKg = factorToKg;
	}

	public double getConversionFactor() {
		return factorToKg;
	}

	public double convertToBaseUnit(double value) {
		return value * factorToKg;
	}

	public double convertFromBaseUnit(double baseValue) {
		return baseValue / factorToKg;
	}

	public String getUnitName() {
		return name();
	}
}