package lab;

/**
 * Created by Monika_ on 2016-10-22.
 */
public class CarTheftCounter extends Counter {
    public CarTheftCounter() {
        this.colIndex = 6;
    }

    @Override
    public boolean checkCondition(String s) {
        if (s.contains("AUTOMOBILE")) {
            return true;
        } else {
            return false;
        }
    }
}
