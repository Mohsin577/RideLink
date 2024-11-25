package com.RideLink.Service;

import com.RideLink.PayLoad.RideDto;

import java.util.List;

public interface RideService {
//    RideDto createRide(RideDto rideDto);
    RideDto createRide(long userId,RideDto rideDto);
    List<RideDto> showRides();
    RideDto updateRide(long id, RideDto ridedto);
    RideDto deleteRide(long id);
}
