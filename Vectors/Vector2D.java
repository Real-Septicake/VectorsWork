package Vectors;

import Bases.VectorBase;
import Tools.Op1;

public class Vector2D extends VectorBase {
    private double x = 0;
    private double y = 0;

    public Vector2D() {
        super(2);
    }

    public Vector2D(double x, double y) {
        super(2);
        this.x = x;
        this.y = y;
    }

    @Override
    public double get(int i) {
        updateVals();
        switch (i) {
            case 0:
                return x;
            case 1:
                return y;
            default:
                throw new IndexOutOfBoundsException(i);
        }
    }

    @Override
    public double unsafeGet(int i) {
        return (i == 0) ? x : y;
    }

    @Override
    public boolean set(int i, double val) {
        generalValueCheck(i, val);
        switch (i) {
            case 0:
                x = val;
                return x == val;
            case 1:
                y = val;
                return x == val;
            default:
                throw new IndexOutOfBoundsException(i);
        }
    }

    @Override
    public boolean unsafeSet(int i, double val) {
        if (Double.isNaN(val)) throw new IllegalArgumentException("Input value is NaN");
        if (i == 0) {
            x = val;
            return x == val;
        } else {
            y = val;
            return y == val;
        }
    }

    @Override
    protected void updateVals() {
        double scalarMultiple = Op1.findScalarMultiple(getMagnitude(), getMax());
        multiply(scalarMultiple);
    }

    @Override
    public double[] toDoubleArray() {
        return new double[]{x, y};
    }

    @Override
    public void add(double val) {
        x += val;
        y += val;
    }

    @Override
    public VectorBase addCopy(double val) {
        return new Vector2D(x + val, y + val);
    }

    @Override
    public void subtract(double val) {
        x -= val;
        y -= val;
    }

    @Override
    public VectorBase subtractCopy(double val) {
        return new Vector2D(x - val, y - val);
    }

    @Override
    public void multiply(double val) {
        x *= val;
        y *= val;
    }

    @Override
    public VectorBase multiplyCopy(double val) {
        return new Vector2D(x * val, y * val);
    }

    @Override
    public void divide(double val) {
        x /= val;
        y /= val;
    }

    @Override
    public VectorBase divideCopy(double val) {
        return new Vector2D(x / val, y / val);
    }
}
