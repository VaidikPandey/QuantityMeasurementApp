package com.QuantityMeasurementApp;

public enum LengthUnit implements IMeasurable {

	FEET(1.0), INCH(1.0 / 12.0), YARDS(3.0), CENTIMETER(0.393701 / 12.0);

	private final double factorToFeet;

	LengthUnit(double factorToFeet) {
		this.factorToFeet = factorToFeet;
	}

	public double getConversionFactor() {
		return factorToFeet;
	}

	public double convertToBaseUnit(double value) {
		return value * factorToFeet;
	}

	public double convertFromBaseUnit(double baseValue) {
		return baseValue / factorToFeet;
	}

	public String getUnitName() {
		return name();
	}
}