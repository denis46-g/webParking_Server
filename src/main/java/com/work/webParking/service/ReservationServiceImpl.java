package com.work.webParking.service;

import com.work.webParking.model.Reservation;
import com.work.webParking.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> readAll(){
        return reservationRepository.findAll();
    }

    @Override
    public Reservation read(int id){
        return reservationRepository.getReferenceById(id); // ?
    }

    @Override
    public void create(Reservation r){
        reservationRepository.save(r);
    }

    @Override
    public boolean update(Reservation r, int id){
        if (reservationRepository.existsById(id)) {
            r.setId(id);
            reservationRepository.save(r);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id){
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
