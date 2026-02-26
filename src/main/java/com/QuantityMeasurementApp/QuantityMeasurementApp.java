package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

	    QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
	    QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

	    QuantityLength sumFeet =
	            QuantityLength.add(a, b, LengthUnit.FEET);
	    System.out.println("Sum in feet: " + sumFeet);

	    QuantityLength sumInches =
	            QuantityLength.add(a, b, LengthUnit.INCH);
	    System.out.println("Sum in inches: " + sumInches);

	    QuantityLength sumYards =
	            QuantityLength.add(a, b, LengthUnit.YARDS);
	    System.out.println("Sum in yards: " + sumYards);

	    QuantityLength sumCm =
	            QuantityLength.add(
	                    new QuantityLength(2.54, LengthUnit.CENTIMETER),
	                    new QuantityLength(1.0, LengthUnit.INCH),
	                    LengthUnit.CENTIMETER);

	    System.out.println("2.54 cm + 1 in in cm = " + sumCm);
	}
}


