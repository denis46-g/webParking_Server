package com.work.webParking.repository;

import com.work.webParking.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Integer> {
}
