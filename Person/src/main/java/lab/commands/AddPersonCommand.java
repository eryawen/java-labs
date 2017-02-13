package lab.commands;

import lab.Person;
import lab.PersonsDB;

import java.util.Scanner;

/**
 * Created by Monika_ on 2016-10-24.
 */
public class AddPersonCommand implements Command {
	private PersonsDB personsDB;
	private Person addedPerson;
	
	public AddPersonCommand(PersonsDB personsDB) {
		this.personsDB = personsDB;
	}
	
	public static Person readPerson() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Fullname:");
		String name = scanner.nextLine();
		System.out.println("Pesel:");
		String pesel = scanner.nextLine();
		System.out.println("Phone:");
		String phone = scanner.nextLine();
		Person p = new Person(name, pesel, phone);
		scanner.close();
		return p;
	}
	
	@Override
	public void execute() {
		Person p = readPerson();
		personsDB.addPerson(p);
		addedPerson = p;
	}
	
	@Override
	public void undo() {
		personsDB.deletePerson(addedPerson.getPesel());
	}
}
