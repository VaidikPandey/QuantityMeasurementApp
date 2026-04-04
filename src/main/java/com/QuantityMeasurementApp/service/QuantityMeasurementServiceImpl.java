package com.QuantityMeasurementApp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QuantityMeasurementApp.Quantity;
import com.QuantityMeasurementApp.model.QuantityDTO;
import com.QuantityMeasurementApp.model.QuantityMeasurementEntity;
import com.QuantityMeasurementApp.repository.QuantityMeasurementRepository;
import com.QuantityMeasurementApp.unit.*;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repo;

    // build Quantity from DTO
    private Quantity buildQuantity(double value, String unit, String type) {
        IMeasurable unitEnum = switch (type.toUpperCase()) {
            case "WEIGHT"      -> WeightUnit.valueOf(unit);
            case "VOLUME"      -> VolumeUnit.valueOf(unit);
            case "TEMPERATURE" -> TemperatureUnit.valueOf(unit);
            default            -> LengthUnit.valueOf(unit);
        };
        return new Quantity(value, unitEnum);
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2, String userEmail) {
        try {
            Quantity a = buildQuantity(q1.getValue(), q1.getUnit(), q1.getType());
            Quantity b = buildQuantity(q2.getValue(), q2.getUnit(), q2.getType());
            boolean result = a.equals(b);
            repo.save(new QuantityMeasurementEntity("COMPARE", q1.getType(), String.valueOf(result), userEmail));
            return result;
        } catch (Exception e) {
            repo.save(new QuantityMeasurementEntity("COMPARE", q1.getType(), e.getMessage(), true, userEmail));
            throw e;
        }
    }

    @Override
    public QuantityDTO convert(QuantityDTO q, String target, String userEmail) {
        try {
            Quantity a = buildQuantity(q.getValue(), q.getUnit(), q.getType());
            IMeasurable targetUnit = switch (q.getType().toUpperCase()) {
                case "WEIGHT"      -> WeightUnit.valueOf(target);
                case "VOLUME"      -> VolumeUnit.valueOf(target);
                case "TEMPERATURE" -> TemperatureUnit.valueOf(target);
                default            -> LengthUnit.valueOf(target);
            };
            Quantity r = a.convertTo(targetUnit);
            repo.save(new QuantityMeasurementEntity("CONVERT", q.getType(), r.toString(), userEmail));
            return new QuantityDTO(r.getValue(), target, q.getType());
        } catch (Exception e) {
            repo.save(new QuantityMeasurementEntity("CONVERT", q.getType(), e.getMessage(), true, userEmail));
            throw e;
        }
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String userEmail) {
        try {
            Quantity a = buildQuantity(q1.getValue(), q1.getUnit(), q1.getType());
            Quantity b = buildQuantity(q2.getValue(), q2.getUnit(), q2.getType());
            Quantity r = a.add(b);
            repo.save(new QuantityMeasurementEntity("ADD", q1.getType(), r.toString(), userEmail));
            return new QuantityDTO(r.getValue(), r.getUnit().getUnitName(), q1.getType());
        } catch (Exception e) {
            repo.save(new QuantityMeasurementEntity("ADD", q1.getType(), e.getMessage(), true, userEmail));
            throw e;
        }
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String userEmail) {
        try {
            Quantity a = buildQuantity(q1.getValue(), q1.getUnit(), q1.getType());
            Quantity b = buildQuantity(q2.getValue(), q2.getUnit(), q2.getType());
            Quantity r = a.subtract(b);
            repo.save(new QuantityMeasurementEntity("SUBTRACT", q1.getType(), r.toString(), userEmail));
            return new QuantityDTO(r.getValue(), r.getUnit().getUnitName(), q1.getType());
        } catch (Exception e) {
            repo.save(new QuantityMeasurementEntity("SUBTRACT", q1.getType(), e.getMessage(), true, userEmail));
            throw e;
        }
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2, String userEmail) {
        try {
            Quantity a = buildQuantity(q1.getValue(), q1.getUnit(), q1.getType());
            Quantity b = buildQuantity(q2.getValue(), q2.getUnit(), q2.getType());
            double r = a.divide(b);
            repo.save(new QuantityMeasurementEntity("DIVIDE", q1.getType(), String.valueOf(r), userEmail));
            return r;
        } catch (Exception e) {
            repo.save(new QuantityMeasurementEntity("DIVIDE", q1.getType(), e.getMessage(), true, userEmail));
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

    @Override
    public List<QuantityMeasurementEntity> getUserHistory(String userEmail) {
        return repo.findByUserEmailOrderByIdDesc(userEmail);
    }

    @Override
    public List<QuantityMeasurementEntity> getUserHistoryByOperation(String userEmail, String operation) {
        return repo.findByUserEmailAndOperation(userEmail, operation);
    }

    @Override
    public void clearUserHistory(String userEmail) {
        repo.deleteByUserEmail(userEmail);
    }
}