package energyEmissions;
import java.util.concurrent.Callable;

public class logic implements Callable<Double> {
    private String energyType;
    private double energyConsumed;
    private double emissionFactor;

    public logic(String energyType, double energyConsumed, double emissionFactor) {
        this.energyType = energyType;
        this.energyConsumed = energyConsumed;
        this.emissionFactor = emissionFactor;
    }

    @Override
    public Double call() {
        double emissions = energyConsumed * emissionFactor;
        System.out.println(energyType + " emissions: " + emissions + " kg CO₂");
        return emissions;
    }
}

