package com.work.webParking.controller;

import com.work.webParking.model.Car;
import com.work.webParking.model.Reservation;
import com.work.webParking.request.AddCarRequest;
import com.work.webParking.request.AddReservationRequest;
import com.work.webParking.response.Response;
import com.work.webParking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    @DeleteMapping(value="/reservations/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        final boolean is_deleted = reservationService.delete(id);

        return is_deleted ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // основные API для работы с бронированиями
    @GetMapping(value="/users/{id}/reservs")
    public ResponseEntity<List<Reservation>> readThisUserReservaions(@PathVariable(name = "id") int id){
        final List<Reservation> reservs = reservationService.readAll();
        if(reservs != null) {
            reservs.removeIf(r -> r.getUserId() != id);
            return new ResponseEntity<>(reservs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/addReservation")
    public ResponseEntity<Response> addReservation(@RequestBody AddReservationRequest addReservationRequest){
        var parkingSpaceId = addReservationRequest.getParkingSpaceId();
        var timeFrom = addReservationRequest.getTimeFrom();
        var timeTo = addReservationRequest.getTimeTo();
        var userId = addReservationRequest.getUserId();

        if(timeFrom == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response(false, "Введите время от"));
        else if(timeTo == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response(false, "Введите время до"));
        else if(timeFrom.after(timeTo))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response(false, "Время от всегда раньше времени до"));
        else if(timeFrom.before(Timestamp.valueOf(LocalDateTime.now())) ||
            timeTo.before(Timestamp.valueOf(LocalDateTime.now())))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response(false, "Время от и до должно быть будущим"));

        final List<Reservation> thisParkingPlaceReservations = reservationService.readAll();
        if(thisParkingPlaceReservations!=null){
            thisParkingPlaceReservations.removeIf(r -> r.getParkingSpaceId() != parkingSpaceId);
            for(Reservation reservation : thisParkingPlaceReservations){
                if(reservation.getTimeFrom().before(timeFrom) && reservation.getTimeTo().after(timeFrom)||
                        reservation.getTimeFrom().before(timeTo) && reservation.getTimeTo().after(timeTo)){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new Response(false, "Это время уже забронировано"));
                }
            }
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        Reservation r = new Reservation();
        r.setParkingSpaceId(parkingSpaceId);
        r.setTimeFrom(timeFrom);
        r.setTimeTo(timeTo);
        r.setUserId(userId);

        reservationService.create(r);

        return ResponseEntity.ok(new Response(true, "Парковочное место забронировано"));
    }

    @GetMapping(value="/parking_spaces/{id}/reservs")
    public ResponseEntity<List<Reservation>> readThisParkingSpaceReservations(@PathVariable(name = "id") int id){
        final List<Reservation> reservs = reservationService.readAll();
        if(reservs != null) {
            reservs.removeIf(r -> r.getParkingSpaceId()!= id);
            return new ResponseEntity<>(reservs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
