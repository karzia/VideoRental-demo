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
}
