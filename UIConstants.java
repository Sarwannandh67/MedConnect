package online.medicine.donation.system;

import java.awt.Color;
import java.awt.Font;

/**
 * UIConstants - Centralized constants for UI configuration
 * This class defines all reusable UI values to maintain consistency
 * and allow easy theme modifications across the application.
 * 
 * @author Team
 */
public class UIConstants {
    
    // ========== COLORS ==========
    public static final Color PRIMARY_ACCENT = new Color(0x9929EA);
    public static final Color SECONDARY_ACCENT = new Color(0xCC66DA);
    public static final Color PRIMARY_BG = new Color(0xFAEB92);
    public static final Color SECONDARY_BG = new Color(0xFAEB92);
    public static final Color TEXT_COLOR_DARK = new Color(0x000000);
    public static final Color TEXT_COLOR_LIGHT = new Color(0xFFFFFF);
    public static final Color HEADER_BG = Color.ORANGE;
    public static final Color NAV_BG = Color.MAGENTA;
    public static final Color TABLE_SELECTION = Color.PINK;
    public static final Color GRAY_BG = Color.GRAY;
    public static final Color BLUE_TEXT = Color.BLUE;
    
    // ========== FONTS ==========
    public static final Font FONT_TITLE = new Font("Arial", Font.BOLD, 42);
    public static final Font FONT_BUTTON = new Font("Arial", Font.BOLD, 24);
    public static final Font FONT_SUBTITLE = new Font("Arial", Font.BOLD, 20);
    public static final Font FONT_TABLE_HEADER = new Font("Arial", Font.BOLD, 20);
    public static final Font FONT_TABLE_CONTENT = new Font("Arial", Font.BOLD, 18);
    public static final Font FONT_LABEL = new Font("Arial", Font.BOLD, 16);
    
    // ========== WINDOW DIMENSIONS ==========
    public static final int WINDOW_WIDTH = 1930;
    public static final int WINDOW_HEIGHT = 1030;
    public static final int DEFAULT_BUTTON_HEIGHT = 80;
    public static final int DEFAULT_BUTTON_WIDTH = 200;
    
    // ========== COMPONENT POSITIONS ==========
    public static final int TITLE_HEIGHT = 80;
    public static final int NAV_BAR_Y = 100;
    public static final int NAV_BAR_HEIGHT = 150;
    public static final int CONTENT_START_Y = 260;
    public static final int TABLE_START_Y = 350;
    public static final int TABLE_HEIGHT = 630;
    
    // ========== BUTTON POSITIONS (Common Navigation) ==========
    public static final int BUTTON_START_X = 20;
    public static final int BUTTON_START_Y = 30;
    public static final int BUTTON_SPACING = 251;
    
    // ========== CURSOR TYPES ==========
    public static final int CURSOR_HAND = java.awt.Cursor.HAND_CURSOR;
    
    // ========== DATABASE ==========
    public static final String DB_URL = "jdbc:mysql://localhost/MedConnect";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";
    
    private UIConstants() {
        // Prevent instantiation
    }
}
