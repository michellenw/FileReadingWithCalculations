public class DataSort {

    //PRIVATE 2D sorted data array
    private int[][] sortedData;

    public DataSort(int[][] rawData) {

        sortedData = new int[rawData.length][];

        for (int dataSet = 0; dataSet < rawData.length; dataSet++) {
            //create copy of data set to not sort actual data
            int[] copyOfDataSet = new int[rawData[dataSet].length];

            for (int j = 0; j <rawData[dataSet].length; j++) {
                copyOfDataSet[j] = rawData[dataSet][j];
            }
            insertionSort(copyOfDataSet);
            sortedData[dataSet] = copyOfDataSet;
        }

    }

    public void insertionSort(int[] array) {
        //insertion sort
        for (int i = 1; i < array.length; i++) {
            //temp = key
            int temp = array[i];
            int j;

            for (j=i -1; j  >= 0 && temp < array[j]; j--) {
                array[j + 1] = array[j];
                array[j] = temp;
            }
        }
    }
    //Getter
    public int[][] getSortedData() {
        return sortedData;

    }
}
