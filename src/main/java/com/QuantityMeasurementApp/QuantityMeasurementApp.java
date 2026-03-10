package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

	    Quantity<LengthUnit> a = new Quantity<>(10, LengthUnit.FEET);
	    Quantity<LengthUnit> b = new Quantity<>(6, LengthUnit.INCH);

	    Quantity<LengthUnit> result = a.subtract(b);

	    System.out.println("Subtraction: " + result);

	    double ratio = a.divide(new Quantity<>(2, LengthUnit.FEET));

	    System.out.println("Division: " + ratio);
	}
}