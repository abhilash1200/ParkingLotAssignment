package parking;

import exceptions.InvalidParkingTicketException;
import exceptions.ParkingLotIsFullException;
import parking.Policies.LevelAssignmentPolicy;
import parking.Policies.ParkingAssignmentPolicy;
import payment.*;
import exceptions.InvalidPaymentTicketException;
import vehicles.Vehicle;

import java.util.Date;

public class ParkingLot implements ParkingLotFacade{
    private ParkingLevelsCollection fullLevels;
    private ParkingLevelsCollection nonFullLevels;
    private LevelAssignmentPolicy levelAssignmentPolicy;
    private ParkingAssignmentPolicy parkingAssignmentPolicy;
    private PaymentCalculation paymentCalculation;

    public ParkingLot
            (ParkingLevelsCollection allLevels,
             LevelAssignmentPolicy levelAssignmentPolicy,
             ParkingAssignmentPolicy parkingAssignmentPolicy,
             PaymentCalculation paymentCalculation) {
        this.nonFullLevels = allLevels;
        this.levelAssignmentPolicy = levelAssignmentPolicy;
        this.parkingAssignmentPolicy = parkingAssignmentPolicy;
        this.paymentCalculation = paymentCalculation;
        this.fullLevels= new ArrayListParkingLevelsCollection();
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) throws ParkingLotIsFullException {
        if (nonFullLevels.size() == 0) {
            throw new ParkingLotIsFullException("No vacant levels for parking");
        }
        ParkingLevel assignedParkingLevel = levelAssignmentPolicy.assignLevel(nonFullLevels, vehicle);
        ParkingSpot assignedParkingSpot =
                assignedParkingLevel.parkVehicle(vehicle, parkingAssignmentPolicy);

        if (assignedParkingLevel.getTotalNumOfVacantSpots() == 0) {
            nonFullLevels.remove(assignedParkingLevel);
            fullLevels.add(assignedParkingLevel);
        }
        return generateParkingTicket(vehicle, assignedParkingSpot);
    }

    public void unparkVehicle(Vehicle vehicle, PaymentTicket paymentTicket) throws InvalidPaymentTicketException {
        if (paymentTicket == null) {
            throw new InvalidPaymentTicketException("Invalid payment ticket " +
                    "was passed");
        }
        int parkingLevelID = paymentTicket.getParkingSpot().getParkingLevelID();
        ParkingLevel parkingLevel = getLevel(parkingLevelID);
        if (parkingLevel.getTotalNumOfVacantSpots() == 0) {
            fullLevels.remove(parkingLevel);
            nonFullLevels.add(parkingLevel);
        }
        parkingLevel.unparkVehicle(vehicle, paymentTicket);
    }

    public PaymentTicket pay(ParkingTicket parkingTicket, PaymentMethod paymentMethod) throws InvalidParkingTicketException {
        if (parkingTicket==null) {
            throw new InvalidParkingTicketException("an invalid parking " +
                    "ticket was passed");
        }
        double paymentSum =
                paymentCalculation.calculatePaymentSum(parkingTicket);
        try {
            paymentMethod.pay(paymentSum);
        } catch (PaymentException paymentException) {
            System.out.println("A payment exception occurred with the " +
                    "following details: " + paymentException.getMessage());
        }
        return generatePaymentTicket(parkingTicket, paymentSum);
    }


    private ParkingTicket generateParkingTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        return new ParkingTicket(vehicle.EmployeeID(), parkingSpot, new Date());
    }

    private PaymentTicket generatePaymentTicket(ParkingTicket parkingTicket, double paymentSum) {
        return new PaymentTicket(
                parkingTicket.getParkingSpot(),
                java.util.Calendar.getInstance().getTime()
        );
    }


    //admin

    public ParkingLevel getLevel(int parkingLevelID) {
        ParkingLevel parkingLevel = fullLevels.getParkingLevel(parkingLevelID);
        if (parkingLevel != null) {
            return parkingLevel;
        }
        return nonFullLevels.getParkingLevel((parkingLevelID));
    }

    public ParkingLevelsCollection getAllLevels(){
        ParkingLevelsCollection combined= new ArrayListParkingLevelsCollection();
        combined.addAll(nonFullLevels);
        combined.addAll(fullLevels);
        return combined;
    }
}
