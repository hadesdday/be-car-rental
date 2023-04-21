package com.carrental.service.impl;

import com.carrental.entity.ExtraFeeEntity;
import com.carrental.repository.IExtraFeeRepository;
import com.carrental.service.IExtraFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraFeeService implements IExtraFeeService {
    @Autowired
    private IExtraFeeRepository repository;

    @Override
    public List<ExtraFeeEntity> saveAll(List<ExtraFeeEntity> list) {
        return repository.saveAll(list);
    }
}
