package com.carrental.service;

import com.carrental.enums.ChartCategory;
import com.carrental.responsemodel.CarOwnerChartStatResponse;
import com.carrental.responsemodel.CarOwnerStatResponse;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ICarOwnerStatService {
    CarOwnerStatResponse getStatByOwner(String username);

    List<CarOwnerChartStatResponse> getChartStats(ChartCategory category, String username, Date startDate, Date endDate);
}
