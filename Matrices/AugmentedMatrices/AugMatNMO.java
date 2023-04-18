package Matrices.AugmentedMatrices;

import Bases.MatrixBase;

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
