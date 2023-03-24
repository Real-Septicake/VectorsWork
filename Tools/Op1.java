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
        double sum = 0;
        for (double val : v.toDoubleArray()) {
            sum += val;
        }
        return sum;
    }

    public static double wholeSquaresSum(double... vals) {
        double sum = 0;
        for (double val : vals) {
            sum += val * val;
        }
        return sum;
    }

    public static double wholeSquaresSum(VectorBase v) {
        double sum = 0;
        for (double val : v.toDoubleArray()) {
            sum += val * val;
        }
        return sum;
    }

    public static double findScalarMultiple(double magnitude, double maxMagnitude) {
        if (magnitude <= maxMagnitude || maxMagnitude == 0) return 1;
        return Math.min(magnitude, maxMagnitude) / magnitude;
    }
}
