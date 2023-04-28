package com.carrental.service;

import com.carrental.responsemodel.ProvinceResponse;

import java.util.List;

public interface IProvinceService {
    List<ProvinceResponse> findAll();
}
