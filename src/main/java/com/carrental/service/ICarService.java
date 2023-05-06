package com.carrental.service;

import com.carrental.requestmodel.CarAdminRequest;
import com.carrental.requestmodel.CarRegisterRequest;
import com.carrental.responsemodel.CarAdminResponse;
import com.carrental.responsemodel.CarRegisterResponse;
import com.carrental.responsemodel.RegisteredCarResponse;

import java.util.List;
import java.util.Set;

public interface ICarService {
    String findByPlate(String plate);

    CarRegisterResponse registerNewCar(CarRegisterRequest request) throws Exception;

    Set<RegisteredCarResponse> findAllRegisteredCar(String username);

    List<CarAdminResponse> findAll(); // for admin

    CarAdminResponse updateCar(CarAdminRequest request) throws Exception;
}
