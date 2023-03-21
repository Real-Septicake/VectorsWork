package Bases;

public abstract class VectorBase {
    protected double maxMagnitude = 0;

    public abstract double getMagnitude();
    public abstract double getMax();
    public abstract double[] getAngles();
    public abstract double get(int i);
    public abstract double unsafeGet(int i);
    public abstract double set(int i);
    public abstract double unsafeSet(int i);
    public abstract void updateVals();

    public void setMax(double max){
        maxMagnitude = max;
    }
}
