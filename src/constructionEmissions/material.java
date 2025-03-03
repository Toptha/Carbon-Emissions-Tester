package constructionEmissions;

public class material {
    private String name;
    private double usage;

    public material(String name, double usage) {
        this.name = name;
        this.usage = usage;
    }

    public String getName() {
        return name;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }
}
