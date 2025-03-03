
import airQuality.*;
import java.util.*;

public class AQT {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        qual tester=new qual();
        try {
            int[][] breakpoints=defineBreakpoints(scanner);
            boolean exit=false;
            while(!exit) {
                System.out.println("\n--- Air Quality Tester Menu ---");
                System.out.println("1. Input Pollutant Concentration");
                System.out.println("2. View Breakpoints");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                try {
                    int choice=scanner.nextInt();
                    
                    switch(choice) {
                        case 1:
                            try {
                                System.out.print("Enter pollutant concentration: ");
                                double concentration=scanner.nextDouble();
                                if(concentration<0) {
                                    throw new IllegalArgumentException("Concentration cannot be negative.");
                                }
                                int aqi=tester.calculateAQI(concentration, breakpoints);
                                String action=tester.suggestAction(aqi);
                                System.out.println("Calculated AQI: "+aqi);
                                System.out.println("Recommended Action: "+action);
                            } catch(IllegalArgumentException e) {
                                System.out.println("Error: "+e.getMessage());
                            }
                            break;
                        case 2:
                            System.out.println("Current AQI Breakpoints:");
                            for(int[] range:breakpoints) {
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
                } catch(InputMismatchException e) {
                    System.out.println("Error: Invalid input. Please enter a number.");
                    scanner.next(); 
                } finally {
                    System.out.println("Returning to the main menu...");
                }
            }
        } catch(Exception e) {
            System.out.println("Unexpected error: "+e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static int[][] defineBreakpoints(Scanner scanner) throws IllegalArgumentException, InputMismatchException {
        System.out.println("Define AQI Breakpoints:");
        System.out.print("Enter the number of ranges: ");
        int n=scanner.nextInt();
        if(n<= 0) {
            throw new IllegalArgumentException("Number of ranges must be positive.");
        }
        int[][] breakpoints=new int[n][4];
        for(int i=0; i<n; i++) {
            System.out.printf("Range %d:%n", i+1);
            try {
                System.out.print("  Low Concentration: ");
                breakpoints[i][0]=scanner.nextInt();
                System.out.print("  High Concentration: ");
                breakpoints[i][1]=scanner.nextInt();
                System.out.print("  Low AQI: ");
                breakpoints[i][2]=scanner.nextInt();
                System.out.print("  High AQI: ");
                breakpoints[i][3]=scanner.nextInt();
            } catch(InputMismatchException e) {
                throw new InputMismatchException("Invalid input. Please enter integer values.");
            }
        }
        return breakpoints;
    }
}
