package Matrices.AugmentedMatrices;

import Bases.MatrixBase;
import Tools.ErrorMessages;

import java.util.Arrays;

public class AugMatNMO {

    private final int ROWS;
    private final int COLSMAIN;
    private final int COLSAUG;
    private final int COLSTOTAL;

    MatrixBase main;
    MatrixBase augment;
    public AugMatNMO(int row, int colMain, int colAug){
        ROWS = row;
        COLSMAIN = colMain;
        COLSAUG = colAug;
        COLSTOTAL = COLSMAIN + COLSAUG;
        this.main = MatrixBase.ofSize(row, colMain);
        this.augment = MatrixBase.ofSize(row, colAug);
    }

    public AugMatNMO(MatrixBase main, MatrixBase aug){
        if(main.getRows() != aug.getRows()) throw new IllegalArgumentException(ErrorMessages.AugMatErrors.matrixSizeMismatch(main, aug));
        ROWS = main.getRows();
        COLSMAIN = main.getCols();
        COLSAUG = aug.getCols();
        COLSTOTAL = COLSMAIN + COLSAUG;
        this.main = main.clone();
        augment = aug.clone();
    }

    public boolean setSafe(int row, int col, double val){
        if(row >= ROWS) throw new IllegalArgumentException(ErrorMessages.MatrixErrors.indexOutOfBounds(ROWS, row, ErrorMessages.MatrixErrors.HEIGHT_OFFENSE));
        if(col >= COLSTOTAL) throw new IllegalArgumentException(ErrorMessages.MatrixErrors.indexOutOfBounds(COLSMAIN + COLSAUG, col, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
        if(col >= COLSMAIN){
            col %= COLSMAIN;
            return augment.setSafe(row, col, val);
        }
        return main.setSafe(row, col, val);
    }

    public boolean setUnsafe(int row, int col, double val){
        row = Math.min(row, ROWS);
        col = Math.min(col, COLSTOTAL);
        return setSafe(row, col, val);
    }

    public int getRows(){
        return ROWS;
    }

    public int getCols(){
        return COLSTOTAL;
    }

    public int getColsMain(){
        return COLSMAIN;
    }

    public int getColsAug(){
        return COLSAUG;
    }

    public double[][] mainToDoubleMatrix(){
        return main.toDoubleMatrix();
    }

    public double[][] augToDoubleMatrix(){
        return augment.toDoubleMatrix();
    }

    public double[][] toDoubleMatrix(){
        double[][] clone = new double[ROWS][COLSTOTAL];
        for(int i = 0; i < ROWS; i++){
            System.arraycopy(main.getRowSafe(i), 0, clone[i], 0, COLSMAIN);
            System.arraycopy(augment.getRowSafe(i), 0, clone[i], COLSMAIN, COLSAUG);
        }
        return clone;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < ROWS; i++){
            sb.append("[").
                    append(Arrays.toString(main.getRowSafe(i))).
                    append(" | ").
                    append(Arrays.toString(augment.getRowSafe(i))).
                    append("]");
            if(i != ROWS - 1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
