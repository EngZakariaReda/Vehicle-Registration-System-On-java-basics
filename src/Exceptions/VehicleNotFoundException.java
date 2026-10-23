package Exceptions;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String plateNumber) {
        super("Plate number already registered: " + plateNumber);
    }
}
