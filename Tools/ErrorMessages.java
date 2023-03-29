package Tools;

import Bases.VectorBase;
import Matrices.MatrixBase;

public class ErrorMessages {
    private ErrorMessages() {
    }


    // ========================================
    // Vector Errors
    // ========================================
    public static final String NAN_INPUT = "Input value cannot be NaN";

    public static String invalidSourceArrayLength(VectorBase offended, double[] data) {
        return "Cannot create " + offended.getClass().getSimpleName() + " from array of length " + data.length;
    }

    public static String sourceVectorTypeMismatch(VectorBase offended, VectorBase offender) {
        return "Cannot create " + offended.getClass().getSimpleName() + " from " + offender.getClass().getSimpleName();
    }

    public static String indexOutOfBounds(VectorBase offended, int offender) {
        return "Index " + offender + " out of bounds for Vector of size " + offended.size();
    }

    public static String vectorSizeMismatch(VectorBase v1, VectorBase v2){
        return "Vector of length " + v1.size() + " is not compatible with Vector of length " + v2.size();
    }

    public static String arraySizeMismatch(double[] a1, double[] a2){
        return "Array of length " + a1.length + " is not compatible with Array of length " + a2.length;
    }

    public static String vectorArraySizeMismatch(VectorBase v, double[] a){
        return "Vector of length " + v.size() + " is not compatible with Array of length " + a.length;
    }

    // ========================================
    // Matrix Errors
    // ========================================
    public static String NON_UNIFORM_INPUT = "Input is not uniform";

    public static String matrixMultiplySizeMismatch(MatrixBase m1, MatrixBase m2){
        return "Matrix of height " + m1.getRows() + " cannot be multiplied by Matrix of width " + m2.getCols();
    }
}