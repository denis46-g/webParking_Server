package com.work.webParking.service;

import com.work.webParking.model.ParkingSpace;
import com.work.webParking.repository.ParkingSpaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService {
    private final ParkingSpaceRepository parkingSpaceRepository;

    public ParkingSpaceServiceImpl(ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    @Override
    public List<ParkingSpace> readAll(){
        return parkingSpaceRepository.findAll();
    }

    @Override
    public ParkingSpace read(int id){
        return parkingSpaceRepository.getReferenceById(id); // ?
    }

    @Override
    public void create(ParkingSpace p){
        parkingSpaceRepository.save(p);
    }

    @Override
    public boolean update(ParkingSpace p, int id){
        if (parkingSpaceRepository.existsById(id)) {
            p.setId(id);
            parkingSpaceRepository.save(p);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id){
        if (parkingSpaceRepository.existsById(id)) {
            parkingSpaceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
