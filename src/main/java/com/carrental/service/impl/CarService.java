package com.carrental.service.impl;

import com.carrental.entity.*;
import com.carrental.enums.CarStatus;
import com.carrental.enums.UserStatus;
import com.carrental.repository.ICarRepository;
import com.carrental.requestmodel.CarRegisterRequest;
import com.carrental.requestmodel.ExtraFeeRequest;
import com.carrental.responsemodel.CarRegisterResponse;
import com.carrental.responsemodel.RegisteredCarResponse;
import com.carrental.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarService implements ICarService {
    @Autowired
    private ICarRepository carRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IServiceTypeService serviceTypeService;
    @Autowired
    private IModelService modelService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private IFeatureService featureService;
    @Autowired
    private ModelMapper mapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String findByPlate(String plate) {
        List<CarEntity> list = carRepository.findByPlate(plate);
        if (list.size() < 1) return "";
        return list.get(0).getPlate();
    }

    @Override
    @Transactional
    public CarRegisterResponse registerNewCar(CarRegisterRequest request) throws Exception {
        String existedPlate = findByPlate(request.getPlate());
        if (!existedPlate.isEmpty()) throw new Exception("Biển số xe đã được đăng ký trên hệ thống vui lòng thử lại !");
        UserEntity user = userService.findByUsername(request.getUsername());
        if (null == user || user.getStatus() != UserStatus.ACTIVATED)
            throw new Exception("Không tìm thấy người dùng hợp lệ !");

        ServiceTypeEntity serviceType = serviceTypeService.findById(request.getServiceTypeId());

        ServiceFeeEntity serviceFee = ServiceFeeEntity.builder()
                .serviceType(serviceType)
                .defaultPrice(request.getDefaultPrice())
                .discountByWeek(request.getDiscountByWeek())
                .discountByMonth(request.getDiscountByMonth())
                .build();

        List<ExtraFeeEntity> extraFees = request.getExtraFees().stream().map(
                i -> ExtraFeeEntity.builder()
                        .name(i.getName())
                        .limitValue(i.getLimit())
                        .unit(i.getUnit())
                        .fee(i.getFee())
                        .serviceFee(serviceFee)
                        .build()
        ).collect(Collectors.toList());

        serviceFee.setExtraFeeList(extraFees);

        DeliveryAddressEntity address = DeliveryAddressEntity.builder()
                .addressName(request.getAddressName())
                .build();

        CarEntity carEntity = CarEntity.builder()
                .plate(request.getPlate())
                .description(request.getDescription())
                .yearOfManufacture(request.getYearOfManufacture())
                .seats(request.getSeats())
                .color(request.getColor())
                .fuel(request.getFuel())
                .fuelConsumption(request.getFuelConsumption())
                .transmission(request.getTransmission())
                .status(CarStatus.PENDING_APPROVAL)
                .isFastRent(request.getIsFastRent())
                .service(serviceFee)
                .model(modelService.findById(request.getModelId()))
                .brand(brandService.findById(request.getBrandId()))
                .address(address)
                .policies(request.getPolicies())
                .user(user)
                .build();
        carEntity.setImages(request.getImagesList().stream().map(i ->
                CarImagesEntity.builder()
                        .imageUrl(i)
                        .isThumbnail(false)
                        .status("ACTIVE")
                        .car(carEntity)
                        .build()
        ).collect(Collectors.toList()));
        List<FeatureEntity> features = featureService.findAllByIdIn(request.getFeatureList());
        for (int i = 0; i < features.size(); i++) {
            FeatureEntity feat = features.get(i);
            feat.setCars(carEntity);
            features.set(i, feat);
        }
        carEntity.setFeatures(features);
        CarEntity savedCar = carRepository.save(carEntity);
        return CarRegisterResponse.builder()
                .id(savedCar.getId())
                .username(savedCar.getUser().getUsername())
                .plate(savedCar.getPlate())
                .modelId(savedCar.getModel().getId())
                .brandId(savedCar.getBrand().getId())
                .seats(savedCar.getSeats())
                .fuel(savedCar.getFuel())
                .color(savedCar.getColor())
                .fuelConsumption(savedCar.getFuelConsumption())
                .transmission(savedCar.getTransmission())
                .status(savedCar.getStatus())
                .description(savedCar.getDescription())
                .yearOfManufacture(savedCar.getYearOfManufacture())
                .featureList(savedCar.getFeatures().stream().map(BaseEntity::getId).collect(Collectors.toList()))
                .defaultPrice(savedCar.getService().getDefaultPrice())
                .discountByWeek(savedCar.getService().getDiscountByWeek())
                .discountByMonth(savedCar.getService().getDiscountByMonth())
                .isFastRent(savedCar.getIsFastRent())
                .addressName(savedCar.getAddress().getAddressName())
                .extraFees(savedCar.getService().getExtraFeeList().stream().map(
                        i -> mapper.map(i, ExtraFeeRequest.class)).collect(Collectors.toList()))
                .serviceTypeId(savedCar.getService().getId())
                .policies(savedCar.getPolicies())
                .imagesList(savedCar.getImages().stream().map(CarImagesEntity::getImageUrl).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Set<RegisteredCarResponse> findAllRegisteredCar(String username) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RegisteredCarResponse> query = cb.createQuery(RegisteredCarResponse.class);
        Root<CarEntity> root = query.from(CarEntity.class);

        //car rating join
        Join<CarEntity, CarRatingEntity> carRatingJoin = root.join("ratings", JoinType.LEFT);

        //car rental join
        Join<CarEntity, CarRentalEntity> carRentalEntityJoin = root.join("rentals", JoinType.LEFT);
        Expression<Long> countRental = cb.count(carRentalEntityJoin);

        //car images join
        Join<CarEntity, CarImagesEntity> imageJoin = root.join("images", JoinType.LEFT);
        imageJoin.on(
                cb.and(
                        cb.equal(imageJoin.get("car"), root),
                        cb.isTrue(imageJoin.get("isThumbnail"))
                )
        );

        query.multiselect(
                root.get("id"),
                root.get("model").get("name"),
                root.get("service").get("defaultPrice"),
                root.get("status"),
                cb.coalesce(countRental, 0),
                cb.coalesce(cb.avg(carRatingJoin.get("rating")), 0),// return 0 if there is no rating,
                imageJoin.get("imageUrl")
        ).distinct(true);

        query.where(cb.equal(root.get("user").get("username"), username));
        query.groupBy(root.get("id"), imageJoin.get("car").get("id"), imageJoin.get("imageUrl"));

        TypedQuery<RegisteredCarResponse> typedQuery = entityManager.createQuery(query);
        return new HashSet<>(typedQuery.getResultList());
    }
}
