import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

public class Sudoku {
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
    private JFrame main = new JFrame();
    private SudokuPanel Sudoku_P = new SudokuPanel();
    private RightPanel Right = new RightPanel();

    public Sudoku(){
        main.setTitle("Our Sudoku");//Set title
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // If closed, then exit the program
        main.setLayout(new GridBagLayout());

        Sudoku_P.setBorder(BorderFactory.createEmptyBorder(10,10,10,5));
        Sudoku_P.setBackground(Color.WHITE);

        // Create an action array for 1-9
        Action ac_for_NG = GenerateActionForNewGame();
        Right.setNewGame_Action(ac_for_NG);
        Right.setNewGame_Text("New Game");
        Right.setNP_Outlook();// Set 1-9
        Right.setMenu_Outlook();

        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets=Toolkit.getDefaultToolkit().getScreenInsets(main.getGraphicsConfiguration());
        final Rectangle frameBounds=new Rectangle(
                screenInsets.left, screenInsets.top,
                screenSize.width * 2/3,
                screenSize.height * 2/3);
        main.setBounds(frameBounds);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        main.add(Sudoku_P, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.2;
        main.add(Right, gbc);

        main.setResizable(false);
        main.setVisible(true);
    }

    private Action[][] GenerateActionForNP(){
        Action [][] ac = new Action[3][3];
        for (int i: range(3)){
            for (int j: range(3)){
                ac[i][j] = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int Num = i * 3 + j + 1; // The Num on The NumPanel

                        int [] Coor = Sudoku_P.getCoordinate(); // Which one do you want to change?
                        if (Coor[0] >= 0 && Coor[1] >= 0 && Coor[2] >= 0){ // Default -1,-1,-1
                            Sudoku_P.setTxtGame_Text(Coor, Num);
                            String command = Sudoku_P.getFirstRemember();

                            if (command.equals("CLOSE")){
                                main.dispatchEvent(new WindowEvent(main, WindowEvent.WINDOW_CLOSING));
                            }
                            else {
                                if (command.equals("NG")){
                                    ChooseDiff diff = new ChooseDiff();
                                    if (diff.getN() != -1){
                                        try {
                                            Sudoku_P.StartGame(diff.getN());
                                        } catch (FileNotFoundException ex) {
                                            ex.printStackTrace();
                                        }
                                        Action [][] acc = GenerateActionForNP();
                                        Right.setNP_Action(acc); // Set the above action
                                        Right.setNP_Outlook();// Set 1-9

                                        Action[][] ac_for_Menu = GenerateActionForMenu();
                                        Right.setMenu_Action(ac_for_Menu);
                                        Right.setMenu_Outlook();
                                    }
                                }
                            }
                        }
                    }
                };
            }
        }
        return ac;
    }
    private Action GenerateActionForNewGame(){
        Action ac = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseDiff diff = new ChooseDiff();
                if (diff.getN() != -1){
                    try {
                        Sudoku_P.StartGame(diff.getN());
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    Action [][] acc = GenerateActionForNP();
                    Right.setNP_Action(acc); // Set the above action
                    Right.setNP_Outlook();// Set 1-9

                    Action[][] ac_for_Menu = GenerateActionForMenu();
                    Right.setMenu_Action(ac_for_Menu);
                    Right.setMenu_Outlook();
                }
            }
        };
        return ac;
    }
    private Action[][] GenerateActionForMenu(){
        Action [][] acmenu = new Action[1][2];

        acmenu[0][0] = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String last = Sudoku_P.last_line();

                if (last.equals("no")) {
                    new LastStep();
                }
                else{
                    if (String.valueOf(last.charAt(last.length() - 1)).equals(" ")) {
                        int length = last.length();
                        last = last.substring(0, length - 1);
                    }

                    String[] hold = last.split(" ");
                    int[] cor = new int[3];//read the cor
                    for (int i = 0; i < 3; i++) {
                        cor[i] = Integer.parseInt(hold[i]);
                    }
                    if (hold.length == 3) {
                        Sudoku_P.setTxtGame_Texttoblank_back(cor);
                    }
                    if (hold.length == 4) {
                        Sudoku_P.setTxtGame_Text_back(cor, Integer.parseInt(hold[3]));
                    }
                }
                Sudoku_P.setDefaultColor();
                Sudoku_P.setDefaultCoor();
            }
        };
        acmenu[0][1] = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] Coor = Sudoku_P.getCoordinate();
                Sudoku_P.setTxtGame_Texttoblank(Coor);
                Sudoku_P.setDefaultColor();
                Sudoku_P.setDefaultCoor();
                //this method means make it be a blank,in the SudokuPanel
            }
        };

        return acmenu;
    }

    public static void main(String[] args) {
        new Sudoku();
    }
}
