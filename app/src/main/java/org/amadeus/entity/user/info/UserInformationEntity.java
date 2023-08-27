package org.amadeus.entity.user.info;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.amadeus.entity.user.BaseEntity;
import org.amadeus.entity.user.UserEntity;
import org.amadeus.entity.user.address.AddressEntity;
import org.amadeus.data.GenderEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user_information")
public class UserInformationEntity extends BaseEntity {

    @NotBlank
    @Size(min = 2,max = 25)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2,max = 25)
    private String lastName;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDay;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private AddressEntity address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String imagePath;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;


}
