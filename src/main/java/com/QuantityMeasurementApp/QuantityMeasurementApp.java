package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		Quantity<LengthUnit> l1 = new Quantity<>(1, LengthUnit.FEET);

		Quantity<LengthUnit> l2 = new Quantity<>(12, LengthUnit.INCH);

		System.out.println("Length equality: " + l1.equals(l2));

		Quantity<WeightUnit> w1 = new Quantity<>(1, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> w2 = new Quantity<>(1000, WeightUnit.GRAM);

		System.out.println("Weight addition: " + w1.add(w2));

		Quantity<VolumeUnit> v1 = new Quantity<>(1, VolumeUnit.LITRE);

		Quantity<VolumeUnit> v2 = new Quantity<>(500, VolumeUnit.MILLILITRE);

		System.out.println("Volume subtraction: " + v1.subtract(v2));

		Quantity<TemperatureUnit> t1 = new Quantity<>(0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> t2 = new Quantity<>(32, TemperatureUnit.FAHRENHEIT);

		System.out.println("Temperature equality: " + t1.equals(t2));

		System.out.println("Temperature conversion: " + t1.convertTo(TemperatureUnit.FAHRENHEIT));

		try {
			t1.add(t2);
		} catch (UnsupportedOperationException e) {
			System.out.println("Temperature arithmetic not supported");
		}
	}
}