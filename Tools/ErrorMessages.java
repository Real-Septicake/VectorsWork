package Tools;

import Bases.VectorBase;

public class ErrorMessages {
    private ErrorMessages() {
    }

    public static final String NAN_INPUT = "Input value cannot be NaN";

    public static String invalidSourceArrayLength(double[] data, VectorBase source) {
        return "Cannot create " + source.getClass().getSimpleName() + " from array of length " + data.length;
    }

    public static String sourceVectorTypeMismatch(VectorBase offended, VectorBase offender) {
        return "Cannot create " + offended.getClass().getSimpleName() + " from " + offender.getClass().getSimpleName();
    }

    public static String indexOutOfBounds(VectorBase offended, int offender) {
        return "Index " + offender + " out of bounds for Vector of size " + offended.size();
    }

    public static String vectorSizeMismatch(VectorBase v1, VectorBase v2){
        return "Vector of length " + v1.size() + " cannot be compared to Vector of length " + v2.size();
    }

    public static String arraySizeMismatch(double[] a1, double[] a2){
        return "Array of length " + a1.length + " cannot be compared to Array of length " + a2.length;
    }
}