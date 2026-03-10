package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityWeightTest {

    // Equality

    @Test
    void testEquality_KgToKg() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertTrue(a.equals(b));
    }

    @Test
    void testEquality_KgToGram() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(a.equals(b));
    }

    @Test
    void testEquality_KgToPound() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(2.20462, WeightUnit.POUND);

        assertTrue(a.equals(b));
    }

    // Conversion

    @Test
    void testConversion_KgToGram() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight result = a.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 1e-6);
    }

    @Test
    void testConversion_PoundToKg() {
        QuantityWeight a = new QuantityWeight(2.20462, WeightUnit.POUND);

        QuantityWeight result = a.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-3);
    }

    // Addition

    @Test
    void testAddition_KgPlusKg() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        QuantityWeight result = a.add(b);

        assertEquals(3.0, result.getValue());
    }

    @Test
    void testAddition_KgPlusGram() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = a.add(b);

        assertEquals(2.0, result.getValue(), 1e-6);
    }

    // Explicit target unit

    @Test
    void testAddition_TargetUnitGram() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result =
                QuantityWeight.add(a, b, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), 1e-6);
    }

    // Category safety

    @Test
    void testWeightVsLength_NotEqual() {

        QuantityWeight weight =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityLength length =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }

    // Constructor validation

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(1.0, null));
    }
}