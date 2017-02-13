package lab.commands;

import java.util.ArrayList;
import java.util.ListIterator;

public class MacroCommand implements Command {
	ArrayList<Command> commands = new ArrayList<>();
	
	public MacroCommand(ArrayList<Command> commands) {
		this.commands = commands;
	}
	
	@Override
	public void execute() {
		for (Command c : commands) {
			c.execute();
		}
		Commands.usedCommands.add(this);
	}
	
	@Override
	public void undo() {
		ListIterator<Command> listIter = commands.listIterator(commands.size());
		while (listIter.hasPrevious()) {
			Command prev = listIter.previous();
			prev.undo();
		}
		Commands.usedCommands.remove(this);
	}
}
