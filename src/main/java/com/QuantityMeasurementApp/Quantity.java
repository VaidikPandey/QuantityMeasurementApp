package com.QuantityMeasurementApp;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

	private final double value;
	private final U unit;

	private static final double EPSILON = 1e-6;

	public Quantity(double value, U unit) {

		if (unit == null)
			throw new IllegalArgumentException("Unit cannot be null");

		if (!Double.isFinite(value))
			throw new IllegalArgumentException("Invalid value");

		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public U getUnit() {
		return unit;
	}

	private double toBaseUnit() {
		return unit.convertToBaseUnit(value);
	}

	public Quantity<U> convertTo(U targetUnit) {

		double base = unit.convertToBaseUnit(value);
		double converted = targetUnit.convertFromBaseUnit(base);

		return new Quantity<>(converted, targetUnit);
	}

	public Quantity<U> add(Quantity<U> other) {
		return add(other, this.unit);
	}

	public Quantity<U> add(Quantity<U> other, U targetUnit) {

		double a = unit.convertToBaseUnit(value);
		double b = other.unit.convertToBaseUnit(other.value);

		double sum = a + b;

		double result = targetUnit.convertFromBaseUnit(sum);

		return new Quantity<>(result, targetUnit);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		Quantity<?> other = (Quantity<?>) obj;

		if (!unit.getClass().equals(other.unit.getClass()))
			return false;

		return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Math.round(toBaseUnit() / EPSILON));  
	}

	@Override
	public String toString() {
		return value + " " + unit.getUnitName();
	}
}