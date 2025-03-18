import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class CarbonOffset {
    private static final String URL = "jdbc:mysql://localhost:3306/carbon_offset_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private JFrame frame;
    private JTextField vehicleField, distanceField, idField;
    private JComboBox<String> fuelBox;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    public CarbonOffset() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame = new JFrame("Carbon Emissions Offset Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Vehicle Details"));
        inputPanel.setBackground(Color.DARK_GRAY);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel idLabel = new JLabel("ID:");
        idLabel.setForeground(Color.WHITE);
        inputPanel.add(idLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        idField = new JTextField();
        inputPanel.add(idField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        JLabel vehicleLabel = new JLabel("Vehicle Type:");
        vehicleLabel.setForeground(Color.WHITE);
        inputPanel.add(vehicleLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        vehicleField = new JTextField();
        inputPanel.add(vehicleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        JLabel fuelLabel = new JLabel("Fuel Type:");
        fuelLabel.setForeground(Color.WHITE);
        inputPanel.add(fuelLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        fuelBox = new JComboBox<>(new String[]{"Petrol", "Diesel", "Electric"});
        inputPanel.add(fuelBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        JLabel distanceLabel = new JLabel("Distance (km):");
        distanceLabel.setForeground(Color.WHITE);
        inputPanel.add(distanceLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        distanceField = new JTextField();
        inputPanel.add(distanceField, gbc);
   
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.DARK_GRAY);
        
        JButton addButton = new JButton("Add Record");
        addButton.addActionListener(this::addRecord);
        buttonPanel.add(addButton);
        
        JButton updateButton = new JButton("Update Record");
        updateButton.addActionListener(this::updateRecord);
        buttonPanel.add(updateButton);
        
        JButton deleteButton = new JButton("Delete Record");
        deleteButton.addActionListener(this::deleteRecord);
        buttonPanel.add(deleteButton);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        inputPanel.add(buttonPanel, gbc);

        frame.add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Vehicle", "Fuel", "Distance", "Emission", "Trees"}, 0);
        dataTable = new JTable(tableModel);

        dataTable.setFillsViewportHeight(true);
        dataTable.setRowHeight(25);
        dataTable.getTableHeader().setBackground(new Color(70, 70, 70));
        dataTable.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.DARK_GRAY);
        
        JButton refreshButton = new JButton("Refresh Data");
        refreshButton.addActionListener(e -> loadRecords());
        bottomPanel.add(refreshButton);
        
        frame.add(bottomPanel, BorderLayout.SOUTH);

        loadRecords();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void addRecord(ActionEvent e) {
        String vehicle = vehicleField.getText();
        String fuel = (String) fuelBox.getSelectedItem();
        double distance;

        try {
            distance = Double.parseDouble(distanceField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid distance input!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double emission = calculateEmissions(fuel, distance);
        double trees = calculateTreesNeeded(emission);

        String sql = "INSERT INTO offsets (vehicle_type, fuel_type, distance, emission, trees_needed) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicle);
            stmt.setString(2, fuel);
            stmt.setDouble(3, distance);
            stmt.setDouble(4, emission);
            stmt.setDouble(5, trees);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Record Added!\nEmissions: " + emission + " kg CO2\nTrees Needed: " + trees);
            loadRecords();
            clearFields();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateRecord(ActionEvent e) {
        int id;
        try {
            id = Integer.parseInt(idField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String vehicle = vehicleField.getText();
        String fuel = (String) fuelBox.getSelectedItem();
        double distance;
        try {
            distance = Double.parseDouble(distanceField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid distance input!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double emission = calculateEmissions(fuel, distance);
        double trees = calculateTreesNeeded(emission);

        String sql = "UPDATE offsets SET vehicle_type=?, fuel_type=?, distance=?, emission=?, trees_needed=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicle);
            stmt.setString(2, fuel);
            stmt.setDouble(3, distance);
            stmt.setDouble(4, emission);
            stmt.setDouble(5, trees);
            stmt.setInt(6, id);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Record Updated!");
                loadRecords();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(frame, "No record found with ID: " + id, "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteRecord(ActionEvent e) {
        int selectedRow = dataTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Select a record to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String sql = "DELETE FROM offsets WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Record Deleted!");
            loadRecords();
            clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        idField.setText("");
        vehicleField.setText("");
        distanceField.setText("");
        fuelBox.setSelectedIndex(0);
    }

    private double calculateEmissions(String fuelType, double distance) {
        return switch (fuelType) {
            case "Petrol" -> 0.2 * distance;
            case "Diesel" -> 0.25 * distance;
            case "Electric" -> 0.05 * distance;
            default -> 0.0;
        };
    }

    private double calculateTreesNeeded(double emissions) {
        return emissions / 20.0;
    }

    private void loadRecords() {
        tableModel.setRowCount(0);
        String sql = "SELECT * FROM offsets";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("vehicle_type"),
                        rs.getString("fuel_type"),
                        rs.getDouble("distance"),
                        rs.getDouble("emission"),
                        rs.getDouble("trees_needed")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarbonOffset::new);
    }
}