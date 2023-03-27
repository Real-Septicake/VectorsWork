package Vectors;

import Bases.VectorBase;
import Tools.ErrorMessages;

public class Vector1D extends VectorBase {

    private double magnitude = 0;

    public Vector1D() {
        super(1);
    }

    public Vector1D(double magnitude) {
        super(1);
        this.magnitude = magnitude;
    }

    public Vector1D(double... values) {
        super(1);
        if (values.length != size())
            throw new IllegalArgumentException(ErrorMessages.invalidSourceArrayLength(values, this));
        this.magnitude = values[0];
    }

    public static Vector1D create(VectorBase source) {
        if (source.size() != 1)
            throw new IllegalArgumentException(ErrorMessages.sourceVectorTypeMismatch(new Vector1D(), source));
        return new Vector1D(source.toDoubleArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double get(int i) {
        if (i >= size()) throw new IndexOutOfBoundsException(ErrorMessages.indexOutOfBounds(this, i));
        return magnitude;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double unsafeGet(int i) {
        return magnitude;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean set(int i, double val) {
        generalValueCheck(i, val);
        magnitude = val;
        return magnitude == val;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unsafeSet(int i, double val) {
        if (Double.isNaN(val)) throw new IllegalArgumentException(ErrorMessages.NAN_INPUT);
        magnitude = val;
        return magnitude == val;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateVals() {
        magnitude = Math.min(getMagnitude(), getMax());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] toDoubleArray() {
        return new double[]{magnitude};
    }

    @Override
    public void add(double val) {
        magnitude += val;
    }

    @Override
    public VectorBase addCopy(double val) {
        return new Vector1D(magnitude + val);
    }

    @Override
    public void subtract(double val) {
        magnitude -= val;
    }

    @Override
    public VectorBase subtractCopy(double val) {
        return new Vector1D(magnitude - val);
    }

    @Override
    public void multiply(double val) {
        magnitude *= val;
    }

    @Override
    public VectorBase multiplyCopy(double val) {
        return new Vector1D(magnitude * val);
    }

    @Override
    public void divide(double val) {
        magnitude /= val;
    }

    @Override
    public VectorBase divideCopy(double val) {
        return new Vector1D(magnitude / val);
    }
}
