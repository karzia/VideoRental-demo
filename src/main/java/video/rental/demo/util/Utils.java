package video.rental.demo.util;

import video.rental.demo.domain.CalendarService;

import java.time.LocalDate;
import java.util.Calendar;

public class Utils {

    public static int getAge(LocalDate dateOfBirth) {

        CalendarService calendarService = new CalendarService();
        Calendar calNow = calendarService.getCurrentDay();
        Calendar calDateOfBirth = calendarService.parseBirthDay(dateOfBirth);

        // calculate age different in years and months
        int ageYr = (calNow.get(Calendar.YEAR) - calDateOfBirth.get(Calendar.YEAR));
        int ageMo = (calNow.get(Calendar.MONTH) - calDateOfBirth.get(Calendar.MONTH));

        return (ageMo < 0) ? ageYr-1 : ageYr;
    }
}
