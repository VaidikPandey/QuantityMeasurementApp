package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

	    System.out.println("1 foot in inches: " +
	            QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCH));

	    System.out.println("3 yards in feet: " +
	            QuantityLength.convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));

	    System.out.println("36 inches in yards: " +
	            QuantityLength.convert(36.0, LengthUnit.INCH, LengthUnit.YARDS));

	    System.out.println("2.54 cm in inches: " +
	            QuantityLength.convert(2.54, LengthUnit.CENTIMETER, LengthUnit.INCH));
	}
}


