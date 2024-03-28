package se.iths.webshop.util;

import java.time.LocalDateTime;

/**
 * @author Depinder Kaur
 * @version 0.1
 * <h2>DateTimeFormatter</h2>
 * @date 2024-03-28
 */
public class DateTimeFormatter {

    public static String getDateFromLocalDateTime(LocalDateTime dateTime) {
        return dateTime.getYear() + "-" + dateTime.getMonthValue() + "-" + dateTime.getDayOfMonth();
    }

    public static String getTimeFromLocalDateTime(LocalDateTime dateTime) {
        return dateTime.getHour() + ":" + dateTime.getMinute() + ":" + dateTime.getSecond();    }
}
