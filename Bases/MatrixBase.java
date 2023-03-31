package Bases;

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
public class MatrixBase {
    protected double[][] data;
    private final int COLS;
    private final int ROWS;

    private final int ROUND_DECIMALS = 14;

    public MatrixBase(int rows, int cols) {
        data = new double[rows][cols];
        this.COLS = cols;
        this.ROWS = rows;
    }

    public MatrixBase(double[]... values) {
        OpMatrices.confirmRect(values);
        data = new double[values.length][];
        ROWS = values.length;
        COLS = values[0].length;
        for (int i = 0; i < values.length; i++) {
            data[i] = new double[values[0].length];
            System.arraycopy(values[i], 0, data[i], 0, values[i].length);
        }
    }

    public int getCols() {
        return COLS;
    }

    public int getRows() {
        return ROWS;
    }

    public boolean setSafe(int row, int col, double val) {
        boundsCheck(row, col);
        data[row][col] = val;
        return data[row][col] == val;
    }

    public boolean setUnsafe(int row, int col, double val) {
        row = Math.min(row, getRows());
        col = Math.min(col, getCols());
        data[row][col] = val;
        return data[row][col] == val;
    }

    public double getSafe(int row, int col) {
        boundsCheck(row, col);
        return data[row][col];
    }

    public double getUnsafe(int row, int col) {
        row = Math.min(row, getRows());
        col = Math.min(col, getCols());
        return data[row][col];
    }

    public void add(double val) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                data[i][j] = OpMatrices.roundToDecimalCount(data[i][j] + val, ROUND_DECIMALS);
            }
        }
    }

    public MatrixBase addCopy(double val) {
        MatrixBase copy = of(this);
        copy.add(val);
        return copy;
    }

    public void addAtSafe(int row, int col, double val) {
        setSafe(row, col, OpMatrices.roundToDecimalCount(getSafe(row, col) + val, ROUND_DECIMALS));
    }

    public void addAtUnsafe(int row, int col, double val) {
        setUnsafe(row, col, OpMatrices.roundToDecimalCount(getUnsafe(row, col) + val, ROUND_DECIMALS));
    }

    public void matrixAdd(MatrixBase m) {
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

    public MatrixBase matrixAddCopy(MatrixBase m){
        MatrixBase copy = of(this);
        copy.matrixAdd(m);
        return copy;
    }

    public void subtract(double val) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                data[i][j] = OpMatrices.roundToDecimalCount(data[i][j] - val, ROUND_DECIMALS);
            }
        }
    }

    public MatrixBase subtractCopy(double val) {
        MatrixBase copy = of(this);
        copy.subtract(val);
        return copy;
    }

    public void subtractAtSafe(int row, int col, double val) {
        setSafe(row, col, OpMatrices.roundToDecimalCount(getSafe(row, col) - val, ROUND_DECIMALS));
    }

    public void subtractAtUnsafe(int row, int col, double val) {
        setUnsafe(row, col, OpMatrices.roundToDecimalCount(getUnsafe(row, col) - val, ROUND_DECIMALS));
    }

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

    public MatrixBase matrixSubtractCopy(MatrixBase m){
        MatrixBase copy = of(this);
        copy.matrixSubtract(m);
        return copy;
    }

    public static MatrixBase of(MatrixBase mb) {
        return new MatrixBase(mb.toDoubleMatrix());
    }

    private void boundsCheck(int row, int col) {
        if (row >= getRows()) {
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, row, ErrorMessages.MatrixErrors.HEIGHT_OFFENSE));
        } else if (col >= getCols()) {
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, col, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
        }
    }

    public double[][] toDoubleMatrix() {
        double[][] clone = new double[data.length][];
        for (int i = 0; i < data.length; i++) {
            clone[i] = data[i].clone();
        }
        return clone;
    }

    public String toString() {
        return getRows() + "x" + getCols() + " Matrix: " + Arrays.deepToString(data);
    }
}
