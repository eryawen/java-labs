package lab.filters;

import lab.Person;

import java.util.ArrayList;

/**
 * Created by Monika_ on 2016-11-10.
 */
public class AndFilter extends Filter {
	ArrayList<Filter> filters;
	
	public AndFilter(ArrayList<Filter> filters) {
		this.filters = filters;
	}
	
	@Override
	protected boolean meetsCondition(Person p) {
		boolean result = true;
		for (Filter filter : filters) {
			result = result && filter.meetsCondition(p);
		}
		return result;
	}
}
