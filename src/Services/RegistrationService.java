package Services;
import Models.Vehicle;
import java.util.List;

public interface RegistrationService {
    void registerVehicle (Vehicle vehicle) ;
    Vehicle searchVehicle (String plateNumber) ;
    void updateOwnerName(String plate , String name) ;
    void deleteVehicle (String plateNumber) ;
    List<Vehicle> showAllVehicles () ;
    List<Vehicle> filterByVehicleType(String type) ;
    List<Vehicle> getVehiclesByOwnerName (String name) ;
    List<Vehicle> expiredRegistrations () ;
    void getStatisticsReport () ;
    Boolean DuplicateDetection (String plateNumber) ;
}
