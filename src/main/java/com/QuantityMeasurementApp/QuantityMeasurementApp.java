package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Equal? " + q1.equals(q2));

        System.out.println("1 foot in inches: " + q1.convertTo(LengthUnit.INCH));

        System.out.println("Sum in feet: " + q1.add(q2));

        System.out.println("Sum in yards: " + q1.add(q2));
    }
}