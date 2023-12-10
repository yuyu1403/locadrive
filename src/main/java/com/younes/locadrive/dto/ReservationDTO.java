package com.younes.locadrive.dto;

import com.younes.locadrive.model.enums.VehicleCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {

    private Integer reservationId;
    private LocalDate reservationStart;
    private LocalDate reservationEnd;
    private BigDecimal totalCost;

    // Client details
    private Integer userId;
    private String name;
    private String surname;
    private String email;

    // Vehicle details
    private Integer vehicleId;
    private String brand;
    private String model;
    private VehicleCategory category;

}
