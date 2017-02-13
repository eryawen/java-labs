package lab.commands;

import lab.PersonsDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Commands {
	static final Logger logger = LoggerFactory.getLogger(PersonsDB.class);
	static ArrayList<Command> usedCommands = new ArrayList<Command>();
	
	public static void addPerson(PersonsDB db) {
		AddPersonCommand addPersonCommand = new AddPersonCommand(db);
		addPersonCommand.execute();
		usedCommands.add(addPersonCommand);
	}
	
	public static void listPersons(PersonsDB db) {
		ListPersonsCommand listPersonsCommand = new ListPersonsCommand(db);
		listPersonsCommand.execute();
	}
	
	public static void deletePerson(PersonsDB db) {
		DeletePersonCommand deletePersonCommand = new DeletePersonCommand(db);
		deletePersonCommand.execute();
		usedCommands.add(deletePersonCommand);
	}
	
	public static void filterPerson(PersonsDB db, lab.filters.Filter filter) {
		FilterPersonsCommand filterPersonsCommand = new FilterPersonsCommand(filter, db);
		filterPersonsCommand.execute();
	}
	
	public static void undo() {
		if (usedCommands.size() > 0) {
			(usedCommands.get(usedCommands.size() - 1)).undo();
			usedCommands.remove(usedCommands.size() - 1);
			logger.debug("Undo last command");
		} else {
			logger.debug("There is no command to undo");
		}
	}
}
