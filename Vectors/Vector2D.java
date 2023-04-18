package Vectors;

import Bases.VectorBase;
import Tools.ErrorMessages;
import Tools.OpVectors;

public class Vector2D extends VectorBase {
    private double x = 0;
    private double y = 0;

    public Vector2D() {
        super(2);
    }

    public Vector2D(double x, double y) {
        this();
        this.x = x;
        this.y = y;
    }

    public Vector2D(double... values) {
        this();
        if (values.length != size())
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.invalidSourceArrayLength(this, values));
        x = values[0];
        y = values[1];
    }

    public static Vector2D create(VectorBase source) {
        if (source.size() != 2)
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.sourceVectorTypeMismatch(new Vector2D(), source));
        return new Vector2D(source.toDoubleArray());
    }

    public double getTheta(){
        return Math.tan(y/x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double get(int i) {
        updateVals();
        return switch (i) {
            case 0 -> x;
            case 1 -> y;
            default -> throw new IndexOutOfBoundsException(ErrorMessages.VectorErrors.indexOutOfBounds(this, i));
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double unsafeGet(int i) {
        return (i == 0) ? x : y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean set(int i, double val) {
        boundsCheck(i);
        NaNCheck(val);
        switch (i) {
            case 0 -> {
                x = val;
                return x == val;
            }
            case 1 -> {
                y = val;
                return x == val;
            }
            default -> throw new IndexOutOfBoundsException(ErrorMessages.VectorErrors.indexOutOfBounds(this, i));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unsafeSet(int i, double val) {
        if (Double.isNaN(val)) throw new IllegalArgumentException(ErrorMessages.VectorErrors.NAN_INPUT);
        if (i == 0) {
            x = val;
            return x == val;
        } else {
            y = val;
            return y == val;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateVals() {
        double scalarMultiple = OpVectors.clampMax(getMagnitude(), getMax());
        multiply(scalarMultiple);
    }

    /**
     * {@inheritDoc}
     */
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

    @Override
    public void fill(double val){
        NaNCheck(val);
        x = val;
        y = val;
    }

    @Override
    public VectorBase clone() {
        return new Vector2D(x, y);
    }
}
