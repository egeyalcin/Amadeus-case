package org.amadeus.dto.address;

import lombok.Data;
import org.amadeus.data.CountryEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class AddressModel {

    @Enumerated(EnumType.STRING)
    private CountryEnum country;

    private String city;

    private String town;

}
