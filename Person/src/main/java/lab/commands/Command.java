package lab.commands;

/**
 * Created by Monika_ on 2016-10-24.
 */
public interface Command {
	void execute();
	
	void undo();
}
