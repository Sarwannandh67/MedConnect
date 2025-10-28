package online.medicine.donation.system;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * ViewDetailsCollect - Display collected medicine details for NGO
 * Allows search and viewing of collected medicine information with executive details
 * 
 * @author Team
 */
public class ViewDetailsCollect extends JFrame {

    // ========== UI COMPONENTS ==========
    private JLabel titleLabel;
    private JLabel navigationBar;
    private JLabel contentArea;
    
    // Navigation Buttons
    private JButton homeButton, viewNGOButton, viewMedicineButton, userButton;
    private JButton assignButton, viewExecutiveButton, logoutButton, searchButton, backButton;
    
    // Search Components
    private JComboBox medicineNameCombo, executiveNameCombo, executiveEmailCombo;
    
    // Display Fields - Executive Details
    private JTextField genderField, dateOfBirthField, contactNumberField, locationField;
    
    // Display Fields - Medicine Details
    private JTextField manufactureDateField, expiryDateField, medicineTypeField, quantityField;
    private JTextField collectDateField, collectTimeField;
    
    // Container and User Info
    private Container contentPane;
    private String currentUserName;
    
    // ========== DATABASE COMPONENTS ==========
    private Connection connection, searchConnection;
    private PreparedStatement statement, searchStatement;
    private ResultSet resultSet, searchResultSet;

    /**
     * Constructor
     */
    public ViewDetailsCollect() {
        initComponents();
        loadSearchData();
    }

    /**
     * Initialize all UI components
     */
    private void initComponents() {
        setupFrame();
        createTitleBar();
        createNavigationBar();
        createSearchSection();
        createExecutiveDetailsSection();
        createMedicineDetailsSection();
        createActionButtons();
        addActionListeners();
    }

    /**
     * Setup basic frame properties
     */
    private void setupFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1930, 1030);
        this.setTitle("NGO - View Collected Medicine");
        this.setResizable(false);
        contentPane = this.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.GRAY);
        currentUserName = NGOLogin.usernametf.getText();
    }

    /**
     * Create title bar
     */
    private void createTitleBar() {
        Font titleFont = new Font("Arial", Font.BOLD, 42);
        titleLabel = new JLabel("MedConnect");
        titleLabel.setFont(titleFont);
        titleLabel.setBounds(2, 2, 1930, 80);
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.ORANGE);
        contentPane.add(titleLabel);
    }

    /**
     * Create navigation bar with buttons
     */
    private void createNavigationBar() {
        navigationBar = new JLabel();
        navigationBar.setBounds(1, 100, 1930, 150);
        navigationBar.setOpaque(true);
        navigationBar.setBackground(Color.MAGENTA);
        contentPane.add(navigationBar);

        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
        Font buttonFont = new Font("Arial", Font.BOLD, 24);

        homeButton = createButton("Home Page", 20, 30, 180, 80, handCursor, buttonFont, navigationBar);
        viewNGOButton = createButton("View NGO", 201, 30, 180, 80, handCursor, buttonFont, navigationBar);
        viewMedicineButton = createButton("View Medicine", 382, 30, 220, 80, handCursor, buttonFont, navigationBar);
        userButton = createButton("View User", 603, 30, 180, 80, handCursor, buttonFont, navigationBar);
        assignButton = createButton("Assign Executive", 784, 30, 250, 80, handCursor, buttonFont, navigationBar);
        viewExecutiveButton = createButton("View Executive", 1035, 30, 222, 80, handCursor, buttonFont, navigationBar);
        logoutButton = createButton("Logout", 1258, 30, 140, 80, handCursor, buttonFont, navigationBar);

        JLabel userInfoLabel = new JLabel("Hi " + currentUserName);
        userInfoLabel.setBounds(1660, 30, 500, 80);
        userInfoLabel.setFont(buttonFont);
        navigationBar.add(userInfoLabel);
    }

    /**
     * Create search section
     */
    private void createSearchSection() {
        contentArea = new JLabel();
        contentArea.setBounds(1, 260, 1930, 1030);
        contentArea.setOpaque(true);
        contentArea.setBackground(Color.LIGHT_GRAY);
        contentPane.add(contentArea);

        Font sectionFont = new Font("Arial", Font.BOLD, 26);
        Font labelFont = new Font("Arial", Font.BOLD, 21);

        JLabel sectionTitle = new JLabel("View Collected Medicine");
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 24));
        sectionTitle.setBounds(1, 1, 1900, 40);
        sectionTitle.setForeground(Color.BLUE);
        sectionTitle.setOpaque(true);
        sectionTitle.setBackground(Color.ORANGE);
        contentArea.add(sectionTitle);

        medicineNameCombo = new JComboBox();
        medicineNameCombo.setFont(labelFont);
        medicineNameCombo.setBounds(200, 100, 300, 30);
        contentArea.add(medicineNameCombo);

        new JLabel("Medicine Name").setBounds(2, 100, 320, 30);
        contentArea.add(new JLabel("Medicine Name")).setBounds(2, 100, 320, 30);

        executiveNameCombo = new JComboBox();
        executiveNameCombo.setFont(labelFont);
        executiveNameCombo.setBounds(800, 100, 300, 30);
        contentArea.add(executiveNameCombo);
        contentArea.add(new JLabel("Executive Name")).setBounds(600, 100, 320, 30);

        executiveEmailCombo = new JComboBox();
        executiveEmailCombo.setFont(labelFont);
        executiveEmailCombo.setBounds(1380, 100, 300, 30);
        contentArea.add(executiveEmailCombo);
        contentArea.add(new JLabel("Executive Email")).setBounds(1180, 100, 320, 30);

        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 24));
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(750, 160, 400, 40);
        contentArea.add(searchButton);
    }

    /**
     * Create executive details display section
     */
    private void createExecutiveDetailsSection() {
        Font labelFont = new Font("Arial", Font.BOLD, 21);
        
        genderField = createDisplayField(2, 260, 300, 30, labelFont);
        dateOfBirthField = createDisplayField(800, 260, 300, 30, labelFont);
        contactNumberField = createDisplayField(1400, 260, 300, 30, labelFont);
        locationField = createDisplayField(200, 300, 300, 30, labelFont);
        collectDateField = createDisplayField(800, 300, 300, 30, labelFont);
        collectTimeField = createDisplayField(1400, 300, 300, 30, labelFont);
    }

    /**
     * Create medicine details display section
     */
    private void createMedicineDetailsSection() {
        Font labelFont = new Font("Arial", Font.BOLD, 21);
        
        manufactureDateField = createDisplayField(200, 400, 300, 30, labelFont);
        expiryDateField = createDisplayField(860, 400, 300, 30, labelFont);
        medicineTypeField = createDisplayField(200, 440, 300, 30, labelFont);
        quantityField = createDisplayField(860, 440, 300, 30, labelFont);
    }

    /**
     * Create action buttons (Search and Back)
     */
    private void createActionButtons() {
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 26));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBounds(580, 560, 600, 60);
        contentArea.add(backButton);
    }

    /**
     * Create a display field (read-only text field)
     */
    private JTextField createDisplayField(int x, int y, int width, int height, Font font) {
        JTextField field = new JTextField("");
        field.setFont(font);
        field.setEditable(false);
        field.setBounds(x, y, width, height);
        contentArea.add(field);
        return field;
    }

    /**
     * Create a button with standard styling
     */
    private JButton createButton(String text, int x, int y, int width, int height,
                                 Cursor cursor, Font font, JLabel container) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBounds(x, y, width, height);
        button.setCursor(cursor);
        container.add(button);
        return button;
    }

    /**
     * Load search data into combo boxes
     */
    private void loadSearchData() {
        try {
            String sql = "SELECT DISTINCT `Medicine Name`, `Executive Name`, `Executive Email` FROM `collect medicine`";
            searchConnection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MedConnect", "root", "");
            searchStatement = (PreparedStatement) searchConnection.prepareStatement(sql);
            searchResultSet = searchStatement.executeQuery();

            while (searchResultSet.next()) {
                medicineNameCombo.addItem(searchResultSet.getString("Medicine Name"));
                executiveNameCombo.addItem(searchResultSet.getString("Executive Name"));
                executiveEmailCombo.addItem(searchResultSet.getString("Executive Email"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + ex.getMessage());
        } finally {
            closeConnection(searchConnection, searchStatement, searchResultSet);
        }
    }

    /**
     * Search for medicine details
     */
    private void searchMedicineDetails() {
        String medicineName = medicineNameCombo.getSelectedItem().toString();
        String executiveName = executiveNameCombo.getSelectedItem().toString();
        String executiveEmail = executiveEmailCombo.getSelectedItem().toString();

        if (medicineName.isEmpty() || executiveName.isEmpty() || executiveEmail.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select all search criteria");
            return;
        }

        try {
            String sql = "SELECT * FROM `collect medicine` WHERE `Medicine Name`=? AND `Executive Name`=? AND `Executive Email`=?";
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MedConnect", "root", "");
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, medicineName);
            statement.setString(2, executiveName);
            statement.setString(3, executiveEmail);
            
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                populateFields();
            } else {
                JOptionPane.showMessageDialog(null, "No records found");
                clearFields();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error searching: " + ex.getMessage());
        } finally {
            closeConnection(connection, statement, resultSet);
        }
    }

    /**
     * Populate display fields with search results
     */
    private void populateFields() {
        try {
            genderField.setText(resultSet.getString("Gender"));
            dateOfBirthField.setText(resultSet.getString("Date of Birth"));
            contactNumberField.setText(resultSet.getString("Contact Number"));
            locationField.setText(resultSet.getString("Location"));
            manufactureDateField.setText(resultSet.getString("Manufacture Date"));
            expiryDateField.setText(resultSet.getString("Expiry Date"));
            medicineTypeField.setText(resultSet.getString("Medicine Type"));
            quantityField.setText(resultSet.getString("Number of Tablet"));
            collectDateField.setText(resultSet.getString("Collect Date"));
            collectTimeField.setText(resultSet.getString("Collect Time"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error populating fields: " + ex.getMessage());
        }
    }

    /**
     * Clear all display fields
     */
    private void clearFields() {
        genderField.setText("");
        dateOfBirthField.setText("");
        contactNumberField.setText("");
        locationField.setText("");
        manufactureDateField.setText("");
        expiryDateField.setText("");
        medicineTypeField.setText("");
        quantityField.setText("");
        collectDateField.setText("");
        collectTimeField.setText("");
    }

    /**
     * Close database resources
     */
    private void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error closing connection: " + ex.getMessage());
        }
    }

    /**
     * Add action listeners
     */
    private void addActionListeners() {
        Handler handler = new Handler();
        homeButton.addActionListener(handler);
        viewNGOButton.addActionListener(handler);
        viewMedicineButton.addActionListener(handler);
        userButton.addActionListener(handler);
        assignButton.addActionListener(handler);
        viewExecutiveButton.addActionListener(handler);
        searchButton.addActionListener(handler);
        backButton.addActionListener(handler);
        logoutButton.addActionListener(handler);
    }

    /**
     * Handle button click events
     */
    private class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == searchButton) {
                searchMedicineDetails();
            } else if (event.getSource() == homeButton) {
                navigateTo(new HomePage());
            } else if (event.getSource() == viewNGOButton) {
                navigateTo(new ViewNGOngo());
            } else if (event.getSource() == viewMedicineButton) {
                navigateTo(new ViewMedNGO());
            } else if (event.getSource() == userButton) {
                navigateTo(new UserView());
            } else if (event.getSource() == assignButton) {
                navigateTo(new AssignExecutive());
            } else if (event.getSource() == viewExecutiveButton) {
                navigateTo(new ViewExecutive());
            } else if (event.getSource() == backButton) {
                navigateTo(new ViewCollectMedicine());
            } else if (event.getSource() == logoutButton) {
                handleLogout();
            }
        }

        private void handleLogout() {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?");
            if (result == JOptionPane.YES_OPTION) {
                navigateTo(new NGOLogin());
            }
        }

        private void navigateTo(JFrame frame) {
            setVisible(false);
            frame.setVisible(true);
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        ViewDetailsCollect frame = new ViewDetailsCollect();
        frame.setVisible(true);
    }
}