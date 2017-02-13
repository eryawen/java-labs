package lab.Readers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.Person;

import java.io.IOException;

public class JsonReader extends ObjectReader {
	private static ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected Object readFromString(String str, Class c) throws IOException {
		if (c.equals(Person.class)) {
			Person p = mapper.readValue(str, Person.class);
			return p;
		} else {
			throw new IllegalArgumentException(String.format("cannot deserialize object from json: %s", str));
		}
	}
}

