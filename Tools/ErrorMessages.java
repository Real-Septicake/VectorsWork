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

    public static final String IMPOSSIBLE_ERROR = "This should never happen! If it does, post an issue on the Github with how it was caused.";

    public static String unknownOffense(int i){
        return "No offense with value " + i;
    }

    public static String unknownOperation(int i){
        return "No operation with value " + i;
    }

    public static class VectorErrors {
        public static final String ILLEGAL_0D_METHOD_CALL = "Method cannot be called on Vector0D";

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
            return "Vector of size " + v1.size() + " is not compatible with Vector of size " + v2.size();
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

        public static String indexOutOfBounds(MatrixBase offended, int offender, int offense){
            return switch(offense) {
                case HEIGHT_OFFENSE -> "Index " + offender + " is out of bounds for Matrix of height " + offended.getRows();
                case WIDTH_OFFENSE ->  "Index " + offender + " is out of bounds for Matrix of width " + offended.getCols();
                default -> throw new IllegalArgumentException(unknownOffense(offense));
            };
        }

        public static String indexOutOfBounds(int offended, int offender, int offense){
            return switch(offense) {
                case HEIGHT_OFFENSE -> "Index " + offender + " is out of bounds for Matrix of height " + offended;
                case WIDTH_OFFENSE -> "Index " + offender + " is out of bounds for Matrix of width " + offended;
                default -> throw new IllegalArgumentException(unknownOffense(offense));
            };
        }

        public static String matrixSizeMismatch(MatrixBase offended, MatrixBase offender, int offense, int operation){
            return switch(offense) {
                case HEIGHT_OFFENSE -> switch(operation) {
                    case ADDITION_OFFENSE -> "Matrix of height " + offender.getRows() + " cannot be added to Matrix of height " + offended.getRows();
                    case SUBTRACTION_OFFENSE -> "Matrix of height " + offender.getRows() + " cannot be subtracted from Matrix of height " + offended.getRows();
                    default -> throw new IllegalArgumentException(unknownOperation(operation));
                };
                case WIDTH_OFFENSE -> switch(operation) {
                    case ADDITION_OFFENSE -> "Matrix of width " + offender.getCols() + " cannot be added to Matrix of width " + offended.getCols();
                    case SUBTRACTION_OFFENSE -> "Matrix of width " + offender.getCols() + " cannot be subtracted from Matrix of width " + offended.getCols();
                    default -> throw new IllegalArgumentException(unknownOperation(operation));
                };
                default -> throw new IllegalArgumentException(unknownOffense(offense));
            };
        }

        public static String sourceMatrixSizeMismatch(MatrixBase offended, double[][] offender, int offense){
            return switch(offense){
                case HEIGHT_OFFENSE ->  "Matrix of height " + offended.getRows() + " cannot be created from matrix of height " + offender.length;
                case WIDTH_OFFENSE ->  "Matrix of width " + offended.getCols() + " cannot be created from matrix of width " + offender[0].length;
                default -> throw new IllegalArgumentException(unknownOffense(offense));
            };
        }

        public static String vectorSizeMismatch(MatrixBase offended, VectorBase offender){
            return "Vector of length " + offender.size() + " cannot be copied to Matrix of width " + offended.getCols();
        }

        public static String matrixCopySizeMismatch(MatrixBase offended, MatrixBase offender){
            return "Matrix of size " + offender.getRows() + "x" + offender.getCols() + " cannot be copied to Matrix of size " + offended.getRows() + "x" + offended.getCols();
        }
    }

    public static class AugMatErrors{
        public static String matricesSizeMismatch(MatrixBase m1, MatrixBase m2){
            return "Cannot create Augmented Matrix from matrices of heights " + m1.getRows() + " and " + m2.getRows();
        }

        public static String arraySizeMismatch(int a, int length){
            return "Row of length " + length + " not compatible with array of length " + a;
        }
    }
}