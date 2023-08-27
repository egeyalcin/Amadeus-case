package org.amadeus.repository.flight;

import liquibase.pro.packaged.L;
import org.amadeus.dto.flight.FlightModel;
import org.amadeus.entity.FlightsEntity;
import org.amadeus.entity.airport.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<FlightsEntity, Long> {

    FlightsEntity findByDepartureAirportIdAndLandingAirportIdAndDepartureDateAndLandingDate(AirportEntity departureAirportId, AirportEntity landingAirportId, Date departureDate, Date landingDate);

    FlightsEntity findByDepartureAirportIdAndLandingAirportIdAndDepartureDate(AirportEntity departureAirportId, AirportEntity landingAirportId, Date departureDate);

}
