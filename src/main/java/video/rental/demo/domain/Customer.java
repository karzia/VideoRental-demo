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
//	List<Rental> rentals = new ArrayList<Rental>();

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
		return RentalManager.getRentals(this);
	}

	public void setRentals(List<Rental> rentals) {
		RentalManager.setRentals(this, rentals);
	}

	public void addRental(Rental rental) {
		getRentals().add(rental);
	}




}
