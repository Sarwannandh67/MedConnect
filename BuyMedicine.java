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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * BuyMedicine class - Allows executives to buy medicines from inventory
 * Provides functionality to search medicines and create buy records
 * @author Hp
 */
public class BuyMedicine extends JFrame {
    
    // UI Components - Headers and Labels
    private JLabel title, title1, title2, label, label1, label2;
    private JLabel namel, pricel, mnfl, expl;
    
    // UI Components - Input Fields
    private JTextField priceltf, mnfltf, expltf;
    private JComboBox nameltf;
    
    // UI Components - Buttons
    private JButton viewmedicine, homepage, logout, assign, collect, buymed;
    private JButton search, buymedicine, viewuser, donate;
    
    // UI Components - Other
    private JPasswordField passwordtf;
    private JCheckBox showpasscheckbox;
    private ImageIcon icon;
    
    // Container
    private Container c;
    private Cursor cursor;
    
    // Database components
    private Connection con, con1;
    private PreparedStatement pst, pst1;
    private ResultSet rs, rs1;
    
    // Variables
    private String uname = ExecutiveLogin.nametf.getText();
    private String email = ExecutiveLogin.emailtf.getText();

    /**
     * Constructor
     */
    public BuyMedicine() {
        initComponents();
        ComboBox();
    }

    /**
     * Initialize all UI components
     */
    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1930, 1030);
        this.setTitle("Executive");
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

        cursor = new Cursor(Cursor.HAND_CURSOR);
        Font font1 = new Font("Arial", Font.BOLD, 24);

        // Create navigation buttons
        createNavigationButtons(font1, cursor, label);

        // Create main content area
        createMainContentArea(font1, cursor);

        // Add action listeners
        addActionListeners();
    }

    /**
     * Create and setup navigation buttons
     */
    private void createNavigationButtons(Font font1, Cursor cursor, JLabel label) {
        homepage = new JButton("Home Page");
        homepage.setFont(font1);
        homepage.setBounds(20, 30, 170, 80);
        homepage.setCursor(cursor);
        label.add(homepage);

        assign = new JButton("View Assigned User");
        assign.setFont(font1);
        assign.setBounds(191, 30, 270, 80);
        assign.setCursor(cursor);
        label.add(assign);

        viewmedicine = new JButton("View Medicine");
        viewmedicine.setFont(font1);
        viewmedicine.setBounds(462, 30, 224, 80);
        viewmedicine.setCursor(cursor);
        label.add(viewmedicine);

        viewuser = new JButton("View Buy Med");
        viewuser.setFont(font1);
        viewuser.setBounds(687, 30, 199, 80);
        viewuser.setCursor(cursor);
        label.add(viewuser);

        donate = new JButton("View Donate");
        donate.setFont(font1);
        donate.setBounds(887, 30, 180, 80);
        donate.setCursor(cursor);
        label.add(donate);

        collect = new JButton("Collect Medicine");
        collect.setCursor(cursor);
        collect.setFont(font1);
        collect.setBounds(1068, 30, 230, 80);
        label.add(collect);

        logout = new JButton("LogOut");
        logout.setFont(font1);
        logout.setBounds(1299, 30, 120, 80);
        logout.setCursor(cursor);
        label.add(logout);

        // User info label
        label2 = new JLabel("Hi " + uname);
        label2.setBounds(1660, 30, 500, 80);
        label2.setFont(font1);
        label2.setToolTipText(uname);
        label.add(label2);
    }

    /**
     * Create main content area with medicine form
     */
    private void createMainContentArea(Font font1, Cursor cursor) {
        label1 = new JLabel();
        label1.setBounds(1, 260, 1930, 1030);
        label1.setOpaque(true);
        label1.setBackground(Color.LIGHT_GRAY);
        c.add(label1);

        title1 = new JLabel("Buy Medicine");
        title1.setFont(font1);
        title1.setBounds(1, 1, 1900, 60);
        title1.setForeground(Color.BLUE);
        title1.setOpaque(true);
        title1.setBackground(Color.ORANGE);
        label1.add(title1);

        title2 = new JLabel("Please enter medicine name and click the search button");
        title2.setFont(font1);
        title2.setBounds(600, 68, 1900, 27);
        label1.add(title2);

        // Create search section
        createSearchSection(font1, cursor, label1);

        // Create medicine details section
        createMedicineDetailsSection(font1, label1);

        // Create buy button
        buymedicine = new JButton("Buy Medicine");
        buymedicine.setCursor(cursor);
        buymedicine.setFont(font1);
        buymedicine.setBounds(600, 450, 600, 60);
        label1.add(buymedicine);
    }

    /**
     * Create search section with medicine name combo box and search button
     */
    private void createSearchSection(Font font1, Cursor cursor, JLabel label1) {
        namel = new JLabel("Medicine Name");
        namel.setFont(font1);
        namel.setBounds(50, 100, 300, 40);
        label1.add(namel);

        nameltf = new JComboBox();
        nameltf.setFont(font1);
        nameltf.setBounds(290, 100, 1400, 40);
        label1.add(nameltf);

        search = new JButton("Search");
        search.setCursor(cursor);
        search.setFont(font1);
        search.setBounds(1710, 100, 150, 60);
        label1.add(search);
    }

    /**
     * Create medicine details display section
     */
    private void createMedicineDetailsSection(Font font1, JLabel label1) {
        pricel = new JLabel("Price (10 pcs)");
        pricel.setFont(font1);
        pricel.setBounds(50, 160, 300, 40);
        label1.add(pricel);

        priceltf = new JTextField("");
        priceltf.setFont(font1);
        priceltf.setBounds(290, 160, 1400, 40);
        priceltf.setEditable(false);
        label1.add(priceltf);

        mnfl = new JLabel("Manufacture Date");
        mnfl.setFont(font1);
        mnfl.setBounds(50, 220, 300, 40);
        label1.add(mnfl);

        mnfltf = new JTextField("");
        mnfltf.setFont(font1);
        mnfltf.setBounds(290, 220, 1400, 40);
        mnfltf.setEditable(false);
        label1.add(mnfltf);

        expl = new JLabel("Expiry Date");
        expl.setFont(font1);
        expl.setBounds(50, 280, 300, 40);
        label1.add(expl);

        expltf = new JTextField("");
        expltf.setFont(font1);
        expltf.setBounds(290, 280, 1400, 40);
        expltf.setEditable(false);
        label1.add(expltf);
    }

    /**
     * Add action listeners to all buttons
     */
    private void addActionListeners() {
        Handler handler = new Handler();
        search.addActionListener(handler);
        assign.addActionListener(handler);
        homepage.addActionListener(handler);
        collect.addActionListener(handler);
        viewuser.addActionListener(handler);
        viewmedicine.addActionListener(handler);
        donate.addActionListener(handler);
        logout.addActionListener(handler);
        buymedicine.addActionListener(handler);
    }

    /**
     * Inner class to handle button events
     */
    private class Handler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            String name = nameltf.getSelectedItem().toString();

            if (ae.getSource() == search) {
                handleSearchMedicine(name);
            } else if (ae.getSource() == buymedicine) {
                handleBuyMedicine(name);
            } else if (ae.getSource() == homepage) {
                navigateTo(new HomePage());
            } else if (ae.getSource() == assign) {
                navigateTo(new Viewassignexecutive());
            } else if (ae.getSource() == viewmedicine) {
                navigateTo(new MedicineView());
            } else if (ae.getSource() == viewuser) {
                navigateTo(new User());
            } else if (ae.getSource() == donate) {
                navigateTo(new ViewDonateMedicine());
            } else if (ae.getSource() == collect) {
                navigateTo(new CollectMedicine());
            } else if (ae.getSource() == logout) {
                handleLogout();
            }
        }

        /**
         * Handle search medicine functionality
         */
        private void handleSearchMedicine(String name) {
            int a = 0;

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter medicine name", "Warning", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    String sql = "SELECT `Price`, `Manufacture Date`, `Expire Date` FROM `addmedicine` WHERE `Medicine Name`=?";
                    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MedConnect", "root", "");
                    pst = (PreparedStatement) con.prepareStatement(sql);
                    pst.setString(1, name);
                    rs = pst.executeQuery();

                    while (rs.next()) {
                        priceltf.setText(rs.getString("Price"));
                        mnfltf.setText(rs.getString("Manufacture Date"));
                        expltf.setText(rs.getString("Expire Date"));
                        a = 1;
                        break;
                    }

                    con.close();
                    pst.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Something went wrong: " + ex);
                }

                if (a == 0) {
                    JOptionPane.showMessageDialog(null, "Please Enter Correct Medicine Name", "Warning", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        /**
         * Handle buy medicine functionality
         */
        private void handleBuyMedicine(String name) {
            if (name.isEmpty() || priceltf.getText().isEmpty()
                    || mnfltf.getText().isEmpty() || expltf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter medicine name or Manufacture Date or Expiry Date", "Warning", JOptionPane.ERROR_MESSAGE);
            } else if (checkusername(name)) {
                JOptionPane.showMessageDialog(null, "This medicine already exists");
            } else {
                insertBuyMedicine(name);
            }
        }

        /**
         * Insert medicine purchase record into database
         */
        private void insertBuyMedicine(String name) {
            String sql = "INSERT INTO `buymedicine`(`Medicine Name`, `Price`, `Manufacture Date`, `Expiry Date`, `Executive Name`, `Executive Email`) VALUES (?, ?, ?, ?, ?, ?)";

            try {
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MedConnect", "root", "");
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, priceltf.getText());
                pst.setString(3, mnfltf.getText());
                pst.setString(4, expltf.getText());
                pst.setString(5, uname);
                pst.setString(6, email);

                pst.executeUpdate();
                con.close();

                JOptionPane.showMessageDialog(null, "Buy Medicine successfully");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Buy Medicine unsuccessfully: " + ex);
            }
        }

        /**
         * Handle logout
         */
        private void handleLogout() {
            int a = JOptionPane.showConfirmDialog(null, "Are You Sure?");
            if (a == JOptionPane.YES_OPTION) {
                setVisible(false);
                ExecutiveLogin frame = new ExecutiveLogin();
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
     * Check if medicine is already in buy list
     */
    public boolean checkusername(String medname) {
        boolean med = false;
        Connection con2 = null;
        PreparedStatement pst2 = null;
        ResultSet rs;
        String sql = "SELECT * FROM `buymedicine` WHERE `Medicine Name`=?";

        try {
            con2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MedConnect", "root", "");
            pst2 = (PreparedStatement) con2.prepareStatement(sql);
            pst2.setString(1, medname);
            rs = pst2.executeQuery();

            if (rs.next()) {
                med = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        return med;
    }

    /**
     * Load medicine names from database into combo box
     */
    private void ComboBox() {
        try {
            String sql1 = "SELECT * FROM `addmedicine`";
            con1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MedConnect", "root", "");
            pst1 = (PreparedStatement) con1.prepareStatement(sql1);
            rs1 = pst1.executeQuery();

            while (rs1.next()) {
                String name = rs1.getString("Medicine Name");
                nameltf.addItem(name);
            }
        } catch (Exception e) {
            // Handle exception
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        BuyMedicine frame = new BuyMedicine();
        frame.setVisible(true);
    }
}        