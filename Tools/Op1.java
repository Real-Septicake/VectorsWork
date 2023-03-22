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

    public static double[] scalarMultiplication(double factor, double[] vals){
        double[] returns = new double[vals.length];
        for(int i = 0; i < vals.length; i++){
            returns[i] = vals[i] * factor;
        }
        return returns;
    }
}
