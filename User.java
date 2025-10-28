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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 * User - Display available medicines for purchase by Executive
 * Shows medicines available for buy with pricing and executive details
 * 
 * @author Team
 */
public class User extends JFrame {

    // ========== UI COMPONENTS ==========
    // Title and Navigation
    private JLabel titleLabel;
    private JLabel navigationBar;
    private JLabel sectionTitleLabel;
    
    // Navigation Buttons
    private JButton homeButton;
    private JButton viewAssignedUserButton;
    private JButton viewMedicineButton;
    private JButton buyMedicineButton;
    private JButton viewDonateButton;
    private JButton collectMedicineButton;
    private JButton logoutButton;
    
    // Table Components
    private JTable medicineTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    
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
    public User() {
        initComponents();
    }

    /**
     * Initialize all UI components
     */
    private void initComponents() {
        setupFrame();
        createTitleBar();
        createNavigationBar();
        createSectionTitle();
        createTableSection();
        addActionListeners();
    }

    /**
     * Setup basic frame properties
     */
    private void setupFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1930, 1030);
        this.setTitle("Executive - View Buy Medicine");
        this.setResizable(false);
        
        contentPane = this.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(0xFAEB92));
        
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
        titleLabel.setForeground(new Color(0x000000));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0xFAEB92));
        contentPane.add(titleLabel);
    }

    /**
     * Create and setup navigation bar with buttons
     */
    private void createNavigationBar() {
        navigationBar = new JLabel();
        navigationBar.setBounds(1, 100, 1930, 150);
        navigationBar.setOpaque(true);
        navigationBar.setBackground(new Color(0xFAEB92));
        contentPane.add(navigationBar);

        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
        Font buttonFont = new Font("Arial", Font.BOLD, 24);

        // Home Button
        homeButton = createButton("Home Page", 20, 30, 170, 80, handCursor, buttonFont, navigationBar);

        // View Assigned User Button
        viewAssignedUserButton = createButton("View Assigned User", 191, 30, 270, 80, handCursor, buttonFont, navigationBar);

        // View Medicine Button
        viewMedicineButton = createButton("View Medicine", 462, 30, 224, 80, handCursor, buttonFont, navigationBar);

        // Buy Medicine Button
        buyMedicineButton = createButton("Buy Medicine", 687, 30, 190, 80, handCursor, buttonFont, navigationBar);

        // View Donate Button
        viewDonateButton = createButton("View Donate", 878, 30, 180, 80, handCursor, buttonFont, navigationBar);

        // Collect Medicine Button
        collectMedicineButton = createButton("Collect Medicine", 1059, 30, 230, 80, handCursor, buttonFont, navigationBar);

        // Logout Button
        logoutButton = createButton("LogOut", 1290, 30, 120, 80, handCursor, buttonFont, navigationBar);

        // User Info Label
        JLabel userInfoLabel = new JLabel("Hi " + currentUserName);
        userInfoLabel.setBounds(1660, 30, 500, 80);
        userInfoLabel.setFont(buttonFont);
        userInfoLabel.setToolTipText(currentUserName);
        navigationBar.add(userInfoLabel);
    }

    /**
     * Create section title
     */
    private void createSectionTitle() {
        Font sectionFont = new Font("Arial", Font.BOLD, 24);
        
        sectionTitleLabel = new JLabel("View Buy Medicine");
        sectionTitleLabel.setFont(sectionFont);
        sectionTitleLabel.setBounds(1, 260, 1900, 80);
        sectionTitleLabel.setForeground(new Color(0x000000));
        sectionTitleLabel.setOpaque(true);
        sectionTitleLabel.setBackground(new Color(0xFAEB92));
        contentPane.add(sectionTitleLabel);
    }

    /**
     * Create table section to display medicine information
     */
    private void createTableSection() {
        Font tableContentFont = new Font("Arial", Font.BOLD, 18);
        Font tableHeaderFont = new Font("Arial", Font.BOLD, 20);

        // Define table columns
        String[] columns = {"Medicine Name", "Price (10 pcs)", "Manufacture Date", 
                           "Expiry Date", "Executive Name", "Executive Email"};
        
        // Create table model
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columns);

        // Create table
        medicineTable = new JTable();
        medicineTable.setModel(tableModel);
        medicineTable.setFont(tableContentFont);
        medicineTable.setSelectionBackground(new Color(0xCC66DA));
        medicineTable.setRowHeight(40);
        medicineTable.setEnabled(false);

        // Setup table header
        JTableHeader header = medicineTable.getTableHeader();
        header.setFont(tableHeaderFont);
        header.setEnabled(false);

        // Setup column widths
        setupColumnWidths();

        // Create scroll pane
        scrollPane = new JScrollPane(medicineTable, 
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(1, 350, 1900, 630);
        contentPane.add(scrollPane);

        // Load data into table
        loadTableData();
    }

    /**
     * Setup table column widths
     */
    private void setupColumnWidths() {
        TableColumnModel columnModel = medicineTable.getColumnModel();
        
        columnModel.getColumn(0).setPreferredWidth(420);  // Medicine Name
        columnModel.getColumn(1).setPreferredWidth(100);  // Price
        columnModel.getColumn(2).setPreferredWidth(140);  // Manufacture Date
        columnModel.getColumn(3).setPreferredWidth(90);   // Expiry Date
        columnModel.getColumn(4).setPreferredWidth(440);  // Executive Name
        columnModel.getColumn(5).setPreferredWidth(460);  // Executive Email
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
     * Load medicine data into table from database
     */
    private void loadTableData() {
        try {
            String sql = "SELECT `Medicine Name`, `Price`, `Manufacture Date`, `Expiry Date`, " +
                        "`Executive Name`, `Executive Email` FROM `buymedicine`";
            
            connection = (Connection) DriverManager.getConnection(
                "jdbc:mysql://localhost/MedConnect", "root", ""
            );
            statement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String[] row = {
                    resultSet.getString("Medicine Name"),
                    resultSet.getString("Price"),
                    resultSet.getString("Manufacture Date"),
                    resultSet.getString("Expiry Date"),
                    resultSet.getString("Executive Name"),
                    resultSet.getString("Executive Email")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error loading table data: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            closeDatabase();
        }
    }

    /**
     * Close database resources
     */
    private void closeDatabase() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error closing database: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Add action listeners to buttons
     */
    private void addActionListeners() {
        Handler handler = new Handler();
        
        homeButton.addActionListener(handler);
        viewAssignedUserButton.addActionListener(handler);
        viewMedicineButton.addActionListener(handler);
        buyMedicineButton.addActionListener(handler);
        viewDonateButton.addActionListener(handler);
        collectMedicineButton.addActionListener(handler);
        logoutButton.addActionListener(handler);
    }

    /**
     * Handle button click events
     */
    private class Handler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == homeButton) {
                navigateTo(new HomePage());
            } else if (event.getSource() == viewAssignedUserButton) {
                navigateTo(new Viewassignexecutive());
            } else if (event.getSource() == viewMedicineButton) {
                navigateTo(new MedicineView());
            } else if (event.getSource() == buyMedicineButton) {
                navigateTo(new BuyMedicine());
            } else if (event.getSource() == viewDonateButton) {
                navigateTo(new ViewDonateMedicine());
            } else if (event.getSource() == collectMedicineButton) {
                navigateTo(new CollectMedicine());
            } else if (event.getSource() == logoutButton) {
                handleLogout();
            }
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
        User frame = new User();
        frame.setVisible(true);
    }
}