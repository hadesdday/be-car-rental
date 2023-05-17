package com.carrental.service.impl;

import com.carrental.entity.CarEntity;
import com.carrental.entity.CarRatingEntity;
import com.carrental.entity.CarRentalEntity;
import com.carrental.enums.RentalStatus;
import com.carrental.service.ICarOwnerStatService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Service
public class CarOwnerStatService implements ICarOwnerStatService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> getStatByOwner(String username) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<CarEntity> root = query.from(CarEntity.class);

        //for avg rating
        Join<CarEntity, CarRatingEntity> carRatingJoin = root.join("ratings");

        // for total revenue ( all time )
        Subquery<Long> totalCompletedRentalSubquery = query.subquery(Long.class);
        Root<CarRentalEntity> carRentalRoot = totalCompletedRentalSubquery.from(CarRentalEntity.class);
        totalCompletedRentalSubquery.select(cb.sum(carRentalRoot.get("rentalPrice")))
                .where(
                        cb.equal(carRentalRoot.get("car").get("user").get("username"), username),
                        cb.equal(
                                carRentalRoot.get("status"),
                                RentalStatus.COMPLETED
                        )
                );

        //for total rental
        Subquery<Long> totalRentalSubquery = query.subquery(Long.class);
        Root<CarRentalEntity> totalRentalRoot = totalRentalSubquery.from(CarRentalEntity.class);
        totalRentalSubquery.select(cb.count(totalRentalRoot))
                .where(
                        cb.equal(totalRentalRoot.get("car").get("user").get("username"), username)
                );

        //for total car
        Subquery<Long> totalCarSubquery = query.subquery(Long.class);
        Root<CarEntity> totalCarRoot = totalCarSubquery.from(CarEntity.class);
        totalCarSubquery.select(cb.count(totalCarRoot))
                .where(
                        cb.equal(totalCarRoot.get("user").get("username"), username)
                );

        //for accepted rental rating
        Subquery<Long> totalAcceptedRentalSubquery = query.subquery(Long.class);
        Root<CarRentalEntity> totalAcceptedRentalRoot = totalAcceptedRentalSubquery.from(CarRentalEntity.class);
        totalAcceptedRentalSubquery.select(cb.count(totalAcceptedRentalRoot))
                .where(
                        cb.equal(totalAcceptedRentalRoot.get("car").get("user").get("username"), username),
                        cb.greaterThanOrEqualTo(totalAcceptedRentalRoot.get("status"), RentalStatus.ACCEPTED)
                );
        Expression<Number> acceptedRating = cb.quot(totalAcceptedRentalSubquery.getSelection(), totalRentalSubquery.getSelection());
        Expression<Number> acceptedRatingInPercentage = cb.prod(acceptedRating, 100);

        //for cancel rental rating
        Subquery<Long> totalCancelledRentalSubquery = query.subquery(Long.class);
        Root<CarRentalEntity> totalCancelledRoot = totalCancelledRentalSubquery.from(CarRentalEntity.class);
        totalCancelledRentalSubquery.select(cb.count(totalCancelledRoot))
                .where(
                        cb.equal(totalCancelledRoot.get("car").get("user").get("username"), username),
                        cb.lessThanOrEqualTo(totalCancelledRoot.get("status"), RentalStatus.REJECTED)
                );
        Expression<Number> cancelledRating = cb.quot(totalCancelledRentalSubquery.getSelection(), totalRentalSubquery.getSelection());
        Expression<Number> cancelledRatingInPercentage = cb.prod(cancelledRating, 100);

        query.multiselect(
                cb.coalesce(cb.avg(carRatingJoin.get("rating")), 0),
                cb.coalesce(totalCompletedRentalSubquery.getSelection(), 0),
                cb.coalesce(totalRentalSubquery.getSelection(), 0),
                cb.coalesce(totalCarSubquery.getSelection(), 0),
                cb.coalesce(acceptedRatingInPercentage, 0),
                cb.coalesce(cancelledRatingInPercentage, 0)
        ).distinct(true);
        query.where(
                cb.equal(root.get("user").get("username"), username)
        );

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}
