package Vectors;

import Bases.VectorBase;

public class Vector2D extends VectorBase {
    protected double x = 0;
    protected double y = 0;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getMagnitude() {
        return Math.sqrt((x * x) + (y * y));
    }

    @Override
    public double getMax() {
        return maxMagnitude;
    }

    @Override
    public double[] getAngles() {
        return new double[]{Math.atan2(y, x)};
    }

    @Override
    public double get(int i) {
        throw new IllegalCallerException("Function call not allowed in " + this.getClass());
    }

    @Override
    public double unsafeGet(int i) {
        throw new IllegalCallerException("Function call not allowed in " + this.getClass());
    }

    @Override
    public double set(int i) {
        throw new IllegalCallerException("Function call not allowed in " + this.getClass());
    }

    @Override
    public double unsafeSet(int i) {
        throw new IllegalCallerException("Function call not allowed in " + this.getClass());
    }

    @Override
    public void updateVals() {
        if (getMagnitude() <= maxMagnitude || maxMagnitude == 0) return;
        double scalarMultiple = Math.min(getMagnitude(), maxMagnitude) / getMagnitude();
        x *= scalarMultiple;
        y *= scalarMultiple;
    }

    public double getX(){
        updateVals();
        return x;
    }

    public double getY(){
        updateVals();
        return y;
    }
}
