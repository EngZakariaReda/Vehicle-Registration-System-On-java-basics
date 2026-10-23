package Models;
import java.util.Objects;

public abstract class Vehicle {
    private String plateNumber;
    private String ownerName;
    private String vehicleType;
    private Integer registrationYear  ;
    private String status;

    public Vehicle(String plateNumber, String vehicleType, String ownerName, Integer registrationYear, String status) {
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
        this.ownerName = ownerName;
        this.registrationYear = registrationYear;
        this.status = status;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(Integer registrationYear) {
        this.registrationYear = registrationYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" +
                 plateNumber + ']' + " | " +
                 vehicleType + " | " +
                "owner: " + ownerName + " | " +
                "Year: " + registrationYear + " | " +
                "Status: " + status + " | ";
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;
        return plateNumber.equalsIgnoreCase(vehicle.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber.toLowerCase());
    }

    public abstract String getRegistrationLabel();
}
