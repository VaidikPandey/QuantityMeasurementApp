package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

		QuantityLength result1 = a.add(b);
		System.out.println("1 ft + 12 in = " + result1);

		QuantityLength result2 = new QuantityLength(12.0, LengthUnit.INCH)
				.add(new QuantityLength(1.0, LengthUnit.FEET));

		System.out.println("12 in + 1 ft = " + result2);
	}
}
