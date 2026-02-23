package com.QuantityMeasurementApp;

public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETER(0.393701 / 12.0);

    private final double factorToFeet;

    LengthUnit(double factorToFeet) {
        this.factorToFeet = factorToFeet;
    }

    public double toFeet(double value) {
        return value * factorToFeet;
    }

    public double fromFeet(double feetValue) {
        return feetValue / factorToFeet;
    }

    public double getFactorToFeet() {
        return factorToFeet;
    }
}
