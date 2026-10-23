package Services.Impl;
import Exceptions.DuplicatePlateException;
import Exceptions.VehicleNotFoundException;
import Models.Vehicle;
import Services.RegistrationService;
import Utils.InputValidator;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class RegistrationServiceImpl implements RegistrationService {
    private final List<Vehicle> vehicleList = new ArrayList<>();
    private final Map<String, Vehicle> plateIndex = new HashMap<>();
    private final Set<String> registeredPlates = new HashSet<>();

    @Override
    public void registerVehicle(Vehicle vehicle) {
        String plate = vehicle.getPlateNumber().toUpperCase();

        if (DuplicateDetection(plate)) {
            throw new DuplicatePlateException(plate);
        }

        registeredPlates.add(plate);
        plateIndex.put(plate, vehicle);
        vehicleList.add(vehicle);
    }

    @Override
    public Vehicle searchVehicle(String plateNumber) {
        Vehicle isExisted = plateIndex.get(plateNumber.toUpperCase());

        if (isExisted == null){
            throw new VehicleNotFoundException(plateNumber);
        }

        return isExisted ;
    }

    @Override
    public void updateOwnerName(String plate , String name) {
        Vehicle vehicle = searchVehicle(plate);
        InputValidator.validateOwnerName(name);
        vehicle.setOwnerName(name);
    }

    @Override
    public void deleteVehicle(String plateNumber) {
        Vehicle vehicle = searchVehicle(plateNumber);
        plateNumber = plateNumber.toUpperCase();

        plateIndex.remove(plateNumber);
        registeredPlates.remove(plateNumber);
        vehicleList.remove(vehicle);
    }

    @Override
    public List<Vehicle> showAllVehicles() {
        return Collections.unmodifiableList(vehicleList);
    }

    @Override
    public List<Vehicle> filterByVehicleType(String type) {
        return vehicleList.stream().filter(vehicle -> vehicle.getVehicleType()
                .equalsIgnoreCase(type)).toList();
    }

    @Override
    public List<Vehicle> getVehiclesByOwnerName(String name) {
        return vehicleList.stream()
                .filter(vehicle -> vehicle.getOwnerName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

    }

    @Override
    public List<Vehicle> expiredRegistrations() {
        int currentYear = LocalDate.now().getYear();

        return vehicleList.stream()
                .filter(vehicle -> currentYear - vehicle.getRegistrationYear() > 5)
                .collect(Collectors.toList());
    }

    @Override
    public void getStatisticsReport() {
        IntSummaryStatistics stats = vehicleList.stream()
                .mapToInt(Vehicle::getRegistrationYear)
                .summaryStatistics();

        Map<String, Long> byType = vehicleList.stream()
                .collect(Collectors.groupingBy(
                        Vehicle::getVehicleType,
                        Collectors.counting()
                ));

        Map<Boolean, Long> byStatus = vehicleList.stream()
                .collect(Collectors.partitioningBy(
                        v -> v.getStatus().equalsIgnoreCase("ACTIVE"),
                        Collectors.counting()
                ));

        System.out.println("\n========== REGISTRATION STATISTICS ==========");

        System.out.printf("Total Vehicles   : %d%n", stats.getCount());
        System.out.printf("Average Year     : %.0f%n", stats.getAverage());
        System.out.printf("Newest Vehicle   : %d%n", stats.getMax());
        System.out.printf("Oldest Vehicle   : %d%n", stats.getMin());

        System.out.println("----------------------------------------------");

        System.out.println("Vehicles by Type:");

        System.out.printf("   Car           : %2d%n",
                byType.getOrDefault("Car", 0L));

        System.out.printf("   Truck         : %2d%n",
                byType.getOrDefault("Truck", 0L));

        System.out.printf("   Motorcycle    : %2d%n",
                byType.getOrDefault("Motorcycle", 0L));

        System.out.println("----------------------------------------------");

        System.out.printf("ACTIVE vehicles : %2d%n",
                byStatus.get(true));

        System.out.printf("EXPIRED vehicles: %2d%n",
                vehicleList.stream()
                        .filter(v -> v.getStatus().equalsIgnoreCase("EXPIRED"))
                        .count());

        System.out.println("==============================================");
    }

    @Override
    public Boolean DuplicateDetection(String plateNumber) {
        return registeredPlates.contains(plateNumber);
    }
}
