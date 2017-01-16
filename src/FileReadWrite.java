import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReadWrite {

    //sets columns to 100 (every row has 100 integers - filled w zeros if 100 not inputted). 100 is max. amount.
    //will need to remove these zeros later on
    private static final int COLUMNS = 100;
    private static final int ROWS = 100;

    //how many results will be returned per data set (5 - mean, median, mode, SD, variance)
    private static final int RESULTNUMBER = 5;

    //create 2D PRIVATE array for raw data
    private int[][] rawData;
    private int[][] sortedData;

    //create 2D 50x3 array that will hold results (mean, median, mode, SD, variance) for each data set
    //PRIVATE data
    private double [][] statData = new double [ROWS][RESULTNUMBER];

    //Extracting/reading from file
    public void ReadFile(File file) {
        try {

            //creates first scanner to read file and count how many data sets there are
            Scanner fileScan = new Scanner(file);

            //set initial count (of rows) to zero (data sets)
            int numberOfDataSets = 0;
            int numberOfLines = 0;

            // goes through file and counts number of rows/dataSets to set rawData array parameter for length
            while(fileScan.hasNextLine()) {
                numberOfLines++;

                //if odd line, increase number of data sets because every second line is a new data set.
                if (numberOfLines % 2 == 1) {
                    numberOfDataSets++;
                }
                // advances the scanner to analyze the next line during the next loop
                fileScan.nextLine();

            }
            System.out.println("Number of rows: " + numberOfDataSets + " Number of lines: " + numberOfLines);

            //To ensure the user has the allowed number of data sets, show prompt and exit program
            if (numberOfDataSets > 100 || numberOfDataSets < 1) {
                System.out.println("ERROR: Please enter anywhere between 1 and 200 rows!");
                System.exit(-1);
            }

            // create array of counted size
            int[][] array = new int[numberOfDataSets][COLUMNS];

            //new scanner to reset (read file from beginning again). Used to actually read in the information
            Scanner fileScanReset = new Scanner(file);

            //create array list to store data set with proper length (amount of ints entered)
            ArrayList<Integer> dataSets = new ArrayList<>();

            // create an arraylist to represent your dataSet
            ArrayList<Integer> dataSet = new ArrayList<>();


            // go through the entire file // NOTE: may have to start at 1 not 0, and the "<" will become "<="
            for (int lines = 1, dataSetCount = 0 ; lines <= numberOfLines; lines++){

                // every time a new line is read, a new scanner is created to analyze that particular line
                Scanner lineScan = new Scanner(fileScanReset.nextLine());

                // if the line number is odd, create a new dataSet
                if(lines%2 ==1) {
                    dataSet.clear();
                    dataSetCount++;
                }
                // whether it is odd or even, read in the information, regardless
                for (int column = 0; lineScan.hasNextInt(); column++) {

                    // if there is more than 100 columns in the dataSet:
                    if (column > 100) {
                        System.out.println("ERROR: Please enter anywhere between 1 and 100 columns!");
                        System.exit(-1);
                    }
                    // add the integer to the dataSet
                    dataSet.add(Integer.parseInt(lineScan.next()));

                }
                //if no next line bc one line is one data set in this case (odd)
                if(lines % 2 == 1 && !fileScanReset.hasNextLine()) {
                        // convert the arraylist that holds the data set into an array
                        int [] myArray = new int [dataSet.size()];

                        //copy all values from the array list to the array
                        for (int i = 0; i < dataSet.size(); i++) {
                            myArray[i] = dataSet.get(i);

                        }
                        array[dataSetCount - 1] = myArray;

                }
                //checks if row is even number
                if (lines%2 == 0) {
                    // convert the arraylist that holds the data set into an array
                    int [] myArray = new int [dataSet.size()];

                    //copy all values from the array list to the array
                    for (int i = 0; i < dataSet.size(); i++) {
                        myArray[i] = dataSet.get(i);

                    }
                    array[dataSetCount - 1] = myArray;
                }
            }
            rawData = array;
        }


        //general exception if there is an error
        catch(Exception e){
            System.out.println("An Error had occurred. Please try again.");
            e.printStackTrace();

            //ends program
            System.exit(-1);
        }
    }

    //Getter
    public int[][] getRawData() {
        return rawData;
    }

    //Filling results array
    public void FillResults() {

        //create object
        StatCalc statCalculator = new StatCalc();

        for (int dataSet = 0; dataSet < rawData.length; dataSet++) {
            double mean = statCalculator.calculateMean(rawData[dataSet]);
            double median = statCalculator.calculateMedian(rawData[dataSet]);
            double mode = statCalculator.calculateMode(rawData[dataSet]);
            double stdDev = statCalculator.calculateSD(rawData[dataSet]);
            double variance = statCalculator.calculateVar(rawData[dataSet]);

            statData[dataSet][0] = mean;
            statData[dataSet][1] = median;
            statData[dataSet][2] = mode;
            statData[dataSet][3] = stdDev;
            statData[dataSet][4] = variance;
        }
    }

    //Seperates data sets when printing
    String equal = "=";

    //Repeat string "equal" 50 times when used (replaces null with equal sign)
    String repeated = new String(new char[50]).replace("\0", equal);

    //Printing  to Screen
    public void PrintScreen (){
        int count = 0;

        for (int dataSet = 0; dataSet < rawData.length; dataSet++) {
            int numbersprinted = 0;

            count++;

            //Tells user which data set a particular row is (i.e. row #)
            System.out.println("Data Set " + count + ":");

            //Prints the actual row
            for (int j = 0; j < rawData[dataSet].length; j++) {
                System.out.print(rawData[dataSet][j] + " ");
                numbersprinted++;

                if (numbersprinted%25 == 0)
                    System.out.println();
            }
            numbersprinted = 0;
            System.out.println("\n");
            System.out.println("\t" + "\t" + "Sorted data: ");
            for (int j = 0; j < sortedData[dataSet].length; j++) {
                System.out.print("\t" + "\t");
                System.out.print(sortedData[dataSet][j] + " ");
                numbersprinted++;

                if (numbersprinted%25 == 0)
                    System.out.println();
            }
            System.out.println("  ");
            System.out.println("  ");
            System.out.print("\t" + "\t" + "Mean is: " + statData[dataSet][0] + ".  ");
            System.out.print("Median is: "+ statData[dataSet][1] + ".   ");
            System.out.print("Mode is: "+ statData[dataSet][2] +".   ");
            System.out.print("Standard deviation is: ");
            System.out.printf("%.2f", statData[dataSet][3]);
            System.out.print(".   Variance is: ");
            System.out.printf("%.2f", statData[dataSet][4]);
            System.out.print(".   " + "\n" + repeated + "\n");

        }
    }

    public void getSortedData() {
        //sort data
        DataSort dataSort = new DataSort(rawData);
        sortedData = dataSort.getSortedData();
    }

    //Writing to File
    public void WriteToFile() throws IOException {

        //begins writing on a new line when used
        String newLine = System.getProperty("line.separator");
        String equal = "=";

        //Repeat string "equal" 50 times when used (replaces null with equal sign)
        String repeated = new String(new char[50]).replace("\0", equal);

        //Writes to UserInput_out file
        try (FileWriter Fwrite = new FileWriter("src/UserInput_Out")) {
            int count = 0;
            int numbersprinted = 0;

            for (int dataSet = 0; dataSet < rawData.length; dataSet++) {

                //iteration counter
                count++;

                //Tells user which data set a particular row is (i.e. row #) and displays the array
                Fwrite.write(" " + newLine + repeated + newLine + " " +
                        newLine + "Data Set " + count + ":" + newLine);

                for (int j = 0; j < rawData[dataSet].length; j++) {
                    Fwrite.write(rawData[dataSet][j] + " ");
                    numbersprinted++;

                    //print 50 elements per line
                    if (numbersprinted%50 == 0)
                        Fwrite.write("\n");
                }

                //print sorted data
                Fwrite.write(newLine + newLine + "Sorted Data: " + newLine);
                numbersprinted = 0;

                for (int j = 0; j < sortedData[dataSet].length; j++) {
                    Fwrite.write(sortedData[dataSet][j] + " ");

                    numbersprinted++;

                    //print 50 elements per line
                    if (numbersprinted % 50 == 0)
                        Fwrite.write("\n");
                }
                        //displays mean median and mode of each data set
                        Fwrite.write(newLine + newLine + "Mean: " + statData[dataSet][0] +newLine +
                        "Median: " + statData[dataSet][1] + newLine +
                        "Mode: " + statData[dataSet][2] + newLine +
                        "Standard Deviation: " + statData[dataSet][3] + newLine +
                        "Variance: " + statData[dataSet][4] + newLine);
            }

            Fwrite.close();

        }
    }
}