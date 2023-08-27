package org.amadeus.service;

import org.amadeus.dto.flight.FlightFilterModel;
import org.amadeus.dto.flight.FlightModel;
import org.amadeus.dto.flight.FlightPersistModel;
import org.amadeus.dto.flight.FlightUpdateModel;

import java.util.List;

public interface FlightService {
    void create(FlightPersistModel flightPersistModel);

    void update(FlightUpdateModel flightPersistModel);

    void delete(Long id);

    List<FlightModel> getAll();

    FlightModel getByFlightId(Long id);

    FlightModel getDeparturesByAirportAndByDate(FlightFilterModel model);
}
