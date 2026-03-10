package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testLengthEquality() {

        Quantity<LengthUnit> a = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12, LengthUnit.INCH);

        assertTrue(a.equals(b));
    }

    @Test
    void testAddition() {

        Quantity<LengthUnit> a = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12, LengthUnit.INCH);

        Quantity<LengthUnit> result = a.add(b);

        assertEquals(2.0, result.getValue());
    }

    @Test
    void testDivision() {

        Quantity<LengthUnit> a = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2, LengthUnit.FEET);

        assertEquals(5.0, a.divide(b));
    }
}