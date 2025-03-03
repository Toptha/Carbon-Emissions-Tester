package constructionEmissions;

import java.util.HashMap;
import java.util.Map;

public class data {
        public static Map<String, Double> getEmissionData() {
        Map<String, Double> emissionData = new HashMap<>();
        emissionData.put("Cement", 500.0);
        emissionData.put("Steel", 1800.0);
        emissionData.put("Wood", 20.0);
        emissionData.put("Glass", 150.0);
        emissionData.put("Bricks", 250.0);
        emissionData.put("Concrete", 300.0);
        return emissionData;
    }
}
