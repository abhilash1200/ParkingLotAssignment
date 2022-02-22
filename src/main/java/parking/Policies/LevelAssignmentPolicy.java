package parking.Policies;

import parking.ParkingLevel;
import vehicles.Vehicle;

public interface LevelAssignmentPolicy {
    ParkingLevel assignLevel
            (Iterable<ParkingLevel> levels, Vehicle vehicle);
}
