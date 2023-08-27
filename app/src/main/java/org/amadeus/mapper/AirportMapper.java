package org.amadeus.mapper;

import org.amadeus.dto.airport.AirportModel;
import org.amadeus.dto.airport.AirportPersistModel;
import org.amadeus.entity.airport.AirportEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    AirportEntity converToEntity(AirportPersistModel airportPersistModel);

    List<AirportEntity> converToEntityList(List<AirportPersistModel> airportPersistModelList);

    AirportModel converToModel(AirportEntity airportEntity);

    List<AirportModel> converToModelList(List<AirportEntity> airportEntity);

}
