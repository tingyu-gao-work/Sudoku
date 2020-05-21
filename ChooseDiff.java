import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class ChooseDiff extends JOptionPane {
    private int n;
    private Object[] options = {"Easy", "Medium", "Hard"};

    public ChooseDiff() {
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        UIManager.put("OptionPane.minimumSize",new Dimension(screenSize.width/4, screenSize.height/4));

        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial",Font.PLAIN,40)));

        JLabel label = new JLabel("Please Choose A Difficulty");
        label.setFont(new Font("Times New Roman", Font.BOLD, 50));
        label.setHorizontalAlignment(JLabel.CENTER);

        n = this.showOptionDialog(null,
                label,
                "Difficulty",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title
    }
    public int getN(){
        return n;
    }
}
