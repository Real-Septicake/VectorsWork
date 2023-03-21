package VectorTesting;

import Vectors.*;
public class VectorTest {
    public static void main(String[] args){
        Vector2D v = new Vector2D(7, 14);
        System.out.println("X: " + v.getX() + " Y: " + v.getY() + " magnitude: " + v.getMagnitude());
        v.setMax(1);
        System.out.println("X: " + v.getX() + " Y: " + v.getY() + " magnitude: " + v.getMagnitude());
    }
}
