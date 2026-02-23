package com.QuantityMeasurementApp;

import java.util.Objects;

public final class QuantityLength {

    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON = 1e-6;

    public QuantityLength(double value, LengthUnit unit) {
        validate(value, unit);
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // Static convert (from UC5)
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        validate(value, source);
        validate(value, target);

        double valueInFeet = source.toFeet(value);
        return target.fromFeet(valueInFeet);
    }

    // Addition (UC6)
    // Instance method: result in unit of first operand
    public QuantityLength add(QuantityLength other) {
        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        double thisInFeet = this.unit.toFeet(this.value);
        double otherInFeet = other.unit.toFeet(other.value);

        double sumInFeet = thisInFeet + otherInFeet;

        double resultValue = this.unit.fromFeet(sumInFeet);

        return new QuantityLength(resultValue, this.unit);
    }

    // Static overloaded version with explicit target unit
    public static QuantityLength add(
            QuantityLength a,
            QuantityLength b,
            LengthUnit targetUnit) {

        if (a == null || b == null)
            throw new IllegalArgumentException("Operands cannot be null");

        validate(a.value, a.unit);
        validate(b.value, b.unit);
        validate(0.0, targetUnit); // unit validation

        double aInFeet = a.unit.toFeet(a.value);
        double bInFeet = b.unit.toFeet(b.value);

        double sumInFeet = aInFeet + bInFeet;
        double resultValue = targetUnit.fromFeet(sumInFeet);

        return new QuantityLength(resultValue, targetUnit);
    }

    private static void validate(double value, LengthUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
    }

    private double toBaseUnit() {
        return unit.toFeet(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityLength other = (QuantityLength) obj;
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