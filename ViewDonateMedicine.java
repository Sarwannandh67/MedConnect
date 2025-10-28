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
 * ViewDonateMedicine - Display donated medicines for Executive
 * Shows all medicines donated through the system with donator information
 * 
 * @author Team
 */
public class ViewDonateMedicine extends JFrame {

    // ========== UI COMPONENTS ==========
    // Title and Navigation
    private JLabel titleLabel;
    private JLabel navigationBar;
    private JLabel sectionTitleLabel;
    
    // Navigation Buttons
    private JButton homeButton;
    private JButton assignButton;
    private JButton viewMedicineButton;
    private JButton viewBuyMedicineButton;
    private JButton buyMedicineButton;
    private JButton collectMedicineButton;
    private JButton viewDetailsButton;
    private JButton logoutButton;
    
    // Table Components
    private JTable donateTable;
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
    public ViewDonateMedicine() {
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
        this.setTitle("Executive - View Donated Medicines");
        this.setResizable(false);
        
        contentPane = this.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Theme.PRIMARY_BG);
        
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
        titleLabel.setForeground(Theme.PRIMARY_ACCENT);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Theme.PRIMARY_ACCENT);
        contentPane.add(titleLabel);
    }

    /**
     * Create and setup navigation bar with buttons
     */
    private void createNavigationBar() {
        navigationBar = new JLabel();
        navigationBar.setBounds(1, 100, 1930, 150);
        navigationBar.setOpaque(true);
        navigationBar.setBackground(Theme.SECONDARY_BG);
        contentPane.add(navigationBar);

        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
        Font buttonFont = new Font("Arial", Font.BOLD, 24);

        // Home Button
        homeButton = createButton("Home Page", 20, 30, 170, 80, handCursor, buttonFont, navigationBar);

        // Assign Button
        assignButton = createButton("View Assigned User", 191, 30, 270, 80, handCursor, buttonFont, navigationBar);

        // View Medicine Button
        viewMedicineButton = createButton("View Medicine", 462, 30, 224, 80, handCursor, buttonFont, navigationBar);

        // View Buy Medicine Button
        viewBuyMedicineButton = createButton("View Buy Med", 687, 30, 199, 80, handCursor, buttonFont, navigationBar);

        // Buy Medicine Button
        buyMedicineButton = createButton("Buy Medicine", 887, 30, 190, 80, handCursor, buttonFont, navigationBar);

        // Collect Medicine Button
        collectMedicineButton = createButton("Collect Medicine", 1078, 30, 230, 80, handCursor, buttonFont, navigationBar);

        // Logout Button
        logoutButton = createButton("LogOut", 1309, 30, 120, 80, handCursor, buttonFont, navigationBar);

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
        
        sectionTitleLabel = new JLabel("View Donated Medicines");
        sectionTitleLabel.setFont(sectionFont);
        sectionTitleLabel.setBounds(1, 260, 1900, 80);
        sectionTitleLabel.setForeground(Theme.PRIMARY_ACCENT);
        sectionTitleLabel.setOpaque(true);
        sectionTitleLabel.setBackground(Theme.PRIMARY_ACCENT);
        contentPane.add(sectionTitleLabel);
    }

    /**
     * Create table section to display donated medicine information
     */
    private void createTableSection() {
        Font tableContentFont = new Font("Arial", Font.BOLD, 18);
        Font tableHeaderFont = new Font("Arial", Font.BOLD, 20);

        // Define table columns
        String[] columns = {"Medicine Name", "Executive Name", "Executive Email", 
                           "Donator Name", "Donator Email"};
        
        // Create table model
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columns);

        // Create table
        donateTable = new JTable();
        donateTable.setModel(tableModel);
        donateTable.setFont(tableContentFont);
        donateTable.setSelectionBackground(Theme.PRIMARY_ACCENT);
        donateTable.setRowHeight(40);

        // Setup table header
        JTableHeader header = donateTable.getTableHeader();
        header.setFont(tableHeaderFont);
        header.setEnabled(false);
        header.setBackground(Theme.PRIMARY_BG);

        // Create scroll pane
        scrollPane = new JScrollPane(donateTable, 
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(1, 350, 1900, 530);
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
     * Load donated medicine data into table from database
     */
    private void loadTableData() {
        try {
            String sql = "SELECT `Medicine Name`, `Executive Name`, `Executive Email`, " +
                        "`User Name`, `User Email` FROM `donate medicine`";
            
            connection = (Connection) DriverManager.getConnection(
                "jdbc:mysql://localhost/MedConnect", "root", ""
            );
            statement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String[] row = {
                    resultSet.getString("Medicine Name"),
                    resultSet.getString("Executive Name"),
                    resultSet.getString("Executive Email"),
                    resultSet.getString("User Name"),
                    resultSet.getString("User Email")
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
        assignButton.addActionListener(handler);
        viewMedicineButton.addActionListener(handler);
        viewBuyMedicineButton.addActionListener(handler);
        buyMedicineButton.addActionListener(handler);
        collectMedicineButton.addActionListener(handler);
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
            } else if (event.getSource() == assignButton) {
                navigateTo(new Viewassignexecutive());
            } else if (event.getSource() == viewMedicineButton) {
                navigateTo(new MedicineView());
            } else if (event.getSource() == viewBuyMedicineButton) {
                navigateTo(new User());
            } else if (event.getSource() == buyMedicineButton) {
                navigateTo(new BuyMedicine());
            } else if (event.getSource() == collectMedicineButton) {
                navigateTo(new CollectMedicine());
            } else if (event.getSource() == viewDetailsButton) {
                navigateTo(new ViewDetails());
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
        ViewDonateMedicine frame = new ViewDonateMedicine();
        frame.setVisible(true);
    }
}