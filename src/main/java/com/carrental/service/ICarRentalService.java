package com.carrental.service;

import com.carrental.responsemodel.RentalListingResponse;

import java.util.List;

public interface ICarRentalService {
    List<RentalListingResponse> findByOwner(String username);
}
