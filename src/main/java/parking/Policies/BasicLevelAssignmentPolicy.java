package parking.Policies;

import parking.ParkingLevel;
import vehicles.Vehicle;

public class BasicLevelAssignmentPolicy implements LevelAssignmentPolicy{
    @Override
    public ParkingLevel assignLevel(Iterable<ParkingLevel> levels,
                                    Vehicle vehicle) {
        return levels.iterator().next();
    }
}
