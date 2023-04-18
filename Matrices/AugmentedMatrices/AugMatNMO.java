package Matrices.AugmentedMatrices;

import Bases.MatrixBase;
import Tools.ErrorMessages;

import java.util.Arrays;

public class AugMatNMO {

    private final int ROWS;
    private final int COLSMAIN;
    private final int COLSAUG;

    MatrixBase main;
    MatrixBase augment;
    public AugMatNMO(int row, int colMain, int colAug){
        ROWS = row;
        COLSMAIN = colMain;
        COLSAUG = colAug;
        this.main = MatrixBase.ofSize(row, colMain);
        this.augment = MatrixBase.ofSize(row, colAug);
    }

    public boolean setSafe(int row, int col, double val){
        if(row >= ROWS) throw new IllegalArgumentException(ErrorMessages.MatrixErrors.indexOutOfBounds(ROWS, row, ErrorMessages.MatrixErrors.HEIGHT_OFFENSE));
        if(col >= COLSMAIN + COLSAUG) throw new IllegalArgumentException(ErrorMessages.MatrixErrors.indexOutOfBounds(COLSMAIN + COLSAUG, col, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
        if(col >= COLSMAIN){
            col %= COLSMAIN;
            return augment.setSafe(row, col, val);
        }
        return main.setSafe(row, col, val);
    }

    public int getRows(){
        return ROWS;
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
        double[][] clone = new double[ROWS][COLSMAIN + COLSAUG];
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
