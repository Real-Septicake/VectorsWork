package Matrices;

import Bases.MatrixBase;
import Bases.VectorBase;
import Tools.ErrorMessages;
import Tools.OpMain;
import Vectors.Vector3D;

public class Matrix33 extends MatrixBase {

    private double v00, v01, v02,
                   v10, v11, v12,
                   v20, v21, v22;

    public static final Matrix33 IDENTITY = new Matrix33(new double[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}});

    public Matrix33(){
        super(3, 3);
        v00 = 0; v01 = 0; v02 = 0;
        v10 = 0; v11 = 0; v12 = 0;
        v20 = 0; v21 = 0; v22 = 0;
    }

    public Matrix33(double[]... vals){
        this();
        sizeCheck(vals);
        v00 = vals[0][0]; v01 = vals[0][1]; v02 = vals[0][2];
        v10 = vals[1][0]; v11 = vals[1][1]; v12 = vals[1][2];
        v20 = vals[2][0]; v21 = vals[2][1]; v22 = vals[2][2];
    }

    public Matrix33(MatrixBase source){
        this(source.toDoubleMatrix());
    }

    @Override
    public int elementCount(){
        return 9;
    }

    @Override
    public boolean setSafe(int row, int col, double val) throws IndexOutOfBoundsException {
        boundsCheck(row, col);
        switch (row) {
            case 0 -> {
                switch (col) {
                    case 0 -> {
                        v00 = val;
                        return v00 == val;
                    }
                    case 1 -> {
                        v01 = val;
                        return v01 == val;
                    }
                    case 2 -> {
                        v02 = val;
                        return v02 == val;
                    }
                    default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
                }
            }
            case 1 -> {
                switch (col) {
                    case 0 -> {
                        v10 = val;
                        return v10 == val;
                    }
                    case 1 -> {
                        v11 = val;
                        return v11 == val;
                    }
                    case 2 -> {
                        v12 = val;
                        return v12 == val;
                    }
                    default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
                }
            }
            case 2 -> {
                switch (col) {
                    case 0 -> {
                        v20 = val;
                        return v20 == val;
                    }
                    case 1 -> {
                        v21 = val;
                        return v21 == val;
                    }
                    case 2 -> {
                        v22 = val;
                        return v22 == val;
                    }
                    default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
                }
            }
            default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
        }
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
        if(v instanceof Vector3D){
            for (int i = 0; i < v.size(); i++) {
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
                    case 2 -> v02;
                    default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
                };
            }
            case 1 -> {
                return switch (col) {
                    case 0 -> v10;
                    case 1 -> v11;
                    case 2 -> v12;
                    default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
                };
            }
            case 2 -> {
                return switch (col) {
                    case 0 -> v20;
                    case 1 -> v21;
                    case 2 -> v22;
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
        return new VectorBase[]{VectorBase.of(v00, v01, v02), VectorBase.of(v10, v11, v12), VectorBase.of(v20, v21, v22)};
    }

    @Override
    public double[] getRowSafe(int row) throws IndexOutOfBoundsException {
        boundsCheck(row, getRows(), ErrorMessages.MatrixErrors.HEIGHT_OFFENSE);
        return switch (row) {
            case 0 -> new double[]{v00, v01, v02};
            case 1 -> new double[]{v10, v11, v12};
            case 2 -> new double[]{v20, v21, v22};
            default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
        };
    }

    @Override
    public double[] getRowUnsafe(int row) {
        return switch (row) {
            case 0 -> new double[]{v00, v01, v02};
            case 1 -> new double[]{v10, v11, v12};
            default -> new double[]{v20, v21, v22};
        };
    }

    @Override
    public void fillRow(int row, double val) {
        switch(row){
            case 0: v00 = val; v01 = val; v02 = val;
            case 1: v10 = val; v11 = val; v12 = val;
            default: v20 = val; v21 = val; v22 = val;
        }
    }

    @Override
    public double[] getColSafe(int col) {
        boundsCheck(col, getCols(), ErrorMessages.MatrixErrors.WIDTH_OFFENSE);
        return switch (col) {
            case 0 -> new double[]{v00, v10, v20};
            case 1 -> new double[]{v01, v11, v21};
            case 2 -> new double[]{v02, v12, v22};
            default -> throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
        };
    }

    @Override
    public double[] getColUnsafe(int col) {
        return switch (col) {
            case 0 -> new double[]{v00, v10, v20};
            case 1 -> new double[]{v01, v11, v21};
            default -> new double[]{v02, v12, v22};
        };
    }

    @Override
    public void fillCol(int col, double val) {
        switch(col){
            case 0: v00 = val; v10 = val; v20 = val;
            case 1: v01 = val; v11 = val; v21 = val;
            default: v02 = val; v12 = val; v22 = val;
        }
    }

    @Override
    public double determinant() {
        return v00*(v11*v22-v12*v21)-v01*(v10*v22-v20*v12)+v02*(v10*v21-v20*v11);
    }

    @Override
    public double minor(int row, int column) {
        return switch (row) {
            case 0 -> switch (column){
                case 0 -> v11*v22 - v21*v12;
                case 1 -> v10*v22 - v20*v12;
                case 2 -> v10*v21 - v20*v11;
                default -> throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, column, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
            };
            case 1 -> switch (column){
                case 0 -> v01*v22 - v21*v02;
                case 1 -> v00*v22 - v20*v02;
                case 2 -> v00*v21 - v20*v01;
                default -> throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, column, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
            };
            case 2 -> switch (column){
                case 0 -> v01*v12 - v11*v02;
                case 1 -> v00*v12 - v10*v02;
                case 2 -> v00*v11 - v10*v01;
                default -> throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, column, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
            };
            default -> throw new IndexOutOfBoundsException(ErrorMessages.MatrixErrors.indexOutOfBounds(this, column, ErrorMessages.MatrixErrors.HEIGHT_OFFENSE));
        };
    }

    @Override
    public MatrixBase minorMatrix() {
        double[][] minor = new double[][]{
                {minor(0, 0), minor(0, 1), minor(0, 2)},
                {minor(1, 0), minor(1, 1), minor(1, 2)},
                {minor(2, 0), minor(2, 1), minor(2, 2)},
        };
        return new Matrix33(minor);
    }

    @Override
    public MatrixBase cofactorMatrix() {
        double[][] cofactor = new double[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                cofactor[i][j] = Math.pow(-1, i+j) * minor(i, j);
            }
        }
        return new Matrix33(cofactor);
    }

    @Override
    public double trace() {
        return v00 + v11 + v22;
    }

    @Override
    public MatrixBase getIdentityMatrix(){
        return IDENTITY;
    }

    @Override
    public void copyVals(MatrixBase mb){
        if(mb instanceof Matrix33){
            v00 = mb.getUnsafe(0, 0); v01 = mb.getUnsafe(0, 1); v02 = mb.getUnsafe(0, 2);
            v10 = mb.getUnsafe(1, 0); v11 = mb.getUnsafe(1, 1); v12 = mb.getUnsafe(1, 2);
            v20 = mb.getUnsafe(2, 0); v21 = mb.getUnsafe(2, 1); v22 = mb.getUnsafe(2, 2);
        }else{
            throw new IllegalArgumentException(ErrorMessages.MatrixErrors.matrixCopySizeMismatch(this, mb));
        }
    }

    @Override
    public double[][] toDoubleMatrix() {
        return new double[][]{{v00, v01, v02},{v10, v11, v12},{v20, v21, v22}};
    }

    @Override
    public MatrixBase clone() {
        return new Matrix33(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Matrix33 m){
            boolean ret = OpMain.valEqual(v00, m.v00);
            ret = ret && OpMain.valEqual(v01, m.v01);
            ret = ret && OpMain.valEqual(v02, m.v02);
            ret = ret && OpMain.valEqual(v10, m.v10);
            ret = ret && OpMain.valEqual(v11, m.v11);
            ret = ret && OpMain.valEqual(v12, m.v12);
            ret = ret && OpMain.valEqual(v20, m.v20);
            ret = ret && OpMain.valEqual(v21, m.v21);
            return ret && OpMain.valEqual(v22, m.v22);
        }else{
            return super.equals(obj);
        }
    }
}
