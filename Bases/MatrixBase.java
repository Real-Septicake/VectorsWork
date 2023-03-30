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
    private final int cols;
    private final int rows;

    public MatrixBase(int rows, int cols) {
        data = new double[rows][cols];
        this.cols = cols;
        this.rows = rows;
    }

    public MatrixBase(double[]... values) {
        uniformCheck(values);
        data = new double[values.length][];
        rows = values.length;
        cols = values[0].length;
        for (int i = 0; i < values.length; i++) {
            data[i] = new double[values[0].length];
            System.arraycopy(values[i], 0, data[i], 0, values[i].length);
        }
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public boolean setSafe(int row, int col, double val) {
        boundsCheck(row, col);
        data[row][col] = val;
        return data[row][col] == val;
    }

    public boolean setUnsafe(int row, int col, double val){
        row = Math.min(row, getRows());
        col = Math.min(col, getCols());
        data[row][col] = val;
        return data[row][col] == val;
    }

    public double getSafe(int row, int col){
        boundsCheck(row, col);
        return data[row][col];
    }

    public double getUnsafe(int row, int col){
        row = Math.min(row, getRows());
        col = Math.min(col, getCols());
        return data[row][col];
    }

    public void add(double val){
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                data[i][j] = OpMatrices.roundToDecimalCount(data[i][j] + val, 15);
            }
        }
    }

    public MatrixBase addCopy(double val){
        MatrixBase copy = MatrixBase.of(this);
        copy.add(val);
        return copy;
    }

    public void subtract(double val){
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                data[i][j] = OpMatrices.roundToDecimalCount(data[i][j] - val, 15);
            }
        }
    }

    public MatrixBase subtractCopy(double val){
        MatrixBase copy = MatrixBase.of(this);
        copy.subtract(val);
        return copy;
    }

    public static MatrixBase of(MatrixBase mb) {
        return new MatrixBase(mb.toDoubleMatrix());
    }

    private void uniformCheck(double[]... vals) {
        if (!OpMatrices.confirmRect(vals))
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.NON_UNIFORM_INPUT);
    }

    private void boundsCheck(int row, int col) {
        if (row >= getRows()) {
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, row, ErrorMessages.MatrixErrors.HEIGHT_OFFENSE));
        }else if (col >= getCols()){
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, col, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
        }
    }

    public double[][] toDoubleMatrix() {
        double[][] clone = new double[data.length][];
        for(int i = 0; i < data.length; i++){
            clone[i] = data[i].clone();
        }
        return clone;
    }

    public String toString() {
        return getRows() + "x" + getCols() + " Matrix: " + Arrays.deepToString(data);
    }
}
