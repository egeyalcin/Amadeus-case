package org.amadeus.dto.user;


import lombok.Data;
import org.amadeus.data.GenderEnum;

import java.util.Date;

@Data
public class UserByTokenModel {

    private String email;

    private String firstName;

    private String lastName;

    private Date birthDay;

    private GenderEnum gender;

    private String imagePath;

}
