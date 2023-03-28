package Vectors;

import Bases.VectorBase;

import Tools.ErrorMessages;
import Tools.OpVectors;

public class VectorND extends VectorBase {

    private double[] vals;

    private VectorND() {
        super(-1);
    }

    public VectorND(double... vals) {
        super(vals.length);
        this.vals = vals;
    }

    public static VectorND create(VectorBase source){
        return new VectorND(source.toDoubleArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double get(int i) {
        if (i >= size()) throw new IndexOutOfBoundsException(ErrorMessages.indexOutOfBounds(this, i));
        return vals[i];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double unsafeGet(int i) {
        return vals[Math.min(i, vals.length - 1)];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean set(int i, double val) {
        generalValueCheck(i, val);
        vals[i] = val;
        return vals[i] == val;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unsafeSet(int i, double val) {
        if(Double.isNaN(val)) throw new IllegalArgumentException(ErrorMessages.NAN_INPUT);
        int n = Math.min(i, vals.length - 1);
        vals[n] = val;
        return vals[n] == val;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateVals() {
        double scalarMultiple = OpVectors.findScalarMultiple(getMagnitude(), getMax());
        multiply(scalarMultiple);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] toDoubleArray() {
        return vals.clone();
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
