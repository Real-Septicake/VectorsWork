package Testing;

import Matrices.AugmentedMatrices.AugMatNMO;

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
    }
}
