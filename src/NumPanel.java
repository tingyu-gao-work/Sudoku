import javax.swing.*;
import java.awt.*;

public class NumPanel extends JPanel {
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

    private MyButton[][] NumButton = new MyButton[3][3]; // 1 - 9;

    public NumPanel(){
        this.setLayout(new GridLayout(3,3));
        for (int i: range(3)){
            for (int j: range(3)){
                NumButton[i][j] = new MyButton();
                this.add(NumButton[i][j]);
            }
        }
    }

    public void setNumButton_Action(Action ac[][]){
        for (int i: range(3)){
            for (int j: range(3)){
                NumButton[i][j].setAction(ac[i][j]);
            }
        }
    }
    public void setOutlook(){
        for (int i: range(3)){
            for (int j: range(3)){
                int Num = i * 3 + j + 1; // The number
                String signature = String.format("%d", Num);

                NumButton[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Separate them by Black Line
                NumButton[i][j].setHorizontalAlignment(JButton.CENTER); // \begin{center}
                NumButton[i][j].setFont(new Font("Dialog", Font.ITALIC, 50));
                NumButton[i][j].setText(signature);

                NumButton[i][j].setForeground(new Color(0, 135, 200).brighter());
                NumButton[i][j].setBackground(Color.WHITE);
                NumButton[i][j].setHoverBackgroundColor(new Color(3, 59, 90).brighter());
                NumButton[i][j].setPressedBackgroundColor(new Color(176,224,230));
            }
        }
    }
}
