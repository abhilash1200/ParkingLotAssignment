package parking;

import vehicles.VehicleType;

import java.util.Collection;

public interface VehicleToParkingSpotTypeMapper {
    Collection<ParkingSpotType> getPossibleParkingSpotTypes(VehicleType vehicleType);
}
