package Matrices;

import Tools.*;

import java.util.Arrays;

/**
 * A class for creating and altering double matrices
 * <p>
 *     Is this redundant? Yes, am I doing it anyway? I have some plans that need this sort of thing, so yes.
 * </p>
 *
 * @author Septicake
 */
public class MatrixBase {
    protected double[][] data;
    private final int cols;
    private final int rows;

    private MatrixBase(){
        cols = 0;
        rows = 0;
        data = new double[rows][cols];
    }

    public MatrixBase(int rows, int cols){
        data = new double[rows][cols];
        this.cols = cols;
        this.rows = rows;
    }

    public MatrixBase(double[]... values){
        if(!OpMatrices.confirmRect(values)) throw new IllegalArgumentException(ErrorMessages.NON_UNIFORM_INPUT);
        data = new double[values.length][];
        rows = values.length;
        cols = values[0].length;
        for(int i = 0; i < values.length; i++){
            data[i] = new double[values[0].length];
            System.arraycopy(values[i], 0, data[i], 0, values[i].length);
        }
    }

    public int getCols(){
        return cols;
    }

    public int getRows(){
        return rows;
    }

    public MatrixBase of(MatrixBase mb){
        return new MatrixBase(mb.toDoubleMatrix());
    }

    public double[][] toDoubleMatrix(){
        return data;
    }

    public String toString(){
        return rows + "x" + cols + " Matrix: " + Arrays.deepToString(data);
    }
}
