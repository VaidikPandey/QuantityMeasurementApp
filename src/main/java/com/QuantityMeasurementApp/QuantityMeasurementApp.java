package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    public static void main(String[] args) {


        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Length equal? " + l1.equals(l2));

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println("Weight equal? " + w1.equals(w2));

        // Conversion
        System.out.println("1 kg to pound: " + w1.convertTo(WeightUnit.POUND));

        // Addition
        System.out.println("1 kg + 1000 g = " + w1.add(w2));
    }
}