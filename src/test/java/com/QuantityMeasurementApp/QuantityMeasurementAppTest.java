package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthUC4Test {

    @Test
    void testYardToYard_SameValue() {
        // Same unit, same value
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.YARDS);
        assertEquals(q1, q2);
    }

    @Test
    void testYardToYard_DifferentValue() {
        // Same unit, different values
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.YARDS);
        assertNotEquals(q1, q2);
    }

    @Test
    void testYardToFeet_Equivalent() {
        // 1 yard = 3 feet
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        assertEquals(yard, feet);
    }

    @Test
    void testFeetToYard_Equivalent() {
        // Symmetry check
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        assertEquals(feet, yard);
    }

    @Test
    void testYardToInch_Equivalent() {
        // 1 yard = 36 inches
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength inch = new QuantityLength(36.0, LengthUnit.INCH);
        assertEquals(yard, inch);
    }

    @Test
    void testCentimeterToCentimeter_SameValue() {
        // Same unit
        QuantityLength c1 = new QuantityLength(2.0, LengthUnit.CENTIMETER);
        QuantityLength c2 = new QuantityLength(2.0, LengthUnit.CENTIMETER);
        assertEquals(c1, c2);
    }

    @Test
    void testCentimeterToInch_Equivalent() {
        // 1 cm = 0.393701 inch
        QuantityLength cm = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength inch = new QuantityLength(0.393701, LengthUnit.INCH);
        assertEquals(cm, inch);
    }

    @Test
    void testCentimeterToFeet_NotEquivalent() {
        // 1 cm != 1 foot
        QuantityLength cm = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        assertNotEquals(cm, feet);
    }

    @Test
    void testMultiUnit_TransitiveProperty() {
        // 1 yard = 3 feet = 36 inches
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(36.0, LengthUnit.INCH);

        assertEquals(yard, feet);
        assertEquals(feet, inch);
        assertEquals(yard, inch);
    }

    @Test
    void testComplexScenario_AllUnits() {
        // 2 yards = 6 feet = 72 inches
        QuantityLength yard = new QuantityLength(2.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(6.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(72.0, LengthUnit.INCH);

        assertEquals(yard, feet);
        assertEquals(feet, inch);
        assertEquals(yard, inch);
    }

    @Test
    void testNullComparison() {
        // Should not equal null
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        assertNotEquals(yard, null);
    }

    @Test
    void testNullUnit() {
        // Constructor should reject null unit
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    void testSameReference() {
        // Reflexive property
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        assertEquals(yard, yard);
    }

    @Test
    void testHashCode_EqualObjects() {
        // Equal objects must have same hashCode
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);

        assertEquals(yard, feet);
        assertEquals(yard.hashCode(), feet.hashCode());
    }
}