package Services.Impl;
import Exceptions.DuplicatePlateException;
import Exceptions.InvalidInputException;
import Exceptions.VehicleNotFoundException;
import Models.*;
import Services.ApplicationService;
import Utils.InputValidator;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ApplicationServiceImpl implements ApplicationService {
    private final Scanner scanner = new Scanner(System.in);
    private InputValidator inputValidator = new InputValidator(scanner);
    private RegistrationServiceImpl registrationService  = new RegistrationServiceImpl();

    @Override
    public void start() {
        int maxAttempt = 5 ;
        int attempts = 0 ;

        while (true) {

            System.out.println("========================================");
            System.out.println("1. Register New Vehicle");
            System.out.println("2. Search Vehicle by Plate");
            System.out.println("3. Update Owner Name");
            System.out.println("4. Delete Vehicle");
            System.out.println("5. List All Vehicles");
            System.out.println("6. Filter by Vehicle Type");
            System.out.println("7. Show Owner History");
            System.out.println("8. Show Expired Registrations");
            System.out.println("9. Statistics Report");
            System.out.println("0. Exit");
            System.out.println("========================================");

            int choice = inputValidator.readInteger("Enter your choice:");
            switch (choice){
                case 0:
                    System.out.println("Exiting the system...");
                    return;

                case 1 :
                    registerVehicle();
                    break;

                case 2 :
                    searchVehicle();
                    break;

                case 3 :
                    updateOwnerName();
                    break;

                case 4 :
                    deleteVehicle();
                    break;

                case 5 :
                    showAllVehicles();
                    break;

                case 6 :
                    filterByVehicleType();
                    break;

                case 7 :
                    getVehiclesByOwnerName();
                    break;

                case 8 :
                    expiredRegistrations();
                    break;

                case 9 :
                    getStatisticsReport();
                    break;

                default:
                    attempts++ ;
                    break;

            }

            if (attempts == maxAttempt){
                System.out.println("your attempts finished");
                break ;
            }
        }
    }

    private void registerVehicle() {

        String plateNumber =
                inputValidator.readString("Please enter plate number");

        String ownerName =
                inputValidator.readString("Please enter your name");

        String vehicleType =
                inputValidator.readString("Please enter vehicle type");

        int registrationYear =
                inputValidator.readInteger("Please enter registration year");

        try {
            InputValidator.validatePlateNumber(plateNumber);
            InputValidator.validateOwnerName(ownerName);
            InputValidator.validateVehicleType(vehicleType);
            InputValidator.validateRegistrationYear(registrationYear);

            String status = LocalDate.now().getYear() - registrationYear > 5
                    ? "EXPIRED"
                    : "ACTIVE";

            Vehicle newVehicle;

            if (vehicleType.equalsIgnoreCase("Car")) {
                newVehicle = new Car(
                        plateNumber,
                        ownerName,
                        registrationYear,
                        status,
                        4
                );

            } else if (vehicleType.equalsIgnoreCase("Truck")) {
                newVehicle = new Truck(
                        plateNumber,
                        ownerName,
                        registrationYear,
                        status,
                        4.5
                );

            } else {

                newVehicle = new Motorcycle(
                        plateNumber.toUpperCase(),
                        ownerName,
                        registrationYear,
                        status,
                        "sport"
                );
            }

            registrationService.registerVehicle(newVehicle);
            System.out.println("Vehicle registered successfully!");

        } catch (InvalidInputException | DuplicatePlateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchVehicle () {
        String plateNumber =
                inputValidator.readString("Please enter plate number");

        try {
            Vehicle vehicle = registrationService.searchVehicle(plateNumber);
            System.out.println(vehicle);

        } catch (VehicleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    };

    private void updateOwnerName() {
        String plateNumber =
                inputValidator.readString("Please enter plate number");

        String newName =
                inputValidator.readString("Please enter new name");

        try {
            registrationService.updateOwnerName(plateNumber , newName);
        } catch (VehicleNotFoundException | InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    };

    private void deleteVehicle (){
        String plateNumber =
                inputValidator.readString("Please enter plate number");

        try {
            registrationService.deleteVehicle(plateNumber);
            System.out.println("the vehicle deleted successfully");
        } catch (VehicleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    } ;

    private void showAllVehicles (){
        List<Vehicle> vehicles = registrationService.showAllVehicles();

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
            return;
        }

        vehicles.forEach(System.out::println);
    };

    private void filterByVehicleType() {
        String type =
                inputValidator.readString("Please enter type of vehicle");

        List<Vehicle> vehicles =
                registrationService.filterByVehicleType(type);

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found for type: " + type);
            return;
        }

        vehicles.forEach(System.out::println);
    };

    private void getVehiclesByOwnerName () {
        String name =
                inputValidator.readString("Please enter the owner name");

        List<Vehicle> listOwned =
                registrationService.getVehiclesByOwnerName(name);

        if (listOwned.isEmpty()) {
            System.out.println("No vehicles found for owner: " + name);
            return;
        }

        listOwned.forEach(System.out::println);
    };

    private void expiredRegistrations () {
        List<Vehicle> expiredVehicles =
                registrationService.expiredRegistrations();

        if (expiredVehicles.isEmpty()) {
            System.out.println("No expired registrations found.");
            return;
        }

        expiredVehicles.forEach(System.out::println);
    };

    private void getStatisticsReport () {
        registrationService.getStatisticsReport();
    };

}
