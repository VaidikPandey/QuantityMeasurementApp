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
			throw new IllegalArgumentException("Value must be finite");

		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public U getUnit() {
		return unit;
	}

	private double toBase() {
		return unit.convertToBaseUnit(value);
	}

	public Quantity<U> convertTo(U targetUnit) {

		if (targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");

		double baseValue = unit.convertToBaseUnit(value);

		double converted = targetUnit.convertFromBaseUnit(baseValue);

		return new Quantity<>(converted, targetUnit);
	}

	public Quantity<U> add(Quantity<U> other) {
		return add(other, this.unit);
	}

	public Quantity<U> add(Quantity<U> other, U targetUnit) {

		if (other == null)
			throw new IllegalArgumentException("Quantity cannot be null");

		double base1 = this.toBase();
		double base2 = other.toBase();

		double resultBase = base1 + base2;

		double result = targetUnit.convertFromBaseUnit(resultBase);

		return new Quantity<>(result, targetUnit);
	}

	public Quantity<U> subtract(Quantity<U> other) {
		return subtract(other, this.unit);
	}

	public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

		if (other == null)
			throw new IllegalArgumentException("Quantity cannot be null");

		double base1 = this.toBase();
		double base2 = other.toBase();

		double resultBase = base1 - base2;

		double result = targetUnit.convertFromBaseUnit(resultBase);

		return new Quantity<>(result, targetUnit);
	}

	public double divide(Quantity<U> other) {

		if (other == null)
			throw new IllegalArgumentException("Quantity cannot be null");

		double base1 = this.toBase();
		double base2 = other.toBase();

		if (base2 == 0)
			throw new ArithmeticException("Division by zero");

		return base1 / base2;
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

		return Math.abs(this.toBase() - other.toBase()) < EPSILON;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Math.round(toBase() / EPSILON));
	}

	@Override
	public String toString() {
		return value + " " + unit.getUnitName();
	}
}