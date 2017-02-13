package lab.filters;

import lab.Person;

public class FirstNameFilter extends Filter {
	private String firstName;
	
	public FirstNameFilter(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	protected boolean meetsCondition(Person p) {
		
		return (firstName.equals(p.getSecondName()));
	}
}
