package video.rental.demo.domain;

import java.time.LocalDate;

public class VideoCD extends Video {

    VideoCD(String title, int priceCode, Rating videoRating, LocalDate registeredDate) {
        super(title, priceCode, videoRating, registeredDate);
    }

    @Override
    public int getLateReturnPointPenalty() {
        return 2;
    }

    @Override
    public int getVideoType() {
        return Video.CD;
    }
    @Override
    public int getDaysRentedLimit() {
        return 3;
    }
}
