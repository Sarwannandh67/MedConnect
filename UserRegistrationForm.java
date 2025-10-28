package online.medicine.donation.system;

import com.toedter.calendar.JDateChooser;
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
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * UserRegistrationForm - User registration interface
 * Allows new users to register with personal and account information
 * 
 * @author Team
 */
public class UserRegistrationForm extends JFrame {

    // ========== UI COMPONENTS - NAVIGATION ==========
    private JLabel titleLabel;
    private JLabel navigationBar;
    private JButton homeButton, adminButton, ngoButton, executiveButton, logoutButton;
    
    // ========== UI COMPONENTS - FORM LABELS ==========
    private JLabel nameLabel, genderLabel, dateOfBirthLabel, emailLabel;
    private JLabel contactLabel, locationLabel, passwordLabel, confirmPasswordLabel;
    
    // ========== UI COMPONENTS - FORM INPUT FIELDS ==========
    private JTextField nameField, emailField, contactField, locationField;
    private JDateChooser dateOfBirthChooser;
    private JPasswordField passwordField, confirmPasswordField;
    private JRadioButton maleRadio, femaleRadio;
    private ButtonGroup genderGroup;
    private JCheckBox showPasswordCheckbox;
    
    // ========== UI COMPONENTS - BUTTONS ==========
    private JButton registerButton, resetButton, backButton;
    
    // ========== UI COMPONENTS - CONTAINERS ==========
    private Container contentPane;
    
    // ========== DATABASE COMPONENTS ==========
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    /**
     * Constructor
     */
    public UserRegistrationForm() {
        initComponents();
    }

    /**
     * Initialize all UI components
     */
    private void initComponents() {
        setupFrame();
        createTitleBar();
        createNavigationBar();
        createScrollableFormPanel();
        addActionListeners();
    }

    /**
     * Setup basic frame properties
     */
    private void setupFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1930, 1030);
        this.setTitle("User Registration Form");
        this.setResizable(false);
        
        contentPane = this.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.GRAY);
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

        homeButton = createButton("Home Page", 20, 30, 200, 80, handCursor, buttonFont, navigationBar);
        adminButton = createButton("Admin", 221, 30, 200, 80, handCursor, buttonFont, navigationBar);
        ngoButton = createButton("NGO", 422, 30, 200, 80, handCursor, buttonFont, navigationBar);
        executiveButton = createButton("Executive", 623, 30, 200, 80, handCursor, buttonFont, navigationBar);
        logoutButton = createButton("LogOut", 824, 30, 200, 80, handCursor, buttonFont, navigationBar);
    }

    /**
     * Create scrollable form panel
     */
    private void createScrollableFormPanel() {
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBounds(1, 260, 1900, 1380);
        contentPane.add(backgroundPanel);

        JScrollPane scrollPane = new JScrollPane(backgroundPanel, 
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(1, 260, 1910, 720);
        contentPane.add(scrollPane);

        // Background image
        ImageIcon icon = new ImageIcon(getClass().getResource("medicin.jpeg"));
        Image img = icon.getImage();
        Image newImage = img.getScaledInstance(backgroundPanel.getWidth(), backgroundPanel.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        JLabel backgroundLabel = new JLabel(icon);
        backgroundPanel.add(backgroundLabel);

        // Form content panel
        JLabel formContentPanel = new JLabel();
        formContentPanel.setFont(new Font("Arial", Font.BOLD, 42));
        formContentPanel.setBounds(480, 2, 1200, 1280);
        formContentPanel.setOpaque(true);
        formContentPanel.setBackground(Color.LIGHT_GRAY);
        backgroundLabel.add(formContentPanel);

        // Form title
        JLabel formTitleLabel = new JLabel("User Registration Form");
        formTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        formTitleLabel.setBounds(2, 2, 1380, 60);
        formTitleLabel.setForeground(Color.BLUE);
        formTitleLabel.setOpaque(true);
        formTitleLabel.setBackground(Color.ORANGE);
        formContentPanel.add(formTitleLabel);

        // Logo panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBounds(480, 80, 230, 230);
        logoPanel.setBackground(Color.WHITE);
        formContentPanel.add(logoPanel);

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("login.png"));
        Image logoImg = logoIcon.getImage();
        Image newLogoImage = logoImg.getScaledInstance(logoPanel.getWidth(), logoPanel.getHeight(), Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(newLogoImage);
        JLabel logoLabel = new JLabel(logoIcon);
        logoPanel.add(logoLabel);

        // Create form fields
        createFormFields(formContentPanel);
        createFormButtons(formContentPanel);
    }

    /**
     * Create form input fields
     */
    private void createFormFields(JLabel formPanel) {
        Font labelFont = new Font("Arial", Font.BOLD, 24);
        Font inputFont = new Font("Arial", Font.BOLD, 24);

        // Name field
        nameLabel = new JLabel("User Name");
        nameLabel.setFont(labelFont);
        nameLabel.setBounds(20, 360, 300, 40);
        formPanel.add(nameLabel);

        nameField = new JTextField("");
        nameField.setFont(inputFont);
        nameField.setBounds(260, 360, 880, 40);
        formPanel.add(nameField);

        // Gender field
        genderLabel = new JLabel("Gender");
        genderLabel.setFont(labelFont);
        genderLabel.setBounds(20, 410, 300, 40);
        formPanel.add(genderLabel);

        maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(480, 410, 100, 40);
        maleRadio.setFont(inputFont);
        formPanel.add(maleRadio);

        femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(780, 410, 130, 40);
        femaleRadio.setFont(inputFont);
        formPanel.add(femaleRadio);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Date of Birth field
        dateOfBirthLabel = new JLabel("Date of Birth");
        dateOfBirthLabel.setFont(labelFont);
        dateOfBirthLabel.setBounds(20, 460, 300, 40);
        formPanel.add(dateOfBirthLabel);

        dateOfBirthChooser = new JDateChooser();
        dateOfBirthChooser.setFont(inputFont);
        dateOfBirthChooser.setBounds(260, 460, 880, 40);
        formPanel.add(dateOfBirthChooser);

        // Email field
        emailLabel = new JLabel("Email");
        emailLabel.setFont(labelFont);
        emailLabel.setBounds(20, 510, 300, 40);
        formPanel.add(emailLabel);

        emailField = new JTextField("");
        emailField.setFont(inputFont);
        emailField.setBounds(260, 510, 880, 40);
        formPanel.add(emailField);

        // Contact field
        contactLabel = new JLabel("Contact Number");
        contactLabel.setFont(labelFont);
        contactLabel.setBounds(20, 560, 300, 40);
        formPanel.add(contactLabel);

        contactField = new JTextField("");
        contactField.setFont(inputFont);
        contactField.setBounds(260, 560, 880, 40);
        formPanel.add(contactField);

        // Location field
        locationLabel = new JLabel("Location");
        locationLabel.setFont(labelFont);
        locationLabel.setBounds(20, 610, 300, 40);
        formPanel.add(locationLabel);

        locationField = new JTextField("");
        locationField.setFont(inputFont);
        locationField.setBounds(260, 610, 880, 40);
        formPanel.add(locationField);

        // Password field
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(labelFont);
        passwordLabel.setBounds(20, 660, 300, 40);
        formPanel.add(passwordLabel);

        passwordField = new JPasswordField("");
        passwordField.setFont(inputFont);
        passwordField.setEchoChar('*');
        passwordField.setBounds(260, 660, 880, 40);
        formPanel.add(passwordField);

        // Confirm password field
        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(labelFont);
        confirmPasswordLabel.setBounds(20, 710, 300, 40);
        formPanel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField("");
        confirmPasswordField.setFont(inputFont);
        confirmPasswordField.setEchoChar('*');
        confirmPasswordField.setBounds(260, 710, 880, 40);
        formPanel.add(confirmPasswordField);

        // Show password checkbox
        showPasswordCheckbox = new JCheckBox("Show Password");
        showPasswordCheckbox.setBounds(300, 780, 250, 40);
        showPasswordCheckbox.setFont(inputFont);
        showPasswordCheckbox.setBackground(Color.LIGHT_GRAY);
        formPanel.add(showPasswordCheckbox);
    }

    /**
     * Create form action buttons
     */
    private void createFormButtons(JLabel formPanel) {
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
        Font buttonFont = new Font("Arial", Font.BOLD, 24);

        registerButton = new JButton("Register");
        registerButton.setFont(buttonFont);
        registerButton.setBounds(250, 900, 700, 60);
        registerButton.setCursor(handCursor);
        formPanel.add(registerButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(buttonFont);
        resetButton.setBounds(290, 980, 600, 60);
        resetButton.setCursor(handCursor);
        formPanel.add(resetButton);

        backButton = new JButton("Back to Login");
        backButton.setFont(buttonFont);
        backButton.setBounds(320, 1060, 500, 60);
        backButton.setCursor(handCursor);
        formPanel.add(backButton);
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
     * Validate form inputs
     */
    private boolean validateForm() {
        if (nameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter name", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!maleRadio.isSelected() && !femaleRadio.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please select gender", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (dateOfBirthChooser.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Please enter date of birth", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (emailField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter email", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (contactField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter contact number", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (locationField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter location", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (passwordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter password", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            JOptionPane.showMessageDialog(null, "Passwords do not match", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Check if username and email already exist
     */
    private boolean checkUsernameExists(String username, String email) {
        try {
            String sql = "SELECT * FROM `userregistration` WHERE `Name`=? AND `Email`=?";
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MedConnect", "root", "");
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeDatabase();
        }
        return false;
    }

    /**
     * Register user in database
     */
    private void registerUser() {
        if (!validateForm()) return;

        if (checkUsernameExists(nameField.getText(), emailField.getText())) {
            JOptionPane.showMessageDialog(null, "Username and email already exist");
            return;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
            String birthDate = dateFormat.format(dateOfBirthChooser.getDate());
            String gender = maleRadio.isSelected() ? maleRadio.getText() : femaleRadio.getText();

            String sql = "INSERT INTO `userregistration`(`Name`, `Gender`, `Date of Birth`, `Email`, `Contact Number`, `Location`, `Password`) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MedConnect", "root", "");
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, nameField.getText());
            statement.setString(2, gender);
            statement.setString(3, birthDate);
            statement.setString(4, emailField.getText());
            statement.setString(5, contactField.getText());
            statement.setString(6, locationField.getText());
            statement.setString(7, passwordField.getText());

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "User registration successful");
            clearForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Registration error: " + ex.getMessage());
        } finally {
            closeDatabase();
        }
    }

    /**
     * Clear all form fields
     */
    private void clearForm() {
        nameField.setText("");
        genderGroup.clearSelection();
        dateOfBirthChooser.setDate(null);
        emailField.setText("");
        contactField.setText("");
        locationField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    /**
     * Toggle password visibility
     */
    private void togglePasswordVisibility() {
        char echoChar = showPasswordCheckbox.isSelected() ? (char) 0 : '*';
        passwordField.setEchoChar(echoChar);
        confirmPasswordField.setEchoChar(echoChar);
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
            Logger.getLogger(UserRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Add action listeners to buttons
     */
    private void addActionListeners() {
        Handler handler = new Handler();
        registerButton.addActionListener(handler);
        showPasswordCheckbox.addActionListener(handler);
        resetButton.addActionListener(handler);
        backButton.addActionListener(handler);
        homeButton.addActionListener(handler);
        adminButton.addActionListener(handler);
        ngoButton.addActionListener(handler);
        executiveButton.addActionListener(handler);
        logoutButton.addActionListener(handler);
    }

    /**
     * Handle button click events
     */
    private class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == registerButton) {
                registerUser();
            } else if (event.getSource() == showPasswordCheckbox) {
                togglePasswordVisibility();
            } else if (event.getSource() == resetButton) {
                clearForm();
            } else if (event.getSource() == backButton) {
                navigateTo(new UserLogin());
            } else if (event.getSource() == homeButton) {
                navigateTo(new HomePage());
            } else if (event.getSource() == adminButton) {
                navigateTo(new Login());
            } else if (event.getSource() == ngoButton) {
                navigateTo(new NGOLogin());
            } else if (event.getSource() == executiveButton) {
                navigateTo(new ExecutiveLogin());
            } else if (event.getSource() == logoutButton) {
                System.exit(0);
            }
        }

        private void navigateTo(JFrame frame) {
            setVisible(false);
            frame.setVisible(true);
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        UserRegistrationForm frame = new UserRegistrationForm();
        frame.setVisible(true);
    }
}