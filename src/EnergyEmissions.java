import java.util.*;
import energyEmissions.*;

public class EnergyEmissions {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        threader calculator=new threader();
        
        while (true) {
            System.out.println("\nEnergy Emissions Calculator Menu:");
            System.out.println("1. View Energy Source Options");
            System.out.println("2. Calculate Energy Emissions");
            System.out.println("3. View Background Tasks Status");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice=scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    calculator .displayEnergyOptions();
                    break;

                case 2:
                    System.out.print("Enter selected sources (comma separated): ");
                    String[] choices=scanner.nextLine().split(",");
                    List<Integer> selectedSources=new ArrayList<>();
                    for (String c : choices) {
                        selectedSources.add(Integer.parseInt(c.trim()));
                    }
                    System.out.print("Enter energy consumption (kWh or liters): ");
                    double energyConsumed=scanner.nextDouble();
                    double totalEmissions=calculator.calculateTotalEmissions(selectedSources, energyConsumed);
                    System.out.println("Total energy-related emissions: "+totalEmissions+" kg CO₂");
                    break;
                
                case 3:
                    calculator.displayThreadStatus();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    calculator.shutdown();
                    scanner.close();
                    return;

                default:
                    System.err.println("Invalid choice!");
            }
        }
    }
}