package lab.filters;

import lab.Person;

public class SecondNameFilter extends Filter {
	private String secondName;
	
	public SecondNameFilter(String secondName) {
		this.secondName = secondName;
	}
	
	@Override
	protected boolean meetsCondition(Person p) {
		
		return (secondName.equals(p.getSecondName()));
	}
}
