package lab.Writers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.Person;

import java.io.IOException;

public class JsonWriter extends ObjectWriter {
	@Override
	protected String writeToString(Object obj) throws IOException {
		if (obj instanceof Person) {
			ObjectMapper mapper = new ObjectMapper();
			String JsonInString = mapper.writeValueAsString(obj);
			return JsonInString;
		} else {
			throw new IllegalArgumentException(String.format("cannot serialize object to json: %s", obj));
		}
	}
}
