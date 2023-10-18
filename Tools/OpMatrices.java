package Tools;

import Bases.MatrixBase;
import Bases.VectorBase;
import Matrices.Matrix11;

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
     * The same as {@link Tools.OpMain#sumSign(double[]...)}, but with a {@code Matrix} as the input
     * @param m The {@code Matrix} to find the sum of the signs of the values of
     * @return The sum of signs of the values in the {@code Matrix}
     */
    public static double sumSign(MatrixBase m){
        return sumSign(m.toDoubleMatrix());
    }

    /**
     * The same as {@link Tools.OpMain#signSum(double[]...)}, but with a {@code Matrix} as the input
     * @param m The {@code Matrix} to find the sign of the sum of
     * @return The sign of the sum of values in the {@code Matrix}
     */
    public static double signSum(MatrixBase m){
        return signSum(m.toDoubleMatrix());
    }

    /**
     * The same as {@link Tools.OpMain#squareSum(double[]...)}, but with a {@code Matrix} as the input
     * @param m The {@code Matrix} to find the sum of
     * @return The sum of values in the {@code Matrix}
     */
    public static double squareSum(MatrixBase m){
        return squareSum(m.toDoubleMatrix());
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
        MatrixBase m = MatrixBase.ofSize(multiplied.getRows(), multiplier.getCols());
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

    public static MatrixBase transpose(MatrixBase m){
        double[][] trans = new double[m.getCols()][m.getRows()];
        for(int i = 0; i < m.getCols(); i++){
            double[] col = m.getColSafe(i);
            trans[i] = col;
        }
        return MatrixBase.of(trans);
    }

    public static MatrixBase inverse(MatrixBase m){
        if(m instanceof Matrix11){
            return new Matrix11(1/m.getSafe(0,0));
        }
        MatrixBase inverse = OpMatrices.transpose(m.cofactorMatrix());
        inverse.multiply(1/m.determinant());
        return inverse;
    }

    public static MatrixBase rowEchelon(MatrixBase m){
        MatrixBase r = m.clone();

        int h = 0, k = 0;
        while(h < r.getRows() && k < m.getCols()){
            int i_max = 0;
            for(int i = 0; i < m.getRows(); i++){
                if(Math.abs(r.getSafe(i, k)) > Math.abs(r.getSafe(i_max, k))){
                    i_max = i;
                }
            }

            if(r.getSafe(i_max, k) == 0){
                k++;
            }else{
                VectorBase temp = r.getRowVector(i_max);
                r.setRow(i_max, r.getRowVector(h));
                r.setRow(h, temp);

                for(int i = h+1; i < m.getRows(); i++){
                    double f = r.getSafe(i, k) / r.getSafe(h, k);
                    r.setSafe(i, k, 0);
                    for(int j = k+1; j < m.getCols(); j++){
                        double v = r.getSafe(i, j) - r.getSafe(h, j) * f;
                        r.setSafe(i, j, v);
                    }
                }
            }

            h++; k++;
        }

        return r;
    }
}
