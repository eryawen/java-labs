package lab.Readers;

import com.thoughtworks.xstream.XStream;
import lab.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class XMLReader extends ObjectReader {
	@Override
	public Object readFromString(String xml, Class c) {
		XStream xstream = new XStream();
		Person p = null;
		if (c.equals(Person.class)) {
			xstream.alias("Person", Person.class);
			p = (Person) xstream.fromXML(xml);
		} else {
			throw new IllegalArgumentException(String.format("cannot deserialize object from XML"));
		}
		return p;
	}
	
	@Override
	public ArrayList<Person> readFromStream(InputStream in, Class c) throws IOException {
		//String s = IOUtils.toString(in, StandardCharsets.UTF_8);
		ArrayList<Person> list = new ArrayList<>();
		Scanner scanner = new Scanner(in);
		scanner.useDelimiter("</Person>");
		while (scanner.hasNext()) {
			String next = scanner.next();
			if (next.contains("<Person>")) {
				list.add((Person) readFromString(next + "</Person>", c));
			}
		}
		
		return list;
	}
}
