package lab.filters;

import lab.Person;

import java.util.ArrayList;

/**
 * Created by Monika_ on 2016-11-10.
 */
public class OrFilter extends Filter {
	ArrayList<Filter> filters;
	
	public OrFilter(ArrayList<Filter> filters) {
		this.filters = filters;
	}
	
	@Override
	protected boolean meetsCondition(Person p) {
		boolean result = false;
		for (Filter filter : filters) {
			result = result || filter.meetsCondition(p);
		}
		return result;
	}
}

