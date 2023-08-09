package kz.manap.flightstatusapi.dto;

import kz.manap.flightstatusapi.models.Status;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "flight")
public class FlightDto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Origin is required")
    @Column(name = "origin")
    private String origin;

    @NotEmpty(message = "Destination is required")
    @Column(name = "destination")
    private String destination;

    @NotEmpty
    @Column(name = "departure")
    private String departure;

    @NotEmpty
    @Column(name = "arrival")
    private String arrival;

    @NotNull
    @Column(name = "status")
    private Status status;

    public FlightDto() {
    }

    public FlightDto(String origin, String destination, String departure, String arrival, Status status) {
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
