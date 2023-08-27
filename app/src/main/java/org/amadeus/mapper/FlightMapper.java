package org.amadeus.mapper;


import org.amadeus.dto.flight.FlightModel;
import org.amadeus.dto.flight.FlightPersistModel;
import org.amadeus.dto.flight.FlightUpdateModel;
import org.amadeus.entity.FlightsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    @Mapping(source = "departureAirportId", target = "departureAirportId.id")
    @Mapping(source = "landingAirportId", target = "landingAirportId.id")
    FlightsEntity convertToEntity(FlightPersistModel flightPersistModel);

    @Mapping(source = "departureAirportId", target = "departureAirportId.id")
    @Mapping(source = "landingAirportId", target = "landingAirportId.id")
    List<FlightsEntity> convertToEntityList(List<FlightPersistModel> flightPersistModelList);

    @Mapping(source = "departureAirportId", target = "departureAirportId.id")
    @Mapping(source = "landingAirportId", target = "landingAirportId.id")
    FlightsEntity convertToEntityFromUpdateModel(FlightUpdateModel flightUpdateModel);

    @Mapping(source = "departureAirportId", target = "departureAirportId.id")
    @Mapping(source = "landingAirportId", target = "landingAirportId.id")
    List<FlightsEntity> convertToEntityFromUpdateModel(List<FlightUpdateModel> flightUpdateModelList);

    @Mapping(source = "departureAirportId.id", target = "departureAirportId")
    @Mapping(source = "landingAirportId.id", target = "landingAirportId")
    FlightModel convertToModel(FlightsEntity flightsEntity);

    @Mapping(source = "departureAirportId.id", target = "departureAirportId")
    @Mapping(source = "landingAirportId.id", target = "landingAirportId")
    List<FlightModel> convertToModelList(List<FlightsEntity> flightsEntityList);

}
