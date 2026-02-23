import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality_SameValue() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(first.equals(second), "1.0 ft should be equal to 1.0 ft");
    }

    @Test
    void testEquality_DifferentValue() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(2.0);

        assertFalse(first.equals(second), "1.0 ft should not be equal to 2.0 ft");
    }

    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(first.equals(null), "Value should not be equal to null");
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(first.equals(first), "Object must be equal to itself");
    }

    @Test
    void testEquality_DifferentType() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(first.equals("1.0"), "Feet should not be equal to a String");
    }
}