package lab.commands;

import lab.PersonsDB;

/**
 * Created by Monika_ on 2016-10-24.
 */
public class ListPersonsCommand implements Command {
	private PersonsDB personsDB;
	
	public ListPersonsCommand(PersonsDB personsDB) {
		this.personsDB = personsDB;
	}
	
	@Override
	public void execute() {
		System.out.println(personsDB.listPersons());
	}
	
	@Override
	public void undo() {
		
	}
}
