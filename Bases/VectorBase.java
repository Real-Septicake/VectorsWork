package Bases;

public abstract class VectorBase {
    protected double maxMagnitude = 0;
    protected int size;

    protected VectorBase(int size){
        this.size = size;
    }

    public abstract double getMagnitude();

    public double getMax(){
        return maxMagnitude;
    }

    public abstract double get(int i);

    public abstract double unsafeGet(int i);

    public abstract boolean set(int i, double val);

    public abstract boolean unsafeSet(int i, double val);

    public abstract void updateVals();

    public int size(){
        return size;
    }

    public abstract double[] toDoubleArray();

    public void setMax(double max){
        maxMagnitude = max;
    }

    public void generalValueCheck(int i, double val){
        if(i >= size) throw new IndexOutOfBoundsException(i);
        if(Double.isNaN(val)) throw new IllegalArgumentException("Input value is NaN");
    }
}
