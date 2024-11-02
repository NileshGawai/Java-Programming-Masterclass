package restaurant.management.system;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import restaurant.management.system.ui.RestaurantManagementUI;

/**
 * Main class
 *
 * @author Nilesh Gawai
 * @version 1.0.0 Date Created: 02/10/2024
 */
public class Main {

    /**
     * Main Entry Point of application.
     *
     * @param args list of command line arguments
     */
    public static void main(String[] args) {

//        try {
//            UIManager.setLookAndFeel(new WindowsLookAndFeel());
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
//            JPanel topPanel = new JPanel();
//            CustomLabel restaurantName = new CustomLabel("Twisty & Tasty Meals Parcel Service", 32);
//            topPanel.add(restaurantName);
//            frame.getContentPane().add(topPanel, BorderLayout.NORTH);
            frame.getContentPane().add(new RestaurantManagementUI(), BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
