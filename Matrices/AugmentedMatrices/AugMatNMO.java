package Matrices.AugmentedMatrices;

import Bases.MatrixBase;
import Bases.VectorBase;
import Tools.ErrorMessages;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AugMatNMO implements Iterable<VectorBase>{

    private final int ROW_COUNT;
    private final int MAIN_COLUMN_COUNT;
    private final int AUGMENTED_MATRIX_COLUMN_COUNT;
    private final int TOTAL_COLUMN_COUNT;

    MatrixBase main;
    MatrixBase augment;
    public AugMatNMO(int row, int colMain, int colAug){
        ROW_COUNT = row;
        MAIN_COLUMN_COUNT = colMain;
        AUGMENTED_MATRIX_COLUMN_COUNT = colAug;
        TOTAL_COLUMN_COUNT = MAIN_COLUMN_COUNT + AUGMENTED_MATRIX_COLUMN_COUNT;
        this.main = MatrixBase.ofSize(row, colMain);
        this.augment = MatrixBase.ofSize(row, colAug);
    }

    public AugMatNMO(MatrixBase main, MatrixBase aug){
        if(main.getRows() != aug.getRows()) throw new IllegalArgumentException(ErrorMessages.AugMatErrors.matricesSizeMismatch(main, aug));
        ROW_COUNT = main.getRows();
        MAIN_COLUMN_COUNT = main.getCols();
        AUGMENTED_MATRIX_COLUMN_COUNT = aug.getCols();
        TOTAL_COLUMN_COUNT = MAIN_COLUMN_COUNT + AUGMENTED_MATRIX_COLUMN_COUNT;
        this.main = main.clone();
        augment = aug.clone();
    }

    public boolean setSafe(int row, int col, double val){
        boundHigh(row);
        boundWide(col);
        if(col >= MAIN_COLUMN_COUNT){
            col -= MAIN_COLUMN_COUNT;
            return augment.setSafe(row, col, val);
        }
        return main.setSafe(row, col, val);
    }

    public boolean setUnsafe(int row, int col, double val){
        row = Math.min(row, ROW_COUNT);
        col = Math.min(col, TOTAL_COLUMN_COUNT);
        return setSafe(row, col, val);
    }

    public double getSafe(int row, int col){
        boundHigh(row);
        boundWide(col);
        if(col >= MAIN_COLUMN_COUNT){
            col -= MAIN_COLUMN_COUNT;
            return augment.getSafe(row, col);
        }
        return main.getSafe(row, col);
    }

    public void addRows(int row1, int row2){
        boundHigh(row1);
        boundHigh(row2);
        main.addRows(row1, row2);
        augment.addRows(row1, row2);
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
        for(int i = 0; i < TOTAL_COLUMN_COUNT; i++){
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
        row = Math.min(row, ROW_COUNT);
        col = Math.min(col, TOTAL_COLUMN_COUNT);
        return getSafe(row, col);
    }

    public double[] getRowSafe(int row){
        boundHigh(row);
        double[] copy = new double[TOTAL_COLUMN_COUNT];
        System.arraycopy(main.getRowSafe(row), 0, copy, 0, MAIN_COLUMN_COUNT);
        System.arraycopy(augment.getRowSafe(row), 0, copy, MAIN_COLUMN_COUNT, AUGMENTED_MATRIX_COLUMN_COUNT);
        return copy;
    }

    public double[] getRowUnsafe(int row){
        row = Math.min(row, ROW_COUNT);
        return getRowSafe(row);
    }

    public double[] getColSafe(int col){
        boundWide(col);
        if(col >= MAIN_COLUMN_COUNT){
            col -= MAIN_COLUMN_COUNT;
            return augment.getColSafe(col);
        }
        return main.getColSafe(col);
    }

    public double[] getColUnsafe(int col){
        col = Math.min(col, getCols());
        return getColSafe(col);
    }

    public int getRows(){
        return ROW_COUNT;
    }

    public int getCols(){
        return TOTAL_COLUMN_COUNT;
    }

    public int getColsMain(){
        return MAIN_COLUMN_COUNT;
    }

    public int getColsAug(){
        return AUGMENTED_MATRIX_COLUMN_COUNT;
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
        if(check >= TOTAL_COLUMN_COUNT) throw new IllegalArgumentException(ErrorMessages.MatrixErrors.indexOutOfBounds(MAIN_COLUMN_COUNT + AUGMENTED_MATRIX_COLUMN_COUNT, check, ErrorMessages.MatrixErrors.WIDTH_OFFENSE));
    }

    public void boundHigh(int check){
        if(check >= ROW_COUNT) throw new IllegalArgumentException(ErrorMessages.MatrixErrors.indexOutOfBounds(ROW_COUNT, check, ErrorMessages.MatrixErrors.HEIGHT_OFFENSE));
    }

    public void vectorSize(int a){
        if(a != TOTAL_COLUMN_COUNT) throw new IllegalArgumentException(ErrorMessages.AugMatErrors.arraySizeMismatch(a, TOTAL_COLUMN_COUNT));
    }

    public double[][] toDoubleMatrix(){
        double[][] clone = new double[ROW_COUNT][TOTAL_COLUMN_COUNT];
        for(int i = 0; i < ROW_COUNT; i++){
            System.arraycopy(main.getRowSafe(i), 0, clone[i], 0, MAIN_COLUMN_COUNT);
            System.arraycopy(augment.getRowSafe(i), 0, clone[i], MAIN_COLUMN_COUNT, AUGMENTED_MATRIX_COLUMN_COUNT);
        }
        return clone;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < ROW_COUNT; i++){
            sb.append("[").
                    append(Arrays.toString(main.getRowSafe(i))).
                    append(" | ").
                    append(Arrays.toString(augment.getRowSafe(i))).
                    append("]");
            if(i != ROW_COUNT - 1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<VectorBase> iterator() {
        return new AugMatItr();
    }

    private class AugMatItr implements Iterator<VectorBase> {
        int currPos = 0;

        @Override
        public boolean hasNext() {
            return currPos < ROW_COUNT;
        }

        @Override
        public VectorBase next() {
            try{
                return VectorBase.of(joinArrays(main.getRowSafe(currPos), augment.getRowSafe(currPos++)));
            }catch(IndexOutOfBoundsException e){
                throw new NoSuchElementException(e);
            }
        }

        public double[] joinArrays(double[] a1, double[] a2){
            double[] a = new double[a1.length + a2.length];
            System.arraycopy(a1, 0, a, 0, a1.length);
            if (a.length - a1.length >= 0) {
                System.arraycopy(a2, 0, a, a1.length, a2.length);
            }
            return a;
        }
    }
}
