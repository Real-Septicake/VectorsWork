package Matrices;

import Bases.MatrixBase;
import Tools.ErrorMessages;

public class Matrix11 extends MatrixBase {
    private double value;

    public static final Matrix11 IDENTITY = new Matrix11(1);

    public Matrix11(){
        super(1, 1);
        value = 0;
    }

    public Matrix11(double val){
        this();
        value = val;
    }

    public Matrix11(double[]... vals){
        this();
        sizeCheck(vals);
        value = vals[0][0];
    }

    public Matrix11(Matrix11 source){
        this(source.toDoubleMatrix());
    }

    @Override
    public boolean setSafe(int row, int col, double val) throws IndexOutOfBoundsException {
        boundsCheck(row, col);
        value = val;
        return value == val;
    }

    @Override
    public boolean setUnsafe(int row, int col, double val) {
        value = val;
        return value == val;
    }

    @Override
    public double getSafe(int row, int col) throws IndexOutOfBoundsException {
        boundsCheck(row, col);
        return value;
    }

    @Override
    public double getUnsafe(int row, int col) {
        return value;
    }

    @Override
    public double[] getRowSafe(int row) {
        if(row != 0) throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, row, ErrorMessages.MatrixErrors.HEIGHT_OFFENSE));
        return new double[]{value};
    }

    @Override
    public double[] getRowUnsafe(int row) {
        return new double[]{value};
    }

    @Override
    public double[] getColSafe(int col) {
        if(col != 0) throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, col, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
        return new double[]{value};
    }

    @Override
    public double[] getColUnsafe(int col) {
        return new double[]{value};
    }

    @Override
    public MatrixBase getIdentityMatrix(){
        return IDENTITY;
    }

    @Override
    public double[][] toDoubleMatrix(){
        return new double[][]{{value}};
    }
}
