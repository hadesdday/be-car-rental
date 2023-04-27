package com.carrental.service;

import com.carrental.entity.BrandEntity;
import com.carrental.responsemodel.BrandResponse;
import com.carrental.responsemodel.ModelResponse;

import java.util.List;

public interface IBrandService {
    List<ModelResponse> findModelsById(Long id) throws Exception;

    BrandEntity findById(Long id) throws Exception;

    List<BrandResponse> findAll();
}
