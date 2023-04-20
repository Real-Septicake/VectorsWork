package Testing;

import Matrices.AugmentedMatrices.AugMatNMO;
import Matrices.MatrixNM;

import java.util.Arrays;

public class AugmentedMatrixTest {
    public static void main(String[] args){
        AugMatNMO test = new AugMatNMO(2, 2, 3);
        test.setSafe(0, 4, 10);
        System.out.println("toString:");
        System.out.println(test);

        System.out.println("\ntoDoubleMatrix:");
        System.out.println(Arrays.deepToString(test.toDoubleMatrix()));

        System.out.println("\nGet Position:");
        System.out.println(test.getSafe(0, 4));

        System.out.println("\nGet Row:");
        System.out.println(Arrays.toString(test.getRowSafe(0)));

        System.out.println("\nGet Column:");
        System.out.println(Arrays.toString(test.getColSafe(4)));

        System.out.println("\nRow Addition:");
        System.out.println("Before: " + test);
        test.addRows(0, 1);
        System.out.println("After: " + test);

        double val = 3;
        System.out.println("\nRow Multiply:");
        System.out.println("Row Before: " + Arrays.toString(test.getRowSafe(0)));
        System.out.println("Value multiplied: " + val);
        test.multiplyRow(0, val);
        System.out.println("Row After: " + Arrays.toString(test.getRowSafe(0)));

        double mult = 1.0/2.0;
        System.out.println("\nFalse Multiply then Add");
        System.out.println("Row Before: " + Arrays.toString(test.getRowSafe(1)));
        System.out.println("Added Row Before Multiply: " + Arrays.toString(test.getRowSafe(0)));
        System.out.println("Value Multiplied: " + mult);
        test.addRows(test.falseMultiplyRow(0, mult), 1);
        System.out.println("Matrix After: " + test);


        AugMatNMO solve = new AugMatNMO(new MatrixNM(new double[][]{{-3, 3, -6}, {-5, 1, -4}, {-2, -6, -1}}), new MatrixNM(new double[][]{{-3}, {5}, {9}}));
        solve.multiplyRow(0, 1.0/3.0);
        solve.addRows(solve.falseMultiplyRow(0, -2), 2);
        solve.addRows(solve.falseMultiplyRow(1, -1), 0);
        solve.addRows(solve.falseMultiplyRow(0, 2), 1);
        solve.addRows(solve.falseMultiplyRow(1, 8), 2);
        solve.addRows(solve.falseMultiplyRow(0, -6), 2);
        solve.multiplyRow(2, -1.0/9.0);
        solve.addRows(solve.falseMultiplyRow(2, -2), 0);
        solve.multiplyRow(0, 1.0/4.0);
        solve.addRows(solve.falseMultiplyRow(0, -3), 1);
        System.out.println("\n\n" + solve);
        System.out.println(solve.getAugClone());
    }
}
