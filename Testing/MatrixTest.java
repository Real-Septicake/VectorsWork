package Testing;

import Matrices.*;
public class MatrixTest {
    public static void main(String[] args){
        MatrixBase m = new MatrixBase(new double[][]{{16, 3, 7, 0, 12},{0, 0, 0, 0, 0}});
        System.out.println(m + "\nRows: " + m.getRows() + "\nColumns: " + m.getCols());

    }
}
