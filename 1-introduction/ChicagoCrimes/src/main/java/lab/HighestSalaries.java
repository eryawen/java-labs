package lab;

public class HighestSalaries {
    private static final int NUMBER_OF_RECORDS = 10;
    private Record[] array = new Record[NUMBER_OF_RECORDS];

    public int compareSalaries(String o1, String o2) {
        if (o1.length() > o2.length()) {
            return 1;
        } else if (o1.length() < o2.length()) {
            return -1;
        } else {
            for (int i = 0; i < o1.length(); i++) {
                if (o1.compareTo(o2) > 0) {
                    return 1;
                } else if (o1.compareTo(o2) < 0) {
                    return -1;
                }
            }
            return 0;
        }
    }

    public void findHighestSalaries(String path) {
        CsvReader.prepareScanner(path);
        String[] fields;
        String s;
        s = CsvReader.readLineFromCSV();

        for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
            s = CsvReader.readLineFromCSV();
            fields = s.split(",", 5);
            array[i] = new Record(fields[1] + " " + fields[0], fields[4]);
        }

        for (int i = 9; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (compareSalaries(array[j].getSalary(), array[j + 1].getSalary()) > 0) {
                    Record temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        s = CsvReader.readLineFromCSV();
        do {
            fields = s.split(",", 5);

            if (fields.length >= 5) {
                if (compareSalaries(fields[4], array[0].getSalary()) == 1) {
                    addElement(new Record(fields[1] + " " + fields[0], fields[4]));
                }
                s = CsvReader.readLineFromCSV();
            } else {
                break;
            }
        } while (true);

        show();
    }

    private void addElement(Record record) {
        array[0] = record;
        int i = 1;

        while ((i < NUMBER_OF_RECORDS) && (record.compareTo(array[i]) > 0)) {
            array[i - 1] = array[i];
            i++;
        }
        array[i - 1] = record;
    }

    private void show() {
        for (int i = 9; i >= 0; i--) {
            System.out.println(array[i].getFullname());
        }
        System.out.println();
    }

    protected class Record implements Comparable<Record> {
        private String fullname;
        private String salary;

        public Record(String fullname, String salary) {
            this.fullname = fullname;
            this.salary = salary;
        }

        @Override
        public int compareTo(Record r) {
            return compareSalaries(this.salary, r.salary);
        }

        public String getSalary() {
            return salary;
        }

        public String getFullname() {
            return fullname;
        }
    }
}
