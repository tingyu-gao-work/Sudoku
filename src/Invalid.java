import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class Invalid extends JOptionPane {
    private int n;

    public Invalid() {
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        UIManager.put("OptionPane.minimumSize",new Dimension(screenSize.width/4, screenSize.height/4));

        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial",Font.PLAIN,40)));

        JLabel label = new JLabel("This is invalid! Undo Last Step!");
        label.setFont(new Font("Times New Roman", Font.BOLD, 50));
        label.setHorizontalAlignment(JLabel.CENTER);

        n = this.showConfirmDialog(null,
                label, "Warning",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE); //default button title
    }
    public int getN(){
        return n;
    }
}
