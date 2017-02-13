package lab.filters;

import lab.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class Filter {
	protected abstract boolean meetsCondition(Person p);
	
	final public ArrayList<Person> applyFilter(List<Person> list) {
		ArrayList<Person> result = new ArrayList<Person>();
		for (Person p : list) {
			if (meetsCondition(p)) {
				result.add(p);
			}
		}
		return result;
	}
}
