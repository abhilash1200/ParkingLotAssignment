package payment;

import parking.ParkingSpot;

import java.util.Date;

public class PaymentTicket {
    private final ParkingSpot parkingSpot;

    public PaymentTicket(ParkingSpot parkingSpot,
                         Date unparkingTime) {
        this.parkingSpot = parkingSpot;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

}
