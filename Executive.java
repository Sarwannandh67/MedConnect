package online.medicine.donation.system;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Executive class - Main dashboard for executives
 * Provides navigation for medicine management, collection, and user views
 * @author Hp
 */
public class Executive extends JFrame {
    
    // UI Components
    private JLabel title, userlabel, title1, label2, label, label1;
    private JButton homepage, logout, assign, collect, buymed, viewmedicine, viewuser, donate;
    private Cursor cursor;
    private JPasswordField passwordtf;
    private JCheckBox showpasscheckbox;
    private ImageIcon icon;
    private JTextField emailtf, nametf;
    private Container c;
    
    // Database components
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    
    // Variables
    private String uname = ExecutiveLogin.nametf.getText();

    /**
     * Constructor
     */
    public Executive() {
        initComponents();
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
        homepage.setBounds(20, 30, 170, 80);
        homepage.setCursor(cursor);
        label.add(homepage);

        assign = new JButton("View Assigned User");
        assign.setFont(font1);
        assign.setBounds(191, 30, 265, 80);
        assign.setCursor(cursor);
        label.add(assign);

        viewmedicine = new JButton("View Medicine");
        viewmedicine.setFont(font1);
        viewmedicine.setBounds(457, 30, 200, 80);
        viewmedicine.setCursor(cursor);
        label.add(viewmedicine);

        viewuser = new JButton("View Buy Med");
        viewuser.setFont(font1);
        viewuser.setBounds(658, 30, 199, 80);
        viewuser.setCursor(cursor);
        label.add(viewuser);

        buymed = new JButton("Buy Medicine");
        buymed.setFont(font1);
        buymed.setBounds(858, 30, 190, 80);
        buymed.setCursor(cursor);
        label.add(buymed);

        donate = new JButton("View Donate");
        donate.setFont(font1);
        donate.setBounds(1049, 30, 180, 80);
        donate.setCursor(cursor);
        label.add(donate);

        collect = new JButton("Collect Medicine");
        collect.setCursor(cursor);
        collect.setFont(font1);
        collect.setBounds(1230, 30, 230, 80);
        label.add(collect);

        logout = new JButton("LogOut");
        logout.setFont(font1);
        logout.setBounds(1461, 30, 120, 80);
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
     * Create welcome message section
     */
    private void createWelcomeMessage(Font font1) {
        label1 = new JLabel("Welcome " + uname);
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
        assign.addActionListener(handler);
        viewmedicine.addActionListener(handler);
        viewuser.addActionListener(handler);
        buymed.addActionListener(handler);
        donate.addActionListener(handler);
        collect.addActionListener(handler);
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
            } else if (ae.getSource() == assign) {
                navigateTo(new Viewassignexecutive());
            } else if (ae.getSource() == viewmedicine) {
                navigateTo(new MedicineView());
            } else if (ae.getSource() == viewuser) {
                navigateTo(new User());
            } else if (ae.getSource() == buymed) {
                navigateTo(new BuyMedicine());
            } else if (ae.getSource() == donate) {
                navigateTo(new ViewDonateMedicine());
            } else if (ae.getSource() == collect) {
                navigateTo(new CollectMedicine());
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
     * Main method
     */
    public static void main(String[] args) {
        Executive frame = new Executive();
        frame.setVisible(true);
    }
}            