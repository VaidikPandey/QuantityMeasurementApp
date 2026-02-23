package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthConversionTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testFeetToInches() {
        assertEquals(12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testInchesToFeet() {
        assertEquals(2.0,
                QuantityLength.convert(24.0, LengthUnit.INCH, LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testYardsToInches() {
        assertEquals(36.0,
                QuantityLength.convert(1.0, LengthUnit.YARDS, LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testCentimeterToInches() {
        assertEquals(1.0,
                QuantityLength.convert(2.54, LengthUnit.CENTIMETER, LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testSameUnitConversion() {
        assertEquals(5.0,
                QuantityLength.convert(5.0, LengthUnit.FEET, LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testZeroConversion() {
        assertEquals(0.0,
                QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testNegativeConversion() {
        assertEquals(-12.0,
                QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testRoundTripConversion() {
        double original = 3.5;
        double inches = QuantityLength.convert(original, LengthUnit.FEET, LengthUnit.INCH);
        double backToFeet = QuantityLength.convert(inches, LengthUnit.INCH, LengthUnit.FEET);

        assertEquals(original, backToFeet, EPSILON);
    }

    @Test
    void testNullUnitThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(1.0, null, LengthUnit.FEET));
    }

    @Test
    void testNaNThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH));
    }

    @Test
    void testInfinityThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH));
    }

    @Test
    void testInstanceConvertTo() {
        QuantityLength length = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = length.convertTo(LengthUnit.INCH);

        assertEquals(12.0, inches.getValue(), EPSILON);
        assertEquals(LengthUnit.INCH, inches.getUnit());
    }
}