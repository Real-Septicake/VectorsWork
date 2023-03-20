package Bases;

public interface VectorBase {
    double getMagnitude();
    double getMax();
    double[] getAngles();
    double get(int i);
    double unsafeGet(int i);
    double set(int i);
    double unsafeSet(int i);
}
