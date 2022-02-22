package parking.Policies;

import parking.ParkingSpot;
import parking.ParkingSpotType;

import java.util.Collection;
import java.util.Map;

public class BasicParkingAssignmentPolicy implements ParkingAssignmentPolicy {
    @Override
    public ParkingSpot assignSpot(Map<ParkingSpotType,
            ? extends Iterable<ParkingSpot>> vacantSpots,
                                  Collection<ParkingSpotType> possibleParkingSpotTypes) {
        ParkingSpotType parkingSpotType =
                possibleParkingSpotTypes.iterator().next();
        return vacantSpots.get(parkingSpotType).iterator().next();
    }
}
