package com.carrental.repository;

import com.carrental.entity.ExtraFeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExtraFeeRepository extends JpaRepository<ExtraFeeEntity, Long> {
}
