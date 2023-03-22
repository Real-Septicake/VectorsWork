package Tools;

import Vectors.Vector3D;

public class Op1 {
    private Op1(){}

    public static double wholeSum(double... vals){
        double sum = 0;
        for(double val : vals){
            sum += val;
        }
        return sum;
    }

    public static double wholeSquaresSum(double... vals){
        double sum = 0;
        for(double val : vals){
            sum += val * val;
        }
        return sum;
    }

    public static double[] scalarMultiplication(double factor, double[] vals){
        double[] returns = new double[vals.length];
        for(int i = 0; i < vals.length; i++){
            returns[i] = vals[i] * factor;
        }
        return returns;
    }

    public static double[] crossProduct(Vector3D v1, Vector3D v2){
        return new Vector3D((v1.get(1) * v2.get(2)) - (v1.get(2) * v2.get(1)),
                (v1.get(2) * v2.get(0)) - (v1.get(0) * v2.get(2)),
                (v1.get(0) * v2.get(1)) - (v1.get(1) * v2.get(0))).toDoubleArray();
    }

    public static double findScalarMultiple(double magnitude, double maxMagnitude){
        if (magnitude <= maxMagnitude || maxMagnitude == 0) return magnitude;
        return Math.min(magnitude, maxMagnitude) / magnitude;
    }
}
