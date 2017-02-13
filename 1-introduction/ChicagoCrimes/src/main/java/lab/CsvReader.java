package lab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CsvReader {
    private static Scanner scanner;

    public static void prepareScanner(String path) {
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String readLineFromCSV() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            scanner.close();
            return null;
        }
    }

}
