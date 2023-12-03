package com.younes.locadrive.service;

import com.younes.locadrive.model.Vehicle;
import com.younes.locadrive.model.enums.VehicleCategory;
import com.younes.locadrive.repos.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service //indicates that the class is a Spring service => auto-managed by Spring's application context
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    //declares the dependency of VehicleServiceIMpl on the VehicleRepository.
    //private final = cannot be reassigned during the lifetime of the object.
    private final ReservationService reservationService;
    //(•̀ᴗ•́)و in Spring Boot when we inject an interface, Spring's dependency injection container automatically
    //knows how to provide the appropriate implementation at runtime(=>IOC)
    //this is important because it decouples VehicleServiceImpl from ReservationServiceImpl, and from possible
    //code changes in the future.

    @Autowired //enables dependency injection.
    // auto-injects an instance of VehicleRepository when creating an instance of VehicleServiceImpl.
    public  VehicleServiceImpl(VehicleRepository vehicleRepository, ReservationService reservationService){
        this.vehicleRepository = vehicleRepository;
        //assigns the injected VehicleRepository to the field vehicleRepository.
        //This reference to VehicleRepository enables VehicleServiceImpl to interact with the DB.
        this.reservationService = reservationService;
    }
    //Constructor injection in Spring is a technique where dependencies of a class are provided through its
    //constructor, ensuring all the necessary dependencies are set at the creation of the object.
    //Benefits: immutability, ease of testing(mocking), avoids NullReferences...

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicleStatus(Integer vehicleId, Boolean isTankOk, Boolean isConform, Integer mileage, LocalDate serviceDate) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with Id" + vehicleId));
        //findById method returns an Optional<Vehicle>, which is a container object that can contain or not a value.
        //Optional is a good way to handle the possibility of null values compared ...
        //the orElseThrow method within Optional throws an exception specified by the lambda expression (anonymous in JS).
        vehicle.setIsTankOk(isTankOk);
        vehicle.setIsConform(isConform);
        vehicle.setMileage(mileage);
        vehicle.setServiceDate(serviceDate);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void removeVehicle(Integer vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public Vehicle getVehicleById(Integer vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with Id" + vehicleId));
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> findVehiclesByCategory(VehicleCategory category) {
        return vehicleRepository.findByCategory(category);
    }

    @Override
    public List<Vehicle> findAvailableVehicles(LocalDate reservationStart, LocalDate reservationEnd) {
        List<Vehicle> availableVehicles = vehicleRepository
                .findVehiclesByIsConformTrueAndServiceDateAfter(reservationEnd);

        return availableVehicles.stream()
                .filter(vehicle -> reservationService
                        .isVehicleAvailableForDates(vehicle.getVehicleId(), reservationStart, reservationEnd))
                .collect(Collectors.toList());
    }
    //1st step: we fetch a list of vehicles which are conform and which don't have to be serviced.
    //2nd step: the availableVehicles list is converted in a stream.
    //[.stream() is a method which is called on a collection (List here) and returns a sequence of the elements
    //of the collection.
    //the StreamAPI allows for various operations on streams (filter, map, reduce, forEach...)
    //3rd step: we use .filter() method to keep only the elements that match .isVehicleAvailableForDates defined in
    //the ReservationServiceImpl.
    //Last step: the .collect() method ... This method is used with Collectors which include methods like:
    //toList(), toSet(), toMap()...
    //In brief STREAM => PROCESS => COLLECT

    @Override
    public List<Vehicle> findAvailableVehiclesByCategory(VehicleCategory category, LocalDate reservationStart, LocalDate reservationEnd) {
        List<Vehicle> availableVehicles = vehicleRepository
                .findVehiclesByCategoryAndIsConformTrueAndServiceDateAfter(category, reservationEnd);

        return availableVehicles.stream()
                .filter(vehicle -> reservationService
                        .isVehicleAvailableForDates(vehicle.getVehicleId(), reservationStart, reservationEnd))
                .collect(Collectors.toList());
    }

}



