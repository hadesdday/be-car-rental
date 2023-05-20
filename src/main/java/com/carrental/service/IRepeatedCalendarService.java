package com.carrental.service;

import com.carrental.requestmodel.CustomPriceRequest;
import com.carrental.requestmodel.DeleteRepeatedCalendarRequest;
import com.carrental.requestmodel.RepeatedCalendarDayRequest;
import com.carrental.responsemodel.CustomPriceResponse;
import com.carrental.responsemodel.PriceRepeatedCalendarResponse;
import com.carrental.responsemodel.RepeatedCalendarDayResponse;

import java.util.Date;
import java.util.List;

public interface IRepeatedCalendarService {
    CustomPriceResponse saveCustomPrice(CustomPriceRequest request) throws Exception;

    void deleteCustomPrice(DeleteRepeatedCalendarRequest request) throws Exception;

    List<CustomPriceResponse> findAllCustomPriceByOwner(String username, Long carId) throws Exception;

    PriceRepeatedCalendarResponse findCustomPriceByDateRange(Long carId, Date startDate, Date endDate) throws Exception;

    RepeatedCalendarDayResponse findByCarIdAndStartDate(RepeatedCalendarDayRequest request) throws Exception;
}
