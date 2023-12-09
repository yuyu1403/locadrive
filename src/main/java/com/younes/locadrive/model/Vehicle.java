package com.younes.locadrive.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.younes.locadrive.model.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data //annotation to use Lombok getter setter generator
@NoArgsConstructor // annotation to use Lombok no argument constructor (required by JPA entities)
//No arg constructor makes a class a bean, which is one of the fundamental concepts in Spring.
// This allows IOC (Inversion of Control) which means that Spring takes over the control of instantiating
// and wiring objects together (instead of the developer)=> see dependency injection.
//After compiling the project, the result of Lombok generation is visible in the structure thumbnail
@AllArgsConstructor
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
    private Integer mileage;

    @Enumerated(EnumType.STRING)
    @Column(name = "veh_transmission", nullable = false)
    private TransmissionType transmissionType;

    @Column(name = "veh_conform", nullable = false)
    private Boolean isConform;

    @Column(name = "veh_tank_ok", nullable = false)
    private Boolean isTankOk;

    @Column(name = "veh_service", nullable = false)
    private LocalDate serviceDate;

    @OneToMany(mappedBy = "vehicle")
    @JsonIgnore
    private List<Reservation> reservations; //needs to be a container as we can store multiple reservations for one vehicle
}

// all the variable are private, meaning that the class is the only only able to modify its fields.
// this is standard practice in entity classes using JPA/ORMs
