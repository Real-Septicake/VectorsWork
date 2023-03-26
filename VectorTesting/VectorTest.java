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
        * HOLD THE FUCK UP
        * YOU MEAN TO TELL ME THAT THE THREAD GETS INTERRUPTED ON MY ROBOTICS LAPTOP
        * BUT NOT ON MY HOME PC??????????
        * WHAT THE FUCK?????
        */
        System.out.println(v + " magnitude: " + v.getMagnitude()); //Thread interrupts are mean >:(
        System.out.println("RERUN: magnitude: " + Math.sqrt(Op1.wholeSquaresSum(v)));
        v.setMax(0);
        System.out.println(v.set(0, 0));
        v.addMultipleSafe(2, 0, 0, 0);
        System.out.println(v);

        VectorND v2 = new VectorND(3, 8, 4);
        System.out.println(v2.size());
        System.out.println("X: " + v2.get(0) + " Y: " + v2.get(1) + " Z: " + v2.get(2) + " | Magnitude: " + v2.getMagnitude());
    }
}
