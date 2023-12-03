package com.younes.locadrive.service;

import com.younes.locadrive.model.Reservation;
import com.younes.locadrive.model.Utilisateur;
import com.younes.locadrive.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Integer reservationId) {
        reservationRepository.deleteById(reservationId);

    }

    @Override
    public Reservation getReservationById(Integer reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with Id: "));
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByClient(Utilisateur utilisateur) {
        return reservationRepository.findByUtilisateur(utilisateur);
    }

    @Override
    public List<Reservation> getReservationsByStartDate(LocalDate reservationStart) {
        return reservationRepository.findByReservationStart(reservationStart);
    }

    @Override
    public List<Reservation> getReservationsByEndDate(LocalDate reservationEnd) {
        return reservationRepository.findByReservationEnd(reservationEnd);
    }

    @Override
    public boolean isVehicleAvailableForDates(Integer vehicleId, LocalDate reservationStart, LocalDate reservationEnd) {
        return !reservationRepository
                .existsByVehicle_VehicleIdAndReservationStartLessThanEqualAndReservationEndGreaterThanEqual(vehicleId, reservationStart, reservationEnd);
    }
    //this method returns true if there is no overlapping reservation for a specific vehicle within
    //the reservation range dates and false otherwise.
    //ʘ‿ʘ personal note: contrary to the first implementation where I had to import vehicle repo, with the boolean
    //method we have access through the reservation to vehicleId as it serves as foreign key.

}
