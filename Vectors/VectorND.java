package Vectors;

import Bases.VectorBase;

import Tools.Op1;

public class VectorND extends VectorBase {

    private double[] vals;

    private VectorND() {
        super(-1);
        throw new IllegalCallerException("How the hell did you even call this?");
    }

    public VectorND(double... vals) {
        super(vals.length);
        this.vals = vals;
    }

    @Override
    public double get(int i) {
        if (i >= size()) throw new IndexOutOfBoundsException(i);
        return vals[i];
    }

    @Override
    public double unsafeGet(int i) {
        throw new IllegalCallerException("Function call not allowed in " + this.getClass());
    }

    @Override
    public boolean set(int i, double val) {
        generalValueCheck(i, val);
        vals[i] = val;
        return vals[i] == val;
    }

    @Override
    public boolean unsafeSet(int i, double val) {
        throw new IllegalCallerException("Function call not allowed in " + this.getClass());
    }

    @Override
    public void updateVals() {
        double scalarMultiple = Op1.findScalarMultiple(getMagnitude(), getMax());
        multiply(scalarMultiple);
    }

    @Override
    public double[] toDoubleArray() {
        return vals;
    }

    @Override
    public void add(double val) {
        for (int i = 0; i < size(); i++) {
            vals[i] += val;
        }
    }

    @Override
    public VectorBase addCopy(double val) {
        double[] d = new double[size()];
        for (int i = 0; i < size(); i++) {
            d[i] = vals[i] + val;
        }
        return new VectorND(d);
    }

    @Override
    public void subtract(double val) {
        for (int i = 0; i < size(); i++) {
            vals[i] -= val;
        }
    }

    @Override
    public VectorBase subtractCopy(double val) {
        double[] d = new double[size()];
        for (int i = 0; i < size(); i++) {
            d[i] = vals[i] - val;
        }
        return new VectorND(d);
    }

    @Override
    public void multiply(double val) {
        for (int i = 0; i < size(); i++) {
            vals[i] *= val;
        }
    }

    @Override
    public VectorBase multiplyCopy(double val) {
        double[] d = new double[size()];
        for (int i = 0; i < size(); i++) {
            d[i] = vals[i] * val;
        }
        return new VectorND(d);
    }

    @Override
    public void divide(double val) {
        for (int i = 0; i < size(); i++) {
            vals[i] /= val;
        }
    }

    @Override
    public VectorBase divideCopy(double val) {
        double[] d = new double[size()];
        for (int i = 0; i < size(); i++) {
            d[i] = vals[i] / val;
        }
        return new VectorND(d);
    }
}
