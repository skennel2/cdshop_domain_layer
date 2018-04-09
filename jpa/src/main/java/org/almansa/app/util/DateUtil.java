package org.almansa.app.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class DateUtil {
    /**
     * Based on GregorianCalendar Month Start With 1 Not 0
     * 
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static Date toDate(int year, int month, int date) {
        Calendar cal = new GregorianCalendar();
        cal.set(year, month + 1, date);

        return cal.getTime();
    }
}