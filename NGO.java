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
 * NGO class - Main dashboard for NGO users
 * Provides navigation to various NGO operations like viewing medicine, users, and assigned executives
 * @author Hp
 */
public class NGO extends JFrame {

    // UI Components - Headers and Labels
    private JLabel title, title1, label, label1, label2;

    // UI Components - Buttons
    private JButton homepage, user, assign, logout, viwngo, viewmed, viewexecutive, collect;

    // Container
    private Container c;
    private Cursor cursor;

    /**
     * Constructor
     */
    public NGO() {
        initComponents();
    }

    /**
     * Initialize all UI components
     */
    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1930, 1030);
        this.setTitle("NGO");
        c = this.getContentPane();
        c.setLayout(null);
        this.setResizable(false);
        c.setBackground(Color.GRAY);

        // Create header and navigation
        createHeaderSection();

        // Create welcome message
        createWelcomeMessage();

        // Add action listeners
        addActionListeners();
    }

    /**
     * Create header and navigation section
     */
    private void createHeaderSection() {
        // Title
        Font font = new Font("Arial", Font.BOLD, 42);
        title = new JLabel("MedConnect");
        title.setFont(font);
        title.setBounds(2, 2, 1930, 80);
        title.setForeground(Color.BLUE);
        title.setOpaque(true);
        title.setBackground(Color.ORANGE);
        c.add(title);

        // Navigation bar
        label = new JLabel();
        label.setBounds(1, 100, 1930, 150);
        label.setOpaque(true);
        label.setBackground(Color.MAGENTA);
        c.add(label);

        cursor = new Cursor(Cursor.HAND_CURSOR);
        Font font1 = new Font("Arial", Font.BOLD, 24);

        // Create navigation buttons
        createNavigationButtons(font1, cursor, label);

        // Create user info label
        label2 = new JLabel("Hi " + NGOLogin.usernametf.getText());
        label2.setBounds(1680, 30, 500, 80);
        label2.setFont(font1);
        label2.setToolTipText(NGOLogin.usernametf.getText());
        label.add(label2);
    }

    /**
     * Create and setup navigation buttons
     */
    private void createNavigationButtons(Font font1, Cursor cursor, JLabel label) {
        homepage = new JButton("Home Page");
        homepage.setFont(font1);
        homepage.setBounds(20, 30, 180, 80);
        homepage.setCursor(cursor);
        label.add(homepage);

        viwngo = new JButton("View NGO");
        viwngo.setCursor(cursor);
        viwngo.setFont(font1);
        viwngo.setBounds(201, 30, 180, 80);
        label.add(viwngo);

        viewmed = new JButton("View Medicine");
        viewmed.setCursor(cursor);
        viewmed.setFont(font1);
        viewmed.setBounds(382, 30, 220, 80);
        label.add(viewmed);

        user = new JButton("View user");
        user.setCursor(cursor);
        user.setFont(font1);
        user.setBounds(603, 30, 180, 80);
        label.add(user);

        assign = new JButton("Assing Executive");
        assign.setCursor(cursor);
        assign.setFont(font1);
        assign.setBounds(784, 30, 250, 80);
        label.add(assign);

        viewexecutive = new JButton("View Executive");
        viewexecutive.setCursor(cursor);
        viewexecutive.setFont(font1);
        viewexecutive.setBounds(1035, 30, 222, 80);
        label.add(viewexecutive);

        collect = new JButton("View Collect Med");
        collect.setCursor(cursor);
        collect.setFont(font1);
        collect.setBounds(1258, 30, 250, 80);
        label.add(collect);

        logout = new JButton("Logout");
        logout.setCursor(cursor);
        logout.setFont(font1);
        logout.setBounds(1509, 30, 140, 80);
        label.add(logout);
    }

    /**
     * Create welcome message for the NGO user
     */
    private void createWelcomeMessage() {
        String username = NGOLogin.usernametf.getText();
        
        label1 = new JLabel("Welcome " + username);
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
        viwngo.addActionListener(handler);
        viewmed.addActionListener(handler);
        user.addActionListener(handler);
        assign.addActionListener(handler);
        viewexecutive.addActionListener(handler);
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
            } else if (ae.getSource() == viwngo) {
                navigateTo(new ViewNGOngo());
            } else if (ae.getSource() == viewmed) {
                navigateTo(new ViewMedNGO());
            } else if (ae.getSource() == user) {
                navigateTo(new UserView());
            } else if (ae.getSource() == assign) {
                navigateTo(new AssignExecutive());
            } else if (ae.getSource() == viewexecutive) {
                navigateTo(new ViewExecutive());
            } else if (ae.getSource() == collect) {
                navigateTo(new ViewCollectMedicine());
            } else if (ae.getSource() == logout) {
                handleLogout();
            }
        }

        /**
         * Handle logout with confirmation
         */
        private void handleLogout() {
            int a = JOptionPane.showConfirmDialog(null, "Are You Sure?");
            if (a == JOptionPane.YES_OPTION) {
                setVisible(false);
                NGOLogin frame = new NGOLogin();
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
        NGO frame = new NGO();
        frame.setVisible(true);
    }
}            