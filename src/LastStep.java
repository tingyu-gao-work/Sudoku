import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class LastStep extends JOptionPane {

    public LastStep() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        UIManager.put("OptionPane.minimumSize", new Dimension(screenSize.width / 4, screenSize.height / 4));

        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial", Font.PLAIN, 40)));

        JLabel label = new JLabel("No Last Step! Nothing can be undone!");
        label.setFont(new Font("Times New Roman", Font.BOLD, 50));
        label.setHorizontalAlignment(JLabel.CENTER);

        this.showMessageDialog(null,
                label, "Last Step", JOptionPane.PLAIN_MESSAGE); //default button title
    }
}
