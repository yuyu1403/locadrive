package com.younes.locadrive.service;

import com.younes.locadrive.model.Vehicle;
import com.younes.locadrive.model.enums.VehicleCategory;

import java.time.LocalDate;
import java.util.List;

//The service interface and implementation interacts with the repository to perform operations related
// to vehicles.
public interface VehicleService {

    // adding a vehicle to the stock
    // updating a vehicle's information totally or partially (in particular:
    // return status: is tank ok? is conform?, service update?, mileage?...
    //getting the total vehicles in stock
    // getting a vehicle per id, brand, category, fuel type...
    // getting the available at date vehicles
    // removing a vehicle from the fleet

    //1. Inventory management:

    //adding a new vehicle to the fleet
    Vehicle addVehicle(Vehicle vehicle);

    //updating the operational status of the vehicle
    Vehicle updateVehicleStatus(Integer vehicleId, Boolean isTankOk, Boolean isConform, Integer mileage, LocalDate serviceDate);

    //removing a vehicle from the fleet
    void removeVehicle(Integer vehicleId);

    //2. Queries on vehicles:

    //get a vehicle by its Id:
    Vehicle getVehicleById(Integer vehicleId);

    // list all the vehicles available in stock:
    List<Vehicle> getAllVehicles();

    // list the vehicles according to their category:
    List<Vehicle> findVehiclesByCategory(VehicleCategory category);

    // get all the vehicles available on date
    List<Vehicle> findAvailableVehicles(LocalDate reservationStart, LocalDate reservationEnd);

    //get vehicles available by category on date
    List<Vehicle> findAvailableVehiclesByCategory(VehicleCategory category, LocalDate reservationStart, LocalDate reservationEnd);
}


