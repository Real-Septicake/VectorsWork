package Vectors;

import Bases.VectorBase;

public class Vector2D implements VectorBase {
    public double x = 0;
    public double y = 0;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getMagnitude(){
        return Math.sqrt((x * x) + (y * y));
    }

    public double getMax() {
        return 0;
    }

    public double[] getAngles() {
        return new double[0];
    }

    public double get(int i) {
        return 0;
    }

    public double unsafeGet(int i) {
        return 0;
    }

    public double set(int i) {
        return 0;
    }

    public double unsafeSet(int i) {
        return 0;
    }
}
