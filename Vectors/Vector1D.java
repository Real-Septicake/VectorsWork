package Vectors;

import Bases.VectorBase;
import Tools.ErrorMessages;

public class Vector1D extends VectorBase {

    private double magnitude = 0;

    public Vector1D() {
        super(1);
    }

    public Vector1D(double magnitude) {
        this();
        this.magnitude = magnitude;
    }

    public Vector1D(double... values) {
        this();
        if (values.length != size())
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.invalidSourceArrayLength(this, values));
        this.magnitude = values[0];
    }

    public static Vector1D create(VectorBase source) {
        if (source.size() != 1)
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.sourceVectorTypeMismatch(new Vector1D(), source));
        return new Vector1D(source.toDoubleArray());
    }

    @Override
    public double getMagnitude() {
        return magnitude;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double get(int i) {
        if (i >= size()) throw new IndexOutOfBoundsException(ErrorMessages.VectorErrors.indexOutOfBounds(this, i));
        return magnitude;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getUnsafe(int i) {
        return magnitude;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean set(int i, double val) {
        boundsCheck(i);
        NaNCheck(val);
        magnitude = val;
        return magnitude == val;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setUnsafe(int i, double val) {
        if (Double.isNaN(val)) throw new IllegalArgumentException(ErrorMessages.VectorErrors.NAN_INPUT);
        magnitude = val;
        return magnitude == val;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateVals() {
        magnitude = Math.min(getMagnitude(), getMax());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] toDoubleArray() {
        return new double[]{magnitude};
    }

    @Override
    public void add(double val) {
        magnitude = round(magnitude + val);
    }

    @Override
    public void add(VectorBase source) {
        if(source instanceof Vector1D){
            magnitude = round(magnitude + source.getMagnitude());
        }else{
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(this, source));
        }
    }

    @Override
    public VectorBase addCopy(double val) {
        return new Vector1D(round(magnitude + val));
    }

    @Override
    public void subtract(double val) {
        magnitude = round(magnitude - val);
    }

    @Override
    public void subtract(VectorBase source) {
        if(source instanceof Vector1D){
            magnitude = round(magnitude - source.getMagnitude());
        }else{
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(this, source));
        }
    }

    @Override
    public VectorBase subtractCopy(double val) {
        return new Vector1D(round(magnitude - val));
    }

    @Override
    public void multiply(double val) {
        magnitude = round(magnitude * val);
    }

    @Override
    public void multiply(VectorBase source) {
        if(source instanceof Vector1D){
            magnitude = round(magnitude * source.getMagnitude());
        }else{
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(this, source));
        }
    }

    @Override
    public VectorBase multiplyCopy(double val) {
        return new Vector1D(round(magnitude * val));
    }

    @Override
    public void divide(double val) {
        magnitude = round(magnitude / val);
    }

    @Override
    public void divide(VectorBase source) {
        if(source instanceof Vector1D){
            magnitude = round(magnitude / source.getMagnitude());
        }else{
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(this, source));
        }
    }

    @Override
    public VectorBase divideCopy(double val) {
        return new Vector1D(round(magnitude / val));
    }

    @Override
    public void fill(double val){
        NaNCheck(val);
        magnitude = val;
    }

    @Override
    public VectorBase getUnit(){
        return new Vector1D(1);
    }

    @Override
    public VectorBase clone() {
        return new Vector1D(magnitude);
    }
}
