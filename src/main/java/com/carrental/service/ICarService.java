package com.carrental.service;

import com.carrental.requestmodel.CarRegisterRequest;
import com.carrental.responsemodel.CarRegisterResponse;
import com.carrental.responsemodel.RegisteredCarResponse;

import java.util.List;

public interface ICarService {
    String findByPlate(String plate);

    CarRegisterResponse registerNewCar(CarRegisterRequest request) throws Exception;

    List<RegisteredCarResponse> findAllRegisteredCar(String username);
}
