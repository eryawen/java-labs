package lab;

import lab.Readers.JsonReader;
import lab.Readers.ObjectReader;
import lab.Readers.XMLReader;
import lab.Writers.JsonWriter;
import lab.Writers.ObjectWriter;
import lab.Writers.XMLWriter;
import lab.commands.*;
import lab.filters.LastNameFilter;
import lab.filters.PeselFilter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		PersonsDB personsDb = new PersonsDB();
		
		//UserInterface.showOptions(personsDb);
		
		personsDb.addPerson(new Person("Very Important Person", "96092514233", "788111333"));
		personsDb.addPerson(new Person("Some Programming Geek", "96062513425", "336733344"));
		personsDb.addPerson(new Person("Lord ofThe Rings", "96032213344", "334223333"));
		
		Commands.undo();
		
		ArrayList<Command> commands = new ArrayList<>();
		commands.add(new FilterPersonsCommand(new LastNameFilter("Person"), personsDb));
		commands.add(new FilterPersonsCommand(new PeselFilter("96062513425"), personsDb));
		MacroCommand macroCommand = new MacroCommand(commands);
		macroCommand.execute();
		//Commands.listPersons(personsDb);
		
		commands = new ArrayList<>();
		commands.add(new DeletePersonCommand(personsDb));
		commands.add(new DeletePersonCommand(personsDb));
		macroCommand = new MacroCommand(commands);
		macroCommand.execute();
		macroCommand.undo();
		
		ObjectWriter objectWriter = new ObjectWriter();
		XMLWriter xmlWriter = new XMLWriter();
		JsonWriter jsonWriter = new JsonWriter();
		ObjectReader objectReader = new ObjectReader();
		XMLReader xmlReader = new XMLReader();
		JsonReader jsonReader = new JsonReader();
		
		FileOutputStream fos = new FileOutputStream("data.txt");
		personsDb.saveToFile(fos, jsonWriter);
		
		FileInputStream fis = new FileInputStream("data.txt");
		PersonsDB personDbTest = new PersonsDB();
		personDbTest.loadFromFile(fis, jsonReader);
		Commands.listPersons(personDbTest);
	}
}
