package org.amadeus.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.amadeus.entity.user.UserEntity;
import org.amadeus.entity.user.address.AddressEntity;
import org.amadeus.data.GenderEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserInformationModel {

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

    @Enumerated(EnumType.STRING)
    private AddressEntity address;

    private UserEntity user;

    private String imagePath;

}
