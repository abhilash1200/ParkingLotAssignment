package parking;

import exceptions.InvalidParkingTicketException;
import exceptions.InvalidPaymentTicketException;
import exceptions.ParkingLotIsFullException;
import payment.ParkingTicket;
import payment.PaymentMethod;
import payment.PaymentTicket;
import vehicles.Vehicle;

public interface ParkingLotFacade {
    ParkingTicket parkVehicle(Vehicle vehicle) throws ParkingLotIsFullException;
    void unparkVehicle(Vehicle vehicle, PaymentTicket paymentTicket) throws InvalidPaymentTicketException;
    PaymentTicket pay(ParkingTicket parkingTicket,
                      PaymentMethod paymentMethod) throws InvalidParkingTicketException;
}
