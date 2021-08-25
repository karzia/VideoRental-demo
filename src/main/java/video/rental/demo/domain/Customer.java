package video.rental.demo.domain;

import java.time.LocalDate;
import java.util.ArrayList;
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

	public boolean rentFor(Video video) {
		int age = CalendarService.getAge(dateOfBirth);
		if (!video.isUnderAge(age)) {
			video.setRented(true);
			rentals.add(new Rental(video));
			return true;
		} else {
			return false;
		}
	}
}
