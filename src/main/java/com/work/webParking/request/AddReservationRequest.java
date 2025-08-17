package com.work.webParking.request;

import java.sql.Timestamp;

public class AddReservationRequest {
    private int parkingSpaceId;
    private Timestamp timeFrom;
    private Timestamp timeTo;
    private int userId;

    public int getParkingSpaceId(){
        return parkingSpaceId;
    }

    public Timestamp getTimeFrom(){
        return timeFrom;
    }

    public Timestamp getTimeTo(){
        return timeTo;
    }

    public int getUserId(){
        return userId;
    }
}
