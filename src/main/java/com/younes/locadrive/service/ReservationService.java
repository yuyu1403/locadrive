package com.younes.locadrive.service;

import com.younes.locadrive.model.Reservation;
import com.younes.locadrive.model.Utilisateur;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    //Creating a new reservation:
    Reservation createReservation(Reservation reservation);

    //Deleting a reservation:
    //ʘ‿ʘ: updating a reservation => deleting and recreating.
    void deleteReservation(Integer reservationId);

    //Getting a reservation by id:
    Reservation getReservationById(Integer reservationId);

    //Getting all the reservations:
    List<Reservation> getAllReservations();

    //Getting all reservations for a client:
    List<Reservation> getReservationsByClient(Utilisateur utilisateur); //??????????????????????????????????

    //Getting all reservations by start date
    List<Reservation> getReservationsByStartDate(LocalDate reservationStart);

    //Getting all reservations by end date
    List<Reservation> getReservationsByEndDate(LocalDate reservationEnd);

    //Checking availability of a vehicle for a given date range:
    boolean isVehicleAvailableForDates(Integer vehicleId, LocalDate reservationStart, LocalDate reservationEnd);
}
