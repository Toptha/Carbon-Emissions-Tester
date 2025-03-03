import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import constructionEmissions.data;
import constructionEmissions.material;
import constructionEmissions.constructionmethod;
public class consEmission {
     public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        Map<String, Double> emissionData=data.getEmissionData();

        List<material> materials=new ArrayList<>();

        constructionmethod constructionMethods=new constructionmethod();

        int choice;
        while(true) {
            System.out.println("\nConstruction & Infrastructure Emissions Calculator");
            System.out.println("1. Add/Remove/Modify Material Usage");
            System.out.println("2. List All Materials & Usage");
            System.out.println("3. Calculate Total Emissions");
            System.out.println("4. View Construction Methods");
            System.out.println("5. Add/Remove Construction Method");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice=scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("what action do you want to perform?");
                    System.out.println("1. Add material usage");
                    System.out.println("2. Remove a material");
                    System.out.println("3. Modify material usage");
                    System.out.print("Enter your choice: ");
                    int action=scanner.nextInt();
                    scanner.nextLine();
                    if (action==1){
                        System.out.println("Available Materials: "+emissionData.keySet());
                        System.out.print("Enter material name: ");
                        String materialName=scanner.nextLine();
    
                        if (!emissionData.containsKey(materialName)) {
                            System.out.println("Invalid material. Please choose from the list.");
                            break;
                        }
    
                        System.out.print("Enter material usage (in tons): ");
                        double usage=scanner.nextDouble();
    
                        materials.add(new material(materialName, usage));
                        System.out.println("Material added successfully!");
                        
                    }else if (action==2){
                        if (materials.isEmpty()) {
                            System.out.println("No materials to remove.");
                        } else {
                            System.out.print("Enter material name to remove: ");
                            String removeMaterial=scanner.nextLine();
                            material materialToRemove=null;
                            for (material m : materials) {
                                if (m.getName().equals(removeMaterial)) {
                                    materialToRemove=m;
                                    break;
                                }
                            }
                            if (materialToRemove != null) {
                                materials.remove(materialToRemove);
                                System.out.println("Material removed successfully.");
                            } else {
                                System.out.println("Material not found.");
                            }
                        }
                    }else if (action==3){
                        if (materials.isEmpty()) {
                            System.out.println("No materials to modify.");
                        } else {
                            System.out.print("Enter material name to modify usage: ");
                            String modifyMaterial=scanner.nextLine();
                            material materialToModify=null;
                            for (material m : materials) {
                                if (m.getName().equals(modifyMaterial)) {
                                    materialToModify=m;
                                    break;
                                }
                            }
                            if (materialToModify != null) {
                                System.out.print("Enter new material usage (in tons): ");
                                double newUsage=scanner.nextDouble();
                                materialToModify.setUsage(newUsage);
                                System.out.println("Material usage updated.");
                            } else {
                                System.out.println("Material not found.");
                            }
                        }
                    }else{
                        System.err.println("Invalid choice! Please try again.");
                    }
                    break;

                case 2:
                    if (materials.isEmpty()) {
                        System.out.println("No materials added yet.");
                    } else {
                        System.out.println("Materials List:");
                        for (material m : materials) {
                            System.out.println(m.getName()+" - Usage: "+m.getUsage()+" tons");
                        }
                    }
                    break;

                case 3:
                    if (materials.isEmpty()) {
                        System.out.println("No materials added yet.");
                    } else {
                        double totalEmissions=0;
                        for (material m : materials) {
                            totalEmissions += m.getUsage() * emissionData.get(m.getName());
                        }
                        System.out.println("Total Construction Emissions: "+totalEmissions+" kg CO2");
                    }
                    break;

                case 4:
                System.out.print("Enter construction method to check: ");
                String checkMethod=scanner.nextLine();
                if (constructionMethods.containsMethod(checkMethod)) {
                    System.out.println("Yes, this method exists.");
                } else {
                    System.out.println("No, this method is not available.");
                }
                System.out.println("Available Construction Methods: "+constructionMethods.getMethods());
                break;

                case 5:
                    System.out.println("What action do you want to perform?");
                    System.out.println("1. Add a Constuction Method ");
                    System.out.println("2. Remove a Construction Method");
                    System.out.println("Enter your Choice:");
                    int action1=scanner.nextInt();
                    scanner.nextLine();
                    if (action1==1){
                        System.out.print("Enter new construction method: ");
                        String method=scanner.nextLine();
                        if (constructionMethods.addMethod(method)) {
                            System.out.println("Construction method added!");
                        } else {
                            System.out.println("This method already exists.");
                        }
                    }else if (action1==2){
                        System.out.print("Enter construction method to remove: ");
                        String removeMethod=scanner.nextLine();
                        if (constructionMethods.removeMethod(removeMethod)) {
                            System.out.println("Construction method removed.");
                        } else {
                            System.out.println("Method not found.");
                        }
                    }else{
                        System.err.println("Invalid choice! Please try again.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }


    }
}
