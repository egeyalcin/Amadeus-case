package org.amadeus.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.amadeus.entity.airport.AirportEntity;
import org.amadeus.entity.user.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "flights")
public class FlightsEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "departure_airport_id", referencedColumnName = "id")
    private AirportEntity departureAirportId;

    @OneToOne
    @JoinColumn(name = "landing_airport_id", referencedColumnName = "id")
    private AirportEntity landingAirportId;

    private Date departureDate;

    private Date landingDate;

    private int price;

}
