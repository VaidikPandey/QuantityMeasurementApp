package com.QuantityMeasurementApp;

public enum WeightUnit {

	KILOGRAM(1.0), GRAM(0.001), POUND(0.453592);

	private final double factorToKg;

	WeightUnit(double factorToKg) {
		this.factorToKg = factorToKg;
	}

	public double toKilogram(double value) {
		return value * factorToKg;
	}

	public double fromKilogram(double kgValue) {
		return kgValue / factorToKg;
	}

	public double getFactorToKg() {
		return factorToKg;
	}
}