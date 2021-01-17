import java.lang.Exception;

/** This class implements message to be printed for InvalidLne exception */
public class InvalidLine extends Exception{
    /** This is the constructor for the InvalidLine exception
     * @param file this takes in the filename read as String
     * @param line takes an int; expects the line number where
     * the error was found */
    public InvalidLine(String file, int line){

        System.out.println("error: in file " + file + " at line " + line);
    }
}