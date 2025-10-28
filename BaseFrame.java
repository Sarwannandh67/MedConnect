package online.medicine.donation.system;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * BaseFrame - Abstract base class for all application frames
 * Provides common UI initialization and layout functionality to reduce code duplication.
 * All frames in the application should extend this class.
 * 
 * @author Team
 */
public abstract class BaseFrame extends JFrame {
    
    protected Container contentPane;
    protected Cursor handCursor;
    protected JLabel titleLabel;
    protected JLabel navigationBar;
    
    /**
     * Constructor initializing common frame properties
     */
    public BaseFrame() {
        initializeFrame();
    }
    
    /**
     * Initialize common frame properties
     */
    protected void initializeFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        this.setResizable(false);
        
        contentPane = this.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(UIConstants.PRIMARY_BG);
        
        handCursor = new Cursor(UIConstants.CURSOR_HAND);
    }
    
    /**
     * Create and add title bar to the frame
     * @param title Title text
     * @return JLabel containing the title
     */
    protected JLabel createTitleBar(String title) {
        titleLabel = new JLabel(title);
        titleLabel.setFont(UIConstants.FONT_TITLE);
        titleLabel.setBounds(2, 2, UIConstants.WINDOW_WIDTH, UIConstants.TITLE_HEIGHT);
        titleLabel.setForeground(UIConstants.BLUE_TEXT);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(UIConstants.HEADER_BG);
        contentPane.add(titleLabel);
        return titleLabel;
    }
    
    /**
     * Create and add navigation bar to the frame
     * @return JLabel containing the navigation bar
     */
    protected JLabel createNavigationBar() {
        navigationBar = new JLabel();
        navigationBar.setBounds(1, UIConstants.NAV_BAR_Y, UIConstants.WINDOW_WIDTH, UIConstants.NAV_BAR_HEIGHT);
        navigationBar.setOpaque(true);
        navigationBar.setBackground(UIConstants.NAV_BG);
        contentPane.add(navigationBar);
        return navigationBar;
    }
    
    /**
     * Create a button with standard styling
     * @param text Button text
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Button width
     * @param height Button height
     * @return Configured JButton
     */
    protected JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(UIConstants.FONT_BUTTON);
        button.setBackground(UIConstants.PRIMARY_ACCENT);
        button.setForeground(UIConstants.TEXT_COLOR_LIGHT);
        button.setBounds(x, y, width, height);
        button.setCursor(handCursor);
        return button;
    }
    
    /**
     * Create a section title label
     * @param text Title text
     * @return Configured JLabel
     */
    protected JLabel createSectionTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UIConstants.FONT_BUTTON);
        label.setBounds(1, UIConstants.CONTENT_START_Y, UIConstants.WINDOW_WIDTH - 2, UIConstants.TITLE_HEIGHT);
        label.setForeground(UIConstants.BLUE_TEXT);
        label.setOpaque(true);
        label.setBackground(UIConstants.HEADER_BG);
        contentPane.add(label);
        return label;
    }
    
    /**
     * Abstract method to be implemented by subclasses for additional component initialization
     */
    protected abstract void initComponents();
}
