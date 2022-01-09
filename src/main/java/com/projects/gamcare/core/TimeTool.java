package com.projects.gamcare.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.*;
import static java.util.Calendar.DATE;

public class TimeTool {
    public static String dateOfBirthDisplay(Date date) {
        return new SimpleDateFormat("dd MMMM yyyy").format(date) +
            " (" + yearsSince(date) + ")";
    }

    public static int yearsSince(Date sinceDate) {
        Calendar timeAgo = getCalendar(sinceDate);
        Calendar rightNow = getCalendar(new Date());
        int yearsDifference = rightNow.get(YEAR) - timeAgo.get(YEAR);

        if (yearsDifferenceShouldBeAYearLess(timeAgo, rightNow)) {
            yearsDifference--;
        }

        return yearsDifference;
    }

    private static boolean yearsDifferenceShouldBeAYearLess(Calendar timeAgo, Calendar rightNow) {
        return timeAgo.get(MONTH) > rightNow.get(MONTH) ||
            (timeAgo.get(MONTH) == rightNow.get(MONTH) && timeAgo.get(DATE) > rightNow.get(DATE));
    }

    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
