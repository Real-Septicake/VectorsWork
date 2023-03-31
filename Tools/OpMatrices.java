package Tools;

import Bases.MatrixBase;

/**
 * A class made specifically for operations pertaining to Matrices
 *
 * @author Septicake
 */
public class OpMatrices extends OpMain {
    private OpMatrices() {
    }

    public static void confirmRect(double[]... vals) {
        for (double[] a : vals) {
            if (a.length != vals[0].length) throw new IllegalArgumentException(ErrorMessages.MatrixErrors.NON_UNIFORM_INPUT);
        }
    }

    public static double invertedSum(MatrixBase m){
        return -sum(m.toDoubleMatrix());
    }

    public static double sum(MatrixBase m) {
        return sum(m.toDoubleMatrix());
    }

    public static MatrixBase matrixMultiply(MatrixBase multiplied, MatrixBase multiplier) {
        if (multiplied.getCols() != multiplier.getRows())
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixMultiplySizeMismatch(multiplied, multiplier));
        MatrixBase m = new MatrixBase(multiplied.getRows(), multiplier.getCols());
        for(int i = 0; i < m.getRows(); i++){
            for(int j = 0; j < m.getCols(); j++){
                double sum = 0;
                for(int n = 0; n < multiplied.getCols(); n++){
                    sum += multiplied.getSafe(i, n) * multiplier.getSafe(n, j);
                }
                m.setSafe(i, j, sum);
            }
        }
        return m;
    }
}
