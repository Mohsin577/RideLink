package com.RideLink.Controller;

import com.RideLink.PayLoad.RideDto;
import com.RideLink.Service.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ride")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

//    @PostMapping
//    public ResponseEntity<RideDto> createRide(@RequestBody RideDto rideDto){
//        RideDto saveDto = rideService.createRide(rideDto);
//        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<RideDto> createRide(@RequestParam long userId, @RequestBody RideDto rideDto) {
        RideDto createdRide = rideService.createRide(userId, rideDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRide);
    }
    @GetMapping()
    public ResponseEntity<List<RideDto>> showRides() {
        List<RideDto> rides = rideService.showRides();
        return ResponseEntity.ok(rides);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RideDto> updateRide(@PathVariable long id, @RequestBody RideDto rideDto) {
        RideDto updatedRide = rideService.updateRide(id, rideDto);
        return ResponseEntity.ok(updatedRide);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRide(@PathVariable long id) {
        RideDto deletedRide = rideService.deleteRide(id);
        return new ResponseEntity<>("Ride Deleted Succesfully", HttpStatus.OK);
    }

}
