package com.carrental.service;

import com.carrental.entity.ExtraFeeEntity;

import java.math.BigInteger;
import java.util.List;

public interface IExtraFeeService {
    List<ExtraFeeEntity> saveAll(List<ExtraFeeEntity> list);

    BigInteger findDeliveryToTenantFee(Long id);
}
