package Tools;

public class OpMain {
    /**
     * Returns the sum of the values of a matrix of doubles
     * @param vals Matrix to find the sum of
     * @return Sum of the values of the matrix
     */
    public static double sum(double[]... vals){
        double sum = 0;
        for (double[] a : vals) {
            for (double i : a) {
                sum += i;
            }
        }
        return sum;
    }

    /**
     * Returns the sum of the squares of the values of a matrix of doubles
     * @param vals Matrix to find the sum of the squared values of
     * @return Sum of the squared values of the matrix
     */
    public static double squareSum(double[]... vals){
        double sum = 0;
        for (double[] a : vals) {
            for (double i : a) {
                sum += i * i;
            }
        }
        return sum;
    }

    /**
     * Returns the sign of the sum of the values of the matrix
     * @param vals Matrix to find the sign of the sum of the values of
     * @return The sign of the sum of the values of the matrix
     */
    public static double sumSign(double[]... vals){
        return Math.signum(sum(vals));
    }

    /**
     * Returns the sum of the signs of the values of the matrix
     * @param vals Matrix to find the sum of the signs of the values of
     * @return The sum of the signs of the values of the matrix
     */
    public static double signSum(double[]... vals){
        double sum = 0;
        for(double[] a : vals){
            for(double i : a){
                sum += Math.signum(i);
            }
        }
        return sum;
    }

    /**
     * Rounds the input to the desired decimal count
     * @param val Value to round
     * @param count Desired decimal count
     * @return Input value rounded to the desired input count
     */
    public static double roundToDecimalCount(double val, int count){
        val *= Math.pow(10, count);
        return (double)Math.round(val) / Math.pow(10, count);
    }
}
