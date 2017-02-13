package lab;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	@JsonIgnore
	private Fullname fullnameData;
	private String fullname;
	private Pesel pesel;
	private String phone;
	private int age;
	
	public Person(String fullname, String pesel, String phone) {
		this.fullnameData = new Fullname(fullname);
		this.fullname = fullnameData.getFullName();
		this.pesel = new Pesel(pesel);
		this.age = this.pesel.getAgeFromPesel();
		this.phone = phone;
	}
	
	public Person() {
	}
	
	@JsonIgnore
	public String getFirstName() {
		return fullnameData.getFirstName();
	}
	
	@JsonIgnore
	public String getSecondName() {
		return fullnameData.getSecondName();
	}
	
	@JsonIgnore
	public String getLastName() {
		return fullnameData.getLastName();
	}
	
	public boolean hasSecondName() {
		return fullnameData.hasSecondName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getPesel() {
		return pesel.getPesel();
	}
	
	public int getAge() {
		return age;
	}
	
	@Override
	public String toString() {
		return String.format("Person [fullname:%s, pesel: %s, phone: %s]", fullname, pesel, phone);
	}
	
	public String getFullname() {
		return fullname;
	}
	
	private static class Fullname {
		
		private String fullname;
		private String firstName;
		private String secondName;
		private String lastName;
		private boolean hasSecondName;
		
		public Fullname(String fullname) {
			this.fullname = fullname;
			String[] names = fullname.split(" ");
			firstName = names[0];
			if (names.length == 2) {
				lastName = names[1];
				hasSecondName = false;
				secondName = "";
			} else if (names.length == 3) {
				secondName = names[1];
				lastName = names[2];
				hasSecondName = true;
			} else {
				throw new IllegalArgumentException("Bad name format");
			}
		}
		
		public String getSecondName() {
			return secondName;
		}
		
		public String getFirstName() {
			return firstName;
		}
		
		public boolean hasSecondName() {
			return hasSecondName;
		}
		
		public String getLastName() {
			return lastName;
		}
		
		public String getFullName() {
			return fullname;
		}
		
		@Override
		public String toString() {
			return fullname;
		}
	}
	
	private static class Pesel {
		private static final Pattern PESEL_PATTERN = Pattern.compile(
			   "(?<YEAR>[0-9]{2})(?<MONTH>[0-9]{2})(?<DAY>[0-9]{2})([0-9]{4})(?<CONTROLSUM>[0-9])");
		private String pesel;
		
		public Pesel(String pesel) {
			this.pesel = pesel;
			checkIfPeselIsCorrect();
		}
		
		@Override
		public String toString() {
			return pesel;
		}
		
		public int getAgeFromPesel() {
			int year = Integer.parseInt(getFieldFromPesel("YEAR"));
			int month = Integer.parseInt(getFieldFromPesel("MONTH"));
			int day = Integer.parseInt(getFieldFromPesel("DAY"));
			
			if (month > 20) {
				month = month - 20;
				year = year + 2000;
			} else {
				year = year + 1900;
			}
			
			LocalDate currentDate = LocalDate.now();
			LocalDate dateofBirth = LocalDate.of(year, month, day);
			
			Period p = Period.between(dateofBirth, currentDate);
			return p.getYears();
		}
		
		public void checkIfPeselIsCorrect() {
			Matcher matcher = PESEL_PATTERN.matcher(pesel);
			if (matcher.find() == false) {
				throw new BadPeselException(String.format(pesel));
			}
		}
		
		private String getFieldFromPesel(String fieldName) {
			Matcher matcher = PESEL_PATTERN.matcher(pesel);
			matcher.find();
			return matcher.group(fieldName);
		}
		
		public String getPesel() {
			return pesel;
		}
	}
}