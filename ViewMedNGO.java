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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * ViewMedNGO - Display medicines available for NGO
 * Shows all available medicines from admin with pricing and details
 * 
 * @author Team
 */
public class ViewMedNGO extends JFrame {

    // ========== UI COMPONENTS ==========
    // Title and Navigation
    private JLabel titleLabel;
    private JLabel navigationBar;
    private JLabel sectionTitleLabel;
    
    // Navigation Buttons
    private JButton homeButton;
    private JButton viewNGOButton;
    private JButton viewUserButton;
    private JButton assignExecutiveButton;
    private JButton viewExecutiveButton;
    private JButton viewCollectMedicineButton;
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
    public ViewMedNGO() {
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
        this.setTitle("NGO - View Medicine");
        this.setResizable(false);
        
        contentPane = this.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.GRAY);
        
        currentUserName = NGOLogin.usernametf.getText();
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
        homeButton = createButton("Home Page", 20, 30, 180, 80, handCursor, buttonFont, navigationBar);

        // View NGO Button
        viewNGOButton = createButton("View NGO", 201, 30, 180, 80, handCursor, buttonFont, navigationBar);

        // View User Button
        viewUserButton = createButton("View User", 382, 30, 180, 80, handCursor, buttonFont, navigationBar);

        // Assign Executive Button
        assignExecutiveButton = createButton("Assign Executive", 563, 30, 250, 80, handCursor, buttonFont, navigationBar);

        // View Executive Button
        viewExecutiveButton = createButton("View Executive", 814, 30, 240, 80, handCursor, buttonFont, navigationBar);

        // View Collect Medicine Button
        viewCollectMedicineButton = createButton("View Collect Med", 1055, 30, 250, 80, handCursor, buttonFont, navigationBar);

        // Logout Button
        logoutButton = createButton("Logout", 1306, 30, 200, 80, handCursor, buttonFont, navigationBar);

        // User Info Label
        JLabel userInfoLabel = new JLabel("Hi " + currentUserName);
        userInfoLabel.setBounds(1640, 30, 500, 80);
        userInfoLabel.setFont(buttonFont);
        userInfoLabel.setToolTipText(currentUserName);
        navigationBar.add(userInfoLabel);
    }

    /**
     * Create section title
     */
    private void createSectionTitle() {
        Font sectionFont = new Font("Arial", Font.BOLD, 24);
        
        sectionTitleLabel = new JLabel("View Medicine");
        sectionTitleLabel.setFont(sectionFont);
        sectionTitleLabel.setBounds(1, 260, 1900, 80);
        sectionTitleLabel.setForeground(Color.BLUE);
        sectionTitleLabel.setOpaque(true);
        sectionTitleLabel.setBackground(Color.ORANGE);
        contentPane.add(sectionTitleLabel);
    }

    /**
     * Create table section to display available medicines
     */
    private void createTableSection() {
        Font tableContentFont = new Font("Arial", Font.BOLD, 18);
        Font tableHeaderFont = new Font("Arial", Font.BOLD, 20);

        // Define table columns
        String[] columns = {"Medicine Name", "Price (10 pcs)", "Manufacture Date", 
                           "Expiry Date", "Admin Name", "Admin Email"};
        
        // Create table model
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columns);

        // Create table
        medicineTable = new JTable();
        medicineTable.setModel(tableModel);
        medicineTable.setFont(tableContentFont);
        medicineTable.setSelectionBackground(Color.PINK);
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
        
        columnModel.getColumn(0).setPreferredWidth(400);   // Medicine Name
        columnModel.getColumn(1).setPreferredWidth(150);   // Price
        columnModel.getColumn(2).setPreferredWidth(180);   // Manufacture Date
        columnModel.getColumn(3).setPreferredWidth(150);   // Expiry Date
        columnModel.getColumn(4).setPreferredWidth(500);   // Admin Name
        columnModel.getColumn(5).setPreferredWidth(500);   // Admin Email
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
            String sql = "SELECT * FROM `addmedicine`";
            
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
                    resultSet.getString("Expire Date"),
                    resultSet.getString("Admin Name"),
                    resultSet.getString("Admin Email")
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
        viewNGOButton.addActionListener(handler);
        viewUserButton.addActionListener(handler);
        assignExecutiveButton.addActionListener(handler);
        viewExecutiveButton.addActionListener(handler);
        viewCollectMedicineButton.addActionListener(handler);
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
            } else if (event.getSource() == viewNGOButton) {
                navigateTo(new ViewNGOngo());
            } else if (event.getSource() == viewUserButton) {
                navigateTo(new UserView());
            } else if (event.getSource() == assignExecutiveButton) {
                navigateTo(new AssignExecutive());
            } else if (event.getSource() == viewExecutiveButton) {
                navigateTo(new ViewExecutive());
            } else if (event.getSource() == viewCollectMedicineButton) {
                navigateTo(new ViewCollectMedicine());
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
                navigateTo(new NGOLogin());
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
        ViewMedNGO frame = new ViewMedNGO();
        frame.setVisible(true);
    }
}        