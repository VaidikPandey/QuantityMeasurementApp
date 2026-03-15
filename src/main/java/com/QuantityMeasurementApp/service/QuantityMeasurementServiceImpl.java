package com.QuantityMeasurementApp.service;

import com.QuantityMeasurementApp.unit.*;
import com.QuantityMeasurementApp.Quantity;
import com.QuantityMeasurementApp.model.*;
import com.QuantityMeasurementApp.repository.*;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private IQuantityMeasurementRepository repo;

	public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo) {
		this.repo = repo;
	}

	private LengthUnit getLengthUnit(String unit) {
		return LengthUnit.valueOf(unit);
	}

	@Override
	public boolean compare(QuantityDTO q1, QuantityDTO q2) {
		Quantity<LengthUnit> a = new Quantity<>(q1.getValue(), getLengthUnit(q1.getUnit()));
		Quantity<LengthUnit> b = new Quantity<>(q2.getValue(), getLengthUnit(q2.getUnit()));
		boolean result = a.equals(b);
		repo.save(new QuantityMeasurementEntity("COMPARE", "LENGTH", String.valueOf(result)));
		return result;
	}

	@Override
	public QuantityDTO convert(QuantityDTO q, String target) {
		Quantity<LengthUnit> a = new Quantity<>(q.getValue(), getLengthUnit(q.getUnit()));
		Quantity<LengthUnit> r = a.convertTo(getLengthUnit(target));
		repo.save(new QuantityMeasurementEntity("CONVERT", "LENGTH", r.toString()));
		return new QuantityDTO(r.getValue(), target, "LENGTH");
	}

	@Override
	public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
		Quantity<LengthUnit> a = new Quantity<>(q1.getValue(), getLengthUnit(q1.getUnit()));
		Quantity<LengthUnit> b = new Quantity<>(q2.getValue(), getLengthUnit(q2.getUnit()));
		Quantity<LengthUnit> r = a.add(b);
		repo.save(new QuantityMeasurementEntity("ADD", "LENGTH", r.toString()));
		return new QuantityDTO(r.getValue(), r.getUnit().name(), "LENGTH");
	}

	@Override
	public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
		Quantity<LengthUnit> a = new Quantity<>(q1.getValue(), getLengthUnit(q1.getUnit()));
		Quantity<LengthUnit> b = new Quantity<>(q2.getValue(), getLengthUnit(q2.getUnit()));
		Quantity<LengthUnit> r = a.subtract(b);
		repo.save(new QuantityMeasurementEntity("SUBTRACT", "LENGTH", r.toString()));
		return new QuantityDTO(r.getValue(), r.getUnit().name(), "LENGTH");
	}

	@Override
	public double divide(QuantityDTO q1, QuantityDTO q2) {
		Quantity<LengthUnit> a = new Quantity<>(q1.getValue(), getLengthUnit(q1.getUnit()));
		Quantity<LengthUnit> b = new Quantity<>(q2.getValue(), getLengthUnit(q2.getUnit()));
		double r = a.divide(b);
		repo.save(new QuantityMeasurementEntity("DIVIDE", "LENGTH", String.valueOf(r)));
		return r;
	}
}