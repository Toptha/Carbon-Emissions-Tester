package airQuality;
public class qual implements AirQualityTester {

    @Override
    public int calculateAQI(double concentration, int[][] breakpoints) {
        for (int[] range : breakpoints) {
            double lowConcentration = range[0];
            double highConcentration = range[1];
            int lowAQI = range[2];
            int highAQI = range[3];

            if (concentration >= lowConcentration && concentration <= highConcentration) {
                return (int) ((highAQI - lowAQI) / (highConcentration - lowConcentration)
                        * (concentration - lowConcentration) + lowAQI);
            }
        }
        return -1; 
    }

    @Override
    public String suggestAction(int aqi) {
        if (aqi <= 50) {
            return "Good: No action needed.";
        } else if (aqi <= 100) {
            return "Moderate: Sensitive groups should limit outdoor activity.";
        } else if (aqi <= 150) {
            return "Unhealthy for Sensitive Groups: Reduce outdoor activities.";
        } else if (aqi <= 200) {
            return "Unhealthy: Minimize outdoor exposure.";
        } else if (aqi <= 300) {
            return "Very Unhealthy: Stay indoors and limit physical exertion.";
        } else {
            return "Hazardous: Avoid all outdoor activities.";
        }
    }
}
