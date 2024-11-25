package com.RideLink.Service.Impl;

import com.RideLink.Entities.Ride;
import com.RideLink.Entities.User;
import com.RideLink.PayLoad.RideDto;
import com.RideLink.Repository.RideRepository;
import com.RideLink.Repository.UserRepository;
import com.RideLink.Service.RideService;
import com.RideLink.Service.UserService;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideServiceImpl implements RideService {

    private final ModelMapper modelMapper;
    private final RideRepository rideRepo;
    private final UserService userService;
    private final UserRepository userRepo;
    public RideServiceImpl(ModelMapper modelMapper, RideRepository rideRepo, UserService userService, UserRepository userRepo) {
        this.modelMapper = modelMapper;
        this.rideRepo = rideRepo;
        this.userService = userService;
        this.userRepo = userRepo;
    }

    RideDto mapToDto(Ride ride){
        return modelMapper.map(ride, RideDto.class);
    }

    Ride mapToEntity(RideDto rideDto){
        return modelMapper.map(rideDto, Ride.class);
    }

//    @Override
//    public RideDto createRide(RideDto rideDto) {
//        // Convert the RideDto to a Ride entity
//        Ride ride = mapToEntity(rideDto);
//
//        // Save the Ride entity to the database
//        Ride savedRide = rideRepo.save(ride);
//
//        // Convert the saved Ride entity back to a RideDto
//        RideDto savedRideDto = mapToDto(savedRide);
//
//        // Return the saved RideDto
//        return savedRideDto;
//    }

    @Override
    public RideDto createRide(long userId, RideDto rideDto) {
        // Fetch the user based on userId
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));


        // Create a new Ride entity from the RideDto and associate it with the user
        Ride ride = mapToEntity(rideDto);
        ride.setUser(user); // Set the user in the Ride entity

        Ride savedRide = rideRepo.save(ride);
        return mapToDto(savedRide);
    }
    @Override
    public List<RideDto> showRides() {
        List<Ride> rides = rideRepo.findAll();
        return rides.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RideDto updateRide(long id, RideDto rideDto) {
        Ride ride = rideRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));

        ride.setOrigin(rideDto.getOrigin());
        ride.setDestination(rideDto.getDestination());
        ride.setVehical(rideDto.getVehical());
        ride.setTime(rideDto.getTime());
        ride.setDate(rideDto.getDate());
        ride.setSeats(rideDto.getSeats());
        ride.setFarePerSeat(rideDto.getFarePerSeat());

        Ride updatedRide = rideRepo.save(ride);

        return mapToDto(updatedRide);
    }

    @Override
    public RideDto deleteRide(long id) {
        Ride ride = rideRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));

        rideRepo.delete(ride);

        return mapToDto(ride);
    }

}
