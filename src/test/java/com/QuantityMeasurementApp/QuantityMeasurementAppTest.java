package com.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 1e-6;

	// equality tests

	@Test
	void testEquality_LitreToLitre() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);

		assertEquals(v1, v2);
	}

	@Test
	void testEquality_LitreToMillilitre() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		assertEquals(v1, v2);
	}

	@Test
	void testEquality_LitreToGallon() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(0.264172, VolumeUnit.GALLON);

		assertEquals(v1, v2);
	}

	// conversion tests

	@Test
	void testConversion_LitreToMillilitre() {
		Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE);

		assertEquals(1000.0, result.getValue(), EPSILON);
	}

	@Test
	void testConversion_GallonToLitre() {
		Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.GALLON);
		Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.LITRE);

		assertEquals(3.78541, result.getValue(), EPSILON);
	}

	@Test
	void testConversion_MillilitreToGallon() {
		Quantity<VolumeUnit> v = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.GALLON);

		assertEquals(0.264172, result.getValue(), EPSILON);
	}

	// addition tests

	@Test
	void testAddition_SameUnit() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = v1.add(v2);

		assertEquals(3.0, result.getValue(), EPSILON);
	}

	@Test
	void testAddition_CrossUnit() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> result = v1.add(v2);

		assertEquals(2.0, result.getValue(), EPSILON);
	}

	@Test
	void testAddition_ExplicitTargetUnit() {
		Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.MILLILITRE);

		assertEquals(2000.0, result.getValue(), EPSILON);
	}

	// edge cases

	@Test
	void testZeroVolume() {
		Quantity<VolumeUnit> v1 = new Quantity<>(0.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(0.0, VolumeUnit.MILLILITRE);

		assertEquals(v1, v2);
	}

	@Test
	void testNegativeVolume() {
		Quantity<VolumeUnit> v1 = new Quantity<>(-1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);

		assertEquals(v1, v2);
	}

}