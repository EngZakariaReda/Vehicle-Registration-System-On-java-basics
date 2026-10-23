package Models;

public class Truck extends Vehicle {

    private Double cargoCapacityTons;

    public Truck(String plateNumber, String ownerName, Integer registrationYear, String status, Double cargoCapacityTons) {
        super(plateNumber, "Truck", ownerName, registrationYear, status);
        this.cargoCapacityTons = cargoCapacityTons;
    }

    public Double getCargoCapacityTons() {
        return cargoCapacityTons;
    }

    public void setCargoCapacityTons(Double cargoCapacityTons) {
        this.cargoCapacityTons = cargoCapacityTons;
    }

    @Override
    public String toString() {
        return super.toString()+
                "CargoCapacityTons: " + cargoCapacityTons ;

    }

    @Override
    public String getRegistrationLabel() {
        return toString();
    }
}
