package Tools;

import Bases.MatrixBase;

/**
 * A class made specifically for operations pertaining to {@code Matrices}
 *
 * @author Septicake
 */
public class OpMatrices extends OpMain {
    private OpMatrices() {
    }

    /**
     * Used to confirm that the input {@code Matrix} is rectangular
     * @param vals The {@code Matrix} to check
     * @throws IllegalArgumentException If the input {@code Matrix} is not rectangular
     */
    public static void confirmRect(double[]... vals) throws IllegalArgumentException {
        for (double[] a : vals) {
            if (a.length != vals[0].length) throw new IllegalArgumentException(ErrorMessages.MatrixErrors.NON_UNIFORM_INPUT);
        }
    }

    /**
     * Returns the inverse of the sum of the values in a {@code Matrix}
     * @param m The {@code Matrix} to find the inverse of the sum of
     * @return The inverse of the sum of the values in the {@code Matrix}
     */
    public static double invertedSum(MatrixBase m){
        return -sum(m.toDoubleMatrix());
    }

    /**
     * The same as {@link Tools.OpMain#sum(double[]...)}, but with a {@code Matrix} as the input
     * @param m The {@code Matrix} to find the sum of
     * @return The sum of values in the {@code Matrix}
     */
    public static double sum(MatrixBase m) {
        return sum(m.toDoubleMatrix());
    }

    /**
     * Multiplies two {@code Matrices} together
     * @param multiplied The {@code Matrix} to be multiplied
     * @param multiplier The {@code Matrix} to multiply by
     * @return The {@code Matrix} resulting from the multiplication of the two input {@code Matrices}
     * @throws IllegalArgumentException If the width of {@code multiplied} is not equal to the height of {@code multiplier}
     */
    public static MatrixBase matrixMultiply(MatrixBase multiplied, MatrixBase multiplier) throws IllegalArgumentException{
        if (multiplied.getCols() != multiplier.getRows())
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixMultiplySizeMismatch(multiplied, multiplier));
        MatrixBase m = MatrixBase.of(multiplied.getRows(), multiplier.getCols());
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
