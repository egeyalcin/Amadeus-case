package org.mockdata.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.RequiredArgsConstructor;
import org.mockdata.constants.Constants;
import org.mockdata.dto.FlightPersistModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class FlightGenerator {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedRate = 30000)
    public void sendMessage() throws JsonProcessingException {
        FlightPersistModel flightPersistModel = generateRandomFlight();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        String json = objectMapper.writeValueAsString(flightPersistModel);
        kafkaTemplate.send(Constants.TOPIC_NAME, json);
    }

    public static FlightPersistModel generateRandomFlight() {
        FlightPersistModel flight = new FlightPersistModel();
        Random random = new Random();

        flight.setDepartureAirportId((long) random.nextInt(100));
        flight.setLandingAirportId((long) random.nextInt(100));

        long now = System.currentTimeMillis();
        long departureTime = now + random.nextInt(86400000);
        long landingTime = departureTime + random.nextInt(21600000);

        flight.setDepartureDate(new Date(departureTime));
        flight.setLandingDate(new Date(landingTime));

        flight.setPrice(random.nextInt(10000));

        return flight;
    }
}
