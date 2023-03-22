package Vectors;

import Bases.VectorBase;

import Tools.Op1;

public class VectorND extends VectorBase {

    protected double[] vals;

    private VectorND(){
        super(-1);
        throw new IllegalCallerException("How the hell did you even call this?");
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
        if (getMagnitude() <= maxMagnitude || maxMagnitude == 0) return;
        double scalarMultiple = Math.min(getMagnitude(), maxMagnitude) / getMagnitude();
        vals = Op1.scalarMultiplication(scalarMultiple, vals);
    }

    @Override
    public double[] toDoubleArray(){
        return vals;
    }
}
