package com.carrental.service.impl;

import com.carrental.entity.ServiceTypeEntity;
import com.carrental.repository.IServiceTypeRepository;
import com.carrental.service.IServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceTypeService implements IServiceTypeService {

    @Autowired
    private IServiceTypeRepository repository;


    @Override
    public ServiceTypeEntity findById(Long id) throws Exception {
        Optional<ServiceTypeEntity> service = repository.findById(id);
        if (!service.isPresent()) throw new Exception("Không tìm thấy dịch vụ !");
        return service.get();
    }
}
