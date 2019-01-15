public class RowSumCounter{
    
    public static void main(String[] args)  {
        try{
        if(args.length != 2) throw new MyException("Exception: Wrong number of arguments!");
        double x;
        double error;
        try{
            x = Double.parseDouble(args[0]);
            error = Double.parseDouble(args[1]);
        }catch(NumberFormatException ex){
            throw new MyException("Exception: Illegal arguments(use \".\" instead of \",\")!");
        }
        System.out.println(countSum(x, error));
        }catch(MyException mex){
            System.out.println(mex.getMessage());
        }
    }

    static double countSum(double x, double error){
        double sum = 0;

        double powerPart = x/3; //to reduce division
        double currentX = powerPart;//first member of power part progression

        int k = 0;
        double member = 0;
        int one = 1;
        do{
            sum += one*member;
            one = -one;
            k++;
            member =  (k+1) * currentX;
            currentX *= powerPart;
        }while(member >= error);

        return sum;
    }
}

class MyException extends Exception {
    public MyException() { super(); }
    public MyException(String message) { super(message); }
    public MyException(String message, Throwable cause) { super(message, cause); }
    public MyException(Throwable cause) { super(cause); }
}