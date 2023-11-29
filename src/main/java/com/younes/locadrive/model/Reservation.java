package com.younes.locadrive.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private Integer reservationId;

    @Column(name = "res_start", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date reservationStart;

    @Column(name = "res_end", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date reservationEnd;

    @Column(name = "res_total", nullable = false, precision = 6, scale = 2)
    private BigDecimal totalCost;



}
