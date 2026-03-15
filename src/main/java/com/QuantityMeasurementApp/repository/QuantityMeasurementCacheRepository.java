package com.QuantityMeasurementApp.repository;

import java.util.*;

import com.QuantityMeasurementApp.model.QuantityMeasurementEntity;

public class QuantityMeasurementCacheRepository
implements IQuantityMeasurementRepository{

    private static QuantityMeasurementCacheRepository instance;

    private List<QuantityMeasurementEntity> list=
            new ArrayList<>();

    private QuantityMeasurementCacheRepository(){}

    public static QuantityMeasurementCacheRepository
    getInstance(){

        if(instance==null){
            instance=
            new QuantityMeasurementCacheRepository();
        }

        return instance;
    }

    @Override
    public void save(
            QuantityMeasurementEntity entity){

        list.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> findAll(){

        return list;
    }
}