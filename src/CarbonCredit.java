public class CarbonCredit {
    private String creditId;
    float creditAmount;
    public CarbonCredit(String creditId, float creditAmount) {
        this.creditId=creditId;
        this.creditAmount=creditAmount;
    }
    public void displayDetails() {
        System.out.println("Credit ID: "+creditId);
        System.out.println("Credit Amount: "+creditAmount+" tons of CO2");
    }
}
