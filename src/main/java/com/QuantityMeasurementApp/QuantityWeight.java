package com.QuantityMeasurementApp;

import java.util.Objects;

public final class QuantityWeight {

	private final double value;
	private final WeightUnit unit;
	private static final double EPSILON = 1e-6;

	public QuantityWeight(double value, WeightUnit unit) {

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

	public WeightUnit getUnit() {
		return unit;
	}

	public QuantityWeight convertTo(WeightUnit targetUnit) {

		double base = unit.toKilogram(value);
		double converted = targetUnit.fromKilogram(base);

		return new QuantityWeight(converted, targetUnit);
	}

	public QuantityWeight add(QuantityWeight other) {
		return add(this, other, this.unit);
	}

	public static QuantityWeight add(QuantityWeight a, QuantityWeight b, WeightUnit targetUnit) {

		double aKg = a.unit.toKilogram(a.value);
		double bKg = b.unit.toKilogram(b.value);

		double sumKg = aKg + bKg;
		double result = targetUnit.fromKilogram(sumKg);

		return new QuantityWeight(result, targetUnit);
	}

	private double toBaseUnit() {
		return unit.toKilogram(value);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		QuantityWeight other = (QuantityWeight) obj;

		return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Math.round(toBaseUnit() / EPSILON));
	}

	@Override
	public String toString() {
		return value + " " + unit;
	}
}