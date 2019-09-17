package com.silv.api.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
