package Vectors;

import Bases.VectorBase;

public class Vector2D extends VectorBase {
    protected double x = 0;
    protected double y = 0;

    public Vector2D(){
        super(2);
    }

    public Vector2D(double x, double y) {
        super(2);
        this.x = x;
        this.y = y;
    }

    @Override
    public double getMagnitude() {
        return Math.sqrt((x * x) + (y * y));
    }

    @Override
    public double get(int i) {
        updateVals();
        switch(i) {
            case 0: return x;
            case 1: return y;
            default: throw new IndexOutOfBoundsException(i);
        }
    }

    @Override
    public double unsafeGet(int i) {
        throw new IllegalCallerException("Function call not allowed in " + this.getClass());
    }

    @Override
    public boolean set(int i, double val) {
        if(Double.isNaN(val)) throw new IllegalArgumentException("Input value is NaN");
        switch(i) {
            case 0: x = val; return x == val;
            case 1: y = val; return x == val;
            default: throw new IndexOutOfBoundsException(i);
        }
    }

    @Override
    public boolean unsafeSet(int i, double val) {
        throw new IllegalCallerException("Function call not allowed in " + this.getClass());
    }

    @Override
    public void updateVals() {
        if (getMagnitude() <= maxMagnitude || maxMagnitude == 0) return;
        double scalarMultiple = Math.min(getMagnitude(), maxMagnitude) / getMagnitude();
        x *= scalarMultiple;
        y *= scalarMultiple;
    }
}
