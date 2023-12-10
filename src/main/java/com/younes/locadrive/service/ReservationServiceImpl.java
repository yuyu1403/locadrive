package com.younes.locadrive.service;

import com.younes.locadrive.dto.ReservationCreateDTO;
import com.younes.locadrive.dto.ReservationDTO;
import com.younes.locadrive.model.Reservation;
import com.younes.locadrive.model.Utilisateur;
import com.younes.locadrive.model.Vehicle;
import com.younes.locadrive.repos.ReservationRepository;
import com.younes.locadrive.repos.UtilisateurRepository;
import com.younes.locadrive.repos.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, UtilisateurRepository utilisateurRepository, VehicleRepository vehicleRepository){
        this.reservationRepository = reservationRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.vehicleRepository = vehicleRepository;
    }

    //PROBLEM: for the moment it possible to make duplicate reservations!!!
    @Override
    public ReservationDTO createReservation(ReservationCreateDTO reservationCreateDTO) {

        Reservation reservation = convertReservationCreateDtoToEntity(reservationCreateDTO);
        Reservation savedReservation = reservationRepository.save(reservation);
        return convertToReservationDto(savedReservation);
    }


    @Override
    public void deleteReservation(Integer reservationId) {
        reservationRepository.deleteById(reservationId);

    }

    @Override
    public ReservationDTO getReservationById(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with Id: " + reservationId));
        return convertToReservationDto(reservation);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(this::convertToReservationDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<ReservationDTO> getReservationsByClient(Integer userId) {
//        Utilisateur utilisateur = utilisateurRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
//        List<Reservation> reservations = reservationRepository.findByUtilisateur(utilisateur);
//        return reservations.stream()
//                .map(this::convertToReservationDto)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<ReservationDTO> getReservationsByStartDate(LocalDate reservationStart) {
        List<Reservation> reservations = reservationRepository.findByReservationStart(reservationStart);
        return reservations.stream()
                .map(this::convertToReservationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getReservationsByEndDate(LocalDate reservationEnd) {
        List<Reservation> reservations = reservationRepository.findByReservationEnd(reservationEnd);
        return reservations.stream()
                .map(this::convertToReservationDto)
                .collect(Collectors.toList());
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


    //------------------------------------------- DTO conversions -----------------------------------------------

    // Conversion methods for ReservationDTO:
    public ReservationDTO convertToReservationDto(Reservation reservation) {
        Utilisateur utilisateur = reservation.getUtilisateur();
        Vehicle vehicle = reservation.getVehicle();

        return ReservationDTO.builder()
                .reservationId(reservation.getReservationId())
                .reservationStart(reservation.getReservationStart())
                .reservationEnd(reservation.getReservationEnd())
                .totalCost(reservation.getTotalCost())
                .userId(utilisateur.getUserId())
                .name(utilisateur.getName())
                .surname(utilisateur.getSurname())
                .email(utilisateur.getEmail())
                .vehicleId(vehicle.getVehicleId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .category(vehicle.getCategory())
                .build();
    }

    public Reservation convertReservationDtoToEntity(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setReservationStart(reservationDTO.getReservationStart());
        reservation.setReservationEnd(reservationDTO.getReservationEnd());
        reservation.setTotalCost(reservationDTO.getTotalCost());

        if (reservationDTO.getReservationId() != null) {
            reservation.setReservationId(reservationDTO.getReservationId());
        }

        // Fetch Utilisateur and Vehicle from the DB
        Utilisateur utilisateur = utilisateurRepository.findById(reservationDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        reservation.setUtilisateur(utilisateur);

        Vehicle vehicle = vehicleRepository.findById(reservationDTO.getVehicleId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid vehicle ID"));
        reservation.setVehicle(vehicle);

        return reservation;
    }

    // Conversion method for ReservationCreateDTO:

    public Reservation convertReservationCreateDtoToEntity(ReservationCreateDTO reservationCreateDTO) {
        Vehicle vehicle = vehicleRepository.findById(reservationCreateDTO.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + reservationCreateDTO.getVehicleId()));

        Utilisateur utilisateur = utilisateurRepository.findById(reservationCreateDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + reservationCreateDTO.getUserId()));

        Reservation reservation = new Reservation();
        reservation.setReservationStart(reservationCreateDTO.getReservationStart());
        reservation.setReservationEnd(reservationCreateDTO.getReservationEnd());
        reservation.setTotalCost(reservationCreateDTO.getTotalCost());
        reservation.setVehicle(vehicle);
        reservation.setUtilisateur(utilisateur);

        return reservation;
    }

}


