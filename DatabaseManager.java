package online.medicine.donation.system;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * DatabaseManager - Centralized database connection and query management
 * Handles all database operations including connections, queries, and error handling.
 * Uses singleton pattern to ensure single database connection instance.
 * 
 * @author Team
 */
public class DatabaseManager {
    
    private static DatabaseManager instance;
    private Connection connection;
    
    /**
     * Private constructor for singleton pattern
     */
    private DatabaseManager() {
        initializeConnection();
    }
    
    /**
     * Get singleton instance of DatabaseManager
     * @return DatabaseManager instance
     */
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    /**
     * Initialize database connection
     */
    private void initializeConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = (Connection) DriverManager.getConnection(
                UIConstants.DB_URL,
                UIConstants.DB_USER,
                UIConstants.DB_PASSWORD
            );
        } catch (ClassNotFoundException | SQLException ex) {
            showError("Database Connection Error", "Failed to connect to database: " + ex.getMessage());
        }
    }
    
    /**
     * Execute a SELECT query and return ResultSet
     * @param sql SQL query string
     * @return ResultSet containing query results
     */
    public ResultSet executeQuery(String sql) {
        try {
            if (connection == null || connection.isClosed()) {
                initializeConnection();
            }
            PreparedStatement pst = (PreparedStatement) connection.prepareStatement(sql);
            return pst.executeQuery();
        } catch (SQLException ex) {
            showError("Query Error", "Failed to execute query: " + ex.getMessage());
            return null;
        }
    }
    
    /**
     * Execute an INSERT, UPDATE, or DELETE query
     * @param sql SQL query string
     * @return true if successful, false otherwise
     */
    public boolean executeUpdate(String sql) {
        try {
            if (connection == null || connection.isClosed()) {
                initializeConnection();
            }
            PreparedStatement pst = (PreparedStatement) connection.prepareStatement(sql);
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (SQLException ex) {
            showError("Update Error", "Failed to execute update: " + ex.getMessage());
            return false;
        }
    }
    
    /**
     * Get a prepared statement for more complex queries
     * @param sql SQL query string
     * @return PreparedStatement object
     */
    public PreparedStatement getPreparedStatement(String sql) {
        try {
            if (connection == null || connection.isClosed()) {
                initializeConnection();
            }
            return (PreparedStatement) connection.prepareStatement(sql);
        } catch (SQLException ex) {
            showError("Statement Error", "Failed to create prepared statement: " + ex.getMessage());
            return null;
        }
    }
    
    /**
     * Close the database connection
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            showError("Close Error", "Failed to close connection: " + ex.getMessage());
        }
    }
    
    /**
     * Check if connection is active
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException ex) {
            return false;
        }
    }
    
    /**
     * Show error message dialog
     * @param title Dialog title
     * @param message Error message
     */
    private void showError(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
