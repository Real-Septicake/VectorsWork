package Vectors;

import Bases.VectorBase;
import Tools.ErrorMessages;

public class Vector0D extends VectorBase {

    public static final Vector0D INSTANCE = new Vector0D();

    private Vector0D(){
        super(0);
    }

    @Override
    public double get(int i) throws IndexOutOfBoundsException {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public double getUnsafe(int i) {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public boolean set(int i, double val) throws IndexOutOfBoundsException, IllegalArgumentException {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public boolean setUnsafe(int i, double val) throws IllegalArgumentException {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    protected void updateVals() {
    }

    @Override
    public double[] toDoubleArray() {
        return new double[0];
    }

    @Override
    public void add(double val) {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public void add(VectorBase source) {
        if(!(source instanceof Vector0D)){
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(INSTANCE, source));
        }
    }

    @Override
    public VectorBase addCopy(double val) {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public void subtract(double val) {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public void subtract(VectorBase source) {
        if(!(source instanceof Vector0D)){
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(INSTANCE, source));
        }
    }

    @Override
    public VectorBase subtractCopy(double val) {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public VectorBase subtractCopy(VectorBase source) {
        if(!(source instanceof Vector0D)){
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(INSTANCE, source));
        }else{
            return INSTANCE;
        }
    }

    @Override
    public void multiply(double val) {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public VectorBase multiplyCopy(double val) {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public void divide(double val) {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public VectorBase divideCopy(double val) {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public VectorBase clone() {
        return INSTANCE;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Vector0D){
            return true;
        }else{
            return super.equals(obj);
        }
    }
}
