package com.younes.locadrive.service;

import com.younes.locadrive.dto.ReservationCreateDTO;
import com.younes.locadrive.dto.ReservationDTO;
import com.younes.locadrive.model.Reservation;
import com.younes.locadrive.model.Utilisateur;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    // Creating a new reservation:
    ReservationDTO createReservation(ReservationCreateDTO reservationCreateDTO);

    // Deleting a reservation:
    void deleteReservation(Integer reservationId);

    // Getting a reservation by id:
    ReservationDTO getReservationById(Integer reservationId);

    // Getting all the reservations:
    List<ReservationDTO> getAllReservations();

    // Getting all reservations for a client:
//    List<ReservationDTO> getReservationsByClient(Integer userId);

    // Getting all reservations by start date:
    List<ReservationDTO> getReservationsByStartDate(LocalDate reservationStart);

    // Getting all reservations by end date:
    List<ReservationDTO> getReservationsByEndDate(LocalDate reservationEnd);

    // Checking availability of a vehicle for a given date range:
    boolean isVehicleAvailableForDates(Integer vehicleId, LocalDate reservationStart, LocalDate reservationEnd);
}
