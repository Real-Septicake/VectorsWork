package Testing;

import Bases.MatrixBase;
import Tools.OpMatrices;

import java.util.Arrays;

public class MatrixTest {
    public static void main(String[] args) {
        MatrixBase m = new MatrixBase(new double[][]{{16, 3, 7, 0, 12}, {0, 0, 0, 0, 0}});
        System.out.println(m + "\nRows: " + m.getRows() + "\nColumns: " + m.getCols());

        MatrixBase m1 = new MatrixBase(new double[][]{{-4, -3, -5}, {0, 5, -5}});
        MatrixBase m2 = new MatrixBase(new double[][]{{0}, {-3}, {3}});

        System.out.println("Matrix Multiplication: ");
        System.out.println("Matrix 1: " + Arrays.deepToString(m1.toDoubleMatrix()));
        System.out.println("Matrix 2: " + Arrays.deepToString(m2.toDoubleMatrix()));
        System.out.println("M1M2: " + OpMatrices.matrixMultiply(m1, m2));
        //System.out.println("Matrix 2 x Matrix 1: " + OpMatrices.matrixMultiply(m2, m1));

        double add = 5.40;
        System.out.println("\nAddition");
        System.out.println("Matrix Before: " + m1);
        System.out.println("Value: " + add);
        m1.add(add);
        System.out.println("Matrix After: " + m1);
    }
}
