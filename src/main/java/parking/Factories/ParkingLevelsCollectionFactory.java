package parking.Factories;

import parking.*;
import vehicles.VehicleType;

import java.util.*;

public class ParkingLevelsCollectionFactory {
    private static final int NumberOfLevels = 5;
    private static final int NumberOfSpotTypes = ParkingSpotType.values().length;
    private static final int NumberOfSpotsForSpotType = 100;

    public static ParkingLevelsCollection createParkingLevelsCollection() {
        ParkingLevelsCollection levels =
                new ArrayListParkingLevelsCollection();
        int parkingSpotID = 0;
        int parkingLevelID = 0;
        for (int i = 0; i < NumberOfLevels; i++) {
            Map<ParkingSpotType, Collection<ParkingSpot>> allSpotsForLevel = generateSpots(parkingLevelID, parkingSpotID);

            VehicleToParkingSpotTypeMapper vehicleToSpotTypesMapper = typesMapper();
            ParkingLevel parkingLevel = new CParkingLevel(parkingLevelID, allSpotsForLevel,
                    vehicleToSpotTypesMapper);
            levels.add(parkingLevel);
            parkingSpotID += NumberOfSpotTypes * NumberOfSpotsForSpotType;
            parkingLevelID++;
        }
        return levels;
    }

    private static VehicleToParkingSpotTypeMapper typesMapper() {
        Map<VehicleType, Collection<ParkingSpotType>> typesMapper =
                new HashMap<>();
        typesMapper.put(VehicleType.Motorcycle,
                Arrays.asList(ParkingSpotType.MOTORCYCLE,
                        ParkingSpotType.COMPACT,
                        ParkingSpotType.LARGE));
        typesMapper.put(VehicleType.Car,
                Arrays.asList(ParkingSpotType.COMPACT, ParkingSpotType.LARGE));
        typesMapper.put(VehicleType.TRUCK,
                Collections.singletonList(ParkingSpotType.LARGE));
        return typesMapper::get;
    }

    private static Map<ParkingSpotType, Collection<ParkingSpot>> generateSpots(int parkingLevelID, int parkingSpotID) {
        Map<ParkingSpotType, Collection<ParkingSpot>> allSpotsForLevel = new HashMap<>();
        for (ParkingSpotType parkingSpotType : ParkingSpotType.values()) {
            Collection<ParkingSpot> spotsForLevel = new ArrayList<>();
            for (int i = 0; i < NumberOfSpotsForSpotType; i++) {
                spotsForLevel.add(new ParkingSpot(parkingSpotID,
                        parkingLevelID, parkingSpotType));
                parkingSpotID++;
            }
            allSpotsForLevel.put(parkingSpotType, spotsForLevel);
        }
        return allSpotsForLevel;
    }
}
