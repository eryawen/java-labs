package lab.commands;

import lab.Person;
import lab.PersonsDB;

import java.util.Scanner;

/**
 * Created by Monika_ on 2016-10-24.
 */
public class DeletePersonCommand implements Command {
	private PersonsDB personsDB;
	private Person lastDeletedPerson;
	
	public DeletePersonCommand(PersonsDB personsDB) {
		this.personsDB = personsDB;
	}
	
	public static String readPersonToDelete() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Deleting person. Enter pesel: ");
		String pesel = scanner.next();
		scanner.nextLine();
		return pesel;
	}
	
	@Override
	public void execute() {
		String pesel = readPersonToDelete();
		lastDeletedPerson = personsDB.deletePerson(pesel);
	}
	
	@Override
	public void undo() {
		if (lastDeletedPerson != null) {
			personsDB.addPerson(lastDeletedPerson);
		}
	}
}