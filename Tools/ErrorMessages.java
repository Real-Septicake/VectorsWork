package Tools;

import Bases.VectorBase;
import Bases.MatrixBase;

/**
 * A class used for holding and creating error messages
 *
 * @author Septicake
 */
//TODO: Condense this, takes too long to call any method or value
public class ErrorMessages {
    private ErrorMessages() {
    }

    public static class VectorErrors {
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

        public static String vectorSizeMismatch(VectorBase v1, VectorBase v2) {
            return "Vector of length " + v1.size() + " is not compatible with Vector of length " + v2.size();
        }

        public static String arraySizeMismatch(double[] a1, double[] a2) {
            return "Array of length " + a1.length + " is not compatible with Array of length " + a2.length;
        }
    }

    public static class MatrixErrors {

        public static final int HEIGHT_OFFENSE = 0;

        public static final int WIDTH_OFFENSE = 1;

        public static final int ADDITION_OFFENSE = 0;

        public static final int SUBTRACTION_OFFENSE = 1;

        public static String NON_UNIFORM_INPUT = "Input is not uniform";

        public static String matrixMultiplySizeMismatch(MatrixBase m1, MatrixBase m2) {
            return "Matrix of width " + m1.getCols() + " cannot be multiplied by Matrix of height " + m2.getRows();
        }

        public static String indexOutOfBounds(MatrixBase m, int offender, int offense){
            if(offense == HEIGHT_OFFENSE){
                return "Index " + offender + " is out of bounds for Matrix of height " + m.getRows();
            }else{
                return "Index " + offender + " is out of bounds for Matrix of width " + m.getCols();
            }
        }

        public static String matrixSizeMismatch(MatrixBase offended, MatrixBase offender, int offense, int operation){
            if(offense == HEIGHT_OFFENSE){
                if(operation == ADDITION_OFFENSE){
                    return "Matrix of height " + offender.getRows() + " cannot be added to Matrix of height " + offended.getRows();
                }else{
                    return "Matrix of height " + offender.getRows() + " cannot be subtracted from Matrix of height " + offended.getRows();
                }
            }else{
                if(operation == ADDITION_OFFENSE){
                    return "Matrix of width " + offender.getCols() + " cannot be added to Matrix of width " + offended.getCols();
                }else{
                    return "Matrix of width " + offender.getCols() + " cannot be subtracted from Matrix of width " + offended.getCols();
                }
            }
        }
    }
}