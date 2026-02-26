package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

	    QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
	    QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
	    QuantityLength inches = new QuantityLength(36.0, LengthUnit.INCH);
	    QuantityLength cm = new QuantityLength(2.54, LengthUnit.CENTIMETER);
	    QuantityLength inch = new QuantityLength(1.0, LengthUnit.INCH);

	    System.out.println("1 yard equals 3 feet? " + yard.equals(feet));
	    System.out.println("1 yard equals 36 inches? " + yard.equals(inches));
	    System.out.println("2.54 cm equals 1 inch? " + cm.equals(inch));
	}
}


