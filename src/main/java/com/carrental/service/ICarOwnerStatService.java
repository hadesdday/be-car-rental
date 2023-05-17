package com.carrental.service;

import java.util.List;

public interface ICarOwnerStatService {
    List<Object[]> getStatByOwner(String username);
}
