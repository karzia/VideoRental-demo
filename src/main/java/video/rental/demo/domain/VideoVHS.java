package video.rental.demo.domain;

import java.time.LocalDate;

public class VideoVHS extends Video{

    VideoVHS(String title, int priceCode, Rating videoRating, LocalDate registeredDate) {
        super(title, priceCode, videoRating, registeredDate);
    }

    @Override
    public int getLateReturnPointPenalty() {
        return 1;
    }

    @Override
    public int getVideoType() {
        return Video.VHS;
    }

    @Override
    public int getDaysRentedLimit() {
        return 5;
    }
}
