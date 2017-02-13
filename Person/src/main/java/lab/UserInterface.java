package lab;

import lab.commands.Commands;

import java.util.Scanner;

public class UserInterface {
	
	public static void showOptions(PersonsDB personsDB) {
		System.out.println("Add:1 \n Delete:2 \n List: 3 \n Quit:4\n Undo:5\n");
		Scanner scanner = new Scanner(System.in);
		int option = scanner.nextInt();
		if (option == 1) {
			Commands.addPerson(personsDB);
		}
		if (option == 2) {
			Commands.deletePerson(personsDB);
		}
		if (option == 3) {
			Commands.listPersons(personsDB);
		}
		if (option == 4) {
			scanner.close();
			return;
		}
		if (option == 5) {
			Commands.undo();
		}
		showOptions(personsDB);
	}
}
