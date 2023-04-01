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
    public double unsafeGet(int i) {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public boolean set(int i, double val) throws IndexOutOfBoundsException, IllegalArgumentException {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public boolean unsafeSet(int i, double val) throws IllegalArgumentException {
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
    public VectorBase addCopy(double val) {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public void subtract(double val) {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
    }

    @Override
    public VectorBase subtractCopy(double val) {
        throw new IllegalCallerException(ErrorMessages.VectorErrors.ILLEGAL_0D_METHOD_CALL);
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
}
