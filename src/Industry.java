public class Industry {
    private String industryId;
    private String industryName;
    private String industryType;
    float annualEnergyConsumption;
    float annualCarbonEmissions;
    public Industry(String industryId, String industryName, String industryType,
                    float annualEnergyConsumption, float annualCarbonEmissions) {
        this.industryId=industryId;
        this.industryName=industryName;
        this.industryType=industryType;
        this.annualEnergyConsumption=annualEnergyConsumption;
        this.annualCarbonEmissions=annualCarbonEmissions;
    }
    public void displayDetails() {
        System.out.println("Industry ID: "+industryId);
        System.out.println("Name: "+industryName);
        System.out.println("Type: "+industryType);
    }
}
