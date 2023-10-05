package Matrices;

import Bases.MatrixBase;
import Bases.VectorBase;
import Tools.ErrorMessages;
import Tools.OpMatrices;

public class MatrixNM extends MatrixBase {
    private final VectorBase[] data;

    public MatrixNM(int rows, int cols){
        super(rows, cols);
        data = new VectorBase[rows];
        for(int i = 0; i < rows; i++){
            data[i] = VectorBase.ofLength(cols);
        }
    }

    public MatrixNM(double[]... vals){
        super(vals.length, vals[0].length);
        OpMatrices.confirmRect(vals);
        data = new VectorBase[vals.length];
        for(int i = 0; i < vals.length; i++){
            data[i] = VectorBase.of(vals[i]);
        }
    }

    public MatrixNM(MatrixNM source){
        this(source.toDoubleMatrix());
    }

    public boolean setSafe(int row, int col, double val) throws IndexOutOfBoundsException {
        boundsCheck(row, col);
        return data[row].set(col, val);
    }

    public boolean setUnsafe(int row, int col, double val) {
        row = Math.min(row, getRows());
        col = Math.min(col, getCols());
        return data[row].set(col, val);
    }

    @Override
    public void setRow(int row, VectorBase v) {
        boundsCheck(row, getRows(), ErrorMessages.MatrixErrors.HEIGHT_OFFENSE);
        if(v.size() == getCols()){
            for(int i = 0; i < v.size(); i++){
                setUnsafe(row, i, v.getUnsafe(i));
            }
        }else{
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.vectorSizeMismatch(this, v));
        }
    }

    public double getSafe(int row, int col) throws IndexOutOfBoundsException {
        boundsCheck(row, col);
        return data[row].get(col);
    }

    public double getUnsafe(int row, int col) {
        row = Math.min(row, getRows());
        col = Math.min(col, getCols());
        return data[row].get(col);
    }

    @Override
    public VectorBase[] toVectorArray() {
        VectorBase[] clone = new VectorBase[getRows()];
        for(int i = 0; i < getRows(); i++){
            clone[i] = data[i].clone();
        }
        return clone;
    }

    @Override
    public double[] getRowSafe(int row) {
        boundsCheck(row, getRows(), ErrorMessages.MatrixErrors.HEIGHT_OFFENSE);
        return data[row].toDoubleArray();
    }

    @Override
    public double[] getRowUnsafe(int row) {
        row = Math.min(row, getRows());
        return data[row].toDoubleArray();
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
        double[] vals = new double[getRows()];
        for(int i = 0; i < getRows(); i++){
            vals[i] = data[i].get(col);
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

    @Override
    public void add(double val) {
        for(int i = 0; i < getRows(); i++){
            data[i].add(val);
        }
    }

    @Override
    public void addRows(int row1, int row2){
        data[row2].add(data[row1]);
    }

    @Override
    public void subtract(double val) {
       for(int i = 0; i < getRows(); i++){
           data[i].subtract(val);
       }
    }

    @Override
    public void multiply(double val) {
        for(int i = 0; i < getRows(); i++){
            data[i].multiply(val);
        }
    }

    @Override
    public void divide(double val) {
        for(int i = 0; i < getRows(); i++){
            data[i].divide(val);
        }
    }

    public double minor(int row, int column){
        return MatrixBase.of(isolateForMinor(row, column)).determinant();
    }

    @Override
    public MatrixBase minorMatrix() {
        double[][] minor = new double[getRows()][getCols()];
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                minor[i][j] = minor(i, j);
            }
        }
        return MatrixBase.of(minor);
    }

    @Override
    public MatrixBase cofactorMatrix() {
        double[][] cofactor = new double[getRows()][getCols()];
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                cofactor[i][j] = Math.pow(-1, i+j) * minor(i, j);
            }
        }
        return MatrixBase.of(cofactor);
    }

    @Override
    public double determinant() {
        if(!(getCols() == getRows())){
            throw new IllegalCallerException("Non-square matrix cannot have determinant");
        }
        double det = 0;
        for(int i = 0; i < getCols(); i++){
            det += Math.pow(-1, i) * getSafe(0, i) * MatrixBase.of(isolateForMinor(0, i)).determinant();
        }
        return det;
    }

    public double[][] toDoubleMatrix() {
        int size = data.length;
        double[][] clone = new double[size][];
        for (int i = 0; i < size; i++) {
            clone[i] = data[i].toDoubleArray();
        }
        return clone;
    }

    private double[][] isolateForMinor(int row, int column){
        double[][] mat = new double[getRows()-1][getCols()-1];
        int rowOffset = 0;
        int columnOffset;
        for(int i = 0; i < getRows(); i++){
            columnOffset = 0;
            if(i == row){
                rowOffset = -1;
                continue;
            }
            for(int j = 0; j < getCols(); j++){
                if(j == column){
                    columnOffset = -1;
                    continue;
                }
                mat[i+rowOffset][j+columnOffset] = getSafe(i, j);
            }
        }
        return mat;
    }

    @Override
    public MatrixBase clone() {
        return new MatrixNM(this);
    }
}
