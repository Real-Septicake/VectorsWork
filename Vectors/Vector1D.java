package Vectors;

import Bases.VectorBase;

public class Vector1D extends VectorBase {

    protected double magnitude = 0;

    public Vector1D(){
        super(1);
    }

    public Vector1D(double magnitude){
        super(1);
        this.magnitude = magnitude;
    }



    @Override
    public double getMagnitude() {
        return magnitude;
    }

    @Override
    public double get(int i) {
        if(i >= size) throw new IndexOutOfBoundsException(i);
        return magnitude;
    }

    @Override
    public double unsafeGet(int i) {
        throw new IllegalCallerException("Function call not allowed in " + this.getClass());
    }

    @Override
    public boolean set(int i, double val) {
        generalValueCheck(i, val);
        magnitude = val;
        return magnitude == val;
    }

    @Override
    public boolean unsafeSet(int i, double val) {
        throw new IllegalCallerException("Function call not allowed in " + this.getClass());
    }

    @Override
    public void updateVals() {
        magnitude = Math.min(getMagnitude(), maxMagnitude);
    }

    @Override
    public double[] toDoubleArray(){
        return new double[]{magnitude};
    }
}
