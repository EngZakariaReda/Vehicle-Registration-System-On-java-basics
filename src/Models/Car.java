package Models;

public class Car extends Vehicle{

    private Integer numberOfDoors = 4 ;

    public Car(String plateNumber, String ownerName, Integer registrationYear, String status ,Integer numberOfDoors) {
        super(plateNumber, "Car", ownerName, registrationYear, status);
        this.numberOfDoors = numberOfDoors;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String toString() {
        return super.toString()+
                "NumberOfDoors: " + numberOfDoors ;

    }

    @Override
    public String getRegistrationLabel() {
        return toString();
    }
}
