package payment;

public interface PaymentMethod {
     void pay(double paymentSum) throws PaymentException;
}
