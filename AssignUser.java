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
 * AssignUser class - Displays all registered users in a table format
 * @author Hp
 */
public class AssignUser extends JFrame {
    
    // UI Components
    private Container c;
    private JTable table;
    private JLabel label, label2, title, title1;
    private JButton homepage, assign, logout;
    private DefaultTableModel mode;
    private JScrollPane scroll, scroll1;
    
    // Database components
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    /**
     * Constructor
     */
    public AssignUser() {
        initComponents();
    }

    /**
     * Initialize all UI components
     */
    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1930, 1030);
        this.setTitle("View Users");
        c = this.getContentPane();
        c.setLayout(null);
        this.setResizable(false);
        c.setBackground(Color.GRAY);

        // Title setup
        Font font = new Font("Arial", Font.BOLD, 42);
        title = new JLabel("MedConnect");
        title.setFont(font);
        title.setBounds(2, 2, 1930, 80);
        title.setForeground(Color.BLUE);
        title.setOpaque(true);
        title.setBackground(Color.ORANGE);
        c.add(title);

        // Navigation bar setup
        label = new JLabel();
        label.setBounds(1, 100, 1930, 150);
        label.setOpaque(true);
        label.setBackground(Color.MAGENTA);
        c.add(label);

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        Font font1 = new Font("Arial", Font.BOLD, 24);
        Font font2 = new Font("Arial", Font.BOLD, 22);

        // Create navigation buttons
        createNavigationButtons(font1, cursor, label);

        // Create table section title
        title1 = new JLabel("View User");
        title1.setFont(font1);
        title1.setBounds(1, 260, 1900, 80);
        title1.setForeground(Color.BLUE);
        title1.setOpaque(true);
        title1.setBackground(Color.ORANGE);
        c.add(title1);

        // Create and populate table
        createUserTable(font1, font2);

        // Add action listeners
        addActionListeners();
    }

    /**
     * Create and setup navigation buttons
     */
    private void createNavigationButtons(Font font1, Cursor cursor, JLabel label) {
        homepage = new JButton("Home Page");
        homepage.setFont(font1);
        homepage.setBounds(20, 30, 250, 80);
        homepage.setCursor(cursor);
        label.add(homepage);

        logout = new JButton("Logout");
        logout.setCursor(cursor);
        logout.setFont(font1);
        logout.setBounds(271, 30, 250, 80);
        label.add(logout);

        // User info label
        label2 = new JLabel("Hi " + NGOLogin.usernametf.getText());
        label2.setBounds(1660, 30, 500, 80);
        label2.setToolTipText(NGOLogin.usernametf.getText());
        label2.setFont(font1);
        label.add(label2);
    }

    /**
     * Create and populate the user table with data from database
     */
    private void createUserTable(Font font1, Font font2) {
        // Define table columns
        String[] column = {"ID", "Name", "Gender", "Age", "Email", "Contact Number", "Location"};
        String[] row = new String[7];

        // Create table model
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        table.setModel(model);

        // Configure table appearance
        table.setFont(font2);
        table.setSelectionBackground(Color.PINK);
        table.setRowHeight(40);
        table.setEnabled(false);

        // Create and add scroll pane
        scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(1, 350, 1900, 630);
        c.add(scroll);

        // Configure table header
        JTableHeader header = table.getTableHeader();
        header.setFont(font1);
        header.setEnabled(false);

        // Configure table columns width
        configureTableColumns();

        // Load data from database
        loadUserData(model);
    }

    /**
     * Configure table column widths
     */
    private void configureTableColumns() {
        TableColumnModel col = table.getColumnModel();
        TableColumn c1 = col.getColumn(1);
        TableColumn c2 = col.getColumn(4);
        TableColumn c3 = col.getColumn(5);
        TableColumn c4 = col.getColumn(6);
        TableColumn c5 = col.getColumn(3);
        TableColumn c6 = col.getColumn(2);

        c1.setPreferredWidth(400);
        c2.setPreferredWidth(400);
        c3.setPreferredWidth(200);
        c4.setPreferredWidth(400);
        c5.setPreferredWidth(50);
        c6.setPreferredWidth(50);
    }

    /**
     * Load user data from database and populate table
     */
    private void loadUserData(DefaultTableModel model) {
        try {
            String sql = "SELECT `ID`, `Name`, `Gender`, `Age`, `Email`, `Contact Number`, `Location` FROM `userregistration`";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MedConnect", "root", "");
            pst = (PreparedStatement) con.prepareStatement(sql);
            rs = pst.executeQuery();

            // Add each row to the table
            while (rs.next()) {
                String[] n = {
                    rs.getString("ID"),
                    rs.getString("Name"),
                    rs.getString("Gender"),
                    rs.getString("Age"),
                    rs.getString("Email"),
                    rs.getString("Contact Number"),
                    rs.getString("Location")
                };
                model.addRow(n);
            }

            con.close();
            pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Something went wrong: " + ex);
        }
    }

    /**
     * Add action listeners to all buttons
     */
    private void addActionListeners() {
        Handler handler = new Handler();
        homepage.addActionListener(handler);
        logout.addActionListener(handler);
    }

    /**
     * Inner class to handle button events
     */
    private class Handler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == homepage) {
                navigateTo(new HomePage());
            } else if (ae.getSource() == logout) {
                handleLogout();
            }
        }

        /**
         * Handle logout
         */
        private void handleLogout() {
            int a = JOptionPane.showConfirmDialog(null, "Are You Sure?");
            if (a == JOptionPane.YES_OPTION) {
                setVisible(false);
                Login frame = new Login();
                frame.setVisible(true);
            }
        }

        /**
         * Navigate to another frame
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
        AssignUser frame = new AssignUser();
        frame.setVisible(true);
    }
}        