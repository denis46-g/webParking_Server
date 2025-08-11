package com.work.webParking.controller;

import com.work.webParking.model.Car;
import com.work.webParking.model.User;
import com.work.webParking.request.AddCarRequest;
import com.work.webParking.request.RegisterRequest;
import com.work.webParking.response.Response;
import com.work.webParking.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping(value="/cars")
    public ResponseEntity<List<Car>> read() {
        final List<Car> cars = carService.readAll();

        return cars != null && !cars.isEmpty() ?
                new ResponseEntity<>(cars, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/cars/{id}")
    public ResponseEntity<Car> read(@PathVariable(name = "id") int id){
        final Car car = carService.read(id);

        return car != null ?
                new ResponseEntity<>(car, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/cars")
    public ResponseEntity<?> create(@RequestBody Car c) {
        carService.create(c);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value="/cars/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Car c){
        final boolean is_updated = carService.update(c, id);

        return is_updated ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value="/cars/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        final boolean is_deleted = carService.delete(id);

        return is_deleted ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // основные API для работы с машинами
    @GetMapping(value="/users/{id}/cars")
    public ResponseEntity<List<Car>> readThisUserCars(@PathVariable(name = "id") int id){
        final List<Car> cars = carService.readAll();
        if(cars != null){
            cars.removeIf(c -> c.getOwnerId() != id);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/addCar")
    public ResponseEntity<Response> addCar(@RequestBody AddCarRequest addCarRequest){
        var brand = addCarRequest.getBrand();
        var licensePlate = addCarRequest.getLicensePlate();
        var ownerId = addCarRequest.getOwnerId();

        if(brand == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response(false, "Введите марку автомобиля"));
        else if(licensePlate == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response(false, "Введите номерной знак"));

        Car c = new Car();
        c.setBrand(brand);
        c.setLicensePlate(licensePlate);
        c.setOwnerId(ownerId);

        carService.create(c);

        return ResponseEntity.ok(new Response(true, "Автомобиль добавлен"));
    }
}
