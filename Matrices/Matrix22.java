package Matrices;

import Bases.MatrixBase;
import Bases.VectorBase;
import Tools.ErrorMessages;
import Tools.OpMain;
import Vectors.Vector2D;

public class Matrix22 extends MatrixBase {
    private double v00, v01,
                   v10, v11;

    public static final Matrix22 IDENTITY = new Matrix22(new double[][]{{1, 0}, {0, 1}});

    public Matrix22(){
        super(2, 2);
        v00 = 0; v01 = 0;
        v10 = 0; v11 = 0;
    }

    public Matrix22(double[]... vals){
        this();
        sizeCheck(vals);
        v00 = vals[0][0]; v01 = vals[0][1];
        v10 = vals[1][0]; v11 = vals[1][1];
    }

    public Matrix22(Matrix22 source){
        this(source.toDoubleMatrix());
    }

    @Override
    public int elementCount(){
        return 4;
    }

    @Override
    public boolean setSafe(int row, int col, double val) {
        boundsCheck(row, col);
        switch (row) {
            case 0 -> {
                switch (col) {
                    case 0 -> v00 = val;
                    case 1 -> v01 = val;
                    default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
                }
            }
            case 1 -> {
                switch (col) {
                    case 0 -> v10 = val;
                    case 1 -> v11 = val;
                    default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
                }
            }
            default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
        }
        return getSafe(row, col) == val;
    }

    @Override
    public boolean setUnsafe(int row, int col, double val) {
        row = Math.min(row, getRows());
        col = Math.min(col, getCols());
        return setSafe(row, col, val);
    }

    @Override
    public void setRow(int row, VectorBase v) {
        boundsCheck(row, getRows(), ErrorMessages.MatrixErrors.HEIGHT_OFFENSE);
        if(v instanceof Vector2D){
            for(int i = 0; i < v.size(); i++){
                setUnsafe(row, i, v.getUnsafe(i));
            }
        }else{
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.vectorSizeMismatch(this, v));
        }
    }

    @Override
    public double getSafe(int row, int col) throws IndexOutOfBoundsException {
        boundsCheck(row, col);
        switch (row) {
            case 0 -> {
                return switch (col) {
                    case 0 -> v00;
                    case 1 -> v01;
                    default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
                };
            }
            case 1 -> {
                return switch (col) {
                    case 0 -> v10;
                    case 1 -> v11;
                    default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
                };
            }
            default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
        }
    }

    @Override
    public double getUnsafe(int row, int col) {
        row = Math.min(row, getRows());
        col = Math.min(col, getCols());
        return getSafe(row, col);
    }

    @Override
    public VectorBase[] toVectorArray() {
        return new VectorBase[]{VectorBase.of(v00, v01), VectorBase.of(v10, v11)};
    }

    @Override
    public double[] getRowSafe(int row) {
        boundsCheck(row, getRows(), ErrorMessages.MatrixErrors.HEIGHT_OFFENSE);
        return switch (row) {
            case 0 -> new double[]{v00, v01};
            case 1 -> new double[]{v10, v11};
            default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
        };
    }

    @Override
    public double[] getRowUnsafe(int row) {
        if (row == 0) {
            return new double[]{v00, v01};
        }
        return new double[]{v10, v11};
    }

    @Override
    public void fillRow(int row, double val) {
        if(row == 0){
            v00 = val;
            v01 = val;
        }else{
            v10 = val;
            v11 = val;
        }
    }

    @Override
    public double[] getColSafe(int col) {
        boundsCheck(col, getCols(), ErrorMessages.MatrixErrors.WIDTH_OFFENSE);
        return switch (col) {
            case 0 -> new double[]{v00, v10};
            case 1 -> new double[]{v01, v11};
            default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
        };
    }

    @Override
    public double[] getColUnsafe(int col) {
        if(col == 0){
            return new double[]{v00, v10};
        }
        return new double[]{v01, v11};
    }

    @Override
    public void fillCol(int col, double val) {
        if(col == 0){
            v00 = val;
            v10 = val;
        }else{
            v01 = val;
            v11 = val;
        }
    }

    @Override
    public double determinant() {
        return (v00*v11)-(v01*v10);
    }

    @Override
    public double minor(int row, int column) {
        return switch (row){
            case 0 -> switch (column){
                case 0 -> v11;
                case 1 -> v10;
                default -> throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, column, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
            };
            case 1-> switch (column) {
                case 0 -> v01;
                case 1 -> v00;
                default -> throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, column, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
            };
            default -> throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, column, ErrorMessages.MatrixErrors.HEIGHT_OFFENSE));
        };
    }

    @Override
    public MatrixBase minorMatrix() {
        double[][] minor = new double[][]{{minor(0,0), minor(0, 1)}, {minor(1, 0), minor(1, 1)}};
        return new Matrix22(minor);
    }

    @Override
    public MatrixBase cofactorMatrix() {
        double[][] cofactor = new double[][]{{minor(0,0), -minor(0, 1)}, {-minor(1, 0), minor(1, 1)}};
        return new Matrix22(cofactor);
    }

    @Override
    public MatrixBase getIdentityMatrix(){
        return IDENTITY;
    }

    @Override
    public void copyVals(MatrixBase mb){
        if(mb instanceof Matrix22){
            v00 = mb.getUnsafe(0, 0); v10 = mb.getUnsafe(1, 0);
            v01 = mb.getUnsafe(0, 1); v11 = mb.getUnsafe(1, 1);
        }else{
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixCopySizeMismatch(this, mb));
        }
    }

    @Override
    public double[][] toDoubleMatrix() {
        return new double[][]{{v00, v01}, {v10, v11}};
    }

    @Override
    public MatrixBase clone() {
        return new Matrix22(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Matrix22 m){
            boolean ret = OpMain.valEqual(v00, m.v00);
            ret = ret && OpMain.valEqual(v01, m.v01);
            ret = ret && OpMain.valEqual(v10, m.v10);
            return ret && OpMain.valEqual(v11, m.v11);
        }else{
            return super.equals(obj);
        }
    }
}
