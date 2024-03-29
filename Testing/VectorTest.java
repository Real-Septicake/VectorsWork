package Testing;

import Bases.VectorBase;
import Vectors.*;
import Tools.*;

public class VectorTest {
    public static void main(String[] args) {
        VectorBase v = VectorBase.of(7, 14, 45, 6);
        System.out.println(v);
        System.out.println(v + " magnitude: " + v.getMagnitude());
        v.setMax(1);
        System.out.println("CHECK: " + v + " magnitude: " + v.getMagnitude());
        System.out.println("RERUN: magnitude: " + Math.sqrt(OpVectors.squareSum(v)));
        v.setMax(0);
        System.out.println(v.set(0, 0));
        v.addMultipleSafe(2, 0, 0, 0);
        System.out.println(v);

        VectorND v2 = new VectorND(3, 8, 4);
        System.out.println(v2.size());
        System.out.println("X: " + v2.get(0) + " Y: " + v2.get(1) + " Z: " + v2.get(2) + " | Magnitude: " + v2.getMagnitude());

        VectorBase vv = VectorBase.of(3, 7, 1, 5, 8);
        double val = 5.6;
        System.out.println("\nAdd:");
        System.out.println("Before: " + vv);
        System.out.println("Adding: " + val);
        vv.add(val);
        System.out.println("After: " + vv);

        VectorBase comp1 = VectorBase.of(4,5,2,3,7,1);
        VectorBase comp2 = VectorBase.of(3);
        System.out.println("\nCompare:");
        System.out.println("Vector 1 magnitude: " + comp1.getMagnitude());
        System.out.println("Vector 2 magnitude: " + comp2.getMagnitude());
        System.out.println("Compare result: " + comp1.compareTo(comp2));


        VectorBase unitTest = VectorBase.of(3, 2, 7, 4, 5);
        System.out.println("\nUnit Vector Creation:");
        System.out.println("Base Vector: " + unitTest);
        VectorBase unit = unitTest.getUnit();
        System.out.println("Unit Output: " + unit);
        System.out.println("Unit Magnitude: " + unit.getMagnitude());

        System.out.println("\nIteration:");
        VectorBase forEach = VectorBase.of(1, 4, 6, 8, 10);
        for(double d : forEach){
            System.out.println(d);
        }
    }
}
