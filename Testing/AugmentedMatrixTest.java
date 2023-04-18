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
    }
}
