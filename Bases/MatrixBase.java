package Bases;

import Matrices.Matrix11;
import Matrices.Matrix22;
import Matrices.MatrixNM;
import Tools.ErrorMessages;
import Tools.OpMatrices;

import java.util.Arrays;

/**
 * A class for creating and altering double precision matrices
 * <p>
 * Is this redundant? Yes, am I doing it anyway? I have some plans that need this sort of thing, so yes.
 * </p>
 *
 * @author Septicake
 */
public abstract class MatrixBase implements Comparable<MatrixBase> {
    private final int COLS;
    private final int ROWS;

    protected final int ROUND_DECIMALS = 14;

    /**
     * Creates an empty {@code Matrix} with a specified height and width
     * @param rows The height of the {@code Matrix}
     * @param cols The width of the {@code Matrix}
     */
    public MatrixBase(int rows, int cols) {
        this.COLS = cols;
        this.ROWS = rows;
    }

    /**
     * @return The width of this {@code Matrix}
     */
    public int getCols() {
        return COLS;
    }

    /**
     * @return The height of this {@code Matrix}
     */
    public int getRows() {
        return ROWS;
    }

    /**
     * Sets the value at the intersection of the specified row and column
     * @param row Row of the desired position
     * @param col Column of the desired position
     * @param val Value to set the index to
     * @return If the index was successfully set
     * @throws IndexOutOfBoundsException If the specified row or column is outside this {@code Matrix}'s height or width
     */
    public abstract boolean setSafe(int row, int col, double val) throws IndexOutOfBoundsException;

    /**
     * Similar to {@link Bases.MatrixBase#setSafe(int, int, double)}, but if a specified value is outside the bounds of this {@code Matrix}, the last index is changed instead
     * @param row Row of the desired position
     * @param col Column of the desired position
     * @param val Value to set the index to
     * @return If the index was successfully set
     */
    public abstract boolean setUnsafe(int row, int col, double val);

    /**
     * Gets the value at the intersection of the specified row and column
     * @param row Row of the desired value
     * @param col Column of the desired value
     * @return The value at the specified index
     * @throws IndexOutOfBoundsException If the specified row or column is outside this {@code Matrix}'s height or width
     */
    public abstract double getSafe(int row, int col) throws IndexOutOfBoundsException;

    /**
     * Similar to {@link Bases.MatrixBase#getSafe(int, int)}, but if a specified value is outside the bounds of this {@code Matrix}, the last index is selected instead
     * @param row Row of the desired value
     * @param col Column of the desired value
     * @return The value at the specified index
     */
    public abstract double getUnsafe(int row, int col);

    public abstract double[] getRowSafe(int row);

    public abstract double[] getRowUnsafe(int row);

    public abstract double[] getColSafe(int col);

    public abstract double[] getColUnsafe(int col);

    /**
     * Adds the input value to every index of this {@code Matrix}
     * @param val The value to add to the indices
     */
    public void add(double val) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                setSafe(i, j, OpMatrices.roundToDecimalCount(getSafe(i, j) + val, ROUND_DECIMALS));
            }
        }
    }

    /**
     * Similar to {@link Bases.MatrixBase#add(double)}, but returns a copy of this {@code Matrix}, leaving the original unchanged
     * @param val The value to add to the indices
     * @return The copy of this {@code Matrix} with the input value added to every index
     */
    public MatrixBase addCopy(double val) {
        MatrixBase copy = of(this);
        copy.add(val);
        return copy;
    }

    /**
     * Adds the input value to the intersection of the specified row and column
     * @param row Row of the desired position
     * @param col Column of the desired position
     * @param val Value to add to the specified index
     * @throws IndexOutOfBoundsException If the specified row or column is outside this {@code Matrix}'s height or width
     */
    public void addAtSafe(int row, int col, double val) throws IndexOutOfBoundsException {
        setSafe(row, col, OpMatrices.roundToDecimalCount(getSafe(row, col) + val, ROUND_DECIMALS));
    }

    /**
     * Similar to {@link Bases.MatrixBase#addAtSafe(int, int, double)}, but if a specified value is outside the bounds of this {@code Matrix}, the last index is changed instead
     * @param row Row of the desired position
     * @param col Column of the desired position
     * @param val Value to add to the specified index
     */
    public void addAtUnsafe(int row, int col, double val) {
        setUnsafe(row, col, OpMatrices.roundToDecimalCount(getUnsafe(row, col) + val, ROUND_DECIMALS));
    }

    /**
     * Adds the values of a {@code Matrix} of the same size to the corresponding values in this {@code Matrix}
     * @param m {@code Matrix} to add to this {@code Matrix}
     * @throws IllegalArgumentException If the input {@code Matrix} is a different size than this {@code Matrix}
     */
    public void matrixAdd(MatrixBase m) throws IllegalArgumentException{
        if (m.getRows() != getRows())
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixSizeMismatch(
                    this,
                    m,
                    ErrorMessages.MatrixErrors.HEIGHT_OFFENSE,
                    ErrorMessages.MatrixErrors.ADDITION_OFFENSE));
        if(m.getCols() != getCols())
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixSizeMismatch(
                    this,
                    m,
                    ErrorMessages.MatrixErrors.WIDTH_OFFENSE,
                    ErrorMessages.MatrixErrors.ADDITION_OFFENSE));
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                addAtSafe(i, j, m.getSafe(i, j));
            }
        }
    }

    /**
     * Similar to {@link Bases.MatrixBase#matrixAdd(MatrixBase)}, but returns a copy of this {@code Matrix}, leaving the original unchanged
     * @param m {@code Matrix} to add to this {@code Matrix}
     * @return A copy of this {@code Matrix} with the values of the input {@code Matrix} added to the corresponding values of this {@code Matrix}
     * @throws IllegalArgumentException If the input {@code Matrix} is a different size than this {@code Matrix}
     */
    public MatrixBase matrixAddCopy(MatrixBase m) throws IllegalArgumentException{
        MatrixBase copy = of(this);
        copy.matrixAdd(m);
        return copy;
    }

    /**
     * Subtracts the input value form every index of this {@code Matrix}
     * @param val The value to subtract from the indices
     */
    public void subtract(double val) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                setSafe(i, j, OpMatrices.roundToDecimalCount(getSafe(i, j) - val, ROUND_DECIMALS));
            }
        }
    }

    /**
     * Similar to {@link Bases.MatrixBase#subtract(double)}, but returns a copy of this {@code Matrix}, leaving the original unchanged
     * @param val The value to subtract from the indices
     * @return The copy of this {@code Matrix} with the input value subtracted from every index
     */
    public MatrixBase subtractCopy(double val) {
        MatrixBase copy = of(this);
        copy.subtract(val);
        return copy;
    }

    /**
     * Subtracts the input value from the intersection of the specified row and column
     * @param row Row of the desired position
     * @param col Column of the desired position
     * @param val Value to subtract from the specified index
     * @throws IndexOutOfBoundsException If the specified row or column is outside this {@code Matrix}'s height or width
     */
    public void subtractAtSafe(int row, int col, double val) {
        setSafe(row, col, OpMatrices.roundToDecimalCount(getSafe(row, col) - val, ROUND_DECIMALS));
    }

    /**
     * Similar to {@link Bases.MatrixBase#subtractAtSafe(int, int, double)}, but if a specified value is outside the bounds of this {@code Matrix}, the last index is changed instead
     * @param row Row of the desired position
     * @param col Column of the desired position
     * @param val Value to subtract from the specified index
     */
    public void subtractAtUnsafe(int row, int col, double val) {
        setUnsafe(row, col, OpMatrices.roundToDecimalCount(getUnsafe(row, col) - val, ROUND_DECIMALS));
    }

    /**
     * Subtracts the values of a {@code Matrix} of the same size from the corresponding values in this {@code Matrix}
     * @param m {@code Matrix} to subtract from this {@code Matrix}
     * @throws IllegalArgumentException If the input {@code Matrix} is a different size than this {@code Matrix}
     */
    public void matrixSubtract(MatrixBase m){
        if (m.getRows() != getRows())
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixSizeMismatch(
                    this,
                    m,
                    ErrorMessages.MatrixErrors.HEIGHT_OFFENSE,
                    ErrorMessages.MatrixErrors.SUBTRACTION_OFFENSE));
        if(m.getCols() != getCols())
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixSizeMismatch(
                    this,
                    m,
                    ErrorMessages.MatrixErrors.WIDTH_OFFENSE,
                    ErrorMessages.MatrixErrors.SUBTRACTION_OFFENSE));
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                subtractAtSafe(i, j, m.getSafe(i, j));
            }
        }
    }

    /**
     * Similar to {@link Bases.MatrixBase#matrixSubtract(MatrixBase)}, but returns a copy of this {@code Matrix}, leaving the original unchanged
     * @param m {@code Matrix} to subtract from this {@code Matrix}
     * @return A copy of this {@code Matrix} with the values of the input {@code Matrix} subtracted from the corresponding values of this {@code Matrix}
     * @throws IllegalArgumentException If the input {@code Matrix} is a different size than this {@code Matrix}
     */
    public MatrixBase matrixSubtractCopy(MatrixBase m){
        MatrixBase copy = of(this);
        copy.matrixSubtract(m);
        return copy;
    }

    /**
     * Multiplies every index of this {@code Matrix} by the input value
     * @param val The value to multiply the indices by
     */
    public void multiply(double val){
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                setSafe(i, j, getSafe(i, j) * val);
            }
        }
    }

    /**
     * Similar to {@link Bases.MatrixBase#multiply(double)}, but returns a copy of this {@code Matrix}, leaving the original unchanged
     * @param val The value to multiply the indices by
     * @return The copy of this {@code Matrix} with the every index multiplied by the input value
     */
    public MatrixBase multiplyCopy(double val){
        MatrixBase copy = of(this);
        copy.multiply(val);
        return copy;
    }

    /**
     * Multiplies the intersection of the specified row and column by the input value
     * @param row Row of the desired position
     * @param col Column of the desired position
     * @param val Value to multiply the specified index by
     * @throws IndexOutOfBoundsException If the specified row or column is outside this {@code Matrix}'s height or width
     */
    public void multiplyAtSafe(int row, int col, double val){
        setSafe(row, col, getSafe(row, col) * val);
    }

    /**
     * Similar to {@link Bases.MatrixBase#multiplyAtSafe(int, int, double)}, but if a specified value is outside the bounds of this {@code Matrix}, the last index is changed instead
     * @param row Row of the desired position
     * @param col Column of the desired position
     * @param val Value to multiply the specified index by
     */
    public void multiplyAtUnsafe(int row, int col, double val){
        setUnsafe(row, col, getUnsafe(row, col) * val);
    }

    /**
     * Divides every index of this {@code Matrix} by the input value
     * @param val The value to divide the indices by
     */
    public void divide(double val){
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                setSafe(i, j, getSafe(i, j) / val);
            }
        }
    }

    /**
     * Similar to {@link Bases.MatrixBase#divide(double)}, but returns a copy of this {@code Matrix}, leaving the original unchanged
     * @param val The value to divide the indices by
     * @return The copy of this {@code Matrix} with the every index divided by the input value
     */
    public MatrixBase divideCopy(double val){
        MatrixBase copy = of(this);
        copy.divide(val);
        return copy;
    }

    /**
     * Divides the intersection of the specified row and column by the input value
     * @param row Row of the desired position
     * @param col Column of the desired position
     * @param val Value to divide the specified index by
     * @throws IndexOutOfBoundsException If the specified row or column is outside this {@code Matrix}'s height or width
     */
    public void divideAtSafe(int row, int col, double val){
        setSafe(row, col, getSafe(row, col) / val);
    }

    /**
     * Similar to {@link Bases.MatrixBase#divideAtSafe(int, int, double)}, but if a specified value is outside the bounds of this {@code Matrix}, the last index is changed instead
     * @param row Row of the desired position
     * @param col Column of the desired position
     * @param val Value to divide the specified index by
     */
    public void divideAtUnsafe(int row, int col, double val){
        setUnsafe(row, col, getUnsafe(row, col) / val);
    }

    public MatrixBase getIdentityMatrix() throws IllegalCallerException {
        if(getCols() != getRows()) throw new IllegalCallerException("Matrix is not square");
        MatrixBase id = of(getRows(), getCols());
        for(int i = 0; i < id.getCols(); i++){
            id.setUnsafe(i, i, 1);
        }
        return id;
    }

    /**
     * Creates a {@code Matrix} with the same values of the input {@code Matrix}
     * @param mb {@code Matrix} to copy the values from
     * @return The copy of the input {@code Matrix}
     */
    public static MatrixBase of(MatrixBase mb) {
        return of(mb.toDoubleMatrix());
    }

    public static MatrixBase of(int rows, int cols){
        if(rows == 1 && cols == 1){
            return new Matrix11();
        }else if(rows == 2 && cols == 2){
            return new Matrix22();
        }else{
            return new MatrixNM(rows, cols);
        }
    }

    public static MatrixBase of(double[]... vals){
        OpMatrices.confirmRect(vals);
        if(vals.length == 1 && vals[0].length == 1){
            return new Matrix11(vals);
        }else if(vals.length == 2 && vals[0].length == 2) {
            return new Matrix22(vals);
        }else{
            return new MatrixNM(vals);
        }
    }

    protected void boundsCheck(int row, int col) {
        if (row >= getRows()) {
            throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, row, ErrorMessages.MatrixErrors.HEIGHT_OFFENSE));
        } else if (col >= getCols()) {
            throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, col, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
        }
    }

    protected void sizeCheck(double[][] test){
        if(test.length > getRows())
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.sourceMatrixSizeMismatch(this, test, ErrorMessages.MatrixErrors.HEIGHT_OFFENSE));
        if(test[0].length > getCols())
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.sourceMatrixSizeMismatch(this, test, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
    }

    /**
     * Returns a matrix with the same values as this {@code Matrix}
     * @return a double matrix with the same values as this {@code Matrix}
     */
    public abstract double[][] toDoubleMatrix();

    @Override
    public int compareTo(MatrixBase mb){
        return (int) Math.signum(OpMatrices.sum(this) - OpMatrices.sum(mb));
    }

    public String toString() {
        return getRows() + "x" + getCols() + " Matrix: " + Arrays.deepToString(toDoubleMatrix());
    }
}
