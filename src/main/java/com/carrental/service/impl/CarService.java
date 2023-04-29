package com.carrental.service.impl;

import com.carrental.entity.*;
import com.carrental.repository.ICarRepository;
import com.carrental.requestmodel.CarRegisterRequest;
import com.carrental.requestmodel.ExtraFeeRequest;
import com.carrental.responsemodel.CarRegisterResponse;
import com.carrental.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarService implements ICarService {
    @Autowired
    private ICarRepository repository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IServiceTypeService serviceTypeService;
    @Autowired
    private IServiceFeeService serviceFeeService;
    @Autowired
    private IExtraFeeService extraFeeService;
    @Autowired
    private IModelService modelService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private IFeatureService featureService;
    @Autowired
    private ModelMapper mapper;


    @Override
    @Transactional
    public String findByPlate(String plate) {
        List<CarEntity> list = repository.findByPlate(plate);
        if (list.size() < 1) return "";
        return list.get(0).getPlate();
    }

    @Override
    @Transactional
    public CarRegisterResponse registerNewCar(CarRegisterRequest request) throws Exception {
        String existedPlate = findByPlate(request.getPlate());
        if (!existedPlate.isEmpty()) throw new Exception("Biển số xe đã được đăng ký trên hệ thống vui lòng thử lại !");
        UserEntity user = userService.findByUsername(request.getUsername());
        if (null == user) throw new Exception("Không tìm thấy người dùng hợp lệ !");

        ServiceTypeEntity serviceType = serviceTypeService.findById(request.getServiceTypeId());

        ServiceFeeEntity serviceFee = ServiceFeeEntity.builder()
                .serviceType(serviceType)
                .defaultPrice(request.getDefaultPrice())
                .discountByWeek(request.getDiscountByWeek())
                .discountByMonth(request.getDiscountByMonth())
//                .extraFeeList(extraFees)
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
//        ServiceFeeEntity savedServiceFee = serviceFeeService.save(serviceFee);

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
                .rentalStatus("PENDING_APPROVAL")
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
        CarEntity savedCar = repository.save(carEntity);
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
                .rentalStatus(savedCar.getRentalStatus())
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
}
