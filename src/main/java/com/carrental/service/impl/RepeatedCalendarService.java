package com.carrental.service.impl;

import com.carrental.constance.ErrorMessage;
import com.carrental.entity.CarEntity;
import com.carrental.entity.RepeatedCalendarEntity;
import com.carrental.enums.RepeatedCalendarType;
import com.carrental.repository.IRepeatedCalendarRepository;
import com.carrental.requestmodel.CustomPriceRequest;
import com.carrental.requestmodel.RepeatedCalendarDayRequest;
import com.carrental.responsemodel.*;
import com.carrental.service.ICarService;
import com.carrental.service.IRepeatedCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RepeatedCalendarService implements IRepeatedCalendarService {
    @Autowired
    private IRepeatedCalendarRepository repeatedCalendarRepository;
    @Autowired
    private ICarService carService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CustomPriceResponse saveCustomPrice(CustomPriceRequest request) throws Exception {
        RepeatedCalendarEntity repeatedCalendar = repeatedCalendarRepository.findFirstByCarIdAndTypeAndStartDateEqualsAndEndDateEquals(
                request.getCarId(), RepeatedCalendarType.PRICE, request.getStartDate(), request.getEndDate());
        if (repeatedCalendar != null) {
            repeatedCalendar.setValue(request.getValue());
        } else {
            Optional<CarEntity> car = carService.findById(request.getCarId());
            if (!car.isPresent()) throw new Exception(ErrorMessage.NO_CAR_WAS_FOUND);
            repeatedCalendar = RepeatedCalendarEntity.builder()
                    .value(request.getValue())
                    .startDate(request.getStartDate())
                    .endDate(request.getEndDate())
                    .car(car.get())
                    .priority(request.getPriority())
                    .type(RepeatedCalendarType.PRICE)
                    .build();
        }
        RepeatedCalendarEntity updatedCalendar = repeatedCalendarRepository.save(repeatedCalendar);
        return CustomPriceResponse.builder()
                .id(updatedCalendar.getId())
                .carId(updatedCalendar.getCar().getId())
                .startDate(updatedCalendar.getStartDate())
                .endDate(updatedCalendar.getEndDate())
                .value(updatedCalendar.getValue())
                .build();
    }

    @Override
    public CustomPriceResponse findById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<CustomPriceResponse> findAllCustomPriceByOwner(String username, Long carId) throws Exception {
        List<RepeatedCalendarEntity> repeatedCalendarList = repeatedCalendarRepository.findAllByCarUserUsernameAndCarIdAndType(username, carId, RepeatedCalendarType.PRICE);

        return repeatedCalendarList.stream().map(i ->
                CustomPriceResponse.builder()
                        .id(i.getId())
                        .carId(i.getCar().getId())
                        .startDate(i.getStartDate())
                        .endDate(i.getEndDate())
                        .value(i.getValue())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public PriceRepeatedCalendarResponse findCustomPriceByDateRange(Long carId, Date startDate, Date endDate) throws Exception {
        Optional<CarEntity> car = carService.findById(carId);
        if (!car.isPresent()) throw new Exception(ErrorMessage.NO_CAR_WAS_FOUND);

        List<DateValueResponse> priceList;

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DateValueResponse> query = cb.createQuery(DateValueResponse.class);
        Root<RepeatedCalendarEntity> root = query.from(RepeatedCalendarEntity.class);

//        List<RepeatedCalendarEntity> repeatedCalendarList = repeatedCalendarRepository.findAllByCarIdAndTypeAndStartDateOrEndDateGreaterThanEqual(carId,
//                RepeatedCalendarType.PRICE, startDate, endDate);

        Join<CarEntity, RepeatedCalendarEntity> carEntityRepeatedCalendarEntityJoin = root.join("car");
        query.where(
                cb.equal(root.get("car").get("id"), carId),
                cb.and(
                        cb.or(
                                cb.greaterThanOrEqualTo(root.get("startDate"), startDate),
                                cb.greaterThanOrEqualTo(root.get("endDate"), endDate)
                        ))
        );
        query.multiselect(
                root.get("startDate"),
                root.get("value")
        );

        TypedQuery<DateValueResponse> typedQuery = entityManager.createQuery(query);
        priceList = (typedQuery.getResultList());

        BigInteger defaultPrice = car.get().getService().getDefaultPrice();

//        if (repeatedCalendarList.size() > 0) {
//            for (RepeatedCalendarEntity calendar : repeatedCalendarList) {
//                priceList.add(DateValueResponse.builder()
//                        .date(calendar.getStartDate())
//                        .value(calendar.getValue())
//                        .build());
//            }
//        }

        return PriceRepeatedCalendarResponse.builder()
                .carId(car.get().getId())
                .priceByDays(priceList)
                .defaultPrice(defaultPrice)
                .build();
    }

    @Override
    public RepeatedCalendarDayResponse findByCarIdAndStartDate(RepeatedCalendarDayRequest request) throws Exception {
        RepeatedCalendarEntity repeatedCalendar = repeatedCalendarRepository.findFirstByCarIdAndTypeAndStartDateEquals(request.getCarId(),
                RepeatedCalendarType.PRICE, request.getStartDate());
        if (repeatedCalendar == null) throw new Exception(ErrorMessage.NO_DATA_WAS_FOUND);
        return RepeatedCalendarDayResponse.builder()
                .id(repeatedCalendar.getId())
                .carId(repeatedCalendar.getCar().getId())
                .startDate(repeatedCalendar.getStartDate())
                .endDate(repeatedCalendar.getEndDate())
                .value(repeatedCalendar.getValue())
                .type(repeatedCalendar.getType())
                .build();
    }
}
