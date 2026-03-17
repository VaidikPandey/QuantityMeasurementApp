package com.QuantityMeasurementApp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QuantityMeasurementApp.Quantity;
import com.QuantityMeasurementApp.model.QuantityDTO;
import com.QuantityMeasurementApp.model.QuantityMeasurementEntity;
import com.QuantityMeasurementApp.repository.QuantityMeasurementRepository;
import com.QuantityMeasurementApp.unit.LengthUnit;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	@Autowired
	private QuantityMeasurementRepository repo;

	private LengthUnit getLengthUnit(String unit) {
		return LengthUnit.valueOf(unit);
	}

	@Override
	public boolean compare(QuantityDTO q1, QuantityDTO q2) {
		try {
			Quantity<LengthUnit> a = new Quantity<>(q1.getValue(), getLengthUnit(q1.getUnit()));
			Quantity<LengthUnit> b = new Quantity<>(q2.getValue(), getLengthUnit(q2.getUnit()));
			boolean result = a.equals(b);
			repo.save(new QuantityMeasurementEntity("COMPARE", "LENGTH", String.valueOf(result)));
			return result;
		} catch (Exception e) {
			repo.save(new QuantityMeasurementEntity("COMPARE", "LENGTH", e.getMessage(), true));
			throw e;
		}
	}

	@Override
	public QuantityDTO convert(QuantityDTO q, String target) {
		try {
			Quantity<LengthUnit> a = new Quantity<>(q.getValue(), getLengthUnit(q.getUnit()));
			Quantity<LengthUnit> r = a.convertTo(getLengthUnit(target));
			repo.save(new QuantityMeasurementEntity("CONVERT", "LENGTH", r.toString()));
			return new QuantityDTO(r.getValue(), target, "LENGTH");
		} catch (Exception e) {
			repo.save(new QuantityMeasurementEntity("CONVERT", "LENGTH", e.getMessage(), true));
			throw e;
		}
	}

	@Override
	public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
		try {
			Quantity<LengthUnit> a = new Quantity<>(q1.getValue(), getLengthUnit(q1.getUnit()));
			Quantity<LengthUnit> b = new Quantity<>(q2.getValue(), getLengthUnit(q2.getUnit()));
			Quantity<LengthUnit> r = a.add(b);
			repo.save(new QuantityMeasurementEntity("ADD", "LENGTH", r.toString()));
			return new QuantityDTO(r.getValue(), r.getUnit().name(), "LENGTH");
		} catch (Exception e) {
			repo.save(new QuantityMeasurementEntity("ADD", "LENGTH", e.getMessage(), true));
			throw e;
		}
	}

	@Override
	public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
		try {
			Quantity<LengthUnit> a = new Quantity<>(q1.getValue(), getLengthUnit(q1.getUnit()));
			Quantity<LengthUnit> b = new Quantity<>(q2.getValue(), getLengthUnit(q2.getUnit()));
			Quantity<LengthUnit> r = a.subtract(b);
			repo.save(new QuantityMeasurementEntity("SUBTRACT", "LENGTH", r.toString()));
			return new QuantityDTO(r.getValue(), r.getUnit().name(), "LENGTH");
		} catch (Exception e) {
			repo.save(new QuantityMeasurementEntity("SUBTRACT", "LENGTH", e.getMessage(), true));
			throw e;
		}
	}

	@Override
	public double divide(QuantityDTO q1, QuantityDTO q2) {
		try {
			Quantity<LengthUnit> a = new Quantity<>(q1.getValue(), getLengthUnit(q1.getUnit()));
			Quantity<LengthUnit> b = new Quantity<>(q2.getValue(), getLengthUnit(q2.getUnit()));
			double r = a.divide(b);
			repo.save(new QuantityMeasurementEntity("DIVIDE", "LENGTH", String.valueOf(r)));
			return r;
		} catch (Exception e) {
			repo.save(new QuantityMeasurementEntity("DIVIDE", "LENGTH", e.getMessage(), true));
			throw e;
		}
	}

	@Override
	public List<QuantityMeasurementEntity> getHistoryByOperation(String operation) {
		return repo.findByOperation(operation);
	}

	@Override
	public List<QuantityMeasurementEntity> getHistoryByMeasurementType(String measurementType) {
		return repo.findByMeasurementType(measurementType);
	}

	@Override
	public List<QuantityMeasurementEntity> getErrorHistory() {
		return repo.findByErrorTrue();
	}

	@Override
	public long getCountByOperation(String operation) {
		return repo.countByOperation(operation);
	}
}