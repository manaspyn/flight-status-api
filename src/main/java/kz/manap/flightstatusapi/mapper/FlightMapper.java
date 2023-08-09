package kz.manap.flightstatusapi.mapper;

import kz.manap.flightstatusapi.dto.FlightDto;
import kz.manap.flightstatusapi.models.Flight;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FlightMapper {

    public Flight toEntity(FlightDto flightDTO) {
        Flight flight = new Flight();
        flight.setOrigin(flightDTO.getOrigin());
        flight.setDestination(flightDTO.getDestination());
        flight.setDeparture(LocalDateTime.parse(flightDTO.getDeparture()));
        flight.setArrival(LocalDateTime.parse(flightDTO.getArrival()));
        flight.setStatus(flightDTO.getStatus());
        return flight;
    }
}

