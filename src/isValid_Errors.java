import java.util.ArrayList;

public class isValid_Errors {
    private boolean isValid;
    private ArrayList<int []> Error;

    public isValid_Errors(boolean isValid, ArrayList<int []> Error){
        this.isValid = isValid;
        this.Error = Error;
    }
    public boolean getisValid(){
        return isValid;
    }
    public ArrayList<int []> getError(){
        return Error;
    }
}
