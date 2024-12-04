public class Vehicle {
    private String vehicleId;
    private String manufacturer;
    private String model;
    String fuelType;
    float engineSize;
    int yearOfManufacture;
    float averageMileage;
    float emissionRate;
    public Vehicle(String vehicleId, String manufacturer, String model, String fuelType,
                   float engineSize, int yearOfManufacture, float averageMileage, float emissionRate) {
        this.vehicleId=vehicleId;
        this.manufacturer=manufacturer;
        this.model=model;
        this.fuelType=fuelType;
        this.engineSize=engineSize;
        this.yearOfManufacture=yearOfManufacture;
        this.averageMileage=averageMileage;
        this.emissionRate=emissionRate;
    }
    public void displayDetails() {
        System.out.println("Vehicle ID: "+vehicleId);
        System.out.println("Manufacturer: "+manufacturer);
        System.out.println("Model: "+model);
    }
}
