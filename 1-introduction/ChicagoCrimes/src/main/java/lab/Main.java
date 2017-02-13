package lab;

public class Main {

    public static void main(String[] args) {
        Counter carTheftCounter = new CarTheftCounter();
        //System.out.println(carTheftCounter.count("D:/programming/java/laboratoria/crimes.csv"));

        Counter crimesIn2015Counter = new CrimesIn2015Counter();
        //System.out.println(crimesIn2015Counter.count("D:/programming/java/laboratoria/crimes.csv"));

        HighestSalaries highestSalaries = new HighestSalaries();
        highestSalaries.findHighestSalaries("D:/programming/java/laboratoria/salaries.csv");
    }
}



//wyniki: 258525, 262796 22/10 20.30
