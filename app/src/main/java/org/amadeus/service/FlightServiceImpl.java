package org.amadeus.service;

import lombok.RequiredArgsConstructor;
import org.amadeus.dto.flight.FlightFilterModel;
import org.amadeus.dto.flight.FlightModel;
import org.amadeus.dto.flight.FlightPersistModel;
import org.amadeus.dto.flight.FlightUpdateModel;
import org.amadeus.entity.FlightsEntity;
import org.amadeus.entity.airport.AirportEntity;
import org.amadeus.exception.LocalizedException;
import org.amadeus.mapper.FlightMapper;
import org.amadeus.repository.airport.AirportRepository;
import org.amadeus.repository.flight.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService{

    private final FlightRepository flightRepository;

    private final FlightMapper flightMapper;

    private final AirportRepository airportRepository;

    @Override
    public void create(FlightPersistModel flightPersistModel) {
        FlightsEntity flightsEntity = flightMapper.convertToEntity(flightPersistModel);
        flightRepository.save(flightsEntity);
    }

    @Override
    public void update(FlightUpdateModel flightPersistModel) {
        FlightsEntity flightsEntity = flightRepository.findById(flightPersistModel.getId()).orElseThrow(() -> new LocalizedException("flight.is.not.exists"));
        FlightsEntity flightsMap = flightMapper.convertToEntityFromUpdateModel(flightPersistModel);
        flightsMap.setId(flightsEntity.getId());
        flightsMap.setUpdatedAt(flightsEntity.getUpdatedAt());
        flightsMap.setCreatedAt(flightsEntity.getCreatedAt());
        flightRepository.save(flightsMap);
    }

    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<FlightModel> getAll() {
        List<FlightsEntity> all = flightRepository.findAll();
        return flightMapper.convertToModelList(all);
    }

    @Override
    public FlightModel getByFlightId(Long id) {
        FlightsEntity flightsEntity = flightRepository.findById(id).orElseThrow(() -> new LocalizedException("flight.is.not.exists"));
        return flightMapper.convertToModel(flightsEntity);
    }


    @Override
    public FlightModel getDeparturesByAirportAndByDate(FlightFilterModel model) {
        if(model.getLandingDate() != null) {
            AirportEntity byDepartureAirportId = airportRepository.findById(model.getDepartureAirportId()).orElseThrow(() -> new LocalizedException("airport.is.not.exists"));
            AirportEntity landingAirport = airportRepository.findById(model.getLandingAirportId()).orElseThrow(() -> new LocalizedException("airport.is.not.exists"));
            FlightsEntity entity = flightRepository.
                    findByDepartureAirportIdAndLandingAirportIdAndDepartureDateAndLandingDate(
                            byDepartureAirportId,
                            landingAirport,
                            model.getDepartureDate(),
                            model.getLandingDate());
            return flightMapper.convertToModel(entity);
        } else {
            AirportEntity byDepartureAirportId = airportRepository.findById(model.getDepartureAirportId()).orElseThrow(() -> new LocalizedException("airport.is.not.exists"));
            AirportEntity landingAirport = airportRepository.findById(model.getLandingAirportId()).orElseThrow(() -> new LocalizedException("airport.is.not.exists"));
            FlightsEntity entity = flightRepository.findByDepartureAirportIdAndLandingAirportIdAndDepartureDate(
                    byDepartureAirportId,
                    landingAirport,
                    model.getDepartureDate());
            return flightMapper.convertToModel(entity);
        }

    }
}
