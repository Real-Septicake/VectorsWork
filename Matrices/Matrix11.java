package Matrices;

import Bases.MatrixBase;
import Bases.VectorBase;
import Tools.ErrorMessages;
import Vectors.Vector1D;

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
    public int elementCount(){
        return 1;
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
    public void setRow(int row, VectorBase v) {
        boundsCheck(row, getRows(), ErrorMessages.MatrixErrors.HEIGHT_OFFENSE);
        if(v instanceof Vector1D){
            value = v.get(0);
        }else{
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.vectorSizeMismatch(this, v));
        }
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
    public VectorBase[] toVectorArray() {
        return new VectorBase[]{VectorBase.of(value)};
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
    public void fillRow(int row, double val) {
        value = val;
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
    public void fillCol(int col, double val) {
        value = val;
    }

    @Override
    public void add(double val) {
        value += val;
    }

    @Override
    public void addRows(int row1, int row2) {
        boundsCheck(row1, getRows(), ErrorMessages.MatrixErrors.HEIGHT_OFFENSE);
        boundsCheck(row2, getRows(), ErrorMessages.MatrixErrors.HEIGHT_OFFENSE);
        multiply(2);
    }

    @Override
    public void matrixAdd(MatrixBase m) throws IllegalArgumentException {
        if(m instanceof Matrix11){
            value += ((Matrix11) m).value;
        }else{
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixSizeMismatch(this, m, ErrorMessages.MatrixErrors.ADDITION_OFFENSE));
        }
    }

    @Override
    public void subtract(double val) {
        value -= val;
    }

    @Override
    public void matrixSubtract(MatrixBase m) {
        if(m instanceof Matrix11){
            value -= ((Matrix11) m).value;
        }else{
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixSizeMismatch(this, m, ErrorMessages.MatrixErrors.SUBTRACTION_OFFENSE));
        }
    }

    @Override
    public double determinant() {
        return value;
    }

    @Override
    public MatrixBase getIdentityMatrix(){
        return IDENTITY;
    }

    @Override
    public void copyVals(MatrixBase mb){
        if(mb instanceof Matrix11){
            value = getUnsafe(1, 1);
        }else{
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixCopySizeMismatch(this, mb));
        }
    }

    @Override
    public double[][] toDoubleMatrix(){
        return new double[][]{{value}};
    }

    @Override
    public MatrixBase clone() {
        return new Matrix11(value);
    }
}
