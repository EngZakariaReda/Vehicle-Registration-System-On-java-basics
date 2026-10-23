package Utils;
import Exceptions.InvalidInputException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    private Scanner input ;
    private static final int CURRENT_YEAR = LocalDate.now().getYear();

    public InputValidator(Scanner sc){
        this.input = sc ;
    }

    public String readString (String prompt){
        System.out.println(prompt);
        return input.nextLine();
    }

    public Integer readInteger (String prompt){
        System.out.println(prompt);
        while (true){
            try{
                int integerValue = input.nextInt();
                input.nextLine();
                return integerValue ;
            } catch (InputMismatchException e) {
                System.out.println("please inter invalid number");
                input.nextLine();
            }
        }
    }

    public static void validatePlateNumber(String plateNumber) {
        if (plateNumber == null || !plateNumber.matches("[A-Za-z0-9-]{3,10}")) {
            throw new InvalidInputException("Plate number must be 3-10 alphanumeric characters");
        }
    }

    public static void validateOwnerName(String ownerName) {
        if (ownerName == null || ownerName.trim().length() < 3 || !ownerName.matches("[a-zA-Z]+")) {
            throw new InvalidInputException( "Owner name must contain only letters & length must be more than 3 " );
        }
    }

    public static void validateVehicleType(String vehicleType) {
        if (vehicleType == null || !(vehicleType.equalsIgnoreCase("Car") ||
                vehicleType.equalsIgnoreCase("Truck")
                || vehicleType.equalsIgnoreCase("Motorcycle"))) {
            throw new InvalidInputException( "Invalid type. Use: Car, Truck, Motorcycle" );
        }
    }

    public static void validateRegistrationYear(int registrationYear) {
        if (registrationYear < 1990 || registrationYear > CURRENT_YEAR) {
            throw new InvalidInputException( "Year must be between 1990 and " + CURRENT_YEAR );
        }
    }

}
