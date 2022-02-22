package vehicles;

public class Vehicle {

    private int ID;
    private VehicleType vehicleType;

    public Vehicle(int ID, VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int EmployeeID() {
        return this.ID;
    }

    public VehicleType getVehicleType() {
        return this.vehicleType;
    }
}
