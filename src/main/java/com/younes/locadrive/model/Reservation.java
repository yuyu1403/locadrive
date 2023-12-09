package com.younes.locadrive.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private Integer reservationId;

    @Column(name = "res_start", nullable = false)
    private LocalDate reservationStart;

    @Column(name = "res_end", nullable = false)
    private LocalDate reservationEnd;

    @Column(name = "res_total", nullable = false, precision = 6, scale = 2)
    private BigDecimal totalCost;

    // Foreign key from Vehicle
    @ManyToOne
    @JoinColumn(name = "veh_id", nullable = false)
    private Vehicle vehicle;

    // Foreign key from Utilisateur
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Utilisateur utilisateur;


}
