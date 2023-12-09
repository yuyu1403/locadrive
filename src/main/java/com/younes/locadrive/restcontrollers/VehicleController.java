package com.younes.locadrive.restcontrollers;

import com.younes.locadrive.model.Vehicle;
import com.younes.locadrive.model.enums.VehicleCategory;
import com.younes.locadrive.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {

        this.vehicleService = vehicleService;
    }

                                                //**** CREATE METHODS ****

    //Adding a vehicle to stock:
    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        Vehicle newVehicle = vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok(newVehicle);
    }


                                                    //**** READ METHODS ****

    //Get one vehicle by id:
    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Integer vehicleId) {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        return ResponseEntity.ok(vehicle);
    }
    //Get all the vehicles in stock:
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    //Get vehicles by category:
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Vehicle>> findVehiclesByCategory(@PathVariable VehicleCategory category) {
        List<Vehicle> vehicles = vehicleService.findVehiclesByCategory(category);
        return ResponseEntity.ok(vehicles);
    }

    //Get available vehicles:
//    @GetMapping("/available")
//    public ResponseEntity<List<Vehicle>> findAvailableVehicles(
//            @RequestParam LocalDate start,
//            @RequestParam LocalDate end) {
//        List<Vehicle> vehicles = vehicleService.findAvailableVehicles(start, end);
//        return ResponseEntity.ok(vehicles);
//    }

    //Get available vehicles by category:
//    @GetMapping("/available/category/{category}")
//    public ResponseEntity<List<Vehicle>> findAvailableVehiclesByCategory(
//            @PathVariable VehicleCategory category,
//            @RequestParam LocalDate start,
//            @RequestParam LocalDate end) {
//        List<Vehicle> vehicles = vehicleService.findAvailableVehiclesByCategory(category, start, end);
//        return ResponseEntity.ok(vehicles);
//    }

                                                //**** UPDATE METHODS ****

    // Update the operational status of a vehicle when returning from rental:
    @PutMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> updateVehicleStatus(@PathVariable Integer vehicleId,
                                                       @RequestBody Vehicle vehicle) {
        Vehicle updatedVehicle = vehicleService.updateVehicleStatus(
                vehicleId, vehicle.getIsTankOk(), vehicle.getIsConform(),
                vehicle.getMileage(), vehicle.getServiceDate());
        return ResponseEntity.ok(updatedVehicle);
    }

                                                //**** DELETE METHODS ****

    //Remove vehicle from stock:
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> removeVehicle(@PathVariable Integer vehicleId) {
        vehicleService.removeVehicle(vehicleId);
        return ResponseEntity.ok().build();
    }

}
