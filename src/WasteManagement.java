public class WasteManagement {
    private String wasteId;
    private String wasteType;
    float quantityGenerated;
    public WasteManagement(String wasteId, String wasteType, float quantityGenerated) {
        this.wasteId = wasteId;
        this.wasteType = wasteType;
        this.quantityGenerated = quantityGenerated;
    }
    public void displayDetails() {
        System.out.println("Waste ID: "+wasteId);
        System.out.println("Type: "+wasteType);
        System.out.println("Generated Quantity: "+quantityGenerated+" tons/year");
    }
}
