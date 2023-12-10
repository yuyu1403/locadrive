package com.younes.locadrive.restcontrollers;

import com.younes.locadrive.dto.ReservationCreateDTO;
import com.younes.locadrive.dto.ReservationDTO;
import com.younes.locadrive.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationCreateDTO reservationCreateDTO) {
        ReservationDTO reservationDTO = reservationService.createReservation(reservationCreateDTO);
        return ResponseEntity.ok(reservationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Integer id) {
        ReservationDTO reservationDTO = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservationDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/start/{startDate}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByStartDate(@PathVariable String startDate) {
        LocalDate date = LocalDate.parse(startDate);
        List<ReservationDTO> reservations = reservationService.getReservationsByStartDate(date);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/end/{endDate}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByEndDate(@PathVariable String endDate) {
        LocalDate date = LocalDate.parse(endDate);
        List<ReservationDTO> reservations = reservationService.getReservationsByEndDate(date);
        return ResponseEntity.ok(reservations);
    }

}

