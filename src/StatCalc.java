import java.util.ArrayList;
import java.util.Arrays;

public class StatCalc {

    //private ArrayList<Double> values = new ArrayList<>();

    //Mean, Median, Mode

    //Mean Calculation
    public double calculateMean(int[] array) {
        int total = 0;

        for (int i = 0; i < array.length; i++) {
            total += array[i];
        }
        double mean = total / array.length;

        return mean;

    }
    //------------------------------------

    //Median Calculation
    public double calculateMedian(int[] array) {
        int[] newArray = copyofArray(array);

        //Sort the array
        Arrays.sort(newArray);

        if (newArray.length % 2 == 1) {
            //if odd number of values
            return newArray[(newArray.length + 1) / 2 - 1];
        } else {
            //if even number of values
            double lower = newArray[newArray.length / 2 - 1];
            double upper = newArray[newArray.length / 2];

            return (lower + upper) / 2.0;
        }
    }
    // ----------------------------------------
    //Prep for mode calculation (must be sorted first)

    //create copy of original array so that when the new array is sorted, it does not effect the original array.
    public int[] copyofArray(int[] array) {
        int[] newArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    //Mode calculation
    public int calculateMode(int[] array) {

        //create new array w tallies
        int[] tally = new int[1900000];
        for (int i = 0; i < tally.length; i++) {
            tally[i] = 0;
        }

        //adds increment to corresponding tally box for each array entry
        int val;

        for (int i = 0; i < array.length; i++) {
            val = array[i];
            tally[val]++;
        }

        //this finds the number w the largest array tally aka the mode
        int maxI = 0; //max index

        for (int i = 1; i < tally.length; i++) {
            if (tally[i] > tally[maxI]) {
                maxI = i;
            }
        }

        //returns the mode
        return maxI;

    }

    // --------------------------------------------
    //calculate standard deviation

    public double calculateSD(int[] array) {
        ArrayList<Double> values = new ArrayList<>();

        double mean = calculateMean(array);

        for (int i = 0; i < array.length; i++) {

            int value = array[i];

            //difference squared
            double difsq = Math.pow(value - mean, 2);
            values.add(difsq);
        }
        //Calculate mean of all numbers in difsq array
        int totalAll = 0;

        for (int i = 0; i < values.size(); i++) {

            //every value
            totalAll += values.get(i);
        }
        double meanSD = totalAll / values.size();
        return Math.sqrt(meanSD);
    }

    //calculate variance
    public double calculateVar(int[] array) {
        double stdDev = calculateSD(array);
        double var = Math.pow(stdDev, 2);
        return var;
    }
}