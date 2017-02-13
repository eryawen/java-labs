package lab.filters;

import lab.Person;

/**
 * Created by Monika_ on 2016-10-21.
 */
public class PeselFilter extends Filter {
	private String pesel;
	
	public PeselFilter(String pesel) {
		this.pesel = pesel;
	}
	
	@Override
	protected boolean meetsCondition(Person p) {
		return (p.getPesel().equals(pesel));
	}
}
