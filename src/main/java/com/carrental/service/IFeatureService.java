package com.carrental.service;

import com.carrental.entity.FeatureEntity;
import com.carrental.responsemodel.FeatureResponse;

import java.util.List;

public interface IFeatureService {
    List<FeatureResponse> findAll();
    List<FeatureEntity> findAllByIdIn(List<Long> ids);
}
