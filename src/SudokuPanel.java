import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SudokuPanel extends JPanel {
    //------------------------
    public static int[] range(int n) {
        return range(0, n, 1);
    }
    public static int[] range(int a, int b, int c) {
        int d[] = new int[]{};

        for (int i = a; i < b; i = i + c) {
            d = append(d, i);
        }

        return d;
    }
    public static int[] append(int a[], int b) {
        int x = a.length;
        int c[] = new int[x + 1];

        for (int i = 0; i < x; i++) {
            c[i] = a[i];
        }
        c[x] = b;

        return c;
    }
    //------------------------

    private JPanel[] pnlGame = new JPanel[9]; // 9 big blocks;
    private JButton[][][] txtGame = new JButton[9][3][3]; // Each block has 3 * 3 grid;
    private ArrayList<String> remember = new ArrayList<>();
    private int[][] Puzzle;
    private boolean The_Game_is_Done = false;
    private int[] Coordinate = new int[]{
            -1, -1, -1
    };

    public SudokuPanel() {
        JPanel Board = new JPanel();
        Board.setLayout(new GridLayout(3, 3));

        this.setLayout(new GridLayout(1, 1));

        // Set big scenario
        for (int i : range(9)) {
            pnlGame[i] = new JPanel(); // Create a new JPanel
            pnlGame[i].setLayout(new GridLayout(3, 3)); // Create a 3 * 3 small grid within each big block

            Border certainLine;
            if ((i + 1) % 3 == 0 && i != 8){
                certainLine = BorderFactory.createEmptyBorder(0,0,-5,0);
            }
            else {
                if (i + 1 > 6 && i != 8){
                    certainLine = BorderFactory.createEmptyBorder(0,0,0,-5);
                }
                else {
                    if (i == 8){
                        certainLine = BorderFactory.createEmptyBorder(0,0,0,0);
                    }
                    else {
                        certainLine = BorderFactory.createEmptyBorder(0,0,-5,-5);
                    }
                }
            }
            Border dense = BorderFactory.createLineBorder(Color.BLACK, 5);

            pnlGame[i].setBorder(BorderFactory.createCompoundBorder(certainLine, dense)); // Separate them by blue lines
            Board.add(pnlGame[i]); //Add the big block to the frame
        }
        setButtons();
        this.add(Board);
    }
    public int[] getCoordinate() {
        return Coordinate;
    }

    private void LoadGame(int[][] arr) {
        for (int i : range(9)) {
            for (int j : range(3)) {
                for (int k : range(3)) {
                    txtGame[i][j][k].setBackground(Color.WHITE);

                    int[] Three_C = new int[]{
                            i, j, k
                    };
                    int[] Two_C = method.make_change(Three_C);
                    int Num = arr[Two_C[0]][Two_C[1]];

                    if (Num != 0) {
                        txtGame[i][j][k].setText(Num + "");
                        txtGame[i][j][k].setBackground(Color.GRAY);
                    } else {
                        txtGame[i][j][k].setText("");
                    }
                }
            }
        }
    }
    public void StartGame(int n) throws FileNotFoundException {
        remember.clear();
        Puzzle = method.LoadPuzzle(n);
        for (int i : range(9)) {
            for (int j : range(3)) {
                for (int k : range(3)) {
                    int[] Coor = {i, j, k};
                    txtGame[i][j][k].setAction(method.getGet(Coor, this, Puzzle, txtGame, Coordinate));
                }
            }
        }
        LoadGame(Puzzle);
    }

    private void Update_Game_Status() {
        for (int i : range(9)) {
            for (int j : range(3)) {
                for (int k : range(3)) {
                    String str = txtGame[i][j][k].getText();
                    if (str.equals("")) {
                        return;
                    }
                }
            }
        }
        The_Game_is_Done = true;
    }
    public void setTxtGame_Text(int c[], int n) {
        isValid_Errors Current = method.Update_Validity(c, n, txtGame);
        boolean isValid = Current.getisValid();
        ArrayList<int []> ErrorCoor = Current.getError();
        String save = txtGame[c[0]][c[1]][c[2]].getText();//得到该位置原本的值

        if (isValid) {
            setDefaultColor();

            String After_Set = String.format("%d", n);

            if (!save.equals(After_Set)) {
                remember.add(c[0] + " " + c[1] + " " + c[2] + " " + save);
                txtGame[c[0]][c[1]][c[2]].setText(n + "");
                Update_Game_Status();
                if (The_Game_is_Done) {
                    WinFrame win = new WinFrame();
                    remember.clear();
                    if (Math.abs(win.getN()) == 1) {
                        remember.add("CLOSE");
                    }
                    else {
                        remember.add("NG");
                    }
                }
            }
            setDefaultCoor();

        }
        else {
            txtGame[c[0]][c[1]][c[2]].setText(n + "");
            for (int[] i: ErrorCoor){
                txtGame[i[0]][i[1]][i[2]].setBackground(new Color(240,128,128));
            }
            Invalid Warn = new Invalid();
            if (Warn.getN() == 0 || Warn.getN() == -1){
                setDefaultColor();
                txtGame[c[0]][c[1]][c[2]].setText(save);
                setDefaultCoor();
            }
        }
    }
    public void setTxtGame_Text_back(int c[], int n) {
        txtGame[c[0]][c[1]][c[2]].setText(n + "");
        if (remember.size() >= 1) {
            remember.remove(remember.size() - 1);
        }
    }

    public void setTxtGame_Texttoblank(int c[]) {
        if (c[0] != -1) {
            String save = txtGame[c[0]][c[1]][c[2]].getText();//得到该位置原本的值
            if (!save.equals("")) {
                remember.add(c[0] + " " + c[1] + " " + c[2] + " " + save);
                txtGame[c[0]][c[1]][c[2]].setText("");
            }
        }
    }
    public void setTxtGame_Texttoblank_back(int c[]) {
        txtGame[c[0]][c[1]][c[2]].setText("");
        if (remember.size() >= 1) {
            remember.remove(remember.size() - 1);
        }
    }
    public String last_line() {
        if (remember.size() >= 1) {
            return remember.get(remember.size() - 1);
        } else return "no";
    }//get_last_line_of_remember

    public void setDefaultColor(){
        ArrayList<int []> Holes = method.getHoles(Puzzle);
        ArrayList<int []> Filled = method.getFilled(Puzzle);

        for (int Two_Coor[]: Holes){
            int [] Three_Coor = method.make_change(Two_Coor);
            txtGame[Three_Coor[0]][Three_Coor[1]][Three_Coor[2]].setBackground(Color.WHITE);
        }
        for (int Two_Coor[]: Filled){
            int [] Three_Coor = method.make_change(Two_Coor);
            txtGame[Three_Coor[0]][Three_Coor[1]][Three_Coor[2]].setBackground(Color.GRAY);
        }
    }
    public void setDefaultCoor(){
        Coordinate[0] = -1;
        Coordinate[1] = -1;
        Coordinate[2] = -1;
    }
    public String getFirstRemember(){
        if (remember.size() >= 1) {
            return remember.get(0);
        }
        else return "no";
    }

    private void setButtons(){
        for (int i : range(9)) {
            for (int j : range(3)) {
                for (int k : range(3)) {
                    txtGame[i][j][k] = new JButton(); //Create a JB
                    txtGame[i][j][k].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                    txtGame[i][j][k].setHorizontalAlignment(JButton.CENTER); // \begin{center}
                    txtGame[i][j][k].setFont(new Font("Dialog", Font.BOLD, 50));
                    txtGame[i][j][k].setBackground(Color.WHITE);

                    pnlGame[i].add(txtGame[i][j][k]); // Add it to the left panel
                }
            }
        }
    }
}