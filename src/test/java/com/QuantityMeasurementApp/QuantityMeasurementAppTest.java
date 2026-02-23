package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthExplicitAdditionTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.YARDS);

        assertEquals(2.0 / 3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength b = new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.CENTIMETER);

        assertEquals(5.08, result.getValue(), 1e-4);
        assertEquals(LengthUnit.CENTIMETER, result.getUnit());
    }

    @Test
    void testAddition_Commutativity_WithExplicitTarget() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result1 =
                QuantityLength.add(a, b, LengthUnit.YARDS);

        QuantityLength result2 =
                QuantityLength.add(b, a, LengthUnit.YARDS);

        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
    }

    @Test
    void testAddition_WithZero_ExplicitTarget() {
        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(0.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.YARDS);

        assertEquals(5.0 / 3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues_ExplicitTarget() {
        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.INCH);

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NullTargetUnit() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.add(a, b, null));
    }

    @Test
    void testAddition_LargeToSmallScale() {
        QuantityLength a = new QuantityLength(1000.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(500.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.INCH);

        assertEquals(18000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_SmallToLargeScale() {
        QuantityLength a = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.YARDS);

        assertEquals(2.0 / 3.0, result.getValue(), EPSILON);
    }
}