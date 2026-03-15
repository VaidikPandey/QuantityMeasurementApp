package com.QuantityMeasurementApp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.QuantityMeasurementApp.model.QuantityMeasurementEntity;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;
    private List<QuantityMeasurementEntity> list = new ArrayList<>();

    private QuantityMeasurementCacheRepository() {
    }

    public static QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {
        list.add(entity);
        System.out.println("CacheRepository: saved - " + entity);
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return list;
    }

    @Override
    public List<QuantityMeasurementEntity> findByOperation(String operation) {
        return list.stream()
                .filter(e -> e.getOperation().equalsIgnoreCase(operation))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuantityMeasurementEntity> findByMeasurementType(String measurementType) {
        return list.stream()
                .filter(e -> e.getMeasurementType().equalsIgnoreCase(measurementType))
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalCount() {
        return list.size();
    }

    @Override
    public void deleteAll() {
        list.clear();
        System.out.println("CacheRepository: all records deleted");
    }
}