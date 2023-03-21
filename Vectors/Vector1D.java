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
        return 0;
    }

    @Override
    public boolean set(int i, double val) {
        if(Double.isNaN(val)) throw new IllegalArgumentException("Input value is NaN");
        if(i >= size) throw new IndexOutOfBoundsException(i);
        magnitude = val;
        return magnitude == val;
    }

    @Override
    public boolean unsafeSet(int i, double val) {
        return false;
    }

    @Override
    public void updateVals() {
        magnitude = Math.min(getMagnitude(), maxMagnitude);
    }
}
