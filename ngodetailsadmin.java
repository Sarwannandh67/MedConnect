package online.medicine.donation.system;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.ResultSet;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * ngodetailsadmin class - Allows admin to view and search NGO details
 * Provides functionality to search NGO by ID and display their information
 * @author Hp
 */
public class ngodetailsadmin extends JFrame {

    // UI Components - Headers and Labels
    private JLabel title, title1, title2, label, label2, label1, newlabel1;
    private JLabel idl, namel, genderl, emaill, contactl, locationl;

    // UI Components - Input Fields
    private JTextField idtf, nametf, gendertf, emailtf, contacttf, locationtf;

    // UI Components - Radio Buttons
    private JRadioButton male, female;
    private ButtonGroup grp;

    // UI Components - Buttons
    private JButton homepage, addngo, addmed, viewmed, viewuser, logout, search;

    // UI Components - Table and Scroll
    private DefaultTableModel mode;
    private JScrollPane scroll, scroll1;
    private JTable table;

    // Container
    private Container c;
    private Cursor cursor;

    // Database components
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    /**
     * Constructor
     */
    public ngodetailsadmin() {
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
        c.setBackground(Color.GRAY);

        // Create header and navigation
        createHeaderSection();

        // Create main content area
        createMainContentArea();
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
        Font font1 = new Font("Arial", Font.BOLD, 26);

        // Create navigation buttons
        createNavigationButtons(font1, cursor, label);
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

        addngo = new JButton("Add NGO");
        addngo.setCursor(cursor);
        addngo.setFont(font1);
        addngo.setBounds(271, 30, 250, 80);
        label.add(addngo);

        addmed = new JButton("Add Medicine");
        addmed.setCursor(cursor);
        addmed.setFont(font1);
        addmed.setBounds(522, 30, 250, 80);
        label.add(addmed);

        viewmed = new JButton("View Medicine");
        viewmed.setCursor(cursor);
        viewmed.setFont(font1);
        viewmed.setBounds(773, 30, 250, 80);
        label.add(viewmed);

        viewuser = new JButton("View User");
        viewuser.setCursor(cursor);
        viewuser.setFont(font1);
        viewuser.setBounds(1024, 30, 250, 80);
        label.add(viewuser);

        logout = new JButton("Logout");
        logout.setCursor(cursor);
        logout.setFont(font1);
        logout.setBounds(1275, 30, 250, 80);
        label.add(logout);
    }

    /**
     * Create main content area with NGO details form
     */
    private void createMainContentArea() {
        Font font1 = new Font("Arial", Font.BOLD, 26);

        // Main content label
        newlabel1 = new JLabel();
        newlabel1.setBounds(1, 260, 1930, 1030);
        newlabel1.setOpaque(true);
        newlabel1.setBackground(Color.LIGHT_GRAY);
        c.add(newlabel1);

        // Title section
        title1 = new JLabel("View NGO");
        title1.setFont(font1);
        title1.setBounds(1, 260, 1900, 80);
        title1.setForeground(Color.BLUE);
        title1.setOpaque(true);
        title1.setBackground(Color.ORANGE);
        c.add(title1);

        // Instructions
        title2 = new JLabel("Please Enter ID and click the search button");
        title2.setFont(font1);
        title2.setBounds(600, 68, 1900, 27);
        newlabel1.add(title2);

        // Create search section
        createSearchSection(font1);

        // Create NGO details section
        createNGODetailsSection(font1);
    }

    /**
     * Create search section with ID field and search button
     */
    private void createSearchSection(Font font1) {
        cursor = new Cursor(Cursor.HAND_CURSOR);

        idl = new JLabel("ID");
        idl.setFont(font1);
        idl.setBounds(50, 100, 300, 40);
        newlabel1.add(idl);

        idtf = new JTextField("");
        idtf.setFont(font1);
        idtf.setBounds(290, 100, 1400, 40);
        idtf.setEditable(false);
        newlabel1.add(idtf);

        search = new JButton("Search");
        search.setCursor(cursor);
        search.setFont(font1);
        search.setBounds(1710, 100, 150, 60);
        newlabel1.add(search);
    }

    /**
     * Create NGO details display section
     */
    private void createNGODetailsSection(Font font1) {
        // Name field
        namel = new JLabel("Name");
        namel.setFont(font1);
        namel.setBounds(50, 160, 300, 40);
        newlabel1.add(namel);

        nametf = new JTextField("");
        nametf.setFont(font1);
        nametf.setBounds(290, 160, 1400, 40);
        newlabel1.add(nametf);

        // Gender section
        genderl = new JLabel("Gender");
        genderl.setFont(font1);
        genderl.setBounds(50, 220, 300, 40);
        newlabel1.add(genderl);

        male = new JRadioButton("Male");
        male.setBounds(440, 220, 100, 40);
        male.setFont(font1);
        newlabel1.add(male);

        female = new JRadioButton("Female");
        female.setBounds(840, 220, 130, 40);
        female.setFont(font1);
        newlabel1.add(female);

        grp = new ButtonGroup();
        grp.add(male);
        grp.add(female);

        // Email field
        emaill = new JLabel("Email");
        emaill.setFont(font1);
        emaill.setBounds(50, 280, 300, 40);
        newlabel1.add(emaill);

        emailtf = new JTextField("");
        emailtf.setFont(font1);
        emailtf.setBounds(290, 280, 1400, 40);
        newlabel1.add(emailtf);

        // Contact field
        contactl = new JLabel("Contact Number");
        contactl.setFont(font1);
        contactl.setBounds(50, 340, 300, 40);
        newlabel1.add(contactl);

        contacttf = new JTextField("");
        contacttf.setFont(font1);
        contacttf.setBounds(290, 340, 880, 40);
        newlabel1.add(contacttf);

        // Location field
        locationl = new JLabel("Location");
        locationl.setFont(font1);
        locationl.setBounds(50, 400, 300, 40);
        newlabel1.add(locationl);

        locationtf = new JTextField("");
        locationtf.setFont(font1);
        locationtf.setBounds(290, 400, 1400, 40);
        newlabel1.add(locationtf);
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        ngodetailsadmin frame = new ngodetailsadmin();
        frame.setVisible(true);
    }
}    