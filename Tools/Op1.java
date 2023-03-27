package Tools;

import Bases.VectorBase;

public class Op1 {
    private Op1() {
    }

    public static double wholeSum(double... vals) {
        double sum = 0;
        for (double val : vals) {
            sum += val;
        }
        return sum;
    }

    public static double wholeSum(VectorBase v) {
        return wholeSum(v.toDoubleArray());
    }

    public static double wholeSquaresSum(double... vals) {
        double sum = 0;
        for (double val : vals) {
            sum += val * val;
        }
        return sum;
    }

    public static double wholeSquaresSum(VectorBase v) {
        return wholeSquaresSum(v.toDoubleArray());
    }

    public static boolean equalValues(double[] val1, double[] val2){
        if(val1.length != val2.length) throw new IllegalArgumentException(ErrorMessages.arraySizeMismatch(val1, val2));
        for(int i = 0; i < val1.length; i++){
            if(val1[i] != val2[i]) return false;
        }
        return true;
    }

    public static boolean equalValues(VectorBase v1, VectorBase v2){
        if(v1.size() != v2.size()) throw new IllegalArgumentException(ErrorMessages.vectorSizeMismatch(v1, v2));
        return equalValues(v1.toDoubleArray(), v2.toDoubleArray());
    }

    public static boolean equalMagnitude(double[] val1, double[] val2){
        return Math.sqrt(wholeSquaresSum(val1)) == Math.sqrt(wholeSquaresSum(val2));
    }

    public static boolean equalMagnitude(VectorBase v1, VectorBase v2){
        return equalMagnitude(v1.toDoubleArray(), v2.toDoubleArray());
    }

    public static double findScalarMultiple(double current, double desired) {
        if (current <= desired || desired == 0) return 1;
        return Math.min(current, desired) / current;
    }
}
