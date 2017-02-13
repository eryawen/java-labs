package lab;

/**
 * Created by Monika_ on 2016-10-22.
 */
public abstract class Counter {
    protected int colIndex;

    abstract boolean checkCondition(String s);

    final long count(String path) {
        CsvReader.prepareScanner(path);

        long count = 0;
        String[] fields;

        String s = CsvReader.readLineFromCSV();
        do {
            fields = s.split(",", colIndex + 2);
            if (checkCondition(fields[colIndex])) {
                count++;
            }
            s = CsvReader.readLineFromCSV();
        } while (s != null);
        return count;
    }
}
