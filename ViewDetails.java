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
 * ViewDetails - Display donation medicine details with search functionality
 * Allows executives to search and view medicine donation information
 * 
 * @author Team
 */
public class ViewDetails extends JFrame {

    // ========== UI COMPONENTS ==========
    // Title and Navigation
    private JLabel titleLabel;
    private JLabel navigationBar;
    private JLabel sectionTitle;
    private JLabel instructionLabel;
    
    // Navigation Buttons
    private JButton homeButton;
    private JButton assignButton;
    private JButton viewMedicineButton;
    private JButton viewUserButton;
    private JButton buyMedicineButton;
    private JButton collectButton;
    private JButton logoutButton;
    private JButton searchButton;
    private JButton backButton;
    
    // Search ComboBoxes
    private JComboBox medicineNameCombo;
    private JComboBox executiveNameCombo;
    private JComboBox executiveEmailCombo;
    private JComboBox donatorNameCombo;
    private JComboBox donatorEmailCombo;
    
    // Donator Details Labels and Fields
    private JLabel contactNumberLabel;
    private JTextField contactNumberField;
    private JLabel flatLabel;
    private JTextField flatField;
    private JLabel streetLabel;
    private JTextField streetField;
    private JLabel areaLabel;
    private JTextField areaField;
    private JLabel pinCodeLabel;
    private JTextField pinCodeField;
    private JLabel stateLabel;
    private JTextField stateField;
    private JLabel cityLabel;
    private JTextField cityField;
    
    // Medicine Details Labels and Fields
    private JLabel manufactureDateLabel;
    private JTextField manufactureDateField;
    private JLabel expiryDateLabel;
    private JTextField expiryDateField;
    private JLabel typeLabel;
    private JTextField typeField;
    private JLabel quantityLabel;
    private JTextField quantityField;
    private JLabel donateTimeLabel;
    private JTextField donateTimeField;
    private JLabel donateDateLabel;
    private JTextField donateDateField;
    
    // Container and User Info
    private Container contentPane;
    private String currentUserName;
    
    // ========== DATABASE COMPONENTS ==========
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    /**
     * Constructor
     */
    public ViewDetails() {
        initComponents();
        loadComboBoxData();
    }

    /**
     * Initialize all UI components
     */
    private void initComponents() {
        setupFrame();
        createTitleBar();
        createNavigationBar();
        createSearchSection();
        createDonatorDetailsSection();
        createMedicineDetailsSection();
        addActionListeners();
    }

    /**
     * Setup basic frame properties
     */
    private void setupFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1930, 1030);
        this.setTitle("Executive - View Details");
        this.setResizable(false);
        
        contentPane = this.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.LIGHT_GRAY);
        
        currentUserName = ExecutiveLogin.nametf.getText();
    }

    /**
     * Create and setup title bar
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
     * Create and setup navigation bar with buttons
     */
    private void createNavigationBar() {
        navigationBar = new JLabel();
        navigationBar.setBounds(1, 100, 1930, 150);
        navigationBar.setOpaque(true);
        navigationBar.setBackground(Color.MAGENTA);
        contentPane.add(navigationBar);

        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
        Font buttonFont = new Font("Arial", Font.BOLD, 24);

        // Home Button
        homeButton = createButton("Home Page", 20, 30, 170, 80, handCursor, buttonFont, navigationBar);

        // Assign Button
        assignButton = createButton("View Assigned User", 191, 30, 270, 80, handCursor, buttonFont, navigationBar);

        // View Medicine Button
        viewMedicineButton = createButton("View Medicine", 462, 30, 224, 80, handCursor, buttonFont, navigationBar);

        // View User Button
        viewUserButton = createButton("View User", 687, 30, 170, 80, handCursor, buttonFont, navigationBar);

        // Buy Medicine Button
        buyMedicineButton = createButton("Buy Medicine", 858, 30, 190, 80, handCursor, buttonFont, navigationBar);

        // Collect Button
        collectButton = createButton("Collect Medicine", 1049, 30, 230, 80, handCursor, buttonFont, navigationBar);

        // Logout Button
        logoutButton = createButton("LogOut", 1280, 30, 120, 80, handCursor, buttonFont, navigationBar);

        // User Info Label
        JLabel userInfoLabel = new JLabel("Hi " + currentUserName);
        userInfoLabel.setBounds(1660, 30, 500, 80);
        userInfoLabel.setFont(buttonFont);
        userInfoLabel.setToolTipText(currentUserName);
        navigationBar.add(userInfoLabel);
    }

    /**
     * Create search section
     */
    private void createSearchSection() {
        JLabel contentArea = new JLabel();
        contentArea.setBounds(1, 260, 1930, 1030);
        contentArea.setOpaque(true);
        contentArea.setBackground(Color.LIGHT_GRAY);
        contentPane.add(contentArea);

        Font subtitleFont = new Font("Arial", Font.BOLD, 20);
        Font labelFont = new Font("Arial", Font.BOLD, 21);
        Font searchButtonFont = new Font("Arial", Font.BOLD, 26);

        // Section Title
        sectionTitle = new JLabel("View Donate Medicine");
        sectionTitle.setFont(subtitleFont);
        sectionTitle.setBounds(1, 1, 1900, 40);
        sectionTitle.setForeground(Color.BLUE);
        sectionTitle.setOpaque(true);
        sectionTitle.setBackground(Color.ORANGE);
        contentArea.add(sectionTitle);

        // Instruction Label
        instructionLabel = new JLabel("Please enter medicine name or executive details and click search");
        instructionLabel.setFont(labelFont);
        instructionLabel.setBounds(400, 45, 1900, 27);
        contentArea.add(instructionLabel);

        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        // Medicine Name
        JLabel medicineLabel = new JLabel("Medicine Name:");
        medicineLabel.setFont(labelFont);
        medicineLabel.setBounds(2, 100, 320, 30);
        contentArea.add(medicineLabel);

        medicineNameCombo = new JComboBox();
        medicineNameCombo.setFont(labelFont);
        medicineNameCombo.setBounds(180, 100, 300, 30);
        contentArea.add(medicineNameCombo);

        // Executive Name
        JLabel executiveNameLabel = new JLabel("Executive Name:");
        executiveNameLabel.setFont(labelFont);
        executiveNameLabel.setBounds(550, 100, 320, 30);
        contentArea.add(executiveNameLabel);

        executiveNameCombo = new JComboBox();
        executiveNameCombo.setFont(labelFont);
        executiveNameCombo.setBounds(740, 100, 300, 30);
        contentArea.add(executiveNameCombo);

        // Executive Email
        JLabel executiveEmailLabel = new JLabel("Executive Email:");
        executiveEmailLabel.setFont(labelFont);
        executiveEmailLabel.setBounds(1090, 100, 320, 30);
        contentArea.add(executiveEmailLabel);

        executiveEmailCombo = new JComboBox();
        executiveEmailCombo.setFont(labelFont);
        executiveEmailCombo.setBounds(1280, 100, 300, 30);
        contentArea.add(executiveEmailCombo);

        // Donator Name
        JLabel donatorNameLabel = new JLabel("Donator Name:");
        donatorNameLabel.setFont(labelFont);
        donatorNameLabel.setBounds(2, 140, 320, 30);
        contentArea.add(donatorNameLabel);

        donatorNameCombo = new JComboBox();
        donatorNameCombo.setFont(labelFont);
        donatorNameCombo.setBounds(180, 140, 300, 30);
        contentArea.add(donatorNameCombo);

        // Donator Email
        JLabel donatorEmailLabel = new JLabel("Donator Email:");
        donatorEmailLabel.setFont(labelFont);
        donatorEmailLabel.setBounds(550, 140, 320, 30);
        contentArea.add(donatorEmailLabel);

        donatorEmailCombo = new JComboBox();
        donatorEmailCombo.setFont(labelFont);
        donatorEmailCombo.setBounds(740, 140, 300, 30);
        contentArea.add(donatorEmailCombo);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setCursor(handCursor);
        searchButton.setFont(searchButtonFont);
        searchButton.setBounds(600, 180, 500, 40);
        contentArea.add(searchButton);
    }

    /**
     * Create donator details section
     */
    private void createDonatorDetailsSection() {
        JLabel contentArea = (JLabel) contentPane.getComponent(contentPane.getComponentCount() - 1);
        
        Font sectionFont = new Font("Arial", Font.BOLD, 26);
        Font fieldFont = new Font("Arial", Font.BOLD, 21);

        // Donator Details Header
        JLabel donatorDetailsLabel = new JLabel("Donator Details");
        donatorDetailsLabel.setFont(sectionFont);
        donatorDetailsLabel.setBounds(2, 250, 320, 30);
        contentArea.add(donatorDetailsLabel);

        // Contact Number
        contactNumberLabel = new JLabel("Contact Number:");
        contactNumberLabel.setFont(fieldFont);
        contactNumberLabel.setBounds(2, 290, 320, 30);
        contentArea.add(contactNumberLabel);

        contactNumberField = new JTextField("");
        contactNumberField.setFont(fieldFont);
        contactNumberField.setEditable(false);
        contactNumberField.setBounds(190, 290, 300, 30);
        contentArea.add(contactNumberField);

        // Flat/Door/Block
        flatLabel = new JLabel("Flat/Door/Block:");
        flatLabel.setFont(fieldFont);
        flatLabel.setBounds(550, 290, 320, 30);
        contentArea.add(flatLabel);

        flatField = new JTextField("");
        flatField.setFont(fieldFont);
        flatField.setEditable(false);
        flatField.setBounds(770, 290, 300, 30);
        contentArea.add(flatField);

        // Street
        streetLabel = new JLabel("Street/Lane:");
        streetLabel.setFont(fieldFont);
        streetLabel.setBounds(1100, 290, 320, 30);
        contentArea.add(streetLabel);

        streetField = new JTextField("");
        streetField.setFont(fieldFont);
        streetField.setEditable(false);
        streetField.setBounds(1250, 290, 300, 30);
        contentArea.add(streetField);

        // Area
        areaLabel = new JLabel("Area/Locality:");
        areaLabel.setFont(fieldFont);
        areaLabel.setBounds(2, 330, 320, 30);
        contentArea.add(areaLabel);

        areaField = new JTextField("");
        areaField.setFont(fieldFont);
        areaField.setEditable(false);
        areaField.setBounds(190, 330, 300, 30);
        contentArea.add(areaField);

        // Pin Code
        pinCodeLabel = new JLabel("Pin Code:");
        pinCodeLabel.setFont(fieldFont);
        pinCodeLabel.setBounds(660, 330, 320, 30);
        contentArea.add(pinCodeLabel);

        pinCodeField = new JTextField("");
        pinCodeField.setFont(fieldFont);
        pinCodeField.setEditable(false);
        pinCodeField.setBounds(770, 330, 300, 30);
        contentArea.add(pinCodeField);

        // State
        stateLabel = new JLabel("State:");
        stateLabel.setFont(fieldFont);
        stateLabel.setBounds(1100, 330, 320, 30);
        contentArea.add(stateLabel);

        stateField = new JTextField("");
        stateField.setFont(fieldFont);
        stateField.setEditable(false);
        stateField.setBounds(1250, 330, 300, 30);
        contentArea.add(stateField);

        // City/Town
        cityLabel = new JLabel("City/Town:");
        cityLabel.setFont(fieldFont);
        cityLabel.setBounds(2, 370, 320, 30);
        contentArea.add(cityLabel);

        cityField = new JTextField("");
        cityField.setFont(fieldFont);
        cityField.setEditable(false);
        cityField.setBounds(190, 370, 300, 30);
        contentArea.add(cityField);
    }

    /**
     * Create medicine details section
     */
    private void createMedicineDetailsSection() {
        JLabel contentArea = (JLabel) contentPane.getComponent(contentPane.getComponentCount() - 1);
        
        Font sectionFont = new Font("Arial", Font.BOLD, 26);
        Font fieldFont = new Font("Arial", Font.BOLD, 21);
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        // Medicine Details Header
        JLabel medicineDetailsLabel = new JLabel("Medicine Details");
        medicineDetailsLabel.setFont(sectionFont);
        medicineDetailsLabel.setBounds(2, 430, 320, 30);
        contentArea.add(medicineDetailsLabel);

        // Manufacture Date
        manufactureDateLabel = new JLabel("Manufacture Date:");
        manufactureDateLabel.setFont(fieldFont);
        manufactureDateLabel.setBounds(2, 490, 320, 30);
        contentArea.add(manufactureDateLabel);

        manufactureDateField = new JTextField("");
        manufactureDateField.setFont(fieldFont);
        manufactureDateField.setEditable(false);
        manufactureDateField.setBounds(290, 490, 300, 30);
        contentArea.add(manufactureDateField);

        // Expiry Date
        expiryDateLabel = new JLabel("Expiry Date:");
        expiryDateLabel.setFont(fieldFont);
        expiryDateLabel.setBounds(630, 490, 320, 30);
        contentArea.add(expiryDateLabel);

        expiryDateField = new JTextField("");
        expiryDateField.setFont(fieldFont);
        expiryDateField.setEditable(false);
        expiryDateField.setBounds(780, 490, 300, 30);
        contentArea.add(expiryDateField);

        // Type
        typeLabel = new JLabel("Type:");
        typeLabel.setFont(fieldFont);
        typeLabel.setBounds(1130, 490, 320, 30);
        contentArea.add(typeLabel);

        typeField = new JTextField("");
        typeField.setFont(fieldFont);
        typeField.setEditable(false);
        typeField.setBounds(1280, 490, 300, 30);
        contentArea.add(typeField);

        // Quantity
        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(fieldFont);
        quantityLabel.setBounds(2, 530, 320, 30);
        contentArea.add(quantityLabel);

        quantityField = new JTextField("");
        quantityField.setFont(fieldFont);
        quantityField.setEditable(false);
        quantityField.setBounds(290, 530, 300, 30);
        contentArea.add(quantityField);

        // Donate Time
        donateTimeLabel = new JLabel("Donate Time:");
        donateTimeLabel.setFont(fieldFont);
        donateTimeLabel.setBounds(630, 530, 320, 30);
        contentArea.add(donateTimeLabel);

        donateTimeField = new JTextField("");
        donateTimeField.setFont(fieldFont);
        donateTimeField.setEditable(false);
        donateTimeField.setBounds(780, 530, 300, 30);
        contentArea.add(donateTimeField);

        // Donate Date
        donateDateLabel = new JLabel("Donate Date:");
        donateDateLabel.setFont(fieldFont);
        donateDateLabel.setBounds(1130, 530, 320, 30);
        contentArea.add(donateDateLabel);

        donateDateField = new JTextField("");
        donateDateField.setFont(fieldFont);
        donateDateField.setEditable(false);
        donateDateField.setBounds(1280, 530, 300, 30);
        contentArea.add(donateDateField);

        // Back Button
        backButton = new JButton("Back");
        backButton.setCursor(handCursor);
        backButton.setFont(new Font("Arial", Font.BOLD, 26));
        backButton.setBounds(600, 620, 680, 40);
        contentArea.add(backButton);
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
     * Load data into combo boxes from database
     */
    private void loadComboBoxData() {
        try {
            String sql = "SELECT * FROM `donate medicine`";
            connection = (Connection) DriverManager.getConnection(
                "jdbc:mysql://localhost/MedConnect", "root", ""
            );
            statement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                medicineNameCombo.addItem(resultSet.getString("Medicine Name"));
                executiveNameCombo.addItem(resultSet.getString("Executive Name"));
                executiveEmailCombo.addItem(resultSet.getString("Executive Email"));
                donatorNameCombo.addItem(resultSet.getString("User Name"));
                donatorEmailCombo.addItem(resultSet.getString("User Email"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Add action listeners to buttons
     */
    private void addActionListeners() {
        Handler handler = new Handler();
        
        homeButton.addActionListener(handler);
        assignButton.addActionListener(handler);
        viewMedicineButton.addActionListener(handler);
        viewUserButton.addActionListener(handler);
        buyMedicineButton.addActionListener(handler);
        collectButton.addActionListener(handler);
        logoutButton.addActionListener(handler);
        searchButton.addActionListener(handler);
        backButton.addActionListener(handler);
    }

    /**
     * Handle button click events
     */
    private class Handler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == searchButton) {
                handleSearch();
            } else if (event.getSource() == homeButton) {
                navigateTo(new HomePage());
            } else if (event.getSource() == assignButton) {
                navigateTo(new Viewassignexecutive());
            } else if (event.getSource() == viewMedicineButton) {
                navigateTo(new MedicineView());
            } else if (event.getSource() == viewUserButton) {
                navigateTo(new User());
            } else if (event.getSource() == buyMedicineButton) {
                navigateTo(new BuyMedicine());
            } else if (event.getSource() == collectButton) {
                navigateTo(new CollectMedicine());
            } else if (event.getSource() == backButton) {
                navigateTo(new ViewDonateMedicine());
            } else if (event.getSource() == logoutButton) {
                handleLogout();
            }
        }

        /**
         * Handle search functionality
         */
        private void handleSearch() {
            String medicineName = medicineNameCombo.getSelectedItem().toString();
            String executiveName = executiveNameCombo.getSelectedItem().toString();
            String executiveEmail = executiveEmailCombo.getSelectedItem().toString();
            String donatorName = donatorNameCombo.getSelectedItem().toString();
            String donatorEmail = donatorEmailCombo.getSelectedItem().toString();

            if (medicineName.isEmpty() || executiveName.isEmpty() || executiveEmail.isEmpty() ||
                donatorName.isEmpty() || donatorEmail.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "Please enter all search criteria", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                String sql = "SELECT * FROM `donate medicine` WHERE `Medicine Name`=? AND " +
                           "`Executive Name`=? AND `Executive Email`=? AND `User Name`=? AND `User Email`=?";
                
                connection = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost/MedConnect", "root", ""
                );
                statement = (PreparedStatement) connection.prepareStatement(sql);
                
                statement.setString(1, medicineName);
                statement.setString(2, executiveName);
                statement.setString(3, executiveEmail);
                statement.setString(4, donatorName);
                statement.setString(5, donatorEmail);

                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    populateFields(resultSet);
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "No matching records found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error executing search: " + ex.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        /**
         * Populate fields with database results
         */
        private void populateFields(ResultSet rs) {
            try {
                manufactureDateField.setText(rs.getString("Manufacture Date"));
                expiryDateField.setText(rs.getString("Expiry Date"));
                typeField.setText(rs.getString("Medicine Type"));
                quantityField.setText(rs.getString("Number of Tablet"));
                donateTimeField.setText(rs.getString("Donate Time"));
                donateDateField.setText(rs.getString("Donate Date"));
                contactNumberField.setText(rs.getString("Contact Number"));
                flatField.setText(rs.getString("Flat No"));
                streetField.setText(rs.getString("Street"));
                areaField.setText(rs.getString("Area"));
                pinCodeField.setText(rs.getString("Pin Code"));
                stateField.setText(rs.getString("State"));
                cityField.setText(rs.getString("CityTown"));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error populating fields: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        /**
         * Clear all display fields
         */
        private void clearFields() {
            manufactureDateField.setText("");
            expiryDateField.setText("");
            typeField.setText("");
            quantityField.setText("");
            donateTimeField.setText("");
            donateDateField.setText("");
            contactNumberField.setText("");
            flatField.setText("");
            streetField.setText("");
            areaField.setText("");
            pinCodeField.setText("");
            stateField.setText("");
            cityField.setText("");
        }

        /**
         * Handle logout action
         */
        private void handleLogout() {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?",
                    "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                navigateTo(new ExecutiveLogin());
            }
        }

        /**
         * Navigate to a new frame
         */
        private void navigateTo(JFrame frame) {
            setVisible(false);
            frame.setVisible(true);
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        ViewDetails frame = new ViewDetails();
        frame.setVisible(true);
    }
}        