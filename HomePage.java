package online.medicine.donation.system;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * HomePage class - Main landing page for MedConnect application
 * Provides navigation to different user roles (Admin, NGO, Executive, User)
 * @author Hp
 */
public class HomePage extends JFrame {
    
    // UI Components
    private JLabel title, imglabel, label, l;
    private JButton homepage, logout, ngo, user, executive, admin;
    private JPanel panel1;
    private Container c;
    
    // Resources
    private ImageIcon icon;
    private Cursor cursor;

    /**
     * Constructor
     */
    public HomePage() {
        initComponents();
    }

    /**
     * Initialize all UI components
     */
    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1930, 1030);
        this.setTitle("MedConnect");
        c = this.getContentPane();
        c.setLayout(null);
        this.setResizable(false);
        c.setBackground(Theme.PRIMARY_BG);

        // Title setup
        Font font = new Font("Arial", Font.BOLD, 42);
        title = new JLabel("MedConnect");
        title.setFont(font);
        title.setBounds(2, 2, 1930, 80);
        title.setForeground(Theme.PRIMARY_TEXT);
        title.setOpaque(true);
        title.setBackground(Theme.SECONDARY_BG);
        c.add(title);

        // Navigation bar setup
        label = new JLabel();
        label.setBounds(1, 100, 1930, 150);
        label.setOpaque(true);
        label.setBackground(Theme.SECONDARY_BG);
        c.add(label);

        cursor = new Cursor(Cursor.HAND_CURSOR);
        Font font1 = new Font("Arial", Font.BOLD, 24);

        // Create navigation buttons
        createNavigationButtons(font1, cursor, label);

        // Create main content area with background image
        createMainContentArea(font);

        // Add action listeners
        addActionListeners();
    }

    /**
     * Create and setup navigation buttons
     */
    private void createNavigationButtons(Font font1, Cursor cursor, JLabel label) {
        homepage = new JButton("Home Page");
        homepage.setFont(font1);
        homepage.setBackground(Theme.PRIMARY_ACCENT);
        homepage.setForeground(Theme.PRIMARY_BG);
        homepage.setBounds(20, 30, 200, 80);
        homepage.setCursor(cursor);
        label.add(homepage);

        admin = new JButton("Admin");
        admin.setFont(font1);
        admin.setBounds(221, 30, 200, 80);
        admin.setCursor(cursor);
        admin.setBackground(Theme.PRIMARY_ACCENT);
        label.add(admin);

        ngo = new JButton("NGO");
        ngo.setFont(font1);
        ngo.setBounds(422, 30, 200, 80);
        ngo.setCursor(cursor);
        ngo.setBackground(Theme.PRIMARY_ACCENT);
        label.add(ngo);

        executive = new JButton("Executive");
        executive.setFont(font1);
        executive.setBounds(623, 30, 200, 80);
        executive.setCursor(cursor);
        executive.setBackground(Theme.PRIMARY_ACCENT);
        label.add(executive);

        user = new JButton("User");
        user.setFont(font1);
        user.setBounds(824, 30, 200, 80);
        user.setCursor(cursor);
        user.setBackground(Theme.PRIMARY_ACCENT);
        label.add(user);

        logout = new JButton("LogOut");
        logout.setFont(font1);
        logout.setBounds(1025, 30, 200, 80);
        logout.setCursor(cursor);
        logout.setBackground(Theme.PRIMARY_ACCENT);
        label.add(logout);

    }

    /**
     * Create main content area with background image
     */
    private void createMainContentArea(Font font) {
        panel1 = new JPanel();
        panel1.setBounds(1, 260, 1930, 720);
        c.add(panel1);

        // Load and scale background image
        icon = new ImageIcon(getClass().getResource("medicin.jpeg"));
        Image img = icon.getImage();
        Image newimage = img.getScaledInstance(panel1.getWidth(), panel1.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimage);

        // Add background image
        imglabel = new JLabel(icon);
        panel1.add(imglabel);

        // Add MedConnect branding label
        l = new JLabel("|MedConnect|");
        l.setBounds(1100, 400, 500, 50);
        l.setFont(font);
        l.setForeground(Theme.PRIMARY_TEXT);
        imglabel.add(l);
    }

    /**
     * Add action listeners to all buttons
     */
    private void addActionListeners() {
        Handler handler = new Handler();
        admin.addActionListener(handler);
        ngo.addActionListener(handler);
        executive.addActionListener(handler);
        user.addActionListener(handler);
        logout.addActionListener(handler);
    }

    /**
     * Inner class to handle button events
     */
    private class Handler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == admin) {
                handleAdminLogin();
            } else if (ae.getSource() == ngo) {
                handleNGOLogin();
            } else if (ae.getSource() == executive) {
                handleExecutiveLogin();
            } else if (ae.getSource() == user) {
                handleUserLogin();
            } else if (ae.getSource() == logout) {
                handleLogout();
            }
        }

        /**
         * Navigate to admin login page
         */
        private void handleAdminLogin() {
            admin.setBackground(Theme.PRIMARY_ACCENT);
            setVisible(false);
            Login frame = new Login();
            frame.setVisible(true);
        }

        /**
         * Navigate to NGO login page
         */
        private void handleNGOLogin() {
            ngo.setBackground(Theme.PRIMARY_ACCENT);
            setVisible(false);
            NGOLogin frame = new NGOLogin();
            frame.setVisible(true);
        }

        /**
         * Navigate to executive login page
         */
        private void handleExecutiveLogin() {
            executive.setBackground(Theme.PRIMARY_ACCENT);
            setVisible(false);
            ExecutiveLogin frame = new ExecutiveLogin();
            frame.setVisible(true);
        }

        /**
         * Navigate to user login page
         */
        private void handleUserLogin() {
            setVisible(false);
            UserLogin frame = new UserLogin();
            frame.setVisible(true);
        }

        /**
         * Handle logout - Exit application
         */
        private void handleLogout() {
            System.exit(0);
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        HomePage frame = new HomePage();
        frame.setVisible(true);
    }
}