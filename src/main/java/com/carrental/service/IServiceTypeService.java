package com.carrental.service;

import com.carrental.entity.ServiceTypeEntity;

public interface IServiceTypeService {
    ServiceTypeEntity findById(Long id) throws Exception;
}
