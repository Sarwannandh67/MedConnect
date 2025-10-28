package online.medicine.donation.system;

import javax.swing.JFrame;

/**
 * NavigationManager - Centralized navigation between application frames
 * Handles switching between different frames while maintaining application state.
 * Uses a singleton pattern for consistent frame management.
 * 
 * @author Team
 */
public class NavigationManager {
    
    private static NavigationManager instance;
    private JFrame currentFrame;
    
    /**
     * Private constructor for singleton pattern
     */
    private NavigationManager() {
    }
    
    /**
     * Get singleton instance of NavigationManager
     * @return NavigationManager instance
     */
    public static NavigationManager getInstance() {
        if (instance == null) {
            instance = new NavigationManager();
        }
        return instance;
    }
    
    /**
     * Navigate to a new frame, hiding the current one
     * @param newFrame The frame to navigate to
     */
    public void navigateTo(JFrame newFrame) {
        if (currentFrame != null) {
            currentFrame.setVisible(false);
        }
        currentFrame = newFrame;
        newFrame.setVisible(true);
    }
    
    /**
     * Navigate to HomePage
     */
    public void toHomePage() {
        navigateTo(new HomePage());
    }
    
    /**
     * Navigate to Admin dashboard
     */
    public void toAdmin() {
        navigateTo(new Admin());
    }
    
    /**
     * Navigate to User dashboard
     */
    public void toUser() {
        navigateTo(new User());
    }
    
    /**
     * Navigate to Executive dashboard
     */
    public void toExecutive() {
        navigateTo(new Executive());
    }
    
    /**
     * Navigate to NGO dashboard
     */
    public void toNGO() {
        navigateTo(new NGO());
    }
    
    /**
     * Navigate to ViewUser page
     */
    public void toViewUser() {
        navigateTo(new ViewUser());
    }
    
    /**
     * Navigate to ViewNGO page
     */
    public void toViewNGO() {
        navigateTo(new ViewNGO());
    }
    
    /**
     * Navigate to ViewMedicine page
     */
    public void toViewMedicine() {
        navigateTo(new ViewMedicine());
    }
    
    /**
     * Navigate to BuyMedicine page
     */
    public void toBuyMedicine() {
        navigateTo(new BuyMedicine());
    }
    
    /**
     * Navigate to DonateMedicine page
     */
    public void toDonateMedicine() {
        navigateTo(new DonateMedicine());
    }
    
    /**
     * Navigate to CollectMedicine page
     */
    public void toCollectMedicine() {
        navigateTo(new CollectMedicine());
    }
    
    /**
     * Navigate to Login page
     */
    public void toLogin() {
        navigateTo(new Login());
    }
    
    /**
     * Navigate to User Login page
     */
    public void toUserLogin() {
        navigateTo(new UserLogin());
    }
    
    /**
     * Navigate to NGO Login page
     */
    public void toNGOLogin() {
        navigateTo(new NGOLogin());
    }
    
    /**
     * Navigate to Executive Login page
     */
    public void toExecutiveLogin() {
        navigateTo(new ExecutiveLogin());
    }
    
    /**
     * Exit the application
     */
    public void exitApplication() {
        System.exit(0);
    }
    
    /**
     * Get the current active frame
     * @return Current JFrame
     */
    public JFrame getCurrentFrame() {
        return currentFrame;
    }
}
