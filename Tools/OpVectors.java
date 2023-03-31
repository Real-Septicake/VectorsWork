package Tools;

import Bases.VectorBase;

/**
 * A class made specifically for operations pertaining to Vectors
 *
 * @author Septicake
 */
public class OpVectors extends OpMain {
    private OpVectors() {
    }

    /**
     * Returns the sum of the values in a Vector
     * @param v Vector to find the sum of the values of
     * @return The sum of the values in the Vector
     */
    public static double wholeSum(VectorBase v) {
        return sum(v.toDoubleArray());
    }

    /**
     * Returns the sum of the squares of the values in a Vector
     * @param v Vector to find the sum of the squares of the values of
     * @return The sum of the squares of the values in a Vector
     */
    public static double wholeSquaresSum(VectorBase v) {
        return squareSum(v.toDoubleArray());
    }


    public static boolean equalValues(double[] a1, double[] a2) {
        if (a1.length != a2.length) throw new IllegalArgumentException(ErrorMessages.VectorErrors.arraySizeMismatch(a1, a2));
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) return false;
        }
        return true;
    }

    public static boolean equalValues(VectorBase v1, VectorBase v2) {
        if (v1.size() != v2.size()) throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(v1, v2));
        return equalValues(v1.toDoubleArray(), v2.toDoubleArray());
    }

    public static boolean equalMagnitude(double[] a1, double[] a2) {
        return Math.sqrt(squareSum(a1)) == Math.sqrt(squareSum(a2));
    }

    public static boolean equalMagnitude(VectorBase v1, VectorBase v2) {
        return equalMagnitude(v1.toDoubleArray(), v2.toDoubleArray());
    }

    /**
     * Finds the scalar multiple required to get the magnitude to the desired value
     * <p>Special Cases:</p>
     * <ul>
     *     <li>If the desired value is 0, 1 is returned</li>
     *     <li>If the current value is less than the desired value, 1 is returned</li>
     *     <li>If either input is NaN, the result is NaN</li>
     * </ul>
     * @param current Current value of the magnitude
     * @param desired Desired value of the magnitude
     * @return The scalar multiple required to get the current magnitude to the desired value
     */
    public static double findScalarMultiple(double current, double desired) {
        if (current <= desired || desired == 0) return 1;
        return Math.min(current, desired) / current;
    }


    public static double dotProduct(double[] a1, double[] a2) {
        if (a1.length != a2.length) throw new IllegalArgumentException(ErrorMessages.VectorErrors.arraySizeMismatch(a1, a2));
        double sum = 0;
        for (int i = 0; i < a1.length; i++) {
            sum += a1[i] * a2[i];
        }
        return sum;
    }

    public static double dotProduct(VectorBase v1, VectorBase v2) {
        if (v1.size() != v2.size()) throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(v1, v2));
        return dotProduct(v1.toDoubleArray(), v2.toDoubleArray());
    }
}
