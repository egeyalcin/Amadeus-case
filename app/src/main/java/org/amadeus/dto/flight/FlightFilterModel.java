package org.amadeus.dto.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.amadeus.entity.airport.AirportEntity;

import java.util.Date;

@Data
public class FlightFilterModel {

    private Long departureAirportId;

    private Long landingAirportId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date departureDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date landingDate;
}
