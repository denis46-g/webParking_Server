package com.work.webParking.service;

import com.work.webParking.model.Car;
import com.work.webParking.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> readAll(){
        return carRepository.findAll();
    }

    @Override
    public Car read(int id){
        return carRepository.getReferenceById(id); // ?
    }

    @Override
    public void create(Car c){
        carRepository.save(c);
    }

    @Override
    public boolean update(Car c, int id){
        if (carRepository.existsById(id)) {
            c.setId(id);
            carRepository.save(c);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id){
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
