import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class WinFrame extends JOptionPane {
    private int n;
    private JLabel Text = new JLabel();
    private Object[] options = {"New Game", "Close"};

    public WinFrame(){
        Text.setText("You win!");
        Font font = new Font("Forte", Font.PLAIN, 50);
        Text.setHorizontalAlignment(SwingConstants.CENTER);//center label text
        Text.setFont(font);
        Text.setForeground(Color.MAGENTA);

        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        UIManager.put("OptionPane.minimumSize",new Dimension(screenSize.width/4, screenSize.height/4));
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial",Font.PLAIN,40)));

        n = this.showOptionDialog(null,
                Text,
                "Congratulation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title
    }
    public int getN(){
        return n;
    }
}
