import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Swing {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Swing().createGUI());
    }

    public void createGUI() {
        JFrame frame = new JFrame("Carbon Emissions Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));
        
        JButton airQualityButton = new JButton("Air Quality Testing");
        JButton carbonFootprintButton = new JButton("Carbon Footprint Calculator");
        JButton constructionEmissionsButton = new JButton("Construction Emissions Calculator");
        JButton energyEmissionsButton = new JButton("Energy Emissions Calculator");
        
        airQualityButton.addActionListener(e -> openAirQualityWindow());
        carbonFootprintButton.addActionListener(e -> openCarbonFootprintWindow());
        constructionEmissionsButton.addActionListener(e -> openConstructionWindow());
        energyEmissionsButton.addActionListener(e -> openEnergyEmissionsWindow());
        
        mainPanel.add(airQualityButton);
        mainPanel.add(carbonFootprintButton);
        mainPanel.add(constructionEmissionsButton);
        mainPanel.add(energyEmissionsButton);
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    private void openAirQualityWindow() {
        JFrame frame = new JFrame("Air Quality Testing");
        frame.setSize(400, 300);
        frame.add(new JLabel("Running Air Quality Testing..."));
        frame.setVisible(true);
        runProgram("AQT");
    }

    private void openCarbonFootprintWindow() {
        JFrame frame = new JFrame("Carbon Footprint Calculator");
        frame.setSize(400, 300);
        frame.add(new JLabel("Running Carbon Footprint Calculator..."));
        frame.setVisible(true);
        runProgram("CFT");
    }
    
    private void openConstructionWindow() {
        JFrame frame = new JFrame("Construction Emissions Calculator");
        frame.setSize(400, 300);
        frame.add(new JLabel("Running Construction Emissions Calculator..."));
        frame.setVisible(true);
        runProgram("consEmission");
    }

    private void openEnergyEmissionsWindow() {
        JFrame frame = new JFrame("Energy Emissions Calculator");
        frame.setSize(400, 300);
        frame.add(new JLabel("Running Energy Emissions Calculator..."));
        frame.setVisible(true);
        runProgram("EnergyEmissions");
    }

    private void runProgram(String className) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "out", className);
            processBuilder.inheritIO();
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
}
