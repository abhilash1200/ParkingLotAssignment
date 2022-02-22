package parking;

import parking.Policies.ParkingAssignmentPolicy;
import payment.PaymentTicket;
import vehicles.Vehicle;

public interface ParkingLevel {

    int getID();

    int getNumOfVacantSpotsOfType(ParkingSpotType parkingSpotType);

    int getTotalNumOfVacantSpots();


    ParkingSpot parkVehicle(Vehicle vehicle, ParkingAssignmentPolicy parkingAssignmentPolicy);

    void unparkVehicle(Vehicle vehicle, PaymentTicket paymentTicket);

}
