
import java.util.Scanner;
public class Vehicle {
    private String vehicleId;
    private String fuelType;
    private float averageMileage;
    private float emission;
    private float kilometersDriven; 
    public Vehicle(){
        vehicleId="";
        fuelType="";
        averageMileage=0;
        emission=0;
        kilometersDriven=0;
    }
    public Vehicle(String vehicleId, String fuelType, float averageMileage, float kilometersDriven) {
        this.vehicleId=vehicleId;
        this.fuelType=fuelType;
        this.averageMileage=averageMileage;
        this.kilometersDriven=kilometersDriven;
    }
    public void calculate() {
        float annualLiters;
        int carbonContent;
        if (fuelType.equalsIgnoreCase("Petrol")) {
            carbonContent=12;
        } else if (fuelType.equalsIgnoreCase("Diesel")) {
            carbonContent=18;
        } else {
            System.err.println("Invalid fuel type.");
            return;
        }
        annualLiters=kilometersDriven/averageMileage;
        emission=annualLiters*carbonContent;
    }
    public void calculate(float customCarbonContent) {
        float annualLiters=kilometersDriven/averageMileage;
        emission=annualLiters*customCarbonContent;
    }
    public void display() {
        System.out.println("Vehicle ID: "+vehicleId);
        System.out.println("Fuel Type: "+fuelType);
        System.out.println("Average Mileage: "+averageMileage+" km/l");
        System.out.println("Emission Rate: "+emission+"gms");
        System.out.println("Kilometers Driven: "+kilometersDriven);
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        Vehicle[] vehicles=new Vehicle[100];
        int vehicleCount=0;
        while (true) {
            System.out.println("=== Carbon Emissions Tester ===");
            System.out.println("1. Add a Vehicle");
            System.out.println("2. Display all Vehicles");
            System.out.println("3. Calculate Carbon Emissions Manually with Carbon Content");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice=s.nextInt();
            s.nextLine();
            switch (choice) {
                case 1:
                    if (vehicleCount<vehicles.length) {
                        System.out.println("Enter details for vehicle "+(vehicleCount+1)+":");
                        System.out.print("Vehicle ID: ");
                        String vehicleId=s.nextLine();
                        System.out.print("Fuel Type (Petrol/Diesel): ");
                        String fuelType=s.nextLine();
                        System.out.print("Average Mileage (in km/l): ");
                        float averageMileage=s.nextFloat();
                        s.nextLine();
                        System.out.print("Kilometers Driven: ");
                        float kilometersDriven=s.nextFloat();
                        s.nextLine();
                        vehicles[vehicleCount]=new Vehicle(vehicleId, fuelType, averageMileage, kilometersDriven);
                        vehicles[vehicleCount].calculate();
                        vehicleCount++;
                        System.out.println("Vehicle added successfully!");
                    } else {
                        System.out.println("Vehicle array is full.");
                    }
                    break;
                case 2:
                    if (vehicleCount>0) {
                        System.out.println("\nDetails of all vehicles:");
                        for (int i=0; i<vehicleCount; i++) {
                            vehicles[i].display();
                        }
                    } else {
                        System.out.println("No vehicles to display.");
                    }
                    break;
                    case 3:
                    if (vehicleCount > 0) {
                        System.out.print("Enter custom carbon content for recalculation: ");
                        float customCarbonContent=s.nextFloat();
                        s.nextLine();
                        for (int i=0;i<vehicleCount;i++) {
                            vehicles[i].calculate(customCarbonContent);
                        }
                        System.out.println("Emissions recalculated with custom carbon content!");
                    } else {
                        System.out.println("No vehicles available to recalculate.");
                    }
                    break;
                    case 4:
                            System.out.println("Exiting program...");
                            s.close();
                            return;
                default:
                    System.err.println("Invalid choice, please try again.");
            }
        }
    }
}