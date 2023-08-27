package org.amadeus.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.amadeus.dto.address.AddressModel;
import org.amadeus.data.GenderEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserDetailModel {

    @NotBlank
    @Size(min = 2,max = 25)
    private String firstName;

    @NotBlank
    @Size(min = 2,max = 25)
    private String lastName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDay;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private AddressModel address;

    private UserModel user;

    private String imagePath;

}
