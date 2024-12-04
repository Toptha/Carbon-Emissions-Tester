public class EnergySource {
    private String energySourceId;
    private String sourceName;
    private String sourceType;
    private float carbonFootprint;
    public EnergySource(String energySourceId, String sourceName, String sourceType, float carbonFootprint) {
        this.energySourceId=energySourceId;
        this.sourceName=sourceName;
        this.sourceType=sourceType;
        this.carbonFootprint=carbonFootprint;
    }
    public void displayDetails() {
        System.out.println("Energy Source ID: "+energySourceId);
        System.out.println("Name: "+sourceName);
        System.out.println("Type: "+sourceType);
    }
}
