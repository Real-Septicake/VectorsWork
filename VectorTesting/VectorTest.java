package VectorTesting;

import Vectors.*;
import Tools.*;
public class VectorTest {
    public static void main(String[] args){
        Vector2D v = new Vector2D(7, 14);
        System.out.println("X: " + v.get(0) + " Y: " + v.get(1) + " magnitude: " + v.getMagnitude());
        v.setMax(1);
        System.out.println("X: " + v.get(0) + " Y: " + v.get(1) + " magnitude: " + v.getMagnitude());
        v.setMax(0);
        System.out.println(v.set(0, 0));
        System.out.println("magnitude: " + v.getMagnitude());

        VectorND v2 = new VectorND(3, 8, 4);
        System.out.println(v2.size());
        System.out.println("X: " + v2.get(0) + " Y: " + v2.get(1) + " Z: " + v2.get(2) + " | Magnitude: " + v2.getMagnitude());
    }
}
