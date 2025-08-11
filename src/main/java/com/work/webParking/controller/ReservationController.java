package com.work.webParking.controller;

import com.work.webParking.model.Reservation;
import com.work.webParking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){

        this.reservationService = reservationService;
    }

    @GetMapping(value="/reservations")
    public ResponseEntity<List<Reservation>> read() {
        final List<Reservation> reservations = reservationService.readAll();

        return reservations != null && !reservations.isEmpty() ?
                new ResponseEntity<>(reservations, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/reservations/{id}")
    public ResponseEntity<Reservation> read(@PathVariable(name = "id") int id){
        final Reservation reservation = reservationService.read(id);

        return reservation != null ?
                new ResponseEntity<>(reservation, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/reservations")
    public ResponseEntity<?> create(@RequestBody Reservation r) {
        reservationService.create(r);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value="/reservation/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Reservation r){
        final boolean is_updated = reservationService.update(r, id);

        return is_updated ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value="/reservation/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        final boolean is_deleted = reservationService.delete(id);

        return is_deleted ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
