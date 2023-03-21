package Vectors;

import Bases.VectorBase;

import Tools.Op1;

public class VectorND extends VectorBase {

    protected double[] vals;

    public VectorND(){
        super(-1);
    }

    public VectorND(double... vals){
        super(vals.length);
        this.vals = vals;
    }

    @Override
    public double getMagnitude() {
        return Math.sqrt(Op1.wholeSquaresSum(vals));
    }

    @Override
    public double get(int i) {
        if(i >= size) throw new IndexOutOfBoundsException(i);
        return vals[i];
    }

    @Override
    public double unsafeGet(int i) {
        return 0;
    }

    @Override
    public boolean set(int i, double val) {
        return false;
    }

    @Override
    public boolean unsafeSet(int i, double val) {
        return false;
    }

    @Override
    public void updateVals() {

    }
}
