package Matrices;

import Bases.MatrixBase;

public class Matrix11 extends MatrixBase {
    private double value;

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
    public double[][] toDoubleMatrix(){
        return new double[][]{{value}};
    }
}
