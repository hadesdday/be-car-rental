package com.carrental.service.impl;

import com.carrental.entity.CarEntity;
import com.carrental.repository.ICarRepository;
import com.carrental.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements ICarService {
    @Autowired
    private ICarRepository repository;

    @Override
    public String findByPlate(String plate) throws Exception {
        List<CarEntity> list = repository.findByPlate(plate);
        if (list.size() < 1) throw new Exception("No car was found !");
        return list.get(0).getPlate();
    }
}
