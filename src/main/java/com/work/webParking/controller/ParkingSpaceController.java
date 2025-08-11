package com.work.webParking.controller;

import com.work.webParking.model.ParkingSpace;
import com.work.webParking.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ParkingSpaceController {
    private final ParkingSpaceService parkingSpaceService;

    @Autowired
    public ParkingSpaceController(ParkingSpaceService parkingSpaceService){
        this.parkingSpaceService = parkingSpaceService;
    }

    @GetMapping(value="/parking_spaces")
    public ResponseEntity<List<ParkingSpace>> read() {
        final List<ParkingSpace> parking_spaces = parkingSpaceService.readAll();

        return parking_spaces != null && !parking_spaces.isEmpty() ?
                new ResponseEntity<>(parking_spaces, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/parking_spaces/{id}")
    public ResponseEntity<ParkingSpace> read(@PathVariable(name = "id") int id){
        final ParkingSpace parkingSpace = parkingSpaceService.read(id);

        return parkingSpace != null ?
                new ResponseEntity<>(parkingSpace, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/parking_spaces")
    public ResponseEntity<?> create(@RequestBody ParkingSpace p) {
        parkingSpaceService.create(p);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value="/parking_spaces/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody ParkingSpace p){
        final boolean is_updated = parkingSpaceService.update(p, id);

        return is_updated ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value="/parking_spaces/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        final boolean is_deleted = parkingSpaceService.delete(id);

        return is_deleted ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
