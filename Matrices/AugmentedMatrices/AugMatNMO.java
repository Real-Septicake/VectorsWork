package Matrices.AugmentedMatrices;

import Bases.MatrixBase;
import Bases.VectorBase;
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
        if(main.getRows() != aug.getRows()) throw new IllegalArgumentException(ErrorMessages.AugMatErrors.matricesSizeMismatch(main, aug));
        ROWS = main.getRows();
        COLSMAIN = main.getCols();
        COLSAUG = aug.getCols();
        COLSTOTAL = COLSMAIN + COLSAUG;
        this.main = main.clone();
        augment = aug.clone();
    }

    public boolean setSafe(int row, int col, double val){
        boundHigh(row);
        boundWide(col);
        if(col >= COLSMAIN){
            col -= COLSMAIN;
            return augment.setSafe(row, col, val);
        }
        return main.setSafe(row, col, val);
    }

    public boolean setUnsafe(int row, int col, double val){
        row = Math.min(row, ROWS);
        col = Math.min(col, COLSTOTAL);
        return setSafe(row, col, val);
    }

    public double getSafe(int row, int col){
        boundHigh(row);
        boundWide(col);
        if(col >= COLSMAIN){
            col -= COLSMAIN;
            return augment.getSafe(row, col);
        }
        return main.getSafe(row, col);
    }

    public void addRows(int row1, int row2){
        boundHigh(row1);
        boundHigh(row2);
        main.toVectorArray()[row2].add(row1);
    }

    public void addRows(VectorBase[] row1, int row2){
        vectorSize(row1[0].size() + row1[1].size());
        boundHigh(row2);
        VectorBase copyMain = VectorBase.of(main.getRowSafe(row2));
        copyMain.add(row1[0]);
        main.setRow(row2, copyMain);

        VectorBase copyAug = VectorBase.of(augment.getRowSafe(row2));
        copyAug.add(row1[1]);
        augment.setRow(row2, copyAug);
    }

    public void multiplyRow(int row, double val){
        boundHigh(row);
        for(int i = 0; i < COLSTOTAL; i++){
            setUnsafe(row, i, getUnsafe(row, i) * val);
        }
    }

    public VectorBase[] falseMultiplyRow(int row, double val){
        boundHigh(row);
        VectorBase copyMain = main.getRowVector(row);
        copyMain.multiply(val);

        VectorBase copyAug = augment.getRowVector(row);
        copyAug.multiply(val);

        return new VectorBase[]{copyMain, copyAug};
    }

    public double getUnsafe(int row, int col){
        row = Math.min(row, ROWS);
        col = Math.min(col, COLSTOTAL);
        return getSafe(row, col);
    }

    public double[] getRowSafe(int row){
        boundHigh(row);
        double[] copy = new double[COLSTOTAL];
        System.arraycopy(main.getRowSafe(row), 0, copy, 0, COLSMAIN);
        System.arraycopy(augment.getRowSafe(row), 0, copy, COLSMAIN, COLSAUG);
        return copy;
    }

    public double[] getRowUnsafe(int row){
        row = Math.min(row, ROWS);
        return getRowSafe(row);
    }

    public double[] getColSafe(int col){
        boundWide(col);
        if(col >= COLSMAIN){
            col -= COLSMAIN;
            return augment.getColSafe(col);
        }
        return main.getColSafe(col);
    }

    public double[] getColUnsafe(int col){
        col = Math.min(col, getCols());
        return getColSafe(col);
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

    public MatrixBase getMainClone(){
        return main.clone();
    }

    public MatrixBase getAugClone(){
        return augment.clone();
    }

    public double[][] augToDoubleMatrix(){
        return augment.toDoubleMatrix();
    }

    public void boundWide(int check){
        if(check >= COLSTOTAL) throw new IllegalArgumentException(ErrorMessages.MatrixErrors.indexOutOfBounds(COLSMAIN + COLSAUG, check, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
    }

    public void boundHigh(int check){
        if(check >= ROWS) throw new IllegalArgumentException(ErrorMessages.MatrixErrors.indexOutOfBounds(ROWS, check, ErrorMessages.MatrixErrors.HEIGHT_OFFENSE));
    }

    public void vectorSize(int a){
        if(a != COLSTOTAL) throw new IllegalArgumentException(ErrorMessages.AugMatErrors.arraySizeMismatch(a, COLSTOTAL));
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
