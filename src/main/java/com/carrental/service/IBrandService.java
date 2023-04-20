package com.carrental.service;

import com.carrental.responsemodel.ModelResponse;

import java.util.List;

public interface IBrandService {
    List<ModelResponse> findModelsById(Long id) throws Exception;
}
