package kz.manap.flightstatusapi.services;

import javassist.NotFoundException;
import kz.manap.flightstatusapi.models.Flight;
import kz.manap.flightstatusapi.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Flight findById(Integer id) {
        try {
            return flightRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Flight not found"));
        } catch (NotFoundException e) {
            throw new RuntimeException("Flight not found", e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(Flight flight) {
        flightRepository.save(flight);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(Integer id) {
        flightRepository.deleteById(id);
    }
}
