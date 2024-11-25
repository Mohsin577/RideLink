package com.RideLink.PayLoad;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class RideDto {
    private Long rideId;
    private String origin;
    private String destination;
    private String vehical;
    private LocalTime time;
    private LocalDate date;
    private int seats;
    private double farePerSeat;
}