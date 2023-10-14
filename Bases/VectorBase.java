package Bases;

import Tools.ErrorMessages;
import Tools.OpMain;
import Vectors.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

//TODO: REALLY gonna have to organize this stuff

/**
 * Base abstract class for all {@code Vectors}
 *
 * @author Septicake
 */
public abstract class VectorBase implements Comparable<VectorBase>, Cloneable, Iterable<Double> {
    /**
     * The maximum value that this {@code Vector}'s magnitude can be
     *
     * <p>
     * <t>The default value is 0, which does not limit magnitude</t>
     * </p>
     */
    private double maxMagnitude = 0;

    /**
     * Dimension of the {@code Vector}
     */
    private final int size;

    private final int ROUND = 14;

    protected VectorBase(int size) {
        this.size = size;
    }

    /**
     * @return The current magnitude of this {@code Vector}
     */
    public double getMagnitude() {
        return Math.sqrt(OpMain.squareSum(toDoubleArray()));
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
    public abstract double getUnsafe(int i);

    /**
     * Sets the value held at a specified index by the {@code Vector} to a specified value
     *
     * @param i   Index to set the value at
     * @param val Value to set the index to
     * @return Whether the index was successfully set
     * @throws IndexOutOfBoundsException If specified index is greater than the {@code Vector}'s size
     * @throws IllegalArgumentException  If the input value is NaN
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
    public abstract boolean setUnsafe(int i, double val) throws IllegalArgumentException;

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

    protected void NaNCheck(double val) {
        if (Double.isNaN(val)) throw new IllegalArgumentException(ErrorMessages.VectorErrors.NAN_INPUT);
    }

    protected void boundsCheck(int i){
        if (i >= size() || i < 0) throw new IndexOutOfBoundsException(ErrorMessages.VectorErrors.indexOutOfBounds(this, i));
    }

    /**
     * Adds the input value to every value in the {@code Vector}
     * @param val The value to add to the values
     */
    public abstract void add(double val);

    public abstract void add(VectorBase source);

    /**
     * Adds the input value to every value in a copy of the {@code Vector}
     * @param val The value to add to the values
     * @return A copy of the original {@code Vector} with the input value added
     */
    public abstract VectorBase addCopy(double val);

    /**
     * Adds the input value to the values at specified indices
     * @param val The value to add to the indices
     * @param indices The indices to add the input value to
     * @throws IndexOutOfBoundsException If an index is out of bounds for the {@code Vector}
     */
    public void addMultipleSafe(double val, int... indices) {
        for (int i : indices) {
            set(i, get(i) + val);
        }
    }

    /**
     * Adds the input value to the values at specified indices without doing bounds checks
     * @param val The value to add to the indices
     * @param indices The indices to add the input value to
     */
    public void addMultipleUnsafe(double val, int... indices) {
        for (int i : indices) {
            setUnsafe(i, getUnsafe(i) + val);
        }
    }

    /**
     * Subtracts the input value from every value in the {@code Vector}
     * @param val The value to subtract from the values
     */
    public abstract void subtract(double val);

    public abstract void subtract(VectorBase source);

    /**
     * Subtracts the input value from every value in a copy of the {@code Vector}
     * @param val The value to subtract from the values
     * @return A copy of the original {@code Vector} with the input value subtracted
     */
    public abstract VectorBase subtractCopy(double val);

    /**
     * Subtracts the input value from the values at specified indices
     * @param val The value to subtract from the indices
     * @param indices The indices to subtract the input value from
     * @throws IndexOutOfBoundsException If an index is out of bounds for the {@code Vector}
     */
    public void subtractMultipleSafe(double val, int... indices) {
        for (int i : indices) {
            set(i, get(i) - val);
        }
    }

    /**
     * Subtracts the input value from the values at specified indices without doing bounds checks
     * @param val The value to subtract from the indices
     * @param indices The indices to subtract the input value from
     */
    public void subtractMultipleUnsafe(double val, int... indices) {
        for (int i : indices) {
            setUnsafe(i, getUnsafe(i) - val);
        }
    }

    /**
     * Multiplies every value in the {@code Vector} by the input value
     * @param val The value to multiply the values by
     */
    public abstract void multiply(double val);

    /**
     * Multiplies every value in a copy of the {@code Vector} by the input value
     * @param val The value to multiply the values by
     * @return A copy of the original {@code Vector} with the input value multiplied
     */
    public abstract VectorBase multiplyCopy(double val);

    /**
     * Multiplies the values at specified indices by the input value
     * @param val The value to multiply the indices by
     * @param indices The indices to multiply by the input value
     * @throws IndexOutOfBoundsException If an index is out of bounds for the {@code Vector}
     */
    public void multiplyMultipleSafe(double val, int... indices) throws IndexOutOfBoundsException{
        for (int i : indices) {
            set(i, get(i) * val);
        }
    }

    /**
     * Multiplies the values at specified indices by the input value without doing bounds checks
     * @param val The value to multiply the indices by
     * @param indices The indices to multiply by the input value
     */
    public void multiplyMultipleUnsafe(double val, int... indices) {
        for (int i : indices) {
            setUnsafe(i, getUnsafe(i) * val);
        }
    }

    /**
     * Divides every value in the {@code Vector} by the input value
     * @param val The value to divide the values by
     */
    public abstract void divide(double val);

    /**
     * Multiplies every value in a copy of the {@code Vector} by the input value
     * @param val The value to multiply the values by
     * @return A copy of the original vector with the input value multiplied
     */
    public abstract VectorBase divideCopy(double val);

    /**
     * Divides the values at specified indices by the input value
     * @param val The value to divide the indices by
     * @param indices The indices to divide by the input value
     * @throws IndexOutOfBoundsException If an index is out of bounds for the {@code Vector}
     */
    public void divideMultipleSafe(double val, int... indices) throws IndexOutOfBoundsException{
        for (int i : indices) {
            set(i, get(i) / val);
        }
    }

    /**
     * Divides the values at specified indices by the input value without doing bounds checks
     * @param val The value to divide the indices by
     * @param indices The indices to divide by the input value
     */
    public void divideMultipleUnsafe(double val, int... indices) {
        for (int i : indices) {
            setUnsafe(i, getUnsafe(i) / val);
        }
    }

    public void fill(double val) throws IllegalArgumentException {
        NaNCheck(val);
        for(int i = 0; i < size(); i++){
            set(i, val);
        }
    }

    @Override
    public int compareTo(VectorBase vb){
        return (int) Math.signum(getMagnitude() - vb.getMagnitude());
    }

    public VectorBase getUnit(){
        VectorBase unit = of(toDoubleArray());
        unit.setMax(1);
        unit.updateVals();
        return unit;
    }

    protected double round(double val){
        return OpMain.roundToDecimalCount(val, ROUND);
    }

    /**
     * Creates a Vector based on the input values
     * @param values The values to create a {@code Vector} from
     * @return The Vector containing the input values
     */
    public static VectorBase of(double... values) {
        int n = values.length;
        return switch (n) {
            case 0 -> Vector0D.INSTANCE;
            case 1 -> new Vector1D(values);
            case 2 -> new Vector2D(values);
            case 3 -> new Vector3D(values);
            default -> new VectorND(values);
        };
    }

    public static VectorBase ofLength(int size){
        return switch (size) {
            case 0 -> Vector0D.INSTANCE;
            case 1 -> new Vector1D();
            case 2 -> new Vector2D();
            case 3 -> new Vector3D();
            default -> new VectorND(new double[size]);
        };
    }

    @Override
    public abstract VectorBase clone();

    public String toString() {
        updateVals();
        return this.getClass().getSimpleName() + ": " + Arrays.toString(toDoubleArray());
    }

    @Override
    public Iterator<Double> iterator() {
        return new VectorItr();
    }

    private class VectorItr implements Iterator<Double> {
        private int currPos = 0;
        protected VectorItr(){
        }

        @Override
        public boolean hasNext() {
            return currPos < size();
        }

        @Override
        public Double next() {
            try{
                return get(currPos++);
            }catch(IndexOutOfBoundsException e){
                throw new NoSuchElementException(e);
            }
        }
    }
}
