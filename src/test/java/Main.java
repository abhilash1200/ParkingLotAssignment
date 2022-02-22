import displayboard.ConsoleDisplayBoard;
import displayboard.DisplayBoard;
import parking.Factories.ParkingLotFactory;
import parking.ParkingLot;
import parking.ParkingLotFacade;
import payment.ParkingTicket;
import users.CarOwner;
import vehicles.Vehicle;
import vehicles.VehicleType;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = ParkingLotFactory.createNewParkingLot();

        while (true) {
            System.out.println("Welcome to TestVagrant parking lot.\nEnter 1 if you're a employee,2 for display board:");
            int userType = scanner.nextInt();

            if (userType == 1) {   //car owner
                handleCarOwner(scanner, parkingLot);
            }else if (userType==2){
                DisplayBoard displayBoard=new ConsoleDisplayBoard();
                displayBoard.displayNumOfVacantSpotsForEachLevel(parkingLot.getAllLevels());
            }
        }
    }

    static void handleCarOwner(Scanner scanner, ParkingLotFacade parkingLot) {
        System.out.println("Welcome, Please enter your name:");
        String carOwnerName = scanner.next();

        System.out.println("Please enter your Employee's ID:");
        int carID = scanner.nextInt();

        System.out.print("Please enter your vehicle's type:\n"+
                "0 for motorcycle\n" +
                "1 for car\n" +
                "2 for truck\n");
        VehicleType vehicleType = VehicleType.values()[scanner.nextInt()];

        Vehicle vehicle = new Vehicle(carID, vehicleType);

        CarOwner carOwner = new CarOwner(carOwnerName, vehicle);

        System.out.println("Press 1 to park, 2 to unpark:");
        int carOwnersChoice = scanner.nextInt();
        if (carOwnersChoice == 1) { //park
            ParkingTicket parkingTicket = carOwner.parkVehicle(parkingLot);
            System.out.println(parkingTicket);
        } else {
            System.out.println("Not implemented choice.");
        }
    }
}
