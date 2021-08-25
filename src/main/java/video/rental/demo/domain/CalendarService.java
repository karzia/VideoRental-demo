package video.rental.demo.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class CalendarService {

    public static int getAge(LocalDate dateOfBirth) {

        Calendar calNow = CalendarService.getCurrentDay();
        Calendar calDateOfBirth = CalendarService.parseBirthDay(dateOfBirth);

        // calculate age different in years and months
        int ageYr = (calNow.get(Calendar.YEAR) - calDateOfBirth.get(Calendar.YEAR));
        int ageMo = (calNow.get(Calendar.MONTH) - calDateOfBirth.get(Calendar.MONTH));

        return (ageMo < 0) ? ageYr-1 : ageYr;
    }

    private static Calendar getCurrentDay() {
        // get current date
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(new java.util.Date());
        return calNow;
    }

    private static Calendar parseBirthDay(LocalDate dateOfBirth) {

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
