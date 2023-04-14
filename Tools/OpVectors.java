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
     * The same as {@link Tools.OpMain#sum(double[]...)}, but with a {@code Vector} as the input
     * @param v The {@code Vector} to find the sum of
     * @return The sum of values in the {@code Vector}
     */
    public static double sum(VectorBase v) {
        return sum(v.toDoubleArray());
    }

    /**
     * The same as {@link Tools.OpMain#squareSum(double[]...)}, but with a {@code Vector} as the input
     * @param v The {@code Vector} to find the sum of the squares of the values of
     * @return The sum of the squares of the values in the {@code Vector}
     */
    public static double squareSum(VectorBase v) {
        return squareSum(v.toDoubleArray());
    }

    /**
     * The same as {@link Tools.OpMain#sumSign(double[]...)}, but with a {@code Vector} as the input
     * @param v The {@code Vector} to find the sign of the sum of
     * @return The sign of the sum of values in the {@code Vector}
     */
    public static double sumSign(VectorBase v){
        return sumSign(v.toDoubleArray());
    }

    /**
     * The same as {@link Tools.OpMain#signSum(double[]...)}, but with a {@code Vector} as the input
     * @param v The {@code Vector} to find the sum of the signs of the values of
     * @return The sum of the signs of the values in the {@code Vector}
     */
    public static double signSum(VectorBase v){
        return signSum(v.toDoubleArray());
    }

    public static boolean equalValues(double[] a1, double[] a2, double threshold){
        if (a1.length != a2.length) throw new IllegalArgumentException(ErrorMessages.VectorErrors.arraySizeMismatch(a1, a2));
        for (int i = 0; i < a1.length; i++) {
            if (Math.abs(a1[i] - a2[i]) > threshold) return false;
        }
        return true;
    }

    public static boolean equalValues(double[] a1, double[] a2) {
        return equalValues(a1, a2, 0);
    }

    public static boolean equalValues(VectorBase v1, VectorBase v2, double threshold){
        if (v1.size() != v2.size()) throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(v1, v2));
        return equalValues(v1.toDoubleArray(), v2.toDoubleArray(), threshold);
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
     *     <li>If either input is NaN, the result is NaN</li>
     * </ul>
     * @param current Current value of the magnitude
     * @param desired Desired value of the magnitude
     * @return The scalar multiple required to get the current magnitude to the desired value
     */
    public static double findScalarMultiple(double current, double desired) {
        return Math.min(current, desired) / current;
    }

    /**
     * Finds the scalar multiple required to get the magnitude within a desired maximum value
     * <p>Special Cases:</p>
     * <ul>
     *     <li>If the desired value is 0, 1 is returned</li>
     *     <li>If the current value is less than the desired value, 1 is returned</li>
     *     <li>If either input is NaN, the result is NaN</li>
     * </ul>
     * @param current Current value of the magnitude
     * @param max Maximum value of the magnitude
     * @return The scalar multiple required to get the current magnitude within the specified maximum
     */
    public static double clampMax(double current, double max){
        if (current <= max || max == 0) return 1;
        return findScalarMultiple(current, max);
    }

    /**
     * Returns the dot product of two same-sized arrays
     * @param a1 First array
     * @param a2 Second array
     * @return The dot product of the two input arrays
     */
    public static double dotProduct(double[] a1, double[] a2) {
        if (a1.length != a2.length) throw new IllegalArgumentException(ErrorMessages.VectorErrors.arraySizeMismatch(a1, a2));
        double sum = 0;
        for (int i = 0; i < a1.length; i++) {
            sum += a1[i] * a2[i];
        }
        return sum;
    }

    /**
     * The same as {@link Tools.OpVectors#dotProduct(double[], double[])}, but with {@code Vectors} as inputs
     * @param v1 First {@code Vector}
     * @param v2 Second {@code Vector}
     * @return The dot product of the two input {@code Vectors}
     */
    public static double dotProduct(VectorBase v1, VectorBase v2) {
        if (v1.size() != v2.size()) throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(v1, v2));
        return dotProduct(v1.toDoubleArray(), v2.toDoubleArray());
    }
}
