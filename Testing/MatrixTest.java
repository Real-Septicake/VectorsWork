package Testing;

import Bases.MatrixBase;
import Tools.OpMatrices;

import java.util.Arrays;

public class MatrixTest {
    public static void main(String[] args) {
        double first = System.currentTimeMillis();

        MatrixBase m = MatrixBase.of(new double[][]{{16, 3, 7, 0, 12}, {0, 0, 0, 0, 0}});
        System.out.println("toString Test: \n" + m + "\nRows: " + m.getRows() + "\nColumns: " + m.getCols());

        MatrixBase m1 = MatrixBase.of(new double[][]{{-4, -3, -5}, {0, 5, -5}});
        MatrixBase m2 = MatrixBase.of(new double[][]{{0}, {-3}, {3}});

        System.out.println("\nMatrix Multiplication:");
        System.out.println("Matrix 1: " + Arrays.deepToString(m1.toDoubleMatrix()));
        System.out.println("Matrix 2: " + Arrays.deepToString(m2.toDoubleMatrix()));
        System.out.println("M1M2: " + OpMatrices.matrixMultiply(m1, m2));

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

        MatrixBase addition = MatrixBase.of(new double[][]{{5.32, 85.234, 1}, {4, 0, 0.005}});
        System.out.println("\nMatrix Addition:");
        System.out.println("Matrix Before: " + m1);
        System.out.println("Matrix Added: " + addition);
        m1.matrixAdd(addition);
        System.out.println("Matrix After: " + m1);

        MatrixBase subtraction = MatrixBase.of(new double[][]{{Math.PI}, {5.00004}, {18}});
        System.out.println("\nMatrix Subtraction:");
        System.out.println("Matrix Before: " + m2);
        System.out.println("Matrix Subtracted: " + subtraction);
        m2.matrixSubtract(subtraction);
        System.out.println("Matrix After: " + m2);

        System.out.println("\nSums:");
        System.out.println("Matrix: " + m2);
        System.out.println("Sum: " + OpMatrices.sum(m2));
        System.out.println("Inverted Sum: " + OpMatrices.invertedSum(m2));

        MatrixBase id = MatrixBase.of(new double[][]{{4, 6},{23, 18}});
        System.out.println("\nIdentity:");
        System.out.println("Matrix: " + id);
        System.out.println("Identity: " + id.getIdentityMatrix());
        System.out.println("Matrix multiplied by Identity: " + OpMatrices.matrixMultiply(id.getIdentityMatrix(), id));

        MatrixBase comp1 = MatrixBase.of(new double[][]{{8, -3, 6, 2, 4}, {3, 3, 3, 3, 3}});
        MatrixBase comp2 = MatrixBase.of(new double[][]{{2}});
        System.out.println("\nCompare:");
        System.out.println("Matrix 1 sum: " + OpMatrices.sum(comp1));
        System.out.println("Matrix 2 sum: " + OpMatrices.sum(comp2));
        System.out.println("Compare: " + comp1.compareTo(comp2));

        MatrixBase copied = MatrixBase.of(new double[][]{{12, 16, 4, 36, 4}, {345, 987, 341, 77777, 0}});
        MatrixBase copy = MatrixBase.ofSize(2, 4);

        double second = System.currentTimeMillis();
        System.out.println(second - first);

        MatrixBase det = MatrixBase.of(new double[][]{{0, 6, -2, -1, 5}, {0, 0, 0, -9, -7}, {0, 15, 35, 0, 0}, {0, -1, -11, -2, 1}, {-2, -2, 3, 0, -2}});
        System.out.println("\nDeterminant:");
        System.out.println(det.determinant()); // 2480

        MatrixBase minor = MatrixBase.of(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        System.out.println("\nMinor:");
        System.out.println(minor.cofactorMatrix());

        MatrixBase trans = MatrixBase.of(new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}});
        System.out.println("\nTranspose:");
        System.out.println("Before: " + trans);
        System.out.println("After: " + OpMatrices.transpose(trans));

        MatrixBase test = MatrixBase.of(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 2, 9}});
        System.out.println("\nInverse:");
        System.out.println("Original: "+test);
        System.out.println("Inverse: "+OpMatrices.inverse(test));
    }
}
