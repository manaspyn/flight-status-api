package kz.manap.flightstatusapi.controllers;

import kz.manap.flightstatusapi.dto.FlightDto;
import kz.manap.flightstatusapi.mapper.FlightMapper;
import kz.manap.flightstatusapi.models.Flight;
import kz.manap.flightstatusapi.services.FlightService;
import kz.manap.flightstatusapi.util.FlightDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;
    private final FlightDtoValidator flightDtoValidator;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightController(FlightService flightService, FlightDtoValidator flightDtoValidator, FlightMapper flightMapper) {
        this.flightService = flightService;
        this.flightDtoValidator = flightDtoValidator;
        this.flightMapper = flightMapper;
    }

    @GetMapping
    public String viewFlights(Model model) {
        List<Flight> flights = flightService.getAllFlights();
        model.addAttribute("flights", flights);

        return "flights/view";
    }

    @GetMapping("/edit/{id}")
    public String editFlight(@PathVariable Integer id, Model model) {
        Flight flight = flightService.findById(id);
        model.addAttribute("flight", flight);
        return "flights/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateFlight(@PathVariable Integer id, @ModelAttribute Flight flight) {
        flightService.save(flight);
        return "redirect:/flights";
    }

    @GetMapping("/delete/{id}")
    public String deleteFlight(@PathVariable Integer id) {
        flightService.deleteById(id);
        return "redirect:/flights";
    }

    @GetMapping("/add-flight")
    public String addFlightForm(Model model) {
        model.addAttribute("flightDto", new FlightDto());
        return "flights/add";
    }

    @PostMapping("/add-flight")
    public String addFlight(@Valid @ModelAttribute FlightDto flightDto, BindingResult bindingResult) {
        flightDtoValidator.validate(flightDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "flights/add";
        }

        flightService.save(flightMapper.toEntity(flightDto));
        return "redirect:/flights";
    }
}
