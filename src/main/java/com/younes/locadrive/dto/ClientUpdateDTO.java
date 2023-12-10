package com.younes.locadrive.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //allows the use of the design pattern builder of Lombok
public class ClientUpdateDTO {

    //Utilisateur entity fields:
    private Integer userId;
    private String surname;

    private String name;

    @Email(message = "Invalid email format") //checking at DTO level that the email format is ok.
    private String email;

    private String address;

    @Pattern(regexp = "^[+]?[0-9]{10,13}$", message = "Invalid phone number format")
    //this regex pattern checks that: + sign is allowed and that digits from 0 to 9 are allowed. Mini 10 char, Max 13.
    private String phone;

    private String password; //password modification will be done here for the moment but should later be done separately

    //Client entity fields:
    private String licenceNumber;

}
