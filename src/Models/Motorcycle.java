package Models;

public class Motorcycle extends Vehicle {
    private String engineType;

    public Motorcycle(String plateNumber, String ownerName, Integer registrationYear, String status, String engineType) {
        super(plateNumber, "Motorcycle", ownerName, registrationYear, status);
        this.engineType = engineType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return super.toString()+
                "EngineType: " + engineType ;

    }

    @Override
    public String getRegistrationLabel() {
        return toString();
    }
}
