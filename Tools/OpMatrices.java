package Tools;

import Matrices.MatrixBase;

public class OpMatrices {
    private OpMatrices(){}

    public static boolean confirmRect(double[]... vals){
        for(double[] a : vals){
            if(a.length != vals[0].length) return false;
        }
        return true;
    }

//    public static MatrixBase matrixMultiply(MatrixBase m1, MatrixBase m2){
//
//    }
}
