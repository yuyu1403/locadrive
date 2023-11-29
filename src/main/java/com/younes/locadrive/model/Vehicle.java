package com.younes.locadrive.model;

import com.younes.locadrive.model.enums.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "VEHICLE")
public class Vehicle {

    @Id //marks the field as the primary key of the entity (every JPA entity needs one)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // the id is auto-incremented by the DB itself (IDENTITY)
    @Column(name = "veh_id")
    private Integer vehicleId;

    @Column(name = "veh_num", unique = true, nullable = false, length=9)
    private String numPlate;

    @Column(name = "veh_brand", nullable = false, length=20)
    private String brand;

    @Column(name = "veh_model", nullable = false, length=50)
    private String model;

    @Enumerated(EnumType.STRING) //This is a common practice to store Enums as Strings in the DB. Did not have looked in depth why.
    @Column(name = "veh_cat", nullable = false)
    private VehicleCategory category;
    // Creating an Enum allows me to keep the type safety intended when creating the DB.
    // I could have changed the Enum to a String but I would have lost the type safety.
    // For String there is no compile-time check to ensure that only correct values are used.

    @Column(name = "veh_daily_price", nullable = false, precision = 6, scale = 2)
    //precision = total digits of the number, scale = digits after decimal
    private BigDecimal dailyPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "veh_fuel", nullable = false)
    private FuelType fuelType;

    @Column(name = "veh_mileage", nullable = false)
    private Integer Mileage;

    @Enumerated(EnumType.STRING)
    @Column(name = "veh_transmission", nullable = false)
    private TransmissionType transmissionType;

    @Column(name = "veh_conform", nullable = false)
    private Boolean isConform;

    @Column(name = "veh_tank_ok", nullable = false)
    private Boolean isTankOk;

    @Column(name = "veh_service", nullable = false)
    @Temporal(TemporalType.DATE)
    // specifies the type of temporal data (data, time, timestamp)
    // indicates that only the date part of the date-time value should be persisted
    private Date serviceDate;
}

// all the variable are private, meaning that the class is the only only able to modify its fields.
// this is standard practice in entity classes using JPA/ORMs
