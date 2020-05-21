import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class method {
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

    private static boolean isOccupied_Default(int Coor[], int[][] Pattern){
        int[] Two_Coor = make_change(Coor);
        int Num = Pattern[Two_Coor[0]][Two_Coor[1]];

        return !(Num == 0);
    }
    public static ArrayList<int []> getHoles(int [][] Pattern){
        ArrayList<int []> Holes = new ArrayList<>();

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (Pattern[i][j] == 0){
                    int [] Coor = new int[]{
                            i, j
                    };
                    Holes.add(Coor);
                }
            }
        }
        return Holes;
    }
    public static ArrayList<int []> getFilled(int [][] Pattern){
        ArrayList<int []> Holes = new ArrayList<>();

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (Pattern[i][j] != 0){
                    int [] Coor = new int[]{
                            i, j
                    };
                    Holes.add(Coor);
                }
            }
        }
        return Holes;
    }
    public static Action getGet(int[] Coor, SudokuPanel Sudoku_P, int[][] Pattern, JButton[][][] txtGame, int[] Coordinate) {
        int[] Two_Coor = make_change(Coor);
        boolean isOcc_default = isOccupied_Default(Coor, Pattern);

        if (isOcc_default) {
            Action select = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Sudoku_P.setDefaultColor();
                    Sudoku_P.setDefaultCoor();
                }
            };
            return select;
        }
        else {
            Action select = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Sudoku_P.setDefaultColor();
                    Sudoku_P.setDefaultCoor();

                    for (int a:range(3)){
                        for (int b: range(3)){
                            txtGame[Coor[0]][a][b].setBackground(new Color(230,230,250)); // Set the clicked one to Green
                        }
                    }

                    for (int a: range(9)){
                        int r_c[] = new int[]{
                                Two_Coor[0], a
                        };
                        int T_r_c[] = make_change(r_c);

                        int c_c[] = new int[]{
                                a, Two_Coor[1]
                        };
                        int T_c_c[] = make_change(c_c);

                        txtGame[T_c_c[0]][T_c_c[1]][T_c_c[2]].setBackground(new Color(230,230,250)); // Set the clicked one to Green
                        txtGame[T_r_c[0]][T_r_c[1]][T_r_c[2]].setBackground(new Color(230,230,250)); // Set the clicked one to Green
                    }

                    txtGame[Coor[0]][Coor[1]][Coor[2]].setBackground(new Color(176,224,230)); // Set the clicked one to Green

                    // Update Coor
                    Coordinate[0] = Coor[0];
                    Coordinate[1] = Coor[1];
                    Coordinate[2] = Coor[2];
                }
            };
            return select;
        }
    }

    public static isValid_Errors Update_Validity(int c[], int n, JButton[][][] txtGame) {
        ArrayList<int []> Error = new ArrayList<>();

        int Block = c[0];
        int Two_Coor[] = make_change(c);
        int row = Two_Coor[0];
        int col = Two_Coor[1];

        for (int i : range(9)) {
            int[] r_c = new int[]{
                    row, i
            };
            int[] Three_r_c = make_change(r_c);
            int[] c_c = new int[]{
                    i, col
            };
            int[] Three_c_c = make_change(c_c);

            String test1 = txtGame[Three_r_c[0]][Three_r_c[1]][Three_r_c[2]].getText();
            String test2 = txtGame[Three_c_c[0]][Three_c_c[1]][Three_c_c[2]].getText();
            boolean p = i != col && !test1.equals("") && Integer.parseInt(test1) == n;
            boolean q = i != row && !test2.equals("") && Integer.parseInt(test2) == n;
            if (p) {
                Error.add(Three_r_c);
            }
            if (q){
                Error.add(Three_c_c);
            }
        }
        for (int i : range(3)) {
            for (int j : range(3)) {
                String test = txtGame[Block][i][j].getText();
                boolean p = i != c[1] && j != c[2] && !test.equals("") && Integer.parseInt(test) == n;
                if (p) {
                    int C[] = new int[]{
                            Block, i, j
                    };
                    Error.add(C);
                }
            }
        }
        boolean validity = Error.size() == 0;

        return new isValid_Errors(validity, Error);
    }

    public static int[] make_change(int[] in) {
        int[] three = new int[3];
        int[] two = new int[2];
        if (in.length == 3) {
            two[0] = 3 * (in[0] / 3) + in[1];
            two[1] = 3 * (in[0] % 3) + in[2];
            return two;
        } else {
            int a = in[0];
            int b = in[1];

            int i = 3 * (a / 3) + (b / 3);
            int j = a - 3 * (a / 3);
            int k = b - 3 * (b / 3);

            three[0] = i;
            three[1] = j;
            three[2] = k;
            return three;
        }
    }

    public static int[][] LoadPuzzle(int n) throws FileNotFoundException {
        //String main = "C:\\Users\\吃西瓜的人\\Desktop\\sukudo\\";
        String main = "";
        String Diff;
        if (n == 0){
            Diff = "Easy";
        }
        else {
            if (n == 1){
                Diff = "Medium";
            }
            else {
                Diff = "Hard";
            }
        }

        File F = new File(main + Diff);
        File[] files = F.listFiles();
        Random rand = new Random();

        assert files != null;

        File file = files[rand.nextInt(files.length)];

        System.out.println(file.getName());

        Scanner sc = new Scanner(file);

        int[][] arr = new int[9][9];
        for (int i : range(9)) {
            for (int j : range(9)) {
                arr[i][j] = sc.nextInt();
            }
        }
        return arr;
    }
}