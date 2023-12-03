package com.younes.locadrive;

import com.younes.locadrive.model.Vehicle;
import com.younes.locadrive.model.enums.VehicleCategory;
import com.younes.locadrive.model.enums.FuelType;
import com.younes.locadrive.model.enums.TransmissionType;
import com.younes.locadrive.repos.VehicleRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;


@SpringBootTest
class LocadriveApplicationTests {

//    // add a new vehicle
//    @Autowired
//    private VehicleRepository vehicleRepository;
//
//    @Test
//    public void testCreateVehicle() {
//
//        Vehicle vehicle = new Vehicle(
//                null, // vehicleId is auto-generated
//                "FB-777-XY",
//                "Peugeot",
//                "3008",
//                VehicleCategory.FAMILY,
//                new BigDecimal("50.00"),
//                FuelType.GO,
//                10000,
//                TransmissionType.M,
//                true,
//                true,
//                LocalDate.parse("2023-12-19")
//        );
//
//        vehicleRepository.save(vehicle);
//    }




}

