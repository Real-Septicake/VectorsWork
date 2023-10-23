package Vectors;

import Bases.VectorBase;
import Tools.*;

public class Vector3D extends VectorBase {

    private double x = 0;
    private double y = 0;
    private double z = 0;

    public Vector3D() {
        super(3);
    }

    public Vector3D(double x, double y, double z) {
        this();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(double... values) {
        this();
        if (values.length != size())
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.invalidSourceArrayLength(this, values));
        x = values[0];
        y = values[1];
        z = values[2];
    }

    public static Vector3D create(VectorBase source) {
        if (source.size() != 3)
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.sourceVectorTypeMismatch(new Vector3D(), source));
        return new Vector3D(source.getUnsafe(0), source.getUnsafe(1), source.getUnsafe(2));
    }

    /**
     * @return The value of the angle on the YZ plane rising from Y in radians
     */
    public double getTheta(){
        return Math.acos(z / (Math.sqrt(OpMain.squareSum(toDoubleArray()))));
    }
    /**
     * @return The value of the angle on the XZ plane rising from X in radians
     */
    public double getPhi(){
        return Math.signum(y) * Math.acos(x / Math.sqrt((x * x) + (y * y)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double get(int i) {
        updateVals();
        return switch (i) {
            case 0 -> x;
            case 1 -> y;
            case 2 -> z;
            default -> throw new IndexOutOfBoundsException(ErrorMessages.VectorErrors.indexOutOfBounds(this, i));
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getUnsafe(int i) {
        return switch (i) {
            case 0 -> x;
            case 1 -> y;
            default -> z;
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean set(int i, double val) {
        boundsCheck(i);
        NaNCheck(val);
        switch (i) {
            case 0 -> {
                x = val;
                return x == val;
            }
            case 1 -> {
                y = val;
                return y == val;
            }
            case 2 -> {
                z = val;
                return z == val;
            }
            default -> throw new IndexOutOfBoundsException(ErrorMessages.VectorErrors.indexOutOfBounds(this, i));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setUnsafe(int i, double val) {
        if (Double.isNaN(val)) throw new IllegalArgumentException(ErrorMessages.VectorErrors.NAN_INPUT);
        switch (i) {
            case 0 -> {
                x = val;
                return x == val;
            }
            case 1 -> {
                y = val;
                return y == val;
            }
            default -> {
                z = val;
                return z == val;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateVals() {
        double scalarMultiple = OpVectors.clampMax(getMagnitude(), getMax());
        multiply(scalarMultiple);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] toDoubleArray() {
        return new double[]{x, y, z};
    }

    @Override
    public void add(double val) {
        x = round(x + val);
        y = round(y + val);
        z = round(z + val);
    }

    @Override
    public void add(VectorBase source) {
        if(source instanceof Vector3D){
            x = round(x + source.getUnsafe(0));
            y = round(y + source.getUnsafe(1));
            z = round(z + source.getUnsafe(2));
        }else{
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(this, source));
        }
    }

    @Override
    public VectorBase addCopy(double val) {
        return new Vector3D(round(x + val), round(y + val), round(z + val));
    }

    @Override
    public void subtract(double val) {
        x = round(x - val);
        y = round(y - val);
        z = round(z - val);
    }

    @Override
    public void subtract(VectorBase source) {
        if(source instanceof Vector3D){
            x = round(x - source.getUnsafe(0));
            y = round(y - source.getUnsafe(1));
            z = round(z - source.getUnsafe(2));
        }else{
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(this, source));
        }
    }

    @Override
    public VectorBase subtractCopy(double val) {
        return new Vector3D(round(x - val), round(y - val), round(z - val));
    }

    @Override
    public VectorBase subtractCopy(VectorBase source) {
        if(source instanceof Vector3D){
            Vector3D v = (Vector3D) this.clone();
            v.subtract(source);
            return v;
        }else{
            throw new IllegalArgumentException(ErrorMessages.VectorErrors.vectorSizeMismatch(this, source));
        }
    }

    @Override
    public void multiply(double val) {
        x = round(x * val);
        y = round(y * val);
        z = round(z * val);
    }

    @Override
    public VectorBase multiplyCopy(double val) {
        return new Vector3D(round(x * val), round(y * val), round(z * val));
    }

    @Override
    public void divide(double val) {
        x = round(x / val);
        y = round(y / val);
        z = round(z / val);
    }

    @Override
    public VectorBase divideCopy(double val) {
        return new Vector3D(round(x / val), round(y / val), round(z / val));
    }

    @Override
    public void fill(double val){
        NaNCheck(val);
        x = val;
        y = val;
        z = val;
    }

    @Override
    public VectorBase clone() {
        return new Vector3D(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vector3D v){
            return OpMain.valEqual(x, v.x) && OpMain.valEqual(y, v.y) && OpMain.valEqual(z, v.z);
        }else{
            return super.equals(obj);
        }
    }
}
