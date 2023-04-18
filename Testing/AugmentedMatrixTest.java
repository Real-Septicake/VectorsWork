package Testing;

import Matrices.AugmentedMatrices.*;

import java.util.Arrays;

public class AugmentedMatrixTest {

    public static void main(String[] args){
        AugMatNMO test = new AugMatNMO(2, 2, 3);
        test.setSafe(0, 4, 10);
        System.out.println(test);

        System.out.println(Arrays.deepToString(test.toDoubleMatrix()));
    }
}
