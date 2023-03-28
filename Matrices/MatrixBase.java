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

    private MatrixBase(){}

    public MatrixBase(int xSize, int ySize){
        data = new double[xSize][ySize];
    }

    public MatrixBase(double[]... values){
        if(!OpMatrices.confirmRect(values)) throw new IllegalArgumentException(ErrorMessages.NON_UNIFORM_INPUT);
        data = new double[values.length][];
        for(int i = 0; i < values.length; i++){
            data[i] = new double[values[0].length];
            System.arraycopy(values[i], 0, data[i], 0, values[i].length);
        }
    }

    public MatrixBase of(MatrixBase mb){
        return new MatrixBase(mb.toDoubleMatrix());
    }

    public double[][] toDoubleMatrix(){
        return data;
    }

    public String toString(){
        return Arrays.deepToString(data);
    }
}
