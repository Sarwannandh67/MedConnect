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
 * ViewCollectMedicine - Display collected medicine for NGO
 * Shows medicine collected by executives assigned to the NGO
 * 
 * @author Team
 */
public class ViewCollectMedicine extends JFrame {

    // ========== UI COMPONENTS ==========
    // Title and Navigation
    private JLabel titleLabel;
    private JLabel navigationBar;
    private JLabel sectionTitleLabel;
    
    // Navigation Buttons
    private JButton homeButton;
    private JButton viewNGOButton;
    private JButton viewMedicineButton;
    private JButton viewUserButton;
    private JButton assignExecutiveButton;
    private JButton viewExecutiveButton;
    private JButton logoutButton;
    private JButton viewDetailsButton;
    
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
    public ViewCollectMedicine() {
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
        createViewDetailsButton();
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

        // View Medicine Button
        viewMedicineButton = createButton("View Medicine", 382, 30, 220, 80, handCursor, buttonFont, navigationBar);

        // View User Button
        viewUserButton = createButton("View User", 603, 30, 180, 80, handCursor, buttonFont, navigationBar);

        // Assign Executive Button
        assignExecutiveButton = createButton("Assign Executive", 784, 30, 250, 80, handCursor, buttonFont, navigationBar);

        // View Executive Button
        viewExecutiveButton = createButton("View Executive", 1035, 30, 222, 80, handCursor, buttonFont, navigationBar);

        // Logout Button
        logoutButton = createButton("Logout", 1258, 30, 140, 80, handCursor, buttonFont, navigationBar);

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
        
        sectionTitleLabel = new JLabel("View Collected Medicine");
        sectionTitleLabel.setFont(sectionFont);
        sectionTitleLabel.setBounds(1, 260, 1900, 40);
        sectionTitleLabel.setForeground(Color.BLUE);
        sectionTitleLabel.setOpaque(true);
        sectionTitleLabel.setBackground(Color.ORANGE);
        contentPane.add(sectionTitleLabel);
    }

    /**
     * Create table section to display collected medicine
     */
    private void createTableSection() {
        Font tableContentFont = new Font("Arial", Font.BOLD, 18);
        Font tableHeaderFont = new Font("Arial", Font.BOLD, 20);

        // Define table columns
        String[] columns = {"Medicine Name", "Executive Name", "Executive Email"};
        
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
        TableColumnModel columnModel = medicineTable.getColumnModel();
        TableColumn column1 = columnModel.getColumn(0);
        TableColumn column2 = columnModel.getColumn(1);
        TableColumn column3 = columnModel.getColumn(2);
        
        column1.setPreferredWidth(500);
        column2.setPreferredWidth(500);
        column3.setPreferredWidth(500);

        // Create scroll pane
        scrollPane = new JScrollPane(medicineTable, 
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(1, 320, 1900, 530);
        contentPane.add(scrollPane);

        // Load data into table
        loadTableData();
    }

    /**
     * Create View Details button
     */
    private void createViewDetailsButton() {
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
        Font buttonFont = new Font("Arial", Font.BOLD, 24);

        viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setCursor(handCursor);
        viewDetailsButton.setFont(buttonFont);
        viewDetailsButton.setBounds(800, 910, 400, 50);
        contentPane.add(viewDetailsButton);
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
     * Load collected medicine data into table from database
     */
    private void loadTableData() {
        try {
            String sql = "SELECT `Executive Name`, `Executive Email`, `Medicine Name` FROM `collect medicine`";
            
            connection = (Connection) DriverManager.getConnection(
                "jdbc:mysql://localhost/MedConnect", "root", ""
            );
            statement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String[] row = {
                    resultSet.getString("Medicine Name"),
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
        viewNGOButton.addActionListener(handler);
        viewMedicineButton.addActionListener(handler);
        viewUserButton.addActionListener(handler);
        assignExecutiveButton.addActionListener(handler);
        viewExecutiveButton.addActionListener(handler);
        logoutButton.addActionListener(handler);
        viewDetailsButton.addActionListener(handler);
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
            } else if (event.getSource() == viewMedicineButton) {
                navigateTo(new ViewMedNGO());
            } else if (event.getSource() == viewUserButton) {
                navigateTo(new UserView());
            } else if (event.getSource() == assignExecutiveButton) {
                navigateTo(new AssignExecutive());
            } else if (event.getSource() == viewExecutiveButton) {
                navigateTo(new ViewExecutive());
            } else if (event.getSource() == viewDetailsButton) {
                navigateTo(new ViewDetailsCollect());
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
        ViewCollectMedicine frame = new ViewCollectMedicine();
        frame.setVisible(true);
    }
}            