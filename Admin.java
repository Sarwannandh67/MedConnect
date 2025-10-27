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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Admin class - Main dashboard for administrators
 * @author Hp
 */
public class Admin extends JFrame {
    
    // UI Components
    private JLabel label, title, label1, label2, label3;
    private JPanel panel;
    private JButton homepage, addmed, viewuser, viewngo, viewmed, addngo, logout;
    private Container c;
    
    // Database components
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    /**
     * Constructor
     */
    public Admin() {
        initComponents();
    }

    /**
     * Initialize all UI components
     */
    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1930, 1030);
        this.setTitle("Admin");
        c = this.getContentPane();
        c.setLayout(null);
        this.setResizable(false);
        c.setBackground(new Color(0xFAEB92));

        // Title setup
        Font font = new Font("Arial", Font.BOLD, 42);
        title = new JLabel("MedConnect");
        title.setFont(font);
        title.setBounds(2, 2, 1930, 80);
        title.setForeground(new Color(0x000000));
        title.setOpaque(true);
        title.setBackground(new Color(0xFAEB92));
        c.add(title);

        // Navigation bar setup
        label = new JLabel();
        label.setBounds(1, 100, 1930, 150);
        label.setOpaque(true);
        label.setBackground(new Color(0xFAEB92));
        c.add(label);

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        Font font1 = new Font("Arial", Font.BOLD, 24);

        // Create navigation buttons
        createNavigationButtons(font1, cursor, label);

        // Create welcome message
        createWelcomeMessage(font1);

        // Add action listeners
        addActionListeners();
    }

    /**
     * Create and setup navigation buttons
     */
    private void createNavigationButtons(Font font1, Cursor cursor, JLabel label) {
        homepage = new JButton("Home Page");
        homepage.setFont(font1);
        homepage.setBackground(new Color(0x9929EA));
        homepage.setBounds(20, 30, 200, 80);
        homepage.setCursor(cursor);
        label.add(homepage);

        addngo = new JButton("Add NGO");
        addngo.setCursor(cursor);
        addngo.setFont(font1);
        addngo.setBounds(221, 30, 200, 80);
        label.add(addngo);

        addmed = new JButton("Add Medicine");
        addmed.setCursor(cursor);
        addmed.setFont(font1);
        addmed.setBounds(422, 30, 250, 80);
        label.add(addmed);

        viewngo = new JButton("View NGO");
        viewngo.setCursor(cursor);
        viewngo.setFont(font1);
        viewngo.setBounds(673, 30, 200, 80);
        label.add(viewngo);

        viewmed = new JButton("View Medicine");
        viewmed.setCursor(cursor);
        viewmed.setFont(font1);
        viewmed.setBounds(874, 30, 250, 80);
        label.add(viewmed);

        viewuser = new JButton("View User");
        viewuser.setCursor(cursor);
        viewuser.setFont(font1);
        viewuser.setBounds(1125, 30, 200, 80);
        label.add(viewuser);

        logout = new JButton("Logout");
        logout.setCursor(cursor);
        logout.setFont(font1);
        logout.setBounds(1326, 30, 200, 80);
        label.add(logout);

        // User info label
        label2 = new JLabel("Hi " + Login.usernametf.getText());
        label2.setBounds(1660, 30, 500, 80);
        label2.setFont(font1);
        label2.setToolTipText(Login.usernametf.getText());
        label.add(label2);
    }

    /**
     * Create welcome message section
     */
    private void createWelcomeMessage(Font font1) {
        label1 = new JLabel("Welcome " + Login.usernametf.getText());
        label1.setBounds(100, 500, 1900, 200);
        Font font2 = new Font("Arial", Font.BOLD, 50);
        label1.setFont(font2);
        c.add(label1);
    }

    /**
     * Add action listeners to all buttons
     */
    private void addActionListeners() {
        Handler handler = new Handler();
        homepage.addActionListener(handler);
        addngo.addActionListener(handler);
        addmed.addActionListener(handler);
        viewngo.addActionListener(handler);
        viewmed.addActionListener(handler);
        viewuser.addActionListener(handler);
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
            } else if (ae.getSource() == addngo) {
                navigateTo(new AddNGO());
            } else if (ae.getSource() == addmed) {
                navigateTo(new AddMedicine());
            } else if (ae.getSource() == viewngo) {
                navigateTo(new ViewNGO());
            } else if (ae.getSource() == viewmed) {
                navigateTo(new ViewMedicine());
            } else if (ae.getSource() == viewuser) {
                navigateTo(new ViewUser());
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
        Admin frame = new Admin();
        frame.setVisible(true);
    }
}