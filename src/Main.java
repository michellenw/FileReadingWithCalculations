import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{

        //Creates new FileReadWrite object
        FileReadWrite objectFRW = new FileReadWrite();

        //Creates new file object
        File file = new File("src/UserInput");

        //reads file to fill up raw data array
        objectFRW.ReadFile(file);

        //Fill results array
        objectFRW.FillResults();

        //Get Sort Data
        objectFRW.getSortedData();
        //Prints to Screen
        objectFRW.PrintScreen();

        //Prints to file
        objectFRW.WriteToFile();

    }
}
