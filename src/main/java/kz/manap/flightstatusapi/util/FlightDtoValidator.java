package kz.manap.flightstatusapi.util;

import kz.manap.flightstatusapi.dto.FlightDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class FlightDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return FlightDto.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "field.required", "Status is required.");

        FlightDto flight = (FlightDto) o;

        if (flight.getDeparture() == null) {
            errors.rejectValue("departure", "field.required", "Departure date is required");
        }
        if (flight.getArrival() == null) {
            errors.rejectValue("arrival", "field.required", "Arrival date is required");
        } else if (!flight.getDeparture().isEmpty() && !flight.getArrival().isEmpty()) {
            if (LocalDateTime.parse(flight.getDeparture()).isAfter(LocalDateTime.parse(flight.getArrival()))) {
                errors.rejectValue("departure", "invalid.date", "Departure cannot be after arrival.");
            }
        }

    }
}
