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
	private IReport report;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(int code, String name, LocalDate dateOfBirth, IReport report) {	// for hibernate

		this.code = code;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.report = report;
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

	public String getReport() {
		return report.getReport(this);
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public boolean isUnderAge(Video video) {
		// calculate customer's age in years and months

		// parse customer date of birth
		Calendar calDateOfBirth = Calendar.getInstance();
		try {
			calDateOfBirth.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(getDateOfBirth().toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// get current date
		Calendar calNow = Calendar.getInstance();
		calNow.setTime(new java.util.Date());

		// calculate age different in years and months
		int ageYr = (calNow.get(Calendar.YEAR) - calDateOfBirth.get(Calendar.YEAR));
		int ageMo = (calNow.get(Calendar.MONTH) - calDateOfBirth.get(Calendar.MONTH));

		// decrement age in years if month difference is negative
		if (ageMo < 0) {
			ageYr--;
		}
		int age = ageYr;

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
