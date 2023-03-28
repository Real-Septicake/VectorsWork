package VectorTesting;

import Bases.VectorBase;
import Vectors.*;
import Tools.*;

//TODO: Cry over thread interrupts because they're mean and I don't like them
public class VectorTest {
    public static void main(String[] args){
        VectorBase v = VectorBase.of(7, 14, 45, 6);
        System.out.println(v);
        System.out.println(v + " magnitude: " + v.getMagnitude());
        v.setMax(1);
        /*
         * I'm gonna commit SO MANY CRIMES
         * The gods will fear my wrath cause this is some real bullshit
         */
        System.out.println("CHECK: " + v + " magnitude: " + v.getMagnitude()); //Thread interrupts are mean >:(
        System.out.println("RERUN: magnitude: " + Math.sqrt(OpMain.wholeSquaresSum(v)));
        v.setMax(0);
        System.out.println(v.set(0, 0));
        v.addMultipleSafe(2, 0, 0, 0);
        System.out.println(v);

        VectorBase vA = VectorBase.of(3, 2, 17);
        VectorBase vB = VectorBase.of(3, 17, 2, 6);

        System.out.println(OpMain.equalMagnitude(vA, vB));
        System.out.println(OpMain.equalValues(vA.toDoubleArray(), vB.toDoubleArray()));

        VectorND v2 = new VectorND(3, 8, 4);
        System.out.println(v2.size());
        System.out.println("X: " + v2.get(0) + " Y: " + v2.get(1) + " Z: " + v2.get(2) + " | Magnitude: " + v2.getMagnitude());
    }
}
