package lab.commands;

import lab.Person;
import lab.PersonsDB;
import lab.filters.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by Monika_ on 2016-10-24.
 */
public class FilterPersonsCommand implements Command {
	final Logger logger = LoggerFactory.getLogger(PersonsDB.class);
	private Filter filter;
	private PersonsDB personsDB;
	
	public FilterPersonsCommand(Filter filter, PersonsDB personsDB) {
		this.filter = filter;
		this.personsDB = personsDB;
	}
	
	@Override
	public void execute() {
		ArrayList<Person> result = personsDB.filter(filter);
		String personsAfterFilter = "\n";
		for (Person p : result) {
			personsAfterFilter = personsAfterFilter + p + "\n";
		}
		logger.debug("List after applying filter: " + personsAfterFilter.toString());
	}
	
	@Override
	public void undo() {
		
	}
}
