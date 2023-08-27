package org.amadeus.service;

import lombok.RequiredArgsConstructor;
import org.amadeus.dto.airport.AirportModel;
import org.amadeus.dto.airport.AirportPersistModel;
import org.amadeus.dto.airport.AirportUpdateModel;
import org.amadeus.entity.airport.AirportEntity;
import org.amadeus.exception.LocalizedException;
import org.amadeus.mapper.AirportMapper;
import org.amadeus.repository.airport.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    private final AirportMapper airportMapper;

    @Override
    public void create(AirportPersistModel airportPersistModel) {
        airportRepository.save(new AirportEntity(airportPersistModel.getCity()));
    }

    @Override
    public void update(AirportUpdateModel airportPersistModel) {
        AirportEntity airportEntity = airportRepository.findById(airportPersistModel.getId()).orElseThrow(() -> new LocalizedException("airport.is.not.exists"));
        airportEntity.setCity(airportPersistModel.getCity());
        airportRepository.save(airportEntity);
    }

    @Override
    public void delete(Long id) {
        AirportEntity airportEntity = airportRepository.findById(id).orElseThrow(() -> new LocalizedException("airport.is.not.exists"));
        airportRepository.delete(airportEntity);
    }

    @Override
    public List<AirportModel> getAirportlist() {
        List<AirportEntity> all = airportRepository.findAll();
        return airportMapper.converToModelList(all);
    }

    @Override
    public AirportModel getAirportById(Long id) {
        AirportEntity airportEntity = airportRepository.findById(id).orElseThrow(() -> new LocalizedException("airport.is.not.exists"));
        return airportMapper.converToModel(airportEntity);
    }
}
