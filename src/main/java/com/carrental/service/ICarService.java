package com.carrental.service;

import com.carrental.requestmodel.CarRegisterRequest;
import com.carrental.responsemodel.CarRegisterResponse;

public interface ICarService {
    String findByPlate(String plate);

    CarRegisterResponse registerNewCar(CarRegisterRequest request) throws Exception;
}
