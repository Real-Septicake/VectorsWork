package Tools;

import Bases.VectorBase;

public class ErrorMessages {
    private ErrorMessages(){}

    public static String invalidSourceArrayLength(double[] data, VectorBase source){
        return "Cannot create " + source.getClass().getSimpleName() + " from array of length " + data.length;
    }

    public static String sourceVectorTypeMismatch(VectorBase offended, VectorBase offender){
        return "Cannot create " + offended.getClass().getSimpleName() + " from " + offender.getClass().getSimpleName();
    }
}
