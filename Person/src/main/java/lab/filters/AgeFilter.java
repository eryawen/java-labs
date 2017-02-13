package lab.filters;

import lab.Person;

/**
 * Created by Monika_ on 2016-11-01.
 */
public class AgeFilter extends Filter {
	private int age;
	
	public AgeFilter(int age) {
		this.age = age;
	}
	
	@Override
	protected boolean meetsCondition(Person p) {
		return (age == p.getAge());
	}
}
