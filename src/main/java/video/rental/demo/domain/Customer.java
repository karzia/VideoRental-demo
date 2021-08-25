package video.rental.demo.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	private int code;
	private String name;
	private LocalDate dateOfBirth;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(int code, String name, LocalDate dateOfBirth) {	// for hibernate

		this.code = code;
		this.name = name;
		this.dateOfBirth = dateOfBirth;

	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}


	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public boolean isUnderAge(Video video) {
		// calculate customer's age in years and months
		CalendarService calService = new CalendarService();
		int age = getAge(dateOfBirth);

		// determine if customer is under legal age for rating
		switch (video.getVideoRating()) {
		case TWELVE:
			return age < 12;
		case FIFTEEN:
			return age < 15;
		case EIGHTEEN:
			return age < 18;
		default:
			return false;
		}
	}

	private int getAge(LocalDate dateOfBirth) {

		CalendarService calendarService = new CalendarService();
		Calendar calNow = calendarService.getCurrentDay();
		Calendar calDateOfBirth = calendarService.parseBirthDay(getDateOfBirth());

		// calculate age different in years and months
		int ageYr = (calNow.get(Calendar.YEAR) - calDateOfBirth.get(Calendar.YEAR));
		int ageMo = (calNow.get(Calendar.MONTH) - calDateOfBirth.get(Calendar.MONTH));

		return (ageMo < 0) ? ageYr-1 : ageYr;
	}

	public boolean rentFor(Video video) {
		if (!isUnderAge(video)) {
			video.setRented(true);
			addRental(new Rental(video));
			return true;
		} else {
			return false;
		}
	}
}
