import java.util.Scanner;
class Waste {
    String type;
    double weight;
    String material;

    Waste(String type, double weight, String material) {
        this.type=type;
        this.weight=weight;
        this.material=material;
    }

    public double calculateEmissions() {
        return weight*getMaterialFactor();
    }

    private double getMaterialFactor() {
        switch (material.toLowerCase()) {
            case "plastic": return 3.0;
            case "metal": return 2.0;
            case "organic": return 1.0;
            default: return 1.5;
        }
    }
}

class IndustrialWaste extends Waste {
    IndustrialWaste(double weight, String material) {
        super("Industrial", weight, material);
    }
 
    @Override
    public double calculateEmissions() {
        return super.calculateEmissions()*1.5;
    }
}

class HouseholdWaste extends Waste {
    HouseholdWaste(double weight, String material) {
        super("Household", weight, material);
    }

    @Override
    public double calculateEmissions() {
        return super.calculateEmissions()*0.9;
    }
}

public class WasteManagement {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Waste[] wastes=new Waste[100];
        int wasteCount=0;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Waste");
            System.out.println("2. View Emissions Report");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice=scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (wasteCount>=wastes.length) {
                        System.out.println("Error: Maximum capacity reached!");
                        break;
                    }
                    System.out.print("Enter type (Industrial/Household): ");
                    String type=scanner.nextLine();
                    System.out.print("Enter weight (kg): ");
                    double weight=scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter material (Plastic/Metal/Organic): ");
                    String material=scanner.nextLine();

                    if (type.equalsIgnoreCase("Industrial")) {
                        wastes[wasteCount]=new IndustrialWaste(weight, material);
                    } else if (type.equalsIgnoreCase("Household")) {
                        wastes[wasteCount]=new HouseholdWaste(weight, material);
                    } else {
                        System.out.println("Invalid type! Waste not added.");
                        break;
                    }
                    wasteCount++;
                    System.out.println("Waste added successfully!");
                    break;

                case 2:
                    if (wasteCount==0) {
                        System.out.println("No waste data available.");
                    } else {
                        System.out.println("\nEmissions Report:");
                        double totalEmissions=0;
                        for (int i=0; i<wasteCount; i++) {
                            Waste waste=wastes[i];
                            double emissions=waste.calculateEmissions();
                            totalEmissions += emissions;
                            System.out.printf("%d. %s (%s, %.2f kg): %.2f kg CO2%n",
                                    i+1, waste.type, waste.material, waste.weight, emissions);
                        }
                        System.out.printf("Total Carbon Emissions: %.2f kg CO2%n", totalEmissions);
                    }
                    break;

                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
