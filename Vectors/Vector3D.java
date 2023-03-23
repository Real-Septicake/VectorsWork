package Vectors;

import Bases.VectorBase;
import Tools.Op1;

public class Vector3D extends VectorBase {

    private double x = 0;
    private double y = 0;
    private double z = 0;

    public Vector3D() {
        super(3);
    }

    public Vector3D(double x, double y, double z) {
        super(3);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double get(int i) {
        updateVals();
        switch (i) {
            case 0:
                return x;
            case 1:
                return y;
            case 2:
                return z;
            default:
                throw new IndexOutOfBoundsException(i);
        }
    }

    @Override
    public double unsafeGet(int i) {
        throw new IllegalCallerException("Function call not allowed in " + this.getClass());
    }

    @Override
    public boolean set(int i, double val) {
        generalValueCheck(i, val);
        switch (i) {
            case 0:
                x = val;
                return x == val;
            case 1:
                y = val;
                return y == val;
            case 2:
                z = val;
                return z == val;
            default:
                throw new IndexOutOfBoundsException(i);
        }
    }

    @Override
    public boolean unsafeSet(int i, double val) {
        throw new IllegalCallerException("Function call not allowed in " + this.getClass());
    }

    @Override
    public void updateVals() {
        double scalarMultiple = Op1.findScalarMultiple(getMagnitude(), getMax());
        multiply(scalarMultiple);
    }

    @Override
    public double[] toDoubleArray() {
        return new double[]{x, y, z};
    }

    @Override
    public void add(double val) {
        x += val;
        y += val;
        z += val;
    }

    @Override
    public VectorBase addCopy(double val) {
        return new Vector3D(x + val, y + val, z + val);
    }

    @Override
    public void subtract(double val) {
        x -= val;
        y -= val;
        z -= val;
    }

    @Override
    public VectorBase subtractCopy(double val) {
        return new Vector3D(x - val, y - val, z - val);
    }

    @Override
    public void multiply(double val) {
        x *= val;
        y *= val;
        z *= val;
    }

    @Override
    public VectorBase multiplyCopy(double val) {
        return new Vector3D(x * val, y * val, z * val);
    }

    @Override
    public void divide(double val) {
        x /= val;
        y /= val;
        z /= val;
    }

    @Override
    public VectorBase divideCopy(double val) {
        return new Vector3D(x / val, y / val, z / val);
    }
}
