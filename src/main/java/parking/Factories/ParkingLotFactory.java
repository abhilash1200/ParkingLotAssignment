package parking.Factories;

import parking.ParkingLevelsCollection;
import parking.ParkingLot;
import parking.Policies.BasicLevelAssignmentPolicy;
import parking.Policies.BasicParkingAssignmentPolicy;
import parking.Policies.LevelAssignmentPolicy;
import parking.Policies.ParkingAssignmentPolicy;
import payment.BasicHourlyPayment;
import payment.PaymentCalculation;

public class ParkingLotFactory {
    private static final double DOLLARS_PER_HOUR = 5;

    public static ParkingLot createNewParkingLot() {
        ParkingLevelsCollection parkingLevels =
                ParkingLevelsCollectionFactory.createParkingLevelsCollection();
        LevelAssignmentPolicy levelAssignmentPolicy = new BasicLevelAssignmentPolicy();
        ParkingAssignmentPolicy parkingAssignmentPolicy =
                new BasicParkingAssignmentPolicy();
        PaymentCalculation paymentCalculation = new BasicHourlyPayment(DOLLARS_PER_HOUR);
        return new ParkingLot(parkingLevels, levelAssignmentPolicy,
                parkingAssignmentPolicy, paymentCalculation);
    }
}
