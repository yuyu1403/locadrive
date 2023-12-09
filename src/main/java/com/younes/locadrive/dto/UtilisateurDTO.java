package com.younes.locadrive.dto;

import com.younes.locadrive.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //allows the use of the design pattern builder of Lombok
public class UtilisateurDTO {
    private Integer userId;
    private String surname;
    private String name;
    private String email;
    private String address;
    private String phone;
    private UserRole role;
}
