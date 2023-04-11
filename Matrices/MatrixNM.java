package Matrices;

import Bases.MatrixBase;
import Tools.ErrorMessages;
import Tools.OpMatrices;

public class MatrixNM extends MatrixBase {
    private double[][] data;

    public MatrixNM(int rows, int cols){
        super(rows, cols);
        data = new double[rows][cols];
    }

    public MatrixNM(double[]... vals){
        super(vals.length, vals[0].length);
        OpMatrices.confirmRect(vals);
        data = new double[vals.length][];
        for(int i = 0; i < vals.length; i++){
            data[i] = vals[i].clone();
        }
    }

    public MatrixNM(MatrixNM source){
        super(source.getRows(), source.getCols());
        data = source.toDoubleMatrix();
    }

    public boolean setSafe(int row, int col, double val) throws IndexOutOfBoundsException {
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

    public double getSafe(int row, int col) throws IndexOutOfBoundsException {
        boundsCheck(row, col);
        return data[row][col];
    }

    public double getUnsafe(int row, int col) {
        row = Math.min(row, getRows());
        col = Math.min(col, getCols());
        return data[row][col];
    }

    @Override
    public double[] getRowSafe(int row) {
        if(row >= getRows() || row < getRows()) throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, row, ErrorMessages.MatrixErrors.HEIGHT_OFFENSE));
        return data[row];
    }

    @Override
    public double[] getRowUnsafe(int row) {
        row = Math.min(row, getRows());
        return data[row];
    }

    @Override
    public double[] getColSafe(int col) {
        if(col >= getCols() || col < getCols()) throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, col, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
        double[] vals = new double[getCols()];
        for(int i = 0; i < getCols(); i++){
            vals[i] = data[i][col];
        }
        return vals;
    }

    @Override
    public double[] getColUnsafe(int col) {
        col = Math.min(col, getCols());
        return getColSafe(col);
    }

    public double[][] toDoubleMatrix() {
        int size = data.length;
        double[][] clone = new double[size][];
        for (int i = 0; i < size; i++) {
            clone[i] = data[i].clone();
        }
        return clone;
    }
}
