package Tools;

public class Op1 {
    private Op1(){}

    public static double wholeSum(double... vals){
        double sum = 0;
        for(double val : vals){
            sum += val;
        }
        return sum;
    }

    public static double wholeSquaresSum(double... vals){
        double sum = 0;
        for(double val : vals){
            sum += val * val;
        }
        return sum;
    }
}
