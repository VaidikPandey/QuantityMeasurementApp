package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {

    // LengthUnit constants

    @Test
    void testLengthUnit_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getFactorToFeet());
    }

    @Test
    void testLengthUnit_InchConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCH.getFactorToFeet());
    }

    @Test
    void testLengthUnit_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getFactorToFeet());
    }

    @Test
    void testLengthUnit_CentimeterConstant() {
        assertEquals(0.393701 / 12.0, LengthUnit.CENTIMETER.getFactorToFeet());
    }

    // Conversion to base unit

    @Test
    void testConvertToBaseUnit_InchToFeet() {
        assertEquals(1.0, LengthUnit.INCH.toFeet(12.0));
    }

    @Test
    void testConvertToBaseUnit_YardToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.toFeet(1.0));
    }

    // Convert from base unit

    @Test
    void testConvertFromBaseUnit_FeetToInch() {
        assertEquals(12.0, LengthUnit.INCH.fromFeet(1.0));
    }

    @Test
    void testConvertFromBaseUnit_FeetToYard() {
        assertEquals(1.0, LengthUnit.YARDS.fromFeet(3.0));
    }

    // Equality

    @Test
    void testEquality_FeetToInch() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        assertTrue(a.equals(b));
    }

    // Conversion

    @Test
    void testConversion_FeetToInch() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = a.convertTo(LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), 1e-6);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    // Addition

    @Test
    void testAddition_FeetPlusInch() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = a.add(b);

        assertEquals(2.0, result.getValue(), 1e-6);
    }

    // Constructor validation

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }
}