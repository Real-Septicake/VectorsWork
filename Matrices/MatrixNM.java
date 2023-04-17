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
        boundsCheck(row, getRows(), ErrorMessages.MatrixErrors.HEIGHT_OFFENSE);
        return data[row];
    }

    @Override
    public double[] getRowUnsafe(int row) {
        row = Math.min(row, getRows());
        return data[row];
    }

    @Override
    public void fillRow(int row, double val) {
        for(int i = 0; i < getRows(); i++){
            setUnsafe(row, i, val);
        }
    }

    @Override
    public double[] getColSafe(int col) {
        boundsCheck(col, getCols(), ErrorMessages.MatrixErrors.WIDTH_OFFENSE);
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

    @Override
    public void fillCol(int col, double val) {
        for(int i = 0; i < getCols(); i++){
            setUnsafe(i, col, val);
        }
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
