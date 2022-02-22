package displayboard;

import parking.ParkingLevel;
import parking.ParkingLevelsCollection;
import parking.ParkingSpotType;

public class ConsoleDisplayBoard implements DisplayBoard {
    public void displayNumOfVacantSpotsForEachLevel(ParkingLevelsCollection levels){
        System.out.println("Vacant spots on each level:");
        for(ParkingLevel level:levels){
            System.out.println("Level " + level.getID()+ ":");
            System.out.println("Total: " +level.getTotalNumOfVacantSpots());
            for(ParkingSpotType parkingSpotType: ParkingSpotType.values()) {
                System.out.println(parkingSpotType+": " + level.getNumOfVacantSpotsOfType(parkingSpotType));
            }
            System.out.println("____________________________________");
        }
    }
}
