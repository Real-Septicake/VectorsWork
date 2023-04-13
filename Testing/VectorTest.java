package Testing;

import Bases.VectorBase;
import Vectors.*;
import Tools.*;

//TODO: Cry over thread interrupts because they're mean and I don't like them
public class VectorTest {
    public static void main(String[] args) {
        VectorBase v = VectorBase.of(7, 14, 45, 6);
        System.out.println(v);
        System.out.println(v + " magnitude: " + v.getMagnitude());
        v.setMax(1);
        /*
         * I'm gonna commit SO MANY CRIMES
         * The gods will fear my wrath cause this is some real bullshit
         */
        System.out.println("CHECK: " + v + " magnitude: " + v.getMagnitude()); //Thread interrupts are mean >:(
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
    }
}
