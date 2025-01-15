import airQuality.*;
import java.util.*;
public class AQT {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        qual tester=new qual();

        int[][] breakpoints=defineBreakpoints(scanner);

        boolean exit=false;

        while (!exit) {
            System.out.println("\n--- Air Quality Tester Menu ---");
            System.out.println("1. Input Pollutant Concentration");
            System.out.println("2. View Breakpoints");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice=scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter pollutant concentration: ");
                    double concentration=scanner.nextDouble();
                    int aqi=tester.calculateAQI(concentration, breakpoints);
                    String action=tester.suggestAction(aqi);

                    System.out.println("Calculated AQI: " + aqi);
                    System.out.println("Recommended Action: " + action);
                    break;

                case 2:
                    System.out.println("Current AQI Breakpoints:");
                    for (int[] range : breakpoints) {
                        System.out.printf("Concentration Range: %d to %d -> AQI: %d to %d%n",
                                range[0], range[1], range[2], range[3]);
                    }
                    break;

                case 3:
                    exit=true;
                    System.out.println("Exiting Air Quality Tester. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static int[][] defineBreakpoints(Scanner scanner) {
        System.out.println("Define AQI Breakpoints:");
        System.out.print("Enter the number of ranges: ");
        int n=scanner.nextInt();
        int[][] breakpoints=new int[n][4];
        for (int i=0; i < n; i++) {
            System.out.printf("Range %d:%n", i + 1);
            System.out.print("  Low Concentration: ");
            breakpoints[i][0]=scanner.nextInt();
            System.out.print("  High Concentration: ");
            breakpoints[i][1]=scanner.nextInt();
            System.out.print("  Low AQI: ");
            breakpoints[i][2]=scanner.nextInt();
            System.out.print("  High AQI: ");
            breakpoints[i][3]=scanner.nextInt();
        }
        return breakpoints;
    }
}
