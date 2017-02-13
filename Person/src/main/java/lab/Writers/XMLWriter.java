package lab.Writers;

import com.thoughtworks.xstream.XStream;
import lab.Person;

public class XMLWriter extends ObjectWriter {
	@Override
	public String writeToString(Object object) {
		if (object instanceof Person) {
			XStream xstream = new XStream();
			String xml;
			xstream.alias("Person", Person.class);
			xml = xstream.toXML(object);
			return xml;
		} else {
			throw new IllegalArgumentException(String.format("cannot serialize object to XML: %s", object));
		}
	}
}
