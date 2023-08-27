package org.amadeus.controller;

import lombok.RequiredArgsConstructor;
import org.amadeus.dto.flight.FlightFilterModel;
import org.amadeus.dto.flight.FlightModel;
import org.amadeus.dto.flight.FlightPersistModel;
import org.amadeus.dto.flight.FlightUpdateModel;
import org.amadeus.exception.LocalizedException;
import org.amadeus.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/flights")
@CrossOrigin("*")
public class FlightController {

    private final FlightService flightService;

    @PostMapping("")
    public void create(@RequestBody FlightPersistModel flightPersistModel) {
        flightService.create(flightPersistModel);
    }


    @PutMapping("")
    public void update(@RequestBody FlightUpdateModel flightPersistModel) {
        flightService.update(flightPersistModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        flightService.delete(id);
    }

    @GetMapping("")
    public List<FlightModel> getAll() {
        return flightService.getAll();
    }

    @GetMapping("/{id}")
    public FlightModel getByFlightId(@PathVariable("id") Long id) {
        return flightService.getByFlightId(id);
    }

    @PostMapping("/filter")
    public FlightModel getDeparturesByAirportAndByDate(@RequestBody FlightFilterModel model) {
        return flightService.getDeparturesByAirportAndByDate(model);
    }




}
