package com.carrental.service.impl;

import com.carrental.entity.*;
import com.carrental.repository.ICarRentalRepository;
import com.carrental.responsemodel.RentalListingResponse;
import com.carrental.service.ICarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Service
public class CarRentalService implements ICarRentalService {
    @Autowired
    private ICarRentalRepository carRentalRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RentalListingResponse> findByOwner(String username) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentalListingResponse> query = cb.createQuery(RentalListingResponse.class);
        Root<CarEntity> root = query.from(CarEntity.class);

        //car user join
        Join<CarEntity, UserEntity> carUserJoin = root.join("user", JoinType.INNER);
        carUserJoin.on(
                cb.equal(carUserJoin.get("username"), username)
        );

        Join<CarEntity, CarRentalEntity> carRentalJoin = root.join("rentals", JoinType.INNER);

        //car images join
        Join<CarEntity, CarImagesEntity> imageJoin = root.join("images", JoinType.LEFT);
        imageJoin.on(
                cb.and(
                        cb.equal(imageJoin.get("car"), root),
                        cb.isTrue(imageJoin.get("isThumbnail"))
                )
        );

        //car rating join
        Join<CarEntity, CarRatingEntity> carRatingJoin = root.join("ratings", JoinType.LEFT);

        query.multiselect(
                carRentalJoin.get("id"),
                imageJoin.get("imageUrl"),
                root.get("model").get("name"),
                root.get("yearOfManufacture"),
                root.get("plate"),
                carRentalJoin.get("user").get("fullName"),
                cb.coalesce(cb.avg(carRatingJoin.get("rating")), 0),
                carRentalJoin.get("rentalPrice"),
                carRentalJoin.get("startDate"),
                carRentalJoin.get("endDate")
        ).distinct(true);
        query.groupBy(
                carRentalJoin.get("id"),
                imageJoin.get("imageUrl"),
                carRentalJoin.get("user").get("fullName"),
                carRentalJoin.get("rentalPrice"),
                carRentalJoin.get("startDate"),
                carRentalJoin.get("endDate")
        );
        TypedQuery<RentalListingResponse> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}
