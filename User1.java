package online.medicine.donation.system;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * User1 - User dashboard for medicine donation system
 * Provides navigation to order tracking, medicine donation, and account functions
 * 
 * @author Team
 */
public class User1 extends JFrame {

    // ========== UI COMPONENTS ==========
    // Title and Navigation
    private JLabel titleLabel;
    private JLabel navigationBar;
    private JLabel welcomeLabel;
    private JLabel userInfoLabel;
    
    // Navigation Buttons
    private JButton homeButton;
    private JButton myOrderButton;
    private JButton donateMedicineButton;
    private JButton logoutButton;
    
    // Container and User Info
    private Container contentPane;
    private String currentUserName;

    /**
     * Constructor
     */
    public User1() {
        initComponents();
    }

    /**
     * Initialize all UI components
     */
    private void initComponents() {
        setupFrame();
        createTitleBar();
        createNavigationBar();
        createWelcomeSection();
        addActionListeners();
    }

    /**
     * Setup basic frame properties
     */
    private void setupFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1930, 1030);
        this.setTitle("User Dashboard");
        this.setResizable(false);
        
        contentPane = this.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.GRAY);
        
        currentUserName = UserLogin.usernametf.getText();
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
        homeButton = createButton("Home Page", 20, 30, 170, 80, handCursor, buttonFont, navigationBar);

        // My Order Button
        myOrderButton = createButton("My Order", 191, 30, 170, 80, handCursor, buttonFont, navigationBar);

        // Donate Medicine Button
        donateMedicineButton = createButton("Donate Medicine", 362, 30, 270, 80, handCursor, buttonFont, navigationBar);

        // Logout Button
        logoutButton = createButton("Logout", 633, 30, 190, 80, handCursor, buttonFont, navigationBar);

        // User Info Label
        userInfoLabel = new JLabel("Hi " + currentUserName);
        userInfoLabel.setBounds(1660, 30, 500, 80);
        userInfoLabel.setFont(buttonFont);
        userInfoLabel.setToolTipText(currentUserName);
        navigationBar.add(userInfoLabel);
    }

    /**
     * Create welcome section with user greeting
     */
    private void createWelcomeSection() {
        Font welcomeFont = new Font("Arial", Font.BOLD, 50);
        
        welcomeLabel = new JLabel("Welcome " + currentUserName);
        welcomeLabel.setFont(welcomeFont);
        welcomeLabel.setBounds(100, 500, 1900, 200);
        contentPane.add(welcomeLabel);
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
     * Add action listeners to buttons
     */
    private void addActionListeners() {
        Handler handler = new Handler();
        
        homeButton.addActionListener(handler);
        myOrderButton.addActionListener(handler);
        donateMedicineButton.addActionListener(handler);
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
            } else if (event.getSource() == myOrderButton) {
                navigateTo(new MyOrder());
            } else if (event.getSource() == donateMedicineButton) {
                navigateTo(new DonateMedicine());
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
                navigateTo(new UserLogin());
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
        User1 frame = new User1();
        frame.setVisible(true);
    }
}