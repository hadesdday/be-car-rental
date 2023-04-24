package com.carrental.service;

import com.carrental.dto.PromoDTO;
import com.carrental.entity.PromoEntity;
import com.carrental.requestmodel.NewPromoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IPromoService {
    public List<PromoDTO> findAllAvailable(Specification<PromoEntity> spec);
    public List<PromoDTO> findAll();

    public PromoEntity addNewPromo(NewPromoRequest newPromo);
    public void removePromo(Long id);

    public PromoDTO updatePromo(PromoDTO updatedPromo);
}
