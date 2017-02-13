package lab.filters;

import lab.Person;

public class LastNameFilter extends Filter {
	private String lastName;
	
	public LastNameFilter(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	protected boolean meetsCondition(Person p) {
		return (lastName.equals(p.getLastName()));
	}
}



