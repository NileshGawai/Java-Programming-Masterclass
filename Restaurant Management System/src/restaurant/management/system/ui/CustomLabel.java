package restaurant.management.system.ui;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author Nilesh Gawai
 */
public class CustomLabel extends JLabel {

    private Font defaultFont;

    public CustomLabel(String text, int fontSize) {
        super(text);
        defaultFont = new Font("Open Sans", Font.BOLD, fontSize);
        setFont(defaultFont);
    }

    public CustomLabel(String text) {
        super(text);
        defaultFont = new Font("Open Sans", Font.BOLD, 24);
        setFont(defaultFont);
    }
}
