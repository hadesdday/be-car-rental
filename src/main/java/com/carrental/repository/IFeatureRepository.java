package com.carrental.repository;

import com.carrental.entity.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFeatureRepository extends JpaRepository<FeatureEntity, Long> {
}
