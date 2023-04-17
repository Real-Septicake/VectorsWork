package Matrices;

import Bases.MatrixBase;
import Tools.ErrorMessages;

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
    public double[] getRowSafe(int row) {
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
        return switch(row){
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
    public MatrixBase getIdentityMatrix(){
        return IDENTITY;
    }

    @Override
    public void copy(MatrixBase mb){
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
}
