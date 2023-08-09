package kz.manap.flightstatusapi.services;

import javassist.NotFoundException;
import kz.manap.flightstatusapi.models.Flight;
import kz.manap.flightstatusapi.repositories.FlightRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {


    @InjectMocks
    private FlightService flightService;

    @Mock
    private FlightRepository flightRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllFlights() {
        // Мокирование репозитория
        when(flightRepository.findAll()).thenReturn(Arrays.asList(new Flight(), new Flight()));

        List<Flight> flights = flightService.getAllFlights();

        assertEquals(2, flights.size());
    }

    @Test
    public void testFindById_ExistingFlight() throws NotFoundException {
        Flight testFlight = new Flight();
        testFlight.setId(1);

        when(flightRepository.findById(anyInt())).thenReturn(Optional.of(testFlight));

        Flight foundFlight = flightService.findById(1);

        assertNotNull(foundFlight);
        assertEquals(1, foundFlight.getId());
    }

    @Test
    public void testFindById_NonExistingFlight() {
        when(flightRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> flightService.findById(1));
    }

    @Test
    public void testSaveFlight() {
        Flight testFlight = new Flight();

        flightService.save(testFlight);

        verify(flightRepository, times(1)).save(testFlight);
    }

    @Test
    public void testDeleteFlight() {
        flightService.deleteById(1);

        verify(flightRepository, times(1)).deleteById(1);
    }
}