public class AirQuality {
    private String recordId;
    private String location;
    private float pm25Level;
    public AirQuality(String recordId, String location, float pm25Level) {
        this.recordId=recordId;
        this.location=location;
        this.pm25Level=pm25Level;
    }
    public void displayDetails() {
        System.out.println("Record ID: "+recordId);
        System.out.println("Location: "+location);
        System.out.println("PM2.5 Level: "+pm25Level+" µg/m³");
    }
}
