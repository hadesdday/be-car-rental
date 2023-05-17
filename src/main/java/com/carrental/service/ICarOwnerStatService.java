package com.carrental.service;

import com.carrental.responsemodel.CarOwnerStatResponse;

public interface ICarOwnerStatService {
    CarOwnerStatResponse getStatByOwner(String username);


}
