
import java.util.Scanner;

public class CFT {
    public static final double CO2perTree=22.0;
    public static final double CO2perCredit=1000.0;
    public static final double CO2perSolar=0.5;
    public static final double CO2perWind=0.7;
    public static double calculateOffsetByTreesPlanted(int trees) {
        return trees*CO2perTree;
    }

    public static double calculateOffsetByCarbonCredits(double creditsBought) {
        return creditsBought*CO2perCredit;
    }

    public static double calculateOffsetBySolarEnergy(double kWhGenerated) {
        return kWhGenerated*CO2perSolar;
    }

    public static double calculateOffsetByWindEnergy(double kWhGenerated) {
        return kWhGenerated*CO2perWind;
    }

    public static int getValidInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.err.print("Invalid input.\n Please enter a valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    public static double getValidDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.err.print("Invalid input.\n Please enter a valid number: ");
            sc.next();
        }
        return sc.nextDouble();
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int choice;
        String off="Offset: ";
        String kg=" kg CO2";
        do {
            System.out.println("\n===Carbon Offset Calculator Menu===");
            System.out.println("1. Calculate Offset by Trees Planted");
            System.out.println("2. Calculate Offset by Carbon Credits");
            System.out.println("3. Calculate Offset by Solar Energy");
            System.out.println("4. Calculate Offset by Wind Energy");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice=getValidInt(sc);
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter the number of trees planted: ");
                    int trees=getValidInt(sc);
                    double treeOffset=calculateOffsetByTreesPlanted(trees);
                    System.out.println(off+treeOffset+kg);
                    if (trees >= 100) {
                        System.out.println("Congrats On Planting so many Trees!!");
                    }
                    break;
                case 2:
                    System.out.print("Enter the number of carbon credits purchased: ");
                    double credits=getValidDouble(sc);
                    double creditOffset=calculateOffsetByCarbonCredits(credits);
                    System.out.println(off+creditOffset+kg);
                    if (credits >= 1000) {
                        System.out.println("Large carbon credit purchase detected!!");
                    }
                    break;
                case 3:
                    System.out.print("Enter the kWh of solar energy generated: ");
                    double solarKWh=getValidDouble(sc);
                    double solarOffset=calculateOffsetBySolarEnergy(solarKWh);
                    System.out.println(off+solarOffset+kg);
                    if (solarKWh >= 500) {
                        System.out.println("Great solar energy generation!!");
                    }
                    break;
                case 4:
                    System.out.print("Enter the kWh of wind energy generated: ");
                    double windKWh=getValidDouble(sc);
                    double windOffset=calculateOffsetByWindEnergy(windKWh);
                    System.out.println(off+windOffset+kg);
                    if (windKWh >= 500) {
                        System.out.println("Great wind energy generation!!");
                    }
                    break;
                case 5:
                    System.out.println("Exiting..");
                    break;
                default:
                    System.err.println("Invalid choice!");
            }
        } while (choice!=5);
        sc.close();
    }
}
