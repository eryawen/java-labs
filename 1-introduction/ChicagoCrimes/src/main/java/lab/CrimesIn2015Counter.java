package lab;

/**
 * Created by Monika_ on 2016-10-22.
 */
public class CrimesIn2015Counter extends Counter {
    public CrimesIn2015Counter() {
        colIndex = 2;
    }

    @Override
    boolean checkCondition(String s) {
        if (s.contains("2015")) {
            return true;
        } else {
            return false;
        }
    }
}
