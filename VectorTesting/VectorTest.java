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
    }
}
