public class Household {
    private String householdId;
    private int numberOfResidents;
    float monthlyElectricityUsage;
    public Household(String householdId, int numberOfResidents, float monthlyElectricityUsage) {
        this.householdId=householdId;
        this.numberOfResidents=numberOfResidents;
        this.monthlyElectricityUsage=monthlyElectricityUsage;
    }
    public void displayDetails() {
        System.out.println("Household ID: "+householdId);
        System.out.println("Residents: "+numberOfResidents);
    }
}
