package Bases;

import Tools.Op1;

//TODO: REALLY gonna have to organize this stuff
public abstract class VectorBase {
    private double maxMagnitude = 0;
    private int size;

    protected VectorBase(int size) {
        this.size = size;
    }

    public double getMagnitude() {
        return Math.sqrt(Op1.wholeSquaresSum(toDoubleArray()));
    }

    public double getMax() {
        return maxMagnitude;
    }

    public abstract double get(int i);

    public abstract double unsafeGet(int i);

    public abstract boolean set(int i, double val);

    public abstract boolean unsafeSet(int i, double val);

    protected abstract void updateVals();

    public int size() {
        return size;
    }

    public abstract double[] toDoubleArray();

    public void setMax(double max) {
        maxMagnitude = max;
    }

    protected void generalValueCheck(int i, double val) {
        if (i >= size) throw new IndexOutOfBoundsException(i);
        if (Double.isNaN(val)) throw new IllegalArgumentException("Input value is NaN");
    }

    public abstract void add(double val);

    public abstract VectorBase addCopy(double val);

    public abstract void subtract(double val);

    public abstract VectorBase subtractCopy(double val);

    public abstract void multiply(double val);

    public abstract VectorBase multiplyCopy(double val);

    public abstract void divide(double val);

    public abstract VectorBase divideCopy(double val);
}
