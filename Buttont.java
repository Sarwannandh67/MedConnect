package javaswingdev;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 * Buttont class - Custom animated button component
 * Extends JButton with hover animation effect using TimingFramework
 * Provides smooth color transition on mouse hover events
 * @author Developer
 */
public class Buttont extends JButton {
    
    // Animation components
    private Animator animator;
    private boolean mouseOver;
    private float animate;
    
    // Color properties
    private Color hoverColor = new Color(0xCC66DA);

    /**
     * Constructor - Initialize the custom button with animation support
     * Sets up mouse listeners and animator for hover effects
     */
    public Buttont() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(new Color(0x9929EA));
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentAreaFilled(false);
        setFocusable(false);

        // Add mouse listener for hover effects
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
                stopAnimation();
                animator.start();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
                stopAnimation();
                animator.start();
            }
        });

        // Initialize animator with timing target
        animator = new Animator(300, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                fraction *= 0.8f;
                if (mouseOver) {
                    animate = fraction;
                } else {
                    animate = 0.8f - fraction;
                }
                repaint();
            }
        });
        animator.setResolution(0);
    }

    /**
     * Get the current hover color
     * @return Color object representing the hover effect color
     */
    public Color getHoverColor() {
        return hoverColor;
    }

    /**
     * Set the hover color
     * @param hoverColor Color to display during hover effect
     */
    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    /**
     * Stop the current animation and prepare for the next animation state
     * This method ensures smooth animation transitions by handling the timing fraction
     */
    private void stopAnimation() {
        if (animator.isRunning()) {
            // If animator is running, save the current fraction
            float f = animator.getTimingFraction();
            animator.stop();
            // Set start fraction to continue from current position
            animator.setStartFraction(1f - f);
        } else {
            // If animator is not running, start from the beginning
            animator.setStartFraction(0f);
        }
    }

    /**
     * Paint the button component with hover animation effect
     * Draws a semi-transparent overlay with animation
     * @param grphcs Graphics object for drawing
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        
        // Enable anti-aliasing for smooth edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Set composite with animation alpha value for hover effect
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, animate));
        
        // Draw the hover color rectangle
        g2.setColor(hoverColor);
        g2.fillRect(0, 0, width, height);
        g2.dispose();

        // Call parent paint component to draw button content
        super.paintComponent(grphcs);
    }
}