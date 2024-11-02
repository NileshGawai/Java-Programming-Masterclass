package restaurant.management.system.ui;

import info.clearthought.layout.TableLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Nilesh Gawai
 */
public class RestaurantManagementUI extends JPanel {

    public RestaurantManagementUI() {
        
//        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        // Create a TableLayout for the panel
        double p = TableLayout.PREFERRED;
        double f = TableLayout.FILL;
        double hGap = 8;
        double vGap = 8;
        double sizes[][] = new double[][]{
            {1, 150, vGap, f, vGap, f, vGap, 0.20, vGap, 150, 1},
            {1, f, hGap, f, hGap, f, 1}
        };

        TableLayout tableLayout = new TableLayout(sizes);
        setLayout(tableLayout);

        JPanel businessName = new JPanel();
        businessName.setBackground(new Color(144, 12, 63));

        JPanel businessName2 = new JPanel();
        businessName2.setBackground(new Color(144, 12, 63));

        add(businessName, "1, 1, 1, 5");
        add(createFoodItemsPanel(), "3, 1");
        add(createFluidItemsPanel(), "3, 3");
        add(createPricesPanel(), "5, 1");
        add(createBillingPanel(), "5, 3, 5, 5");
        add(createControlPanel(), "7, 1, 7, 5");

        add(businessName2, "9, 1, 9, 5");

    }

    private JPanel createFoodItemsPanel() {
        double f = TableLayout.FILL;
        double p = TableLayout.PREFERRED;
        double sizes[][] = {
            {5, f, 20, f, 5}, {5, f, 5, f, 5, f, 5, f, 5, f, 5}
        };

        TableLayout t = new TableLayout(sizes);
        JPanel foodPanel = new JPanel();
        foodPanel.setLayout(t);

        foodPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        CustomLabel hotDog = new CustomLabel("HOT DOG");
        JTextField hotDogPriceField = new JTextField();

        CustomLabel steak = new CustomLabel("STEAK");
        JTextField steakPriceField = new JTextField();

        CustomLabel hamBurger = new CustomLabel("HAMBURGER");
        JTextField hamBurgerPriceField = new JTextField();

        CustomLabel pizza = new CustomLabel("PIZZA");
        JTextField pizzaPriceField = new JTextField();

        foodPanel.add(hotDog, "1, 1");
        foodPanel.add(hotDogPriceField, "3, 1");
        foodPanel.add(steak, "1, 3");
        foodPanel.add(steakPriceField, "3, 3");
        foodPanel.add(hamBurger, "1, 5");
        foodPanel.add(hamBurgerPriceField, "3, 5");
        foodPanel.add(pizza, "1, 7");
        foodPanel.add(pizzaPriceField, "3, 7");

        JPanel panel = new JPanel();
        panel.add(new CustomLabel("FOOD"));
        foodPanel.add(panel, "1, 9, 3, 9");

        return foodPanel;
    }

    private JPanel createFluidItemsPanel() {
        double f = TableLayout.FILL;
        double sizes[][] = {
            {5, f, 20, f, 5}, {5, f, 5, f, 5, f, 5, f, 5, f, 5}
        };

        TableLayout t = new TableLayout(sizes);
        JPanel fluidsPanel = new JPanel(t);
        fluidsPanel.setPreferredSize(new Dimension(350, 450));
        fluidsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        CustomLabel mojito = new CustomLabel("MOJITO");
        JTextField mojitoPriceField = new JTextField();

        CustomLabel cappuccino = new CustomLabel("CAPPUCCINO");
        JTextField cappuccinoPriceField = new JTextField();

        CustomLabel milkShake = new CustomLabel("MILKSHAKE");
        JTextField milkShakePriceField = new JTextField();

        CustomLabel iceTea = new CustomLabel("ICE TEA");
        JTextField iceteaPriceField = new JTextField();

        fluidsPanel.add(mojito, "1, 1");
        fluidsPanel.add(mojitoPriceField, "3, 1");

        fluidsPanel.add(cappuccino, "1, 3");
        fluidsPanel.add(cappuccinoPriceField, "3, 3");

        fluidsPanel.add(milkShake, "1, 5");
        fluidsPanel.add(milkShakePriceField, "3, 5");

        fluidsPanel.add(iceTea, "1, 7");
        fluidsPanel.add(iceteaPriceField, "3, 7");

        JPanel panel = new JPanel();
        panel.add(new CustomLabel("DRINKS"));
        fluidsPanel.add(panel, "1, 9, 3, 9");

        return fluidsPanel;
    }

    private JPanel createPricesPanel() {

        double f = TableLayout.FILL;
        double sizes[][] = {
            {5, f, 20, f, 5}, {5, f, 5, f, 5, f, 5, f, 5}
        };

        TableLayout t = new TableLayout(sizes);
        JPanel pricesPanel = new JPanel(t);
        pricesPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        CustomLabel foodsPrice = new CustomLabel("FOOD'S PRICE");
        CustomLabel fluidsPrice = new CustomLabel("DRINKS' PRICE");
        CustomLabel liveBill = new CustomLabel("LIVE BILL");
        
        JPanel liveBillPanel = new JPanel();
        liveBillPanel.add(liveBill);

        JTextField foodsPriceField = new JTextField();
        JTextField fluidsPriceField = new JTextField();

        CustomLabel totalPriceLabel = new CustomLabel("TOTAL");
        CustomLabel calculatedSum = new CustomLabel("$ 0");

        pricesPanel.add(foodsPrice, "1, 1");
        pricesPanel.add(foodsPriceField, "3, 1");

        pricesPanel.add(fluidsPrice, "1, 3");
        pricesPanel.add(fluidsPriceField, "3, 3");

        pricesPanel.add(totalPriceLabel, "1, 5");
        pricesPanel.add(calculatedSum, "3, 5");
        
        pricesPanel.add(liveBillPanel, "1, 7, 1, 7");

        return pricesPanel;
    }

    private JPanel createControlPanel() {

        JButton btnSum = new JButton("GENERATE BILL");
        btnSum.setPreferredSize(new Dimension(150, 48));

        JButton btnBill = new JButton("PRINT BILL");
        btnBill.setPreferredSize(new Dimension(150, 48));

        JButton btnNewBill = new JButton("NEW BILL");
        btnNewBill.setPreferredSize(new Dimension(150, 48));

        JButton btnClose = new JButton("CLOSE");
        btnClose.setPreferredSize(new Dimension(150, 48));

        double f = TableLayout.FILL;
        double p = TableLayout.PREFERRED;
        double sizes[][] = {
            {5, f, 5},
            {25, p, 25, p, 25, p, 25, p, 25}
        };

        JPanel controlPanel = new JPanel();
        TableLayout t = new TableLayout(sizes);
        controlPanel.setLayout(t);

        controlPanel.add(btnSum, "1, 1");
        controlPanel.add(btnBill, "1, 3");
        controlPanel.add(btnNewBill, "1, 5");
        controlPanel.add(btnClose, "1, 7");
        return controlPanel;
    }

    private JPanel createBillingPanel() {
        JPanel billingPanel = new JPanel(new BorderLayout());
        JTextArea billingTextArea = new JTextArea(5, 20);

//        billingTextArea.setBackground(new Color(255, 255, 220));
        billingTextArea.setMargin(new Insets(5, 10, 5, 10));
        billingTextArea.setEditable(false);

        billingTextArea.setText("Twisty & Tasty Meals\n\n"
                + "INVOICE NO. 1414\n"
                + "***************************\n"
                + String.format("%-10s %-10s %-10s %n", "Items", "Price($)", "Qty")
                + "---------------------------\n"
                + String.format("%-10s %-10d %-10d %n", "HOT DOG", 5, 2)
                + String.format("%-10s %-10d %-10d %n", "STEAK", 4, 1)
                + String.format("%-10s %-10d %-10d %n", "HAMBURGER", 6, 3)
                + "---------------------------\n"
                + String.format("%-10s %-10s %n", "TOTAL", "$32")
                + "\n\nThank you for visiting...\n"
        );

        JScrollPane jsp = new JScrollPane(billingTextArea);

        billingTextArea.setFont(new Font("Lucida Console", Font.PLAIN, 14));
        billingPanel.add(jsp);

        return billingPanel;
    }
}
