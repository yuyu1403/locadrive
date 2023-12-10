package com.younes.locadrive.dto;

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
public class ReservationCreateDTO {
    private LocalDate reservationStart;
    private LocalDate reservationEnd;
    private BigDecimal totalCost; //logic on client side => not ideal but easier for the moment
    private Integer vehicleId;
    private Integer userId;
}
