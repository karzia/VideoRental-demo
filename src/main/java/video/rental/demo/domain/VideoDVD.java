package video.rental.demo.domain;

import java.time.LocalDate;

public class VideoDVD extends Video{

    VideoDVD(String title, int priceCode, Rating videoRating, LocalDate registeredDate) {
        super(title, priceCode, videoRating, registeredDate);
    }
    @Override
    public int getLateReturnPointPenalty() {
        return 3;
    }

    @Override
    public int getVideoType() {
        return Video.DVD;
    }
    @Override
    public int getDaysRentedLimit() {
        return 2;
    }
}
