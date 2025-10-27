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
 * NGOLogin class - Handles NGO user authentication and login
 * Provides login functionality with email, username, and password validation
 * @author Hp
 */
public class NGOLogin extends JFrame {

    // UI Components - Headers and Labels
    private JLabel title, title1, label, label2, imglabel;
    private JLabel emaillabel, passwordlabel, userlabel;

    // UI Components - Input Fields
    public static JTextField usernametf, emailtf;
    private JPasswordField passwordtf;
    private JCheckBox showpasscheckbox;

    // UI Components - Buttons
    private JButton login, clear, homepage, logout, ngo, user, executive, admin, account;

    // UI Components - Panels
    private JPanel panel1, panel2;
    private JScrollPane scroll;

    // UI Components - Icons
    private ImageIcon icon;

    // Container
    private Container c;
    private Cursor cursor;

    // Database components
    private Connection con;
    private PreparedStatement pst, pst1;
    private ResultSet rs, rs1;

    /**
     * Constructor
     */
    public NGOLogin() {
        initComponents();
    }

    /**
     * Initialize all UI components
     */
    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1930, 1030);
        this.setTitle("NGO Login");
        c = this.getContentPane();
        c.setLayout(null);
        this.setResizable(false);
        c.setBackground(Color.GRAY);

        // Create header and navigation
        createHeaderSection();

        // Create main login panel
        createLoginPanel();

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
     * Create main login panel with background image and form
     */
    private void createLoginPanel() {
        // Create main panel with scroll pane
        panel1 = new JPanel();
        panel1.setBounds(1, 260, 1960, 1030);
        c.add(panel1);

        scroll = new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(1, 260, 1910, 720);
        c.add(scroll);

        // Add background image
        setupBackgroundImage();

        // Create login form container
        setupLoginFormContainer();
    }

    /**
     * Setup background image for the login panel
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
     * Setup login form container with all fields
     */
    private void setupLoginFormContainer() {
        Font font = new Font("Arial", Font.BOLD, 42);
        Font font1 = new Font("Arial", Font.BOLD, 24);

        label2 = new JLabel();
        label2.setFont(font);
        label2.setBounds(500, 2, 1130, 900);
        label2.setOpaque(true);
        label2.setBackground(Color.LIGHT_GRAY);
        imglabel.add(label2);

        // Title
        title1 = new JLabel("NGO Login");
        title1.setFont(font1);
        title1.setBounds(2, 2, 1130, 60);
        title1.setForeground(Color.BLUE);
        title1.setOpaque(true);
        title1.setBackground(Color.ORANGE);
        label2.add(title1);

        // Login icon panel
        setupLoginIconPanel();

        // Login form fields
        createLoginFormFields(font1);

        // Login buttons
        createLoginButtons(font1);
    }

    /**
     * Setup login icon panel
     */
    private void setupLoginIconPanel() {
        panel2 = new JPanel();
        panel2.setBounds(450, 80, 230, 230);
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
     * Create login form fields (username, email, password)
     */
    private void createLoginFormFields(Font font1) {
        // Username field
        userlabel = new JLabel("User Name");
        userlabel.setFont(font1);
        userlabel.setBounds(40, 360, 180, 40);
        label2.add(userlabel);

        usernametf = new JTextField("");
        usernametf.setFont(font1);
        usernametf.setBounds(180, 360, 900, 40);
        label2.add(usernametf);

        // Email field
        emaillabel = new JLabel("Email");
        emaillabel.setFont(font1);
        emaillabel.setBounds(40, 410, 180, 40);
        label2.add(emaillabel);

        emailtf = new JTextField("");
        emailtf.setFont(font1);
        emailtf.setBounds(180, 410, 900, 40);
        label2.add(emailtf);

        // Password field
        passwordlabel = new JLabel("Password");
        passwordlabel.setFont(font1);
        passwordlabel.setBounds(40, 460, 180, 40);
        label2.add(passwordlabel);

        passwordtf = new JPasswordField("");
        passwordtf.setFont(font1);
        passwordtf.setBounds(180, 460, 900, 40);
        passwordtf.setEchoChar('*');
        label2.add(passwordtf);

        // Show password checkbox
        showpasscheckbox = new JCheckBox("Show Password");
        showpasscheckbox.setBounds(200, 510, 250, 40);
        showpasscheckbox.setFont(font1);
        showpasscheckbox.setBackground(Color.LIGHT_GRAY);
        label2.add(showpasscheckbox);
    }

    /**
     * Create login and reset buttons
     */
    private void createLoginButtons(Font font1) {
        login = new JButton("Login");
        login.setBounds(360, 580, 150, 50);
        login.setFont(font1);
        login.setCursor(cursor);
        label2.add(login);

        clear = new JButton("Reset");
        clear.setBounds(680, 580, 150, 50);
        clear.setFont(font1);
        clear.setCursor(cursor);
        label2.add(clear);

        account = new JButton("Click Here Create New Account");
        account.setBounds(350, 680, 430, 50);
        account.setFont(font1);
        account.setBackground(Color.LIGHT_GRAY);
        account.setCursor(cursor);
        label2.add(account);
    }

    /**
     * Add action listeners to all buttons
     */
    private void addActionListeners() {
        Handler handler = new Handler();
        login.addActionListener(handler);
        showpasscheckbox.addActionListener(handler);
        clear.addActionListener(handler);
        account.addActionListener(handler);
        homepage.addActionListener(handler);
        admin.addActionListener(handler);
        user.addActionListener(handler);
        executive.addActionListener(handler);
        logout.addActionListener(handler);
    }

    /**
     * Inner class to handle button events
     */
    private class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == login) {
                handleLogin();
            } else if (ae.getSource() == clear) {
                handleClear();
            } else if (ae.getSource() == showpasscheckbox) {
                handleShowPassword();
            } else if (ae.getSource() == account) {
                navigateTo(new NGORegistration());
            } else if (ae.getSource() == homepage) {
                navigateTo(new HomePage());
            } else if (ae.getSource() == admin) {
                navigateTo(new Login());
            } else if (ae.getSource() == executive) {
                navigateTo(new ExecutiveLogin());
            } else if (ae.getSource() == user) {
                navigateTo(new UserLogin());
            } else if (ae.getSource() == logout) {
                System.exit(0);
            }
        }

        /**
         * Handle login authentication
         */
        private void handleLogin() {
            String name = usernametf.getText();
            String email = emailtf.getText();
            String password = passwordtf.getText();

            // Validate input fields
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter User Name", "Warning", JOptionPane.ERROR_MESSAGE);
            } else if (email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Email", "Warning", JOptionPane.ERROR_MESSAGE);
            } else if (password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Password", "Warning", JOptionPane.ERROR_MESSAGE);
            } else {
                authenticateNGO(name, email, password);
            }
        }

        /**
         * Authenticate NGO with database
         */
        private void authenticateNGO(String name, String email, String password) {
            int a = 0;
            String sql = "SELECT * FROM `ngoregistrationform` WHERE `User Name` =? AND `Email`=? AND `Password`=?";

            try {
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MedConnect", "root", "");
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, email);
                pst.setString(3, password);
                rs = pst.executeQuery();

                while (rs.next()) {
                    JOptionPane.showMessageDialog(null, "NGO Logged In Successfully");
                    setVisible(false);
                    NGO frame = new NGO();
                    frame.setVisible(true);
                    a = 1;
                    break;
                }

                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "NGO Login Unsuccessful: " + ex);
            }

            if (a == 0) {
                JOptionPane.showMessageDialog(null, "Please Enter Correct User Name or Email or Password", "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }

        /**
         * Clear all input fields
         */
        private void handleClear() {
            emailtf.setText("");
            usernametf.setText("");
            passwordtf.setText("");
        }

        /**
         * Toggle password visibility
         */
        private void handleShowPassword() {
            if (showpasscheckbox.isSelected()) {
                passwordtf.setEchoChar((char) 0);
            } else {
                passwordtf.setEchoChar('*');
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
        NGOLogin frame = new NGOLogin();
        frame.setVisible(true);
    }
}    