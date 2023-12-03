package com.younes.locadrive.repos;

import com.younes.locadrive.model.Vehicle;
import com.younes.locadrive.model.enums.FuelType;
import com.younes.locadrive.model.enums.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// Spring repository is a Java interface that extends JpaRepository interface.
// This interface has all the needed methods to be able to work with data (save, findById...)
// Entities and their corresponding repositories work in pairs to form the application's data layer
// kind of like pairing sql tables and their corresponding stored procedures

@Repository
public interface VehicleRepository extends JpaRepository <Vehicle, Integer> {
    //Parameters: Entity, IDType
    // when leaving the interface empty this means we inherit all the methods provided by JpaRepository
    // if needed we can add custom methods here.

    // ♥‿♥:
    // if some more custom methods are needed, Spring Data JPA analyzes the method name and creates
    //a query that matches the criteria defined by the method name (see below). The possible queries are
    //suggested by IntelliJ autocompletion.
    //This spares us the use of @Query/JPQL queries.
    List<Vehicle> findByCategory(VehicleCategory category);
    List<Vehicle> findByFuelType(FuelType fuelType);
    List<Vehicle> findVehiclesByIsConformTrueAndServiceDateAfter(LocalDate currentDate);
    List<Vehicle> findVehiclesByCategoryAndIsConformTrueAndServiceDateAfter(VehicleCategory category, LocalDate currentDate);
    // for example, with JPQL, this query would have been:
    //@Query("SELECT v FROM Vehicle v WHERE v.category = :category AND v.isConform = true AND v.serviceDate > :currentDate")
}
