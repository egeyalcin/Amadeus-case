package org.amadeus.entity.user.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.amadeus.entity.user.BaseEntity;
import org.amadeus.entity.user.info.UserInformationEntity;
import org.amadeus.data.CountryEnum;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "address")
public class AddressEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private CountryEnum country;

    private String city;

    private String town;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserInformationEntity userInformation;


}
