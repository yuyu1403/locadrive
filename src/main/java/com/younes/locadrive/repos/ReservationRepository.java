package com.younes.locadrive.repos;

import com.younes.locadrive.model.Reservation;
import com.younes.locadrive.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// Spring repository is a Java interface that extends JpaRepository interface.
// This interface has all the needed methods to be able to work with data (save, findById...)
// Entities and their corresponding repositories work in pairs to form the application's data layer
// kind of like pairing sql tables and their corresponding stored procedures

@Repository
public interface ReservationRepository extends JpaRepository <Reservation, Integer> {
    //Parameters: Entity, IDType
    // when leaving the interface empty this means we inherit all the methods provided by JpaRepository
    // if needed we can add custom methods here.

    List<Reservation> findByUtilisateur(Utilisateur utilisateur);

    List<Reservation> findByReservationStart(LocalDate reservationStart);

    List<Reservation> findByReservationEnd(LocalDate reservationEnd);

    boolean existsByVehicle_VehicleIdAndReservationStartLessThanEqualAndReservationEndGreaterThanEqual(Integer vehicleId, LocalDate endDate, LocalDate startDate);
// ಥ﹏ಥ:
    // this method is intended to determine if there is a reservation for a vehicle for a specific time range:
    // existsByVehicle_VehicleId: this query targets vehicleId within the Vehicle object, which has a relationship with reservation.
    // AndReservationStartLessThanEqual: includes reservations which start on/before a specific date.
    // AndReservationEndGreaterThanEqual: includes reservations which end on/after a specific date.
}