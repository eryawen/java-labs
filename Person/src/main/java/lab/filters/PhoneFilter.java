package lab.filters;

import lab.Person;

/**
 * Created by Monika_ on 2016-10-21.
 */
public class PhoneFilter extends Filter {
	String phone;
	
	public PhoneFilter(String phone) {
		this.phone = phone;
	}
	
	@Override
	protected boolean meetsCondition(Person p) {
		return (p.equals(phone));
	}
}
