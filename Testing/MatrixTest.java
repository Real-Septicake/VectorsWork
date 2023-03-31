package Testing;

import Bases.MatrixBase;
import Tools.OpMatrices;

import java.util.Arrays;

public class MatrixTest {
    public static void main(String[] args) {
        MatrixBase m = new MatrixBase(new double[][]{{16, 3, 7, 0, 12}, {0, 0, 0, 0, 0}});
        System.out.println("toString Test: \n" + m + "\nRows: " + m.getRows() + "\nColumns: " + m.getCols());

        MatrixBase m1 = new MatrixBase(new double[][]{{-4, -3, -5}, {0, 5, -5}});
        MatrixBase m2 = new MatrixBase(new double[][]{{0}, {-3}, {3}});

        System.out.println("\nMatrix Multiplication:");
        System.out.println("Matrix 1: " + Arrays.deepToString(m1.toDoubleMatrix()));
        System.out.println("Matrix 2: " + Arrays.deepToString(m2.toDoubleMatrix()));
        System.out.println("M1M2: " + OpMatrices.matrixMultiply(m1, m2));
        //System.out.println("Matrix 2 x Matrix 1: " + OpMatrices.matrixMultiply(m2, m1));

        double add = 5.40;
        System.out.println("\nAddition:");
        System.out.println("Matrix Before: " + m1);
        System.out.println("Value Added: " + add);
        m1.add(add);
        System.out.println("Matrix After: " + m1);

        double subtract = 2.87;
        System.out.println("\nSubtraction:");
        System.out.println("Matrix Before: " + m2);
        System.out.println("Value Subtracted: " + subtract);
        m2.subtract(subtract);
        System.out.println("Matrix After: " + m2);

        MatrixBase addition = new MatrixBase(new double[][]{{5.32, 85.234, 1}, {4, 0, 0.005}});
        System.out.println("\nMatrix Addition:");
        System.out.println("Matrix Before: " + m1);
        System.out.println("Matrix Added: " + addition);
        m1.matrixAdd(addition);
        System.out.println("Matrix After: " + m1);

        MatrixBase subtraction = new MatrixBase(new double[][]{{Math.PI}, {5.00004}, {18}});
        System.out.println("\nMatrix Subtraction:");
        System.out.println("Matrix Before: " + m2);
        System.out.println("Matrix Subtracted: " + subtraction);
        m2.matrixSubtract(subtraction);
        System.out.println("Matrix After: " + m2);

        System.out.println("\nSums:");
        System.out.println("Matrix: " + m2);
        System.out.println("Sum: " + OpMatrices.sum(m2));
        System.out.println("Inverted Sum: " + OpMatrices.invertedSum(m2));
    }
}
