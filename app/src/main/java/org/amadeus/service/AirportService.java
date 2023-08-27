package org.amadeus.service;

import org.amadeus.dto.airport.AirportModel;
import org.amadeus.dto.airport.AirportPersistModel;
import org.amadeus.dto.airport.AirportUpdateModel;

import java.util.List;

public interface AirportService {
    void create(AirportPersistModel airportPersistModel);

    void update(AirportUpdateModel airportPersistModel);

    void delete(Long id);

    List<AirportModel> getAirportlist();

    AirportModel getAirportById(Long id);
}
