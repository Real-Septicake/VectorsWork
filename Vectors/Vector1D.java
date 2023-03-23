package Vectors;

import Bases.VectorBase;

public class Vector1D extends VectorBase {

    private double magnitude = 0;

    public Vector1D() {
        super(1);
    }

    public Vector1D(double magnitude) {
        super(1);
        this.magnitude = magnitude;
    }

    @Override
    public double get(int i) {
        if (i >= size()) throw new IndexOutOfBoundsException(i);
        return magnitude;
    }

    @Override
    public double unsafeGet(int i) {
        return magnitude;
    }

    @Override
    public boolean set(int i, double val) {
        generalValueCheck(i, val);
        magnitude = val;
        return magnitude == val;
    }

    @Override
    public boolean unsafeSet(int i, double val) {
        if (Double.isNaN(val)) throw new IllegalArgumentException("Input value is NaN");
        magnitude = val;
        return magnitude == val;
    }

    @Override
    protected void updateVals() {
        magnitude = Math.min(getMagnitude(), getMax());
    }

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
