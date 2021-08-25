package video.rental.demo.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class CalendarService {

    public Calendar getCurrentDay() {
        // get current date
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(new java.util.Date());
        return calNow;
    }

    public Calendar parseBirthDay(LocalDate dateOfBirth) {

        // parse customer date of birth
        Calendar calDateOfBirth = Calendar.getInstance();
        try {
            calDateOfBirth.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth.toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calDateOfBirth;
    }
}
