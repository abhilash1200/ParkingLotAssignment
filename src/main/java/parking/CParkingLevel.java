package parking;

import parking.Policies.ParkingAssignmentPolicy;
import payment.PaymentTicket;
import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CParkingLevel implements ParkingLevel {

    private int id;
    private Map<ParkingSpotType, Collection<ParkingSpot>> vacantSpots;
    private Map<ParkingSpotType, Collection<ParkingSpot>> takenSpots;
    private int totalNumOfVacantSpots;
    private VehicleToParkingSpotTypeMapper possibleSpots;

    public CParkingLevel(int id, Map<ParkingSpotType,
            Collection<ParkingSpot>> allSpotsForLevel, VehicleToParkingSpotTypeMapper possibleSpots) {
        this.id = id;
        this.vacantSpots = allSpotsForLevel;
        this.takenSpots = new HashMap<>();
        takenSpots.put(ParkingSpotType.MOTORCYCLE, new ArrayList<>());
        takenSpots.put(ParkingSpotType.COMPACT, new ArrayList<>());
        takenSpots.put(ParkingSpotType.LARGE, new ArrayList<>());
        this.totalNumOfVacantSpots =
                allSpotsForLevel.values().stream().
                        mapToInt(Collection::size).sum();
        this.possibleSpots = possibleSpots;
    }

    public int getID() {
        return id;
    }

    @Override
    public int getNumOfVacantSpotsOfType(ParkingSpotType parkingSpotType) {
        return vacantSpots.get(parkingSpotType).size();
    }

    @Override
    public int getTotalNumOfVacantSpots() {
        return totalNumOfVacantSpots;
    }


    @Override
    public ParkingSpot parkVehicle
            (Vehicle vehicle, ParkingAssignmentPolicy parkingAssignmentPolicy) {
        ParkingSpot assignedParkingSpot =
                parkingAssignmentPolicy.assignSpot(vacantSpots,
                        possibleSpots.getPossibleParkingSpotTypes(vehicle.getVehicleType()));
        ParkingSpotType parkingSpotType =
                assignedParkingSpot.getParkingSpotType();
        vacantSpots.get(parkingSpotType).remove(assignedParkingSpot);
        takenSpots.get(parkingSpotType).add(assignedParkingSpot);
        totalNumOfVacantSpots--;
        return assignedParkingSpot;
    }

    @Override
    public void unparkVehicle(Vehicle vehicle, PaymentTicket paymentTicket) {
        ParkingSpot parkingSpot = paymentTicket.getParkingSpot();
        ParkingSpotType parkingSpotType =
                paymentTicket.getParkingSpot().getParkingSpotType();
        takenSpots.get(parkingSpotType).remove(parkingSpot);
        vacantSpots.get(parkingSpotType).add(parkingSpot);
        totalNumOfVacantSpots++;
    }
}
