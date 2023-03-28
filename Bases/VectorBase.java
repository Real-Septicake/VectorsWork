package Bases;

import Tools.ErrorMessages;
import Tools.OpMain;
import Vectors.Vector1D;
import Vectors.Vector2D;
import Vectors.Vector3D;
import Vectors.VectorND;

//TODO: REALLY gonna have to organize this stuff

/**
 * Base abstract class for all Vectors
 *
 * @author Septicake
 */
public abstract class VectorBase {
    /**
     * Max value that this {@code Vector}'s magnitude can be
     *
     * <p>
     * <t>Default value is 0, which does not limit magnitude</t>
     * </p>
     */
    private double maxMagnitude = 0;

    /**
     * Dimension of the {@code Vector}
     */
    private final int size;

    protected VectorBase(int size) {
        this.size = size;
    }

    /**
     * @return The current magnitude of this {@code Vector}
     */
    public double getMagnitude() {
        return Math.sqrt(OpMain.wholeSquaresSum(toDoubleArray()));
    }

    /**
     * @return The current maximum magnitude of this {@code Vector}
     */
    public double getMax() {
        return maxMagnitude;
    }

    /**
     * Returns the value held by the {@code Vector} at the specified index
     *
     * @param i Index to return the value of
     * @return The value at the specified index
     * @throws IndexOutOfBoundsException If specified index is greater than the {@code Vector}'s size
     */
    public abstract double get(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the value held by the {@code Vector} at the specified index, if the specified index is larger than the
     * size of the {@code Vector}, the last value is returned
     *
     * @param i Index to return the value of
     * @return The value at the specified index, or the value at the last index if the specified index is greater than the
     * {@code Vector}'s size
     */
    public abstract double unsafeGet(int i);

    /**
     * Sets the value held at a specified index by the {@code Vector} to a specified value
     *
     * @param i   Index to set the value at
     * @param val Value to set the index to
     * @return Whether the index was successfully set
     * @throws IndexOutOfBoundsException If specified index is greater than the {@code Vector}'s size
     * @throws IllegalArgumentException If the input value is NaN
     */
    public abstract boolean set(int i, double val) throws IndexOutOfBoundsException, IllegalArgumentException;

    /**
     * Sets the value held by the {@code Vector} at the specified index, if the specified index is larger than the
     * size of the {@code Vector}, the last value is set
     *
     * @param i   Index to return the value of
     * @param val Value to set the index to
     * @return Whether the index was successfully set
     * @throws IllegalArgumentException If the input value is NaN
     */
    public abstract boolean unsafeSet(int i, double val) throws IllegalArgumentException;

    /**
     * Called to check if the {@code Vector} has a maximum magnitude, and if so, alter the values to keep it within the
     * maximum
     */
    protected abstract void updateVals();

    /**
     * @return The size of the {@code Vector}
     */
    public int size() {
        return size;
    }

    /**
     * Converts the {@code Vector} to a double array
     *
     * @return The {@code Vector} converted to a double array
     */
    public abstract double[] toDoubleArray();

    /**
     * Sets the maximum magnitude value allowed for the {@code Vector}
     *
     * @param max The value to set the maximum to, 0 does not limit the magnitude
     */
    public void setMax(double max) {
        maxMagnitude = max;
    }

    protected void generalValueCheck(int i, double val) {
        if (i >= size()) throw new IndexOutOfBoundsException(ErrorMessages.indexOutOfBounds(this, i));
        if (Double.isNaN(val)) throw new IllegalArgumentException(ErrorMessages.NAN_INPUT);
    }

    public abstract void add(double val);

    public abstract VectorBase addCopy(double val);

    public void addMultipleSafe(double val, int... indices){
        for(int i : indices){
            set(i, get(i) + val);
        }
    }

    public void addMultipleUnsafe(double val, int... indices){
        for(int i : indices){
            unsafeSet(i, unsafeGet(i) + val);
        }
    }

    public abstract void subtract(double val);

    public abstract VectorBase subtractCopy(double val);

    public void subtractMultipleSafe(double val, int... indices){
        for(int i : indices){
            set(i, get(i) - val);
        }
    }

    public void subtractMultipleUnsafe(double val, int... indices){
        for(int i : indices){
            unsafeSet(i, unsafeGet(i) + val);
        }
    }

    public abstract void multiply(double val);

    public abstract VectorBase multiplyCopy(double val);

    public void multiplyMultipleSafe(double val, int... indices){
        for(int i : indices){
            set(i, get(i) * val);
        }
    }

    public void multiplyMultipleUnsafe(double val, int... indices){
        for(int i : indices){
            unsafeSet(i, unsafeGet(i) + val);
        }
    }

    public abstract void divide(double val);

    public abstract VectorBase divideCopy(double val);

    public void divideMultipleSafe(double val, int... indices){
        for(int i : indices){
            set(i, get(i) + val);
        }
    }

    public void divideMultipleUnsafe(double val, int... indices){
        for(int i : indices){
            unsafeSet(i, unsafeGet(i) + val);
        }
    }

    public static VectorBase of(double... values) {
        int n = values.length;
        switch (n) {
            case 1:
                return new Vector1D(values);
            case 2:
                return new Vector2D(values);
            case 3:
                return new Vector3D(values);
            default:
                return new VectorND(values);
        }
    }

    public String toString() {
        updateVals();
        StringBuilder sb = new StringBuilder();
        double[] d = toDoubleArray();
        sb.append(this.getClass().getSimpleName() + ": [");
        for (int i = 0; i < d.length; i++) {
            sb.append(d[i]);
            if (i != d.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
