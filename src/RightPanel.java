import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    //------------------------
    public static int[] range(int n){
        return range(0,n,1);
    }
    public static int[] range(int a, int b, int c){
        int d[] = new int[]{};

        for (int i = a; i < b; i = i + c){
            d = append(d,i);
        }

        return d;
    }
    public static int[] append(int a[], int b){
        int x = a.length;
        int c[] = new int[x + 1];

        for (int i = 0; i < x; i++){
            c[i] = a[i];
        }
        c[x] = b;

        return c;
    }
    //------------------------

    private MyButton NewGame = new MyButton();
    private NumPanel NP = new NumPanel();
    private MenuPanel Menu = new MenuPanel();

    public RightPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout()); //Size

        JPanel ForNewG = new JPanel();
        ForNewG.setBorder(BorderFactory.createEmptyBorder(100,50,100,50));
        ForNewG.setBackground(Color.WHITE);
        ForNewG.setLayout(new GridLayout(1,1));
        ForNewG.add(NewGame);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        this.add(ForNewG, gbc);

        JPanel Combine = new JPanel();
        Combine.setLayout(new GridBagLayout());

        gbc.weighty = 0.6;
        Combine.add(NP,gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.4;
        Combine.add(Menu, gbc);
        Combine.setBorder(BorderFactory.createEmptyBorder(0,50,50,50));
        Combine.setBackground(Color.WHITE);

        gbc.weighty = 0.8;
        this.add(Combine, gbc);
    }

    public void setNewGame_Action(Action ac){
        NewGame.setAction(ac);
    }
    public void setNewGame_Text(String str){
        NewGame.setText(str);
        NewGame.setFont(new Font("Dialog", Font.BOLD, 50));

        NewGame.setForeground(Color.WHITE);
        NewGame.setHorizontalTextPosition(SwingConstants.CENTER);
        NewGame.setBackground(new Color(0,191,255));
        NewGame.setHoverBackgroundColor(new Color(30,144,255));
        NewGame.setPressedBackgroundColor(new Color(176,224,230));
    }

    public void setNP_Action(Action ac[][]){
        NP.setNumButton_Action(ac);
    }
    public void setNP_Outlook(){
        NP.setOutlook();
    }

    public void setMenu_Action(Action menu[][]){Menu.setMenuButton_Acion(menu);}
    public void setMenu_Outlook(){
        Menu.setOutlook();
    }
}
