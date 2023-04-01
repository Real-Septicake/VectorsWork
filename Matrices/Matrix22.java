package Matrices;

import Bases.MatrixBase;
import Tools.ErrorMessages;

public class Matrix22 extends MatrixBase {
    private double v00, v01,
                   v10, v11;

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
    public boolean setSafe(int row, int col, double val) {
        boundsCheck(row, col);
        switch(row){
            case 0:
                switch(col){
                    case 0: v00 = val; break;
                    case 1: v01 = val; break;
                    default: throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
                } break;
            case 1:
                switch(col){
                    case 0: v10 = val; break;
                    case 1: v11 = val; break;
                    default: throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
                } break;
            default: throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
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
    public double getSafe(int row, int col) throws IndexOutOfBoundsException {
        //boundsCheck(row, col);
        switch (row) {
            case 0:
                switch (col) {
                    case 0: return v00;
                    case 1: return v01;
                    default: throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
                }
            case 1:
                switch (col) {
                    case 0: return v10;
                    case 1: return v11;
                    default: throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
                }
            default: throw new UnknownError(ErrorMessages.IMPOSSIBLE_ERROR);
        }
    }

    @Override
    public double getUnsafe(int row, int col) {
        row = Math.min(row, getRows());
        col = Math.min(col, getCols());
        return getSafe(row, col);
    }

    @Override
    public double[][] toDoubleMatrix() {
        return new double[][]{{v00, v01}, {v10, v11}};
    }
}
