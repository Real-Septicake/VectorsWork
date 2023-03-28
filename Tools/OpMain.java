package Tools;

import Bases.VectorBase;

public class OpMain {
    private OpMain() {
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

    public static boolean equalValues(double[] a1, double[] a2){
        if(a1.length != a2.length) throw new IllegalArgumentException(ErrorMessages.arraySizeMismatch(a1, a2));
        for(int i = 0; i < a1.length; i++){
            if(a1[i] != a2[i]) return false;
        }
        return true;
    }

    public static boolean equalValues(VectorBase v1, VectorBase v2){
        if(v1.size() != v2.size()) throw new IllegalArgumentException(ErrorMessages.vectorSizeMismatch(v1, v2));
        return equalValues(v1.toDoubleArray(), v2.toDoubleArray());
    }

    public static boolean equalValues(VectorBase v, double[] a){
        if(v.size() != a.length) throw new IllegalArgumentException(ErrorMessages.vectorArraySizeMismatch(v, a));
        return equalValues(v.toDoubleArray(), a);
    }

    public static boolean equalMagnitude(double[] a1, double[] a2){
        return Math.sqrt(wholeSquaresSum(a1)) == Math.sqrt(wholeSquaresSum(a2));
    }

    public static boolean equalMagnitude(VectorBase v1, VectorBase v2){
        return equalMagnitude(v1.toDoubleArray(), v2.toDoubleArray());
    }

    public static boolean equalMagnitude(VectorBase v, double[] a){
        return equalMagnitude(v.toDoubleArray(), a);
    }

    public static double findScalarMultiple(double current, double desired) {
        if (current <= desired || desired == 0) return 1;
        return Math.min(current, desired) / current;
    }

    public static double dotProduct(double[] a1, double[] a2){
        if (a1.length != a2.length) throw new IllegalArgumentException(ErrorMessages.arraySizeMismatch(a1, a2));
        double sum = 0;
        for(int i = 0; i < a1.length; i++){
            sum += a1[i] * a2[i];
        }
        return sum;
    }

    public static double dotProduct(VectorBase v1, VectorBase v2){
        if(v1.size() != v2.size()) throw new IllegalArgumentException(ErrorMessages.vectorSizeMismatch(v1, v2));
        return dotProduct(v1.toDoubleArray(), v2.toDoubleArray());
    }

    public static double dotProduct(VectorBase v, double[] a){
        if(v.size() != a.length) throw new IllegalArgumentException(ErrorMessages.vectorArraySizeMismatch(v, a));
        return dotProduct(v.toDoubleArray(), a);
    }
}
