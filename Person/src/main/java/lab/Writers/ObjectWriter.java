package lab.Writers;

import lab.Person;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ObjectWriter {
	private String separator = "\n";
	
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	
	protected String writeToString(Object obj) throws IOException {
		if (obj instanceof Person) {
			Person p = (Person) obj;
			return String.format("Person{fullname=%s;pesel=%s;phone=%s}", p.getFullname(), p.getPesel(),
							 p.getPhone());
		} else {
			throw new IllegalArgumentException(String.format("cannot serialize object to string: %s", obj));
		}
	}
	
	public final void writeToStream(Object obj, OutputStream out) throws IOException {
		String serialized = writeToString(obj);
		serialized = serialized + separator;
		out.write(serialized.getBytes(StandardCharsets.UTF_8));
	}
}