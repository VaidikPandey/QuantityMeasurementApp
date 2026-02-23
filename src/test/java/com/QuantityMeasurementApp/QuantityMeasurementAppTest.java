package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {

    @Test
    void testFeetToFeet_SameValue() {
        // Same unit, same value
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(q1, q2);
    }

    @Test
    void testInchToInch_SameValue() {
        // Same unit, same value
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.INCH);
        assertEquals(q1, q2);
    }

    @Test
    void testFeetToInch_EquivalentValue() {
        // Cross-unit equivalent (1 ft = 12 in)
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);
        assertEquals(feet, inch);
    }

    @Test
    void testInchToFeet_EquivalentValue() {
        // Symmetry check
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(inch, feet);
    }

    @Test
    void testFeetToFeet_DifferentValue() {
        // Same unit, different values
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);
        assertNotEquals(q1, q2);
    }

    @Test
    void testInchToInch_DifferentValue() {
        // Same unit, different values
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.INCH);
        assertNotEquals(q1, q2);
    }

    @Test
    void testNullComparison() {
        // Object should not equal null
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertNotEquals(q1, null);
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
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(q1, q1);
    }

    @Test
    void testSymmetry() {
        // If A equals B, then B equals A
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
        assertTrue(q2.equals(q1));
    }

    @Test
    void testTransitivity() {
        // If A = B and B = C, then A = C
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(q1, q2);
        assertEquals(q2, q3);
        assertEquals(q1, q3);
    }

    @Test
    void testConsistency() {
        // Multiple calls should return same result
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
        assertTrue(q1.equals(q2));
        assertTrue(q1.equals(q2));
    }

    @Test
    void testHashCode_EqualObjects() {
        // Equal objects must have same hashCode
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);
        assertEquals(q1, q2);
        assertEquals(q1.hashCode(), q2.hashCode());
    }
}