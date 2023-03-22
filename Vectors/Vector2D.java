package Vectors;

import Bases.VectorBase;
import Tools.Op1;

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
        return Math.sqrt(Op1.wholeSquaresSum(toDoubleArray()));
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
        generalValueCheck(i, val);
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
        double scalarMultiple = Op1.findScalarMultiple(getMagnitude(), maxMagnitude);
        x *= scalarMultiple;
        y *= scalarMultiple;
    }

    @Override
    public double[] toDoubleArray(){
        return new double[]{x, y};
    }
}
