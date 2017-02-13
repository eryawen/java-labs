package lab.Readers;

import lab.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectReader {
	private String separator = "\n";
	
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	
	protected Object readFromString(String str, Class c) throws IOException {
		if (c == Person.class) {
			Pattern pattern = Pattern.compile("Person\\{fullname=([^;]*);pesel=([^;]*);phone=([^}]*)\\}");
			Matcher m1 = pattern.matcher(str);
			m1.find();
			Person p = new Person(m1.group(1), m1.group(2), m1.group(3));
			return p;
		} else {
			throw new IllegalArgumentException(String.format("cannot deserialize object from String: %s", str));
		}
	}
	
	public ArrayList<Person> readFromStream(InputStream in, Class c) throws IOException {
		ArrayList<Person> list = new ArrayList<>();
		Scanner scanner = new Scanner(in);
		scanner.useDelimiter(separator);
		String next;
		while (scanner.hasNext()) {
			next = scanner.next();
			list.add((Person) readFromString(next, c));
		}
		return list;
	}
}

