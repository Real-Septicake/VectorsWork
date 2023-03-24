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
}
