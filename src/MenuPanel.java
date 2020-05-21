import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

public class MenuPanel extends JPanel {
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

    private MyButton[][] Menu = new MyButton[1][2];

    public MenuPanel(){
        this.setLayout(new GridLayout(1,2));

        for (int i: range(1)){
            for (int j: range(2)){
                Menu[i][j] = new MyButton();
                this.add(Menu[i][j]);
            }
        }

    }

    public void setMenuButton_Acion(Action menu[][]){
        for (int i: range(1)){
            for (int j: range(2)){
                Menu[i][j].setAction(menu[i][j]);
            }
        }
    }
    public void setOutlook() {
        Menu[0][0].setText("Undo");
//        try {
//            Image img = ImageIO.read(new FileInputStream("undo.jpg"));
//            Menu[0][0].setIcon(new ImageIcon(img));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        Menu[0][1].setText("Erase");
//        try {
//            Image img = ImageIO.read(new FileInputStream("erase.jpg"));
//            Menu[0][1].setIcon(new ImageIcon(img));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        for (int i: range(2)){
            Menu[0][i].setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Separate them by Black Line
            Menu[0][i].setHorizontalAlignment(JButton.CENTER); // \begin{center}
            Menu[0][i].setFont(new Font("Georgia", Font.BOLD, 50));

            Menu[0][i].setForeground(new Color(0, 135, 200).brighter());
            Menu[0][i].setBackground(Color.WHITE);
            Menu[0][i].setHoverBackgroundColor(new Color(3, 59, 90).brighter());
            Menu[0][i].setPressedBackgroundColor(new Color(176,224,230));
        }
    }
}
