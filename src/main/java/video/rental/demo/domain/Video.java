package video.rental.demo.domain;

import video.rental.demo.util.Utils;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "VIDEO", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public abstract class Video {
	@Id
	private String title;
	private Rating videoRating;
	private int priceCode;

	public static final int REGULAR = 1;
	public static final int NEW_RELEASE = 2;
	public static final int CHILDREN = 3;

	public static final int VHS = 1;
	public static final int CD = 2;
	public static final int DVD = 3;

	private LocalDate registeredDate;
	private boolean rented;

	public static Video createVideo(int videoType, String title, int priceCode, Rating videoRating, LocalDate registeredDate) {
		switch (videoType) {
			case Video.CD:
				return new VideoCD(title, priceCode, videoRating, registeredDate);
			case Video.DVD:
				return new VideoDVD(title, priceCode, videoRating, registeredDate);
			case Video.VHS:
				return new VideoVHS(title, priceCode, videoRating, registeredDate);
			default:
				return null;
		}
	}

	public Video() {
	} // for hibernate

	Video(String title, int priceCode, Rating videoRating, LocalDate registeredDate) {
		this.title = title;
		this.priceCode = priceCode;
		this.videoRating = videoRating;
		this.registeredDate = registeredDate;
		this.rented = false;
	}


	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int priceCode) {
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return title;
	}

	public Rating getVideoRating() {
		return videoRating;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public LocalDate getRegisteredDate() {
		return registeredDate;
	}

	public boolean isUnderAge(int age) {
		switch (videoRating) {
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

	// abstract
	public abstract int getLateReturnPointPenalty();
	abstract public int getVideoType();

	abstract public int getDaysRentedLimit();




}
