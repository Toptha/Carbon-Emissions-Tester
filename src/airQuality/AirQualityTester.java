package airQuality;

public interface AirQualityTester {
    int calculateAQI(double concentration, int[][] breakpoints);
    String suggestAction(int aqi);
}
