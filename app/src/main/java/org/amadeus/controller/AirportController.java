package org.amadeus.controller;

import lombok.RequiredArgsConstructor;
import org.amadeus.dto.airport.AirportModel;
import org.amadeus.dto.airport.AirportPersistModel;
import org.amadeus.dto.airport.AirportUpdateModel;
import org.amadeus.service.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/airport")
@CrossOrigin("*")
public class AirportController {

    private final AirportService airportService;

    @PostMapping("")
    public void create(@RequestBody AirportPersistModel airportPersistModel) {
        airportService.create(airportPersistModel);
    }

    @PutMapping("")
    public void update(@RequestBody AirportUpdateModel airportPersistModel) {
        airportService.update(airportPersistModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        airportService.delete(id);
    }


    @GetMapping("")
    public List<AirportModel> getAirportlist() {
        return airportService.getAirportlist();
    }

    @GetMapping("/{id}")
    public AirportModel getAirportById(@PathVariable("id") Long id) {
        return airportService.getAirportById(id);
    }
}

