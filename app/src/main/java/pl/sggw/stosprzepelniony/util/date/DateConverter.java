package pl.sggw.stosprzepelniony.util.date;

import android.text.format.DateUtils;

import java.util.Date;

public class DateConverter {

    private static final String ZERO_MIN = "0 minutes ago";
    private static final String JUST_NOW = "just now";

    public static String getFormattedDate(Date date) {

        String formattedDate = DateUtils.getRelativeTimeSpanString(
                date.getTime(),
                System.currentTimeMillis(),
                DateUtils.MINUTE_IN_MILLIS).toString();

        return formattedDate.equals(ZERO_MIN) ? JUST_NOW : formattedDate;
    }
}
