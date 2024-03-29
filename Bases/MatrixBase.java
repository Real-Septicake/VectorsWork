package Bases;

import Matrices.Matrix11;
import Matrices.Matrix22;
import Matrices.Matrix33;
import Matrices.MatrixNM;
import Tools.ErrorMessages;
import Tools.OpMain;
import Tools.OpMatrices;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class for creating and altering double precision matrices
 * <p>
 * Is this redundant? Yes, am I doing it anyway? I have some plans that need this sort of thing, so yes.
 * </p>
 *
 * @author Septicake
 */
public abstract class MatrixBase implements Comparable<MatrixBase>, Cloneable, Iterable<VectorBase> {
    private final int COLUMN_COUNT;
    private final int ROW_COUNT;

    protected final int ROUND_DECIMALS = 14;

    /**
     * Creates an empty {@code Matrix} with a specified height and width
     * @param rows The height of the {@code Matrix}
     * @param cols The width of the {@code Matrix}
     */
    public MatrixBase(int rows, int cols) {
        this.COLUMN_COUNT = cols;
        this.ROW_COUNT = rows;
    }

    /**
     * @return The width of this {@code Matrix}
     */
    public int getCols() {
        return COLUMN_COUNT;
    }

    /**
     * @return The height of this {@code Matrix}
     */
    public int getRows() {
        return ROW_COUNT;
    }

    public int elementCount(){
        return ROW_COUNT * COLUMN_COUNT;
    }

    protected void boundsCheck(int i, int size, int offense) throws IndexOutOfBoundsException{
        switch (offense) {
            case 0 -> {
                if (i >= size || i < 0)
                    throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, i, ErrorMessages.MatrixErrors.HEIGHT_OFFENSE));
            }
            case 1 -> {
                if (i >= size || i < 0)
                    throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, i, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
            }
            default -> throw new IllegalArgumentException(ErrorMessages.unknownOffense(offense));
        }
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

    public abstract void setRow(int row, VectorBase v);

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

    public abstract VectorBase[] toVectorArray();

    public abstract double[] getRowSafe(int row) throws IndexOutOfBoundsException;

    public abstract double[] getRowUnsafe(int row);

    public VectorBase getRowVector(int row) throws IndexOutOfBoundsException {
        return VectorBase.of(getRowSafe(row));
    }

    public abstract void fillRow(int row, double val);

    public abstract double[] getColSafe(int col);

    public abstract double[] getColUnsafe(int col);

    public abstract void fillCol(int col, double val);

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

    public void addRows(int row1, int row2){
        boundsCheck(row1, getRows(), ErrorMessages.MatrixErrors.HEIGHT_OFFENSE);
        boundsCheck(row2, getRows(), ErrorMessages.MatrixErrors.HEIGHT_OFFENSE);
        for(int i = 0; i < getCols(); i++){
            setUnsafe(row2, i, OpMain.roundToDecimalCount(getUnsafe(row1, i) + getUnsafe(row2, i), ROUND_DECIMALS));
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
        if (m.getRows() != getRows() || m.getCols() != getCols()){
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixSizeMismatch(this, m, ErrorMessages.MatrixErrors.ADDITION_OFFENSE));
        }
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
        if (m.getRows() != getRows() || m.getCols() != getCols()){
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixSizeMismatch(this, m, ErrorMessages.MatrixErrors.SUBTRACTION_OFFENSE));
        }
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

    /**
     * @return The determinant of the {@code Matrix}
     */
    public abstract double determinant();

    /**
     * @param row Row to find the minor of
     * @param column Column to find the minor of
     * @return The minor of the specified indices
     */
    public abstract double minor(int row, int column);

    /**
     * @return A {@code Matrix} comprised of the minors at each position
     */
    public abstract MatrixBase minorMatrix();

    /**
     * @return A {@code Matrix} comprised of the cofactors at each position
     */
    public abstract MatrixBase cofactorMatrix();

    /**
     * @return The trace of a square {@code Matrix}
     * @throws IllegalCallerException If the calling {@code Matrix} is not square
     */
    public abstract double trace() throws IllegalCallerException;

    /**
     * @return The identity matrix of a square {@code Matrix}
     * @throws IllegalCallerException If the calling {@code Matrix} is not square
     */
    public MatrixBase getIdentityMatrix() throws IllegalCallerException {
        if(getCols() != getRows()) throw new IllegalCallerException("Matrix is not square");
        MatrixBase id = ofSize(getRows(), getCols());
        for(int i = 0; i < id.getCols(); i++){
            id.setUnsafe(i, i, 1);
        }
        return id;
    }

    /**
     * Copies the values of the input {@code Matrix} to the calling {@code Matrix}
     * @param mb The {@code Matrix} to copy the values of
     * @throws IllegalArgumentException If the {@code Matrices} are not the same size
     */
    public void copyVals(MatrixBase mb) throws IllegalArgumentException{
        if(ROW_COUNT != mb.getRows() || COLUMN_COUNT != mb.getCols()) throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixCopySizeMismatch(this, mb));
        for(int i = 0; i < ROW_COUNT; i++){
            for(int j = 0; j < COLUMN_COUNT; j++){
                setSafe(i, j, mb.getSafe(i, j));
            }
        }
    }

    /**
     * Creates a {@code Matrix} with the same values of the input {@code Matrix}
     * @param mb {@code Matrix} to copy the values from
     * @return The copy of the input {@code Matrix}
     */
    public static MatrixBase of(MatrixBase mb) {
        return of(mb.toDoubleMatrix());
    }

    /**
     * Creates a {@code Matrix} of the specified size
     * @param rows The number of rows for the returned {@code Matrix}
     * @param cols The number of columns for the returned {@code Matrix}
     * @return A {@code Matrix} of the specified size
     */
    public static MatrixBase ofSize(int rows, int cols){
        if(rows == 1 && cols == 1){
            return new Matrix11();
        }else if(rows == 2 && cols == 2){
            return new Matrix22();
        }else if(rows == 3 && cols == 3) {
            return new Matrix33();
        }else{
            return new MatrixNM(rows, cols);
        }
    }

    /**
     * Creates a {@code Matrix} with the values of the input matrix
     * @param vals The matrix to create the {@code Matrix} from
     * @return A {@code Matrix} comprised of the values of the input matrix
     * @throws IllegalArgumentException If input matrix is not uniform
     */
    public static MatrixBase of(double[]... vals) throws IllegalArgumentException{
        OpMatrices.confirmRect(vals);
        if(vals.length == 1 && vals[0].length == 1){
            return new Matrix11(vals);
        }else if(vals.length == 2 && vals[0].length == 2) {
            return new Matrix22(vals);
        }else if(vals.length == 3 && vals[0].length == 3) {
            return new Matrix33(vals);
        }else{
            return new MatrixNM(vals);
        }
    }

    /**
     * Creates a {@code Matrix} from the values from the array of {@code Vector}s
     * @param vals The array of {@code Vectors} to copy the values from
     * @return A {@code Matrix}
     */
    public static MatrixBase of(VectorBase... vals){
        double[][] copy = new double[vals.length][];
        for(int i = 0; i < vals.length; i++){
            copy[i] = vals[i].toDoubleArray();
        }
        return of(copy);
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
     * @return A double matrix with the same values as this {@code Matrix}
     */
    public abstract double[][] toDoubleMatrix();

    @Override
    public abstract MatrixBase clone();

    @Override
    public int compareTo(MatrixBase mb){
        return (int) Math.signum(OpMatrices.sum(this) - OpMatrices.sum(mb));
    }

    public String toString() {
        return getRows() + "x" + getCols() + " Matrix: " + Arrays.deepToString(toDoubleMatrix());
    }

    @Override
    public Iterator<VectorBase> iterator() {
        return new MatrixItr();
    }

    private class MatrixItr implements Iterator<VectorBase> {
        private int currPos = 0;

        protected MatrixItr(){
        }

        @Override
        public boolean hasNext() {
            return currPos < getRows();
        }

        @Override
        public VectorBase next() {
            try{
                return getRowVector(currPos++);
            }catch (IndexOutOfBoundsException e){
                throw new NoSuchElementException(e);
            }
        }
    }
}
