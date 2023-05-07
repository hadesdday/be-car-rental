package com.carrental.service;

import com.carrental.requestmodel.UpdateRentalStatusRequest;
import com.carrental.responsemodel.RentalDetailsResponse;
import com.carrental.responsemodel.RentalListingResponse;
import com.carrental.responsemodel.UpdateRentalStatusResponse;

import java.util.List;

public interface ICarRentalService {
    List<RentalListingResponse> findByOwner(String username);

    RentalDetailsResponse findById(Long id);

    UpdateRentalStatusResponse acceptRental(UpdateRentalStatusRequest request) throws Exception;
    UpdateRentalStatusResponse rejectRental(UpdateRentalStatusRequest request) throws Exception;
    UpdateRentalStatusResponse confirmDeliveredCarToRenter(UpdateRentalStatusRequest request) throws Exception;
    UpdateRentalStatusResponse completeRental(UpdateRentalStatusRequest request) throws Exception;
}
