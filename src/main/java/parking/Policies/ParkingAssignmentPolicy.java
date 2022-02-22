package parking.Policies;

import parking.ParkingSpot;
import parking.ParkingSpotType;

import java.util.Collection;
import java.util.Map;

public interface ParkingAssignmentPolicy {
    ParkingSpot assignSpot(Map<ParkingSpotType,
            ? extends Iterable<ParkingSpot>> vacantSpots,
                           Collection<ParkingSpotType> possibleParkingSpotTypes);
}
