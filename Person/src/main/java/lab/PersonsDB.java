package lab;

import lab.Readers.ObjectReader;
import lab.Writers.ObjectWriter;
import lab.filters.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PersonsDB implements Iterable<Person> {
	final Logger logger = LoggerFactory.getLogger(PersonsDB.class);
	private List<Person> list = new ArrayList<>();
	
	public PersonsDB(ArrayList<Person> list) {
		this.list = list;
	}
	
	public PersonsDB() {
		
	}
	
	@Override
	public Iterator<Person> iterator() {
		Iterator<Person> it = new Iterator<Person>() {
			private int index = 0;
			
			@Override
			public boolean hasNext() {
				if (index < list.size()) {
					return true;
				} else {
					return false;
				}
			}
			
			@Override
			public Person next() {
				return list.get(index++);
			}
			
			@Override
			public void remove() {
				list.remove(index - 1);
			}
		};
		return it;
	}
	
	public String listPersons() {
		String s = "";
		for (Person p : list)
			s = s + p.toString() + "\n";
		return s;
	}
	
	public void addPerson(Person p) {
		list.add(p);
		logger.debug("Added Person:" + p.toString());
	}
	
	public Person deletePerson(String pesel) {
		Person p;
		Person deletedPerson;
		Iterator<Person> iterator = this.iterator();
		while (iterator.hasNext()) {
			p = iterator.next();
			if (p.getPesel().equals(pesel)) {
				deletedPerson = p;
				iterator.remove();
				logger.debug("Deleted Person:" + p.toString() + "\n");
				return deletedPerson;
			}
		}
		logger.debug(String.format("Cannot find person with pesel %s ", pesel + "\n"));
		return null;
	}
	
	public Person getPerson(int i) {
		return list.get(i);
	}
	
	public void saveToFile(FileOutputStream fos, ObjectWriter objectWriter) {
		for (Person p : list) {
			try {
				objectWriter.writeToStream(p, fos);
			} catch (IOException e) {
				logger.error("Cannot write database to file");
			}
		}
	}
	
	public void loadFromFile(FileInputStream fis, ObjectReader objectReader) {
		List<Person> temp = null;
		try {
			temp = objectReader.readFromStream(fis, Person.class);
		} catch (IOException e) {
			logger.error("Cannot load database from file");
		}
		list = temp;
	}
	
	public ArrayList<Person> filter(Filter filter) {
		return filter.applyFilter(list);
	}
}
