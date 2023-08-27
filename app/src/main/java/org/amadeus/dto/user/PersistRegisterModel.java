package org.amadeus.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PersistRegisterModel {

    @NotBlank
    private String email;

    @NotBlank
    private String firstName;


    @NotBlank
    private String lastName;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    @Size(min = 8)
    private String passwordConfirmation;


}
