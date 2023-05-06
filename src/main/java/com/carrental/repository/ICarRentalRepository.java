package com.carrental.repository;

import com.carrental.entity.CarRentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRentalRepository extends JpaRepository<CarRentalEntity, Long> {
}
