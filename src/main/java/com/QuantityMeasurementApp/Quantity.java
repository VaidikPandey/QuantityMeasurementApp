package com.QuantityMeasurementApp;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

	private final double value;
	private final U unit;

	private enum ArithmeticOperation {

		ADD((a, b) -> a + b),

		SUBTRACT((a, b) -> a - b),

		DIVIDE((a, b) -> {
			if (b == 0)
				throw new ArithmeticException("Division by zero");
			return a / b;
		});

		private final DoubleBinaryOperator operation;

		ArithmeticOperation(DoubleBinaryOperator operation) {
			this.operation = operation;
		}

		public double compute(double a, double b) {
			return operation.applyAsDouble(a, b);
		}
	}

	public Quantity(double value, U unit) {

		if (unit == null)
			throw new IllegalArgumentException("Unit cannot be null");

		if (!Double.isFinite(value))
			throw new IllegalArgumentException("Invalid numeric value");

		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public U getUnit() {
		return unit;
	}

	private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetRequired) {

		if (other == null)
			throw new IllegalArgumentException("Operand cannot be null");

		if (unit.getClass() != other.unit.getClass())
			throw new IllegalArgumentException("Different measurement categories");

		if (!Double.isFinite(value) || !Double.isFinite(other.value))
			throw new IllegalArgumentException("Invalid numeric value");

		if (targetRequired && targetUnit == null)
			throw new IllegalArgumentException("Target unit required");
	}

	private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation op) {

		double base1 = unit.convertToBaseUnit(value);
		double base2 = other.unit.convertToBaseUnit(other.value);

		return op.compute(base1, base2);
	}

	private double round(double val) {
		return Math.round(val * 100.0) / 100.0;
	}

	public Quantity<U> convertTo(U targetUnit) {

		double base = unit.convertToBaseUnit(value);
		double result = targetUnit.convertFromBaseUnit(base);

		return new Quantity<>(round(result), targetUnit);
	}

	public Quantity<U> add(Quantity<U> other) {

		validateArithmeticOperands(other, unit, false);

		double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

		double result = unit.convertFromBaseUnit(baseResult);

		return new Quantity<>(round(result), unit);
	}

	public Quantity<U> add(Quantity<U> other, U targetUnit) {

		validateArithmeticOperands(other, targetUnit, true);

		double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

		double result = targetUnit.convertFromBaseUnit(baseResult);

		return new Quantity<>(round(result), targetUnit);
	}

	public Quantity<U> subtract(Quantity<U> other) {

		validateArithmeticOperands(other, unit, false);

		double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

		double result = unit.convertFromBaseUnit(baseResult);

		return new Quantity<>(round(result), unit);
	}

	public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

		validateArithmeticOperands(other, targetUnit, true);

		double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

		double result = targetUnit.convertFromBaseUnit(baseResult);

		return new Quantity<>(round(result), targetUnit);
	}

	public double divide(Quantity<U> other) {

		validateArithmeticOperands(other, null, false);

		return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof Quantity<?> other))
			return false;

		if (unit.getClass() != other.unit.getClass())
			return false;

		double base1 = unit.convertToBaseUnit(value);
		double base2 = other.unit.convertToBaseUnit(other.value);

		return Math.abs(base1 - base2) < 1e-6;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(unit.convertToBaseUnit(value));
	}

	@Override
	public String toString() {
		return "Quantity(" + value + ", " + unit + ")";
	}
}