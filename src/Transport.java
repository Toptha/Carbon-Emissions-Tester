public class Transport {
    private String systemId;
    private String type;
    int dailyPassengers;
    public Transport(String systemId, String type, int dailyPassengers) {
        this.systemId=systemId;
        this.type=type;
        this.dailyPassengers=dailyPassengers;
    }
    public void displayDetails() {
        System.out.println("System ID: "+systemId);
        System.out.println("Type: "+type);
        System.out.println("Daily Passengers: "+dailyPassengers);
    }
}
