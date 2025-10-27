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
 * NGORegistration class - Handles NGO user registration and signup
 * Provides registration functionality with email, username, and password validation
 * @author Hp
 */
public class NGORegistration extends JFrame {

    // UI Components - Headers and Labels
    private JLabel title, title1, label, label2, imglabel;
    private JLabel namel, emaill, passwordl, confirml, contactl, locationl;

    // UI Components - Input Fields
    private JTextField nametf, emailtf;
    private JPasswordField passwordtf, confirmtf;
    private JCheckBox showpasscheckbox;

    // UI Components - Radio Buttons
    private JRadioButton male, female;
    private ButtonGroup grp;

    // UI Components - Buttons
    private JButton login, clear, homepage, logout, ngo, user, executive, register, back, reset, admin;

    // UI Components - Panels
    private JPanel panel1, panel2;
    private JScrollPane scroll;

    // UI Components - Icons
    private ImageIcon icon;

    // Container
    private Container c;
    private Cursor cursor;

    // Database components
    private Connection con2;
    private PreparedStatement pst2;

    /**
     * Constructor
     */
    public NGORegistration() {
        initComponents();
    }

    /**
     * Initialize all UI components
     */
    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1930, 1030);
        this.setTitle("NGO Registration Form");
        c = this.getContentPane();
        c.setLayout(null);
        this.setResizable(false);
        c.setBackground(Color.GRAY);

        // Create header and navigation
        createHeaderSection();

        // Create registration panel
        createRegistrationPanel();

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
    }

    /**
     * Create and setup navigation buttons
     */
    private void createNavigationButtons(Font font1, Cursor cursor, JLabel label) {
        homepage = new JButton("Home Page");
        homepage.setFont(font1);
        homepage.setBounds(20, 30, 200, 80);
        homepage.setCursor(cursor);
        label.add(homepage);

        admin = new JButton("Admin");
        admin.setFont(font1);
        admin.setBounds(221, 30, 200, 80);
        admin.setCursor(cursor);
        label.add(admin);

        executive = new JButton("Executive");
        executive.setFont(font1);
        executive.setBounds(422, 30, 200, 80);
        executive.setCursor(cursor);
        label.add(executive);

        user = new JButton("User");
        user.setFont(font1);
        user.setBounds(623, 30, 200, 80);
        user.setCursor(cursor);
        label.add(user);

        logout = new JButton("LogOut");
        logout.setFont(font1);
        logout.setBounds(824, 30, 200, 80);
        logout.setCursor(cursor);
        label.add(logout);
    }

    /**
     * Create main registration panel with background image and form
     */
    private void createRegistrationPanel() {
        // Create main panel with scroll pane
        panel1 = new JPanel();
        panel1.setBounds(1, 260, 1900, 1030);
        c.add(panel1);

        scroll = new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(1, 260, 1910, 720);
        c.add(scroll);

        // Add background image
        setupBackgroundImage();

        // Create registration form container
        setupRegistrationFormContainer();
    }

    /**
     * Setup background image for the registration panel
     */
    private void setupBackgroundImage() {
        icon = new ImageIcon(getClass().getResource("medicin.jpeg"));
        Image img = icon.getImage();
        Image newimage = img.getScaledInstance(panel1.getWidth(), panel1.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimage);
        imglabel = new JLabel(icon);
        panel1.add(imglabel);
    }

    /**
     * Setup registration form container with all fields
     */
    private void setupRegistrationFormContainer() {
        Font font = new Font("Arial", Font.BOLD, 42);
        Font font1 = new Font("Arial", Font.BOLD, 24);

        label2 = new JLabel();
        label2.setFont(font);
        label2.setBounds(480, 2, 1200, 900);
        label2.setOpaque(true);
        label2.setBackground(Color.LIGHT_GRAY);
        imglabel.add(label2);

        // Title
        title1 = new JLabel("NGO Registration Form");
        title1.setFont(font1);
        title1.setBounds(2, 2, 11200, 60);
        title1.setForeground(Color.BLUE);
        title1.setOpaque(true);
        title1.setBackground(Color.ORANGE);
        label2.add(title1);

        // Registration icon panel
        setupRegistrationIconPanel();

        // Registration form fields
        createRegistrationFormFields(font1);

        // Registration buttons
        createRegistrationButtons(font1);
    }

    /**
     * Setup registration icon panel
     */
    private void setupRegistrationIconPanel() {
        panel2 = new JPanel();
        panel2.setBounds(480, 80, 230, 230);
        panel2.setBackground(Color.WHITE);
        label2.add(panel2);

        icon = new ImageIcon(getClass().getResource("login.png"));
        Image img1 = icon.getImage();
        Image newimage1 = img1.getScaledInstance(panel2.getWidth(), panel2.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimage1);
        imglabel = new JLabel(icon);
        panel2.add(imglabel);
    }

    /**
     * Create registration form fields (username, email, password, confirm password)
     */
    private void createRegistrationFormFields(Font font1) {
        // Username field
        namel = new JLabel("User Name");
        namel.setFont(font1);
        namel.setBounds(40, 360, 180, 40);
        label2.add(namel);

        nametf = new JTextField("");
        nametf.setFont(font1);
        nametf.setBounds(273, 360, 880, 40);
        label2.add(nametf);

        // Email field
        emaill = new JLabel("Email");
        emaill.setFont(font1);
        emaill.setBounds(40, 410, 180, 40);
        label2.add(emaill);

        emailtf = new JTextField("");
        emailtf.setFont(font1);
        emailtf.setBounds(273, 410, 880, 40);
        label2.add(emailtf);

        // Password field
        passwordl = new JLabel("Password");
        passwordl.setFont(font1);
        passwordl.setBounds(40, 460, 180, 40);
        label2.add(passwordl);

        passwordtf = new JPasswordField("");
        passwordtf.setFont(font1);
        passwordtf.setBounds(273, 460, 880, 40);
        passwordtf.setEchoChar('*');
        label2.add(passwordtf);

        // Confirm password field
        confirml = new JLabel("Confirm Password");
        confirml.setFont(font1);
        confirml.setBounds(35, 510, 300, 40);
        label2.add(confirml);

        confirmtf = new JPasswordField("");
        confirmtf.setFont(font1);
        confirmtf.setBounds(273, 510, 880, 40);
        confirmtf.setEchoChar('*');
        label2.add(confirmtf);

        // Show password checkbox
        showpasscheckbox = new JCheckBox("Show Password");
        showpasscheckbox.setBounds(300, 560, 250, 40);
        showpasscheckbox.setFont(font1);
        showpasscheckbox.setBackground(Color.LIGHT_GRAY);
        label2.add(showpasscheckbox);
    }

    /**
     * Create registration and reset buttons
     */
    private void createRegistrationButtons(Font font1) {
        register = new JButton(" Register");
        register.setBounds(360, 630, 150, 50);
        register.setFont(font1);
        register.setCursor(cursor);
        label2.add(register);

        reset = new JButton("Reset");
        reset.setBounds(680, 630, 150, 50);
        reset.setFont(font1);
        reset.setCursor(cursor);
        label2.add(reset);

        back = new JButton("Click Here Back to Login");
        back.setBounds(385, 720, 390, 50);
        back.setFont(font1);
        back.setBackground(Color.LIGHT_GRAY);
        back.setCursor(cursor);
        label2.add(back);
    }

    /**
     * Add action listeners to all buttons
     */
    private void addActionListeners() {
        Handler handler = new Handler();
        register.addActionListener(handler);
        showpasscheckbox.addActionListener(handler);
        reset.addActionListener(handler);
        back.addActionListener(handler);
        homepage.addActionListener(handler);
        admin.addActionListener(handler);
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
            if (ae.getSource() == register) {
                handleRegister();
            } else if (ae.getSource() == reset) {
                handleReset();
            } else if (ae.getSource() == showpasscheckbox) {
                handleShowPassword();
            } else if (ae.getSource() == back) {
                navigateTo(new NGOLogin());
            } else if (ae.getSource() == homepage) {
                navigateTo(new HomePage());
            } else if (ae.getSource() == admin) {
                navigateTo(new Login());
            } else if (ae.getSource() == user) {
                navigateTo(new UserLogin());
            } else if (ae.getSource() == executive) {
                navigateTo(new ExecutiveLogin());
            } else if (ae.getSource() == logout) {
                System.exit(0);
            }
        }

        /**
         * Handle NGO registration
         */
        private void handleRegister() {
            String name = nametf.getText();
            String email = emailtf.getText();
            String password = passwordtf.getText();
            String confirm = confirmtf.getText();

            // Validate input fields
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter User Name", "Warning", JOptionPane.ERROR_MESSAGE);
            } else if (email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Email", "Warning", JOptionPane.ERROR_MESSAGE);
            } else if (password.isEmpty() || confirm.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Password or Confirm Password", "Warning", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(null, "Password and Confirm Password do not match");
            } else if (checkusername(name, email)) {
                JOptionPane.showMessageDialog(null, "This user name and email already exist");
            } else {
                registerNGO(name, email, password);
            }
        }

        /**
         * Register NGO with database
         */
        private void registerNGO(String name, String email, String password) {
            String sqlSelect = "SELECT `Name`, `Email` FROM `addngo`";

            try {
                con2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MedConnect", "root", "");
                pst2 = (PreparedStatement) con2.prepareStatement(sqlSelect);
                ResultSet rs = pst2.executeQuery();

                int registered = 0;

                while (rs.next()) {
                    String ngoName = rs.getString("Name");
                    String ngoEmail = rs.getString("Email");

                    if (name.equals(ngoName) && email.equals(ngoEmail)) {
                        if (insertNGORegistration(name, email, password)) {
                            registered = 1;
                            break;
                        }
                    }
                }

                if (registered == 0) {
                    JOptionPane.showMessageDialog(null, "You are Not an NGO", "Warning", JOptionPane.ERROR_MESSAGE);
                }

                con2.close();
                pst2.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "NGO Registration Unsuccessful: " + ex);
            }
        }

        /**
         * Insert NGO registration into database
         */
        private boolean insertNGORegistration(String name, String email, String password) {
            String sqlInsert = "INSERT INTO `ngoregistrationform`(`User Name`, `Email`, `Password`) VALUES(?, ?, ?)";

            try {
                con2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MedConnect", "root", "");
                pst2 = (PreparedStatement) con2.prepareStatement(sqlInsert);
                pst2.setString(1, name);
                pst2.setString(2, email);
                pst2.setString(3, password);

                pst2.executeUpdate();
                con2.close();

                JOptionPane.showMessageDialog(null, "NGO Registration Successfully");
                return true;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "NGO Registration Unsuccessful: " + ex);
                return false;
            }
        }

        /**
         * Clear all input fields
         */
        private void handleReset() {
            nametf.setText("");
            emailtf.setText("");
            passwordtf.setText("");
            confirmtf.setText("");
        }

        /**
         * Toggle password visibility
         */
        private void handleShowPassword() {
            if (showpasscheckbox.isSelected()) {
                passwordtf.setEchoChar((char) 0);
                confirmtf.setEchoChar((char) 0);
            } else {
                passwordtf.setEchoChar('*');
                confirmtf.setEchoChar('*');
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
     * Check if username and email already exist in the database
     * @param username The username to check
     * @param email The email to check
     * @return true if user exists, false otherwise
     */
    public boolean checkusername(String username, String email) {
        boolean checkuser = false;
        Connection con2 = null;
        PreparedStatement pst2 = null;
        ResultSet rs;
        String sql = "SELECT * FROM `ngoregistrationform` WHERE `User Name` =? AND `Email`=?";

        try {
            con2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MedConnect", "root", "");
            pst2 = (PreparedStatement) con2.prepareStatement(sql);
            pst2.setString(1, username);
            pst2.setString(2, email);
            rs = pst2.executeQuery();

            if (rs.next()) {
                checkuser = true;
            }

            con2.close();
            pst2.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        return checkuser;
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        NGORegistration frame = new NGORegistration();
        frame.setVisible(true);
    }
}